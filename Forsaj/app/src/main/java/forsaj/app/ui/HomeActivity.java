package forsaj.app.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import forsaj.app.R;
import forsaj.app.ServiceActivity;

public class HomeActivity extends AppCompatActivity {
private ImageView shop_client,servie_client,image_zayvki,image_cart,imagehome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        shop_client=(ImageView) findViewById(R.id.shop_client);
        servie_client=(ImageView) findViewById(R.id.servie_client);
image_cart=(ImageView) findViewById(R.id.image_cart);
imagehome=(ImageView) findViewById(R.id.imagehome);
image_zayvki=(ImageView) findViewById(R.id.image_zayvki);


image_cart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent noImtemt=new Intent(HomeActivity.this,ClientZakazOsmotr.class);
        startActivity(noImtemt);
    }
});
image_zayvki.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent twoInt=new Intent(HomeActivity.this,ZayavkiClientActivity.class);
        startActivity(twoInt);
    }
});


        shop_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent shopInt=new Intent(HomeActivity.this,ShopCLientActivity.class);
startActivity(shopInt);
            }
        });
        servie_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent serInt=new Intent(HomeActivity.this, ServiceActivity.class);
                startActivity(serInt);
            }
        });
    }
}