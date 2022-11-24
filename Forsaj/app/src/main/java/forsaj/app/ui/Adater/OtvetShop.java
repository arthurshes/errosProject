package forsaj.app.ui.Adater;

public class OtvetShop {
    private String numberphone_shop,numberphone, tovarname,tovarprice,magazadress,productId,date,time,image,uidClient,UidShop;
   public OtvetShop(){

    }

    public OtvetShop(String numberphone_shop,String numberphone,String UidShop,String uidClient,String tovarname, String tovarprice, String magazadress, String productId, String date, String time, String image) {
        this.tovarname = tovarname;
        this.uidClient=uidClient;
        this.tovarprice = tovarprice;
        this.magazadress = magazadress;
        this.productId = productId;
        this.date = date;
        this.time = time;
        this.image = image;
        this.UidShop=UidShop;
        this.numberphone=numberphone;
        this.numberphone_shop=numberphone_shop;
    }

    public String getNumberphone_shop() {
        return numberphone_shop;
    }

    public void setNumberphone_shop(String numberphone_shop) {
        this.numberphone_shop = numberphone_shop;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    public String getUidShop() {
        return UidShop;
    }

    public void setUidShop(String uidShop) {
        UidShop = uidShop;
    }

    public String getUidClient() {
        return uidClient;
    }

    public void setUidClient(String uidClient) {
        this.uidClient = uidClient;
    }

    public String getTovarname() {
        return tovarname;
    }

    public void setTovarname(String tovarname) {
        this.tovarname = tovarname;
    }

    public String getTovarprice() {
        return tovarprice;
    }

    public void setTovarprice(String tovarprice) {
        this.tovarprice = tovarprice;
    }

    public String getMagazadress() {
        return magazadress;
    }

    public void setMagazadress(String magazadress) {
        this.magazadress = magazadress;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
