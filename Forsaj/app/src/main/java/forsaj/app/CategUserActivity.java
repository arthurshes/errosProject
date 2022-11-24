package forsaj.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import forsaj.app.Masterskaya.MasterName;
import forsaj.app.Shops.ClientMagazineZayAActivity;
import forsaj.app.Shops.OtvetShopsClientActivity;
import forsaj.app.Shops.ShopName;
import forsaj.app.ui.ClientName;

public class CategUserActivity extends AppCompatActivity {
private AppCompatButton button_delivery,button_shop,button_service,button_client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ_user);
        button_delivery=(AppCompatButton) findViewById(R.id.button_delivery);
button_service=(AppCompatButton) findViewById(R.id.button_service);

button_shop=(AppCompatButton) findViewById(R.id.button_shop);
button_client=(AppCompatButton) findViewById(R.id.button_client);

        button_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent der=new Intent(CategUserActivity.this,DeliveryName.class);
                startActivity(der);
            }
        });
        button_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serv=new Intent(CategUserActivity.this, MasterName.class);
                startActivity(serv);
            }
        });
        button_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sshop=new Intent(CategUserActivity.this, ShopName.class);
                startActivity(sshop);
            }
        });
        button_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent client=new Intent(CategUserActivity.this, ClientName.class);
                startActivity(client);
            }
        });



    }
}