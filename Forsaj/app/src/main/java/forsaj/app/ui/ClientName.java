package forsaj.app.ui;

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

public class ClientName extends AppCompatActivity {
private EditText client_number,client_edit;
private AppCompatButton button_create_clieant;
private FirebaseAuth mAuth;
private DatabaseReference clientRef;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_name);

        button_create_clieant=(AppCompatButton)findViewById(R.id.button_create_clieant);
        client_edit=(EditText) findViewById(R.id.client_edit);
        client_number=(EditText) findViewById(R.id.client_number);
        progressDialog=new ProgressDialog(this);
        clientRef= FirebaseDatabase.getInstance().getReference().child("client");

        mAuth=FirebaseAuth.getInstance();



        button_create_clieant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateClientInfo();
            }
        });
    }

    private void validateClientInfo() {
        if (TextUtils.isEmpty(client_edit.getText().toString())) {
            Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(client_number.getText().toString())){
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("uid", mAuth.getCurrentUser().getUid());
            userMap.put("client_name", client_edit.getText().toString());
            userMap.put("numberphone_client",client_number.getText().toString());

         clientRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            startActivity(new Intent(ClientName.this, HomeActivity.class));
        }
    }

}

