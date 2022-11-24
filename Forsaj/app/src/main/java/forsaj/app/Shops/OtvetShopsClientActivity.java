package forsaj.app.Shops;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import forsaj.app.R;
import forsaj.app.ui.ShopCLientActivity;

public class OtvetShopsClientActivity extends AppCompatActivity {
private ImageView image_otvet_clientu_photo;
private EditText tovar_id_otvet,tovar_price_otvet,magazin_adress_otvet,tovar_number;
private AppCompatButton button_otvet_shop_dla_client;
private String tovarnumber, tovarname,tovarprice,magazadress,SaveCurrentDate,SaveCurrentTime,OtvetRandomKey;
private ProgressDialog progressDialog;
private FirebaseAuth mAuth;
private DatabaseReference otvetRef;
private StorageReference OtvetPhotoRef;
private String DownloadImageUrl;
private Uri ImageUri;


    private static final int GALLERYPICK=1;
MediaPlayer mediaPlayerTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otvet_shops_client);
mAuth=FirebaseAuth.getInstance();
otvetRef= FirebaseDatabase.getInstance().getReference().child("zayavli");
        button_otvet_shop_dla_client=(AppCompatButton) findViewById(R.id.button_otvet_shop_dla_client);
        image_otvet_clientu_photo=(ImageView) findViewById(R.id.image_otvet_clientu_photo);
        tovar_id_otvet=(EditText) findViewById(R.id.tovar_id_otvet);
        tovar_price_otvet=(EditText) findViewById(R.id.tovar_price_otvet);
        magazin_adress_otvet=(EditText) findViewById(R.id.magazin_adress_otvet);
        OtvetPhotoRef= FirebaseStorage.getInstance().getReference().child("tovar_photo");
mediaPlayerTwo=MediaPlayer.create(this,R.raw.zvuk);
progressDialog=new ProgressDialog(this);
tovar_number=(EditText)findViewById(R.id.tovar_number) ;



image_otvet_clientu_photo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
OpenGallery();
    }
});





button_otvet_shop_dla_client.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        OtparavitOtvet();
    }
});

    }

    private void OpenGallery() {
        Intent galleryintent=new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("image/*");
        startActivityForResult(galleryintent,GALLERYPICK);




    }

    private void OtparavitOtvet() {
        tovarname=tovar_id_otvet.getText().toString();
        tovarprice=tovar_price_otvet.getText().toString();
        magazadress=magazin_adress_otvet.getText().toString();
        tovarnumber=tovar_number.getText().toString();

        if (TextUtils.isEmpty(tovarname)){
            Toast.makeText(this, "Введите название товара", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(tovarprice)){
            Toast.makeText(this, "Введите цену товара", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(magazadress)){
            Toast.makeText(this, "Введите адрес магазина", Toast.LENGTH_SHORT).show();
        }
        else {
            loadOtvet();
        }







    }

    private void loadOtvet() {
        progressDialog.setTitle("загрузка данных....");
        progressDialog.setMessage("Пожалуйста подождите");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("ddMMyyyy");
        SaveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HHmmss");
        SaveCurrentTime = currentTime.format(calendar.getTime());


        OtvetRandomKey = SaveCurrentDate + SaveCurrentTime;
        StorageReference filePath=OtvetPhotoRef.child(ImageUri.getLastPathSegment()+OtvetRandomKey+".jpg");
        final UploadTask uploadTask=filePath.putFile(ImageUri);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                String message=e.toString();
                Toast.makeText(OtvetShopsClientActivity.this, "error"+message, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(OtvetShopsClientActivity.this, "Изображение загружено", Toast.LENGTH_SHORT).show();
                Task<Uri> uriTask=uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()){
                            throw task.getException();
                        }
                       DownloadImageUrl=filePath.getDownloadUrl().toString();

                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                 DownloadImageUrl = task.getResult().toString();




                            Toast.makeText(OtvetShopsClientActivity.this, "Фото сохранено", Toast.LENGTH_SHORT).show();


                            SaveProductInfoToDatabase();
                        }


                    }
                });
            }
        });

    }

    private void SaveProductInfoToDatabase() {


        HashMap<String, Object> OtvetMap = new HashMap<>();
        OtvetMap.put("tovarname", tovarname);
        OtvetMap.put("numberphone",tovarnumber);
        OtvetMap.put("tovarprice", tovarprice);
        OtvetMap.put("magazadress", magazadress);
OtvetMap.put( "uidClient" ,  getIntent().getExtras().get("uid").toString());
OtvetMap.put("image",DownloadImageUrl);
        OtvetMap.put("productId", OtvetRandomKey);
        OtvetMap.put("date", SaveCurrentDate);
        OtvetMap.put("time", SaveCurrentTime);
        OtvetMap.put("UidShop",mAuth.getCurrentUser().getUid());


        otvetRef.child(OtvetRandomKey).updateChildren(OtvetMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(OtvetShopsClientActivity.this, "Ответ отправлен", Toast.LENGTH_SHORT).show();
                    mediaPlayerTwo.start();

                } else {
                    progressDialog.dismiss();
                    String message = task.getException().toString();
                    Toast.makeText(OtvetShopsClientActivity.this, "Ошибка" + message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////сохранение заявки в базе данных конец////


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLERYPICK&&resultCode==RESULT_OK&&data !=null){
            ImageUri=data.getData();
          image_otvet_clientu_photo.setImageURI(ImageUri);
        }

    }

    }
