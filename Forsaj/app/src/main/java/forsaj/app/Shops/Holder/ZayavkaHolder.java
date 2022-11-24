package forsaj.app.Shops.Holder;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import forsaj.app.R;
import forsaj.app.Shops.ItemClickListener.ItemClickLIstnerr;
import forsaj.app.Shops.OtvetShopsClientActivity;

public class ZayavkaHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

public ItemClickLIstnerr lIstnerr;
public TextView text_city_shop,text_adress_shop,text_cardate_shop,text_carmarka_shop,text_carmodel_shop,text_dvigatelmodel_shop,text_cusovmodel_shop,text_zapchast_shop;
public AppCompatButton button_otvet_shop;

public ZayavkaHolder(View itemView){
    super(itemView);
text_adress_shop=itemView.findViewById(R.id.text_adress_shop);
text_city_shop=itemView.findViewById(R.id.text_city_shop);
text_cardate_shop=itemView.findViewById(R.id.text_cardate_shop);
text_carmarka_shop=itemView.findViewById(R.id.text_carmarka_shop);
text_carmodel_shop=itemView.findViewById(R.id.text_carmodel_shop);
text_dvigatelmodel_shop=itemView.findViewById(R.id.text_dvigatelmodel_shop);
text_cusovmodel_shop=itemView.findViewById(R.id.text_cusovmodel_shop);
text_zapchast_shop=itemView.findViewById(R.id.text_zapchast_shop);
button_otvet_shop=itemView.findViewById(R.id.button_otvet_shop);

button_otvet_shop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ///Отправка///
        Intent intent=new Intent(view.getContext(),OtvetShopsClientActivity.class);
        intent.putExtra("uid",text_carmodel_shop.getHint().toString());
       view.getContext().startActivity(intent);
    }
});
}
    public void setItemClickListner(ItemClickLIstnerr listner){this.lIstnerr=listner;}

    @Override
    public void onClick(View view){
        lIstnerr.onClick(view,getAdapterPosition(),false);
    }
}
