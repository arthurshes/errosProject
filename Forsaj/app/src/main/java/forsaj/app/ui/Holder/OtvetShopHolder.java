package forsaj.app.ui.Holder;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import forsaj.app.R;
import forsaj.app.Shops.ItemClickListener.ItemClickLIstnerr;
import forsaj.app.Shops.OtvetShopsClientActivity;
import forsaj.app.ui.Adater.OtvetShop;
import forsaj.app.ui.ItemClickListener.ItemShopClickLIstner;
import forsaj.app.ui.OplataActivity;

public class OtvetShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ItemShopClickLIstner lIstner;
    public TextView text_tovar_name,text_tovar_price,text_shop_adress;
public ImageView image_tovar_shop;
public AppCompatButton button_yes_shop,button_no_shop;


    public OtvetShopHolder(View itemView){
        super(itemView);
        button_no_shop=itemView.findViewById(R.id.button_no_shop);
        button_yes_shop=itemView.findViewById(R.id.button_yes_shop);
        text_shop_adress=itemView.findViewById(R.id.text_shop_adress);
        text_tovar_name=itemView.findViewById(R.id.text_tovar_name);
        text_tovar_price=itemView.findViewById(R.id.text_tovar_price);

image_tovar_shop=itemView.findViewById(R.id.image_tovar_shop);

        button_yes_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Отправка///
                Intent intent=new Intent(view.getContext(), OplataActivity.class);
                intent.putExtra("UidShop",text_tovar_price.getHint().toString());

                view.getContext().startActivity(intent);
            }
        });

    }
    public void setItemShopClickListner(ItemShopClickLIstner listner){this.lIstner=listner;}


    @Override
    public void onClick(View view){
        lIstner.onClick(view,getAdapterPosition(),false);
    }
}
