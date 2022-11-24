package forsaj.app.Shops;

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

public class ShopName extends AppCompatActivity {
private EditText shop_edit,shop_number;
private AppCompatButton button_shop_create;
private DatabaseReference shopRef;
private FirebaseAuth mAuth;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_name);
        button_shop_create=(AppCompatButton) findViewById(R.id.button_shop_create);
        shop_edit=(EditText) findViewById(R.id.shop_edit);
        shop_number=(EditText) findViewById(R.id.shop_number);
        shopRef= FirebaseDatabase.getInstance().getReference().child("shop");
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        button_shop_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 validateShopInfo();
            }
        });
    }

    private void validateShopInfo() {






        if (TextUtils.isEmpty(shop_edit.getText().toString())) {
            Toast.makeText(this, "Введите название ", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(shop_number.getText().toString())){
            Toast.makeText(this, "Введите номер", Toast.LENGTH_SHORT).show();
        }
        else {
            HashMap<String, Object> userMap = new HashMap<>();
            userMap.put("ShopInfouid", mAuth.getCurrentUser().getUid());
            userMap.put("client_shop",shop_edit.getText().toString());
            userMap.put("numberphone_shop" ,shop_number.getText().toString());

          shopRef.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);

            startActivity(new Intent(ShopName.this, ClientMagazineZayAActivity.class));

        }






    }
}