package forsaj.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

public class PhotoptsDelivery extends AppCompatActivity {
    private ImageView image_car_del;
    private AppCompatButton button_dobav_phot, load_car_del;
    private StorageReference storageDelivery;
    private StorageTask uploadTask;
    private Uri imageUri;
    private FirebaseAuth mAuth;
    private String myUri = "";
    private DatabaseReference delImRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photopts_delivery);
        storageDelivery = FirebaseStorage.getInstance().getReference().child("delivcarinfo");
        mAuth = FirebaseAuth.getInstance();
        load_car_del = (AppCompatButton) findViewById(R.id.load_car_del);
        button_dobav_phot = (AppCompatButton) findViewById(R.id.button_dobav_phot);
        delImRef = FirebaseDatabase.getInstance().getReference().child("delivery");




        load_car_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfilImage();
            }
        });



        button_dobav_phot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1, 1).start(PhotoptsDelivery.this);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();


        } else {
            Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
        }


    }

    private void uploadProfilImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Идет загрузка");
        progressDialog.setMessage("Пожалуйста подождите");
        progressDialog.show();
        if (imageUri != null) {
            final StorageReference fileRef = storageDelivery.child(mAuth.getCurrentUser().getUid() + "jpg");
            uploadTask = fileRef.putFile(imageUri);
            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        myUri = downloadUri.toString();
                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("uid", mAuth.getCurrentUser().getUid());

                        userMap.put("image", myUri);

                        delImRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();

                        startActivity(new Intent(PhotoptsDelivery.this,PTSActivity.class));
                    }
                }
            });
        }


    }
}