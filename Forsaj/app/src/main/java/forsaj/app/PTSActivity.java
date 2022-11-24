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

import forsaj.app.ui.HomeActivity;

public class PTSActivity extends AppCompatActivity {
    private ImageView image_pts_obrat,image_pts_one;
    private AppCompatButton button_dobav_pts,button_pts_obratnai,load_pts_del;
    private StorageReference storageDeliveryPts;
    private StorageTask uploadTask;
    private Uri imageUriPts;
    private FirebaseAuth mAuth;
    private String myUri = "";

    private DatabaseReference PtsRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptsactivity);
        image_pts_one=(ImageView) findViewById(R.id.image_pts_one);
        image_pts_obrat=(ImageView) findViewById(R.id.image_pts_obrat);
        PtsRef= FirebaseDatabase.getInstance().getReference().child("DeliverPts");
        storageDeliveryPts = FirebaseStorage.getInstance().getReference().child("delivcarPTS");
button_dobav_pts=(AppCompatButton) findViewById(R.id.button_dobav_pts);
load_pts_del=(AppCompatButton) findViewById(R.id.load_pts_del);
        mAuth = FirebaseAuth.getInstance();
        button_pts_obratnai=(AppCompatButton) findViewById(R.id.button_pts_obratnai);

        load_pts_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadImagePts();
            }
        });
        button_dobav_pts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1, 1).start(PTSActivity.this);
            }
        });
        button_pts_obratnai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity().setAspectRatio(1, 1).start(PTSActivity.this);
            }
        });

    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUriPts = result.getUri();

        } else {
            Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
        }


    }



























    private void loadImagePts() {
















        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Идет загрузка");
        progressDialog.setMessage("Пожалуйста подождите");
        progressDialog.show();
        if (imageUriPts != null) {
            final StorageReference fileRef = storageDeliveryPts.child(mAuth.getCurrentUser().getUid() + "jpg");
            uploadTask = fileRef.putFile(imageUriPts);
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

                      PtsRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();

                        startActivity(new Intent(PTSActivity.this, HomeActivity.class));
                    }
                }
            });
        }


    }




    }
