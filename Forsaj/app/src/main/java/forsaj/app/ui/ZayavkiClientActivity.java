package forsaj.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import forsaj.app.R;
import forsaj.app.Shops.Adapter.Zayavka;
import forsaj.app.Shops.Holder.ZayavkaHolder;
import forsaj.app.ui.Adater.OtvetShop;
import forsaj.app.ui.Holder.OtvetShopHolder;

public class ZayavkiClientActivity extends AppCompatActivity {
private DatabaseReference ZayavkaRef;

private RecyclerView recyclerView;
private FirebaseAuth mAuth;
private TextView text_text;
private ImageView pusto;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference ShopInfoRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zayavki_client);
        ZayavkaRef= FirebaseDatabase.getInstance().getReference().child("zayavli");


        recyclerView=(RecyclerView) findViewById(R.id.recycle_client_priem);
pusto=(ImageView) findViewById(R.id.pusto);
text_text=(TextView) findViewById(R.id.text_text);
        layoutManager=new LinearLayoutManager(this);
mAuth=FirebaseAuth.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


    }

    @Override
    protected void onStart() {
        super.onStart();



            FirebaseRecyclerOptions<OtvetShop> options = new FirebaseRecyclerOptions.Builder<OtvetShop>()

                    .setQuery( ZayavkaRef.orderByChild("uidClient").equalTo(mAuth.getCurrentUser().getUid()), OtvetShop.class).build();

            FirebaseRecyclerAdapter<OtvetShop, OtvetShopHolder> adapter = new FirebaseRecyclerAdapter<OtvetShop, OtvetShopHolder>(options) {
                @Override
                protected void onBindViewHolder(@androidx.annotation.NonNull OtvetShopHolder holder, int position, @androidx.annotation.NonNull OtvetShop model) {


                    Log.d("Arthur", model.getUidClient() + "model");


                    holder.text_tovar_price.setHint(model.getUidShop());
                    holder.text_tovar_name.setText(model.getTovarname());
                    holder.text_tovar_price.setText(model.getTovarprice());
                    holder.text_shop_adress.setText(model.getMagazadress());
                    Picasso.get().load(model.getImage()).into(holder.image_tovar_shop);

                }
                @Override
                public OtvetShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_shop, parent, false);
                    OtvetShopHolder holder = new OtvetShopHolder(view);


                    return holder;
                }
            };
            recyclerView.setAdapter(adapter);
            adapter.startListening();


        }

    }
