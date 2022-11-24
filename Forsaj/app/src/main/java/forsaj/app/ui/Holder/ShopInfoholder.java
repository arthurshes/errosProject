package forsaj.app.ui.Holder;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import forsaj.app.R;
import forsaj.app.ui.ItemClickListener.ItemShopClickLIstner;
import forsaj.app.ui.OplataActivity;

public class ShopInfoholder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ItemShopClickLIstner lIstner;
    public TextView text_shop_info,text_shop_requesut,text_shop_phone;
    public ImageView image_call_shop;
    public CircleImageView image_shop_info;



    public ShopInfoholder(View itemView){
        super(itemView);
  text_shop_info=itemView.findViewById(R.id.text_shop_info);
  text_shop_phone=itemView.findViewById(R.id.text_shop_phone);
  text_shop_requesut=itemView.findViewById(R.id.text_shop_requesut);
  image_call_shop=itemView.findViewById(R.id.image_call_shop);
  image_shop_info=itemView.findViewById(R.id.image_shop_info);

image_call_shop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent oph=new Intent(Intent.ACTION_DIAL);
        oph.setData(Uri.parse("tel:"+text_shop_phone.getHint().toString()));
        view.getContext().startActivity(oph);
    }
});
    }
    public void setItemShopClickListner(ItemShopClickLIstner listner){this.lIstner=listner;}


    @Override
    public void onClick(View view){
        lIstner.onClick(view,getAdapterPosition(),false);
    }
}

