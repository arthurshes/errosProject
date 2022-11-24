package forsaj.app.Shops.Adapter;

import com.google.firebase.database.DatabaseReference;

public class ShopUser {
    private String client_shop,numberphone_shop,ShopInfouid;

    public ShopUser(){

    }

    public ShopUser(String client_shop, String numberphone_shop, String shopInfouid) {
        this.client_shop = client_shop;
        this.numberphone_shop = numberphone_shop;
    this.ShopInfouid=shopInfouid;
    }

    public String getClient_shop() {
        return client_shop;
    }

    public void setClient_shop(String client_shop) {
        this.client_shop = client_shop;
    }

    public String getNumberphone_shop() {
        return numberphone_shop;
    }

    public void setNumberphone_shop(String numberphone_shop) {
        this.numberphone_shop = numberphone_shop;
    }

    public String getShopInfouid() {
        return ShopInfouid;
    }

    public void setShopInfouid(String shopInfouid) {
        ShopInfouid = shopInfouid;
    }
}
