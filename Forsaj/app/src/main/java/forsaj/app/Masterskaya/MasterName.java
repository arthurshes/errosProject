package forsaj.app.Masterskaya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import forsaj.app.R;

public class MasterName extends AppCompatActivity {
private EditText mas_edit,mas_number;
private AppCompatButton button_master;
private FirebaseAuth mAuth;
private DatabaseReference masRef;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_name);
        mas_edit=(EditText) findViewById(R.id.mas_edit);
        mas_number=(EditText) findViewById(R.id.mas_number);
        button_master=(AppCompatButton) findViewById(R.id.button_master);
        masRef= FirebaseDatabase.getInstance().getReference().child("Masterskaya");
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        button_master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateAutoMasterInfo();
            }
        });



    }

    private void ValidateAutoMasterInfo() {

        if (TextUtils.isEmpty(mas_edit.getText().toString())) {
            Toast.makeText(this, "Введите название ", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(mas_number.getText().toString())){
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("uid", mAuth.getCurrentUser().getUid());
            userMap.put("client_master",mas_edit.getText().toString());
            userMap.put("numberphone_master",mas_number.getText().toString());

           masRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            startActivity(new Intent(MasterName.this, MasterHomeActivity.class));
        }
    }








}
