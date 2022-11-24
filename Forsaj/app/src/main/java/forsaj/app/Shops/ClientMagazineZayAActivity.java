package forsaj.app.Shops;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import forsaj.app.R;
import forsaj.app.Shops.Adapter.Zayavka;
import forsaj.app.Shops.Holder.ZayavkaHolder;

public class ClientMagazineZayAActivity extends AppCompatActivity {
private DatabaseReference MagZakaz;
private RecyclerView recyclerView;
private AppCompatButton button_test1;
RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_magazine_zay_aactivity);
        MagZakaz= FirebaseDatabase.getInstance().getReference().child("zakaz");
        recyclerView=(RecyclerView) findViewById(R.id.recycle_zayvka_shop);
        layoutManager=new LinearLayoutManager(this);
        button_test1=(AppCompatButton) findViewById(R.id.button_test1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);















    }
////Получение заявок от клиентов начало////
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Zayavka> options=new FirebaseRecyclerOptions.Builder<Zayavka>()
                .setQuery(MagZakaz,Zayavka.class).build();
        FirebaseRecyclerAdapter<Zayavka, ZayavkaHolder> adapter=new FirebaseRecyclerAdapter<Zayavka, ZayavkaHolder>(options) {
            @Override
            protected void onBindViewHolder( @androidx.annotation.NonNull ZayavkaHolder holder, int position,  @androidx.annotation.NonNull Zayavka model) {
              holder.text_carmodel_shop.setHint(model.getUid());
                holder.text_zapchast_shop.setText(model.getZapchastCar());
                holder.text_cusovmodel_shop.setText(model.getCusovcar());
             holder.text_dvigatelmodel_shop.setText(model.getDvigatel());
             holder.text_cardate_shop.setText(model.getCardate());
             holder.text_city_shop.setText(model.getCity());
             holder.text_adress_shop.setText(model.getAdress());
             holder.text_carmodel_shop.setText(model.getModelcar());
             holder.text_carmarka_shop.setText(model.getCarmarka());
            }

            @Override
            public ZayavkaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent,false);
                ZayavkaHolder holder=new ZayavkaHolder(view);


                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }

    ////Получение заявок от клиентов конец////




}