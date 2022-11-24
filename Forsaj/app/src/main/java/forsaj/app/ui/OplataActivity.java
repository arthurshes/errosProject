package forsaj.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import forsaj.app.R;
import forsaj.app.Shops.Adapter.ShopUser;
import forsaj.app.ui.Adater.OtvetShop;
import forsaj.app.ui.Holder.OtvetShopHolder;
import forsaj.app.ui.Holder.ShopInfoholder;

public class OplataActivity extends AppCompatActivity {
private DatabaseReference oplataRef;
private FirebaseAuth mAuth;
private String phone;
    private String DownloadImageUrlTwo;
    private Uri ImageUriTwo;
    private ProgressDialog progressDialog;
    private StorageReference CheckRef;
    private ImageView image_check;
    private DatabaseReference ShopInfoRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oplata);
        oplataRef= FirebaseDatabase.getInstance().getReference().child("oplataShop");
        mAuth=FirebaseAuth.getInstance();
        CheckRef= FirebaseStorage.getInstance().getReference().child("Check_photo");
        progressDialog=new ProgressDialog(this);
        image_check=(ImageView) findViewById(R.id.image_check);

        ShopInfoRef=FirebaseDatabase.getInstance().getReference().child("shop");


        ShopInfoRef.orderByChild("ShopInfouid").equalTo(getIntent().getExtras().get("UidShop").toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phone=snapshot.getValue(ShopUser.class).getNumberphone_shop();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}