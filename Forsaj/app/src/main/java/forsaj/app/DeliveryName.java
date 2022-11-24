package forsaj.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DeliveryName extends AppCompatActivity {
    private EditText delivery_edit,delivery_number;
    private AppCompatButton button_save_ndeliver;
    private DatabaseReference delref;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_name);
mAuth=FirebaseAuth.getInstance();
delref= FirebaseDatabase.getInstance().getReference().child("delivery");
progressDialog=new ProgressDialog(this);
        button_save_ndeliver=(AppCompatButton) findViewById(R.id.button_save_ndeliver);
        delivery_edit=(EditText) findViewById(R.id.delivery_edit);
        delivery_number=(EditText) findViewById(R.id.delivery_number);



button_save_ndeliver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
ValidateAndSaveOnlyInfo();
    }
});






    }

    private void ValidateAndSaveOnlyInfo() {
        if (TextUtils.isEmpty(delivery_edit.getText().toString())) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(delivery_number.getText().toString())){
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("uid", mAuth.getCurrentUser().getUid());
            userMap.put("name", delivery_edit.getText().toString());
userMap.put("numberphone_deliver",delivery_number.getText().toString());

           delref.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            startActivity(new Intent(DeliveryName.this, PhotoptsDelivery.class));
        }
    }

}