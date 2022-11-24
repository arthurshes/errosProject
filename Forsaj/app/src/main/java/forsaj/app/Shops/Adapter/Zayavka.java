package forsaj.app.Shops.Adapter;

public class Zayavka {
    private String uid,date,time,carmarka,cardate, dvigatel,cusovcar,zapchastCar,productId,city,adress,modelcar;

    Zayavka(){

    }



    public Zayavka(String uid, String modelcar                   , String date, String time, String carmarka, String cardate, String dvigatel, String cusovcar, String zapchastCar, String productId, String city, String adress) {
        this.uid = uid;
       this.modelcar=modelcar;
        this.date = date;
        this.time = time;
        this.carmarka = carmarka;
        this.cardate = cardate;
        this.dvigatel = dvigatel;
        this.cusovcar = cusovcar;
        this.zapchastCar = zapchastCar;
        this.productId = productId;
        this.city = city;
        this.adress = adress;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getModelcar() {
        return modelcar;
    }

    public void setModelcar(String modelcar) {
        this.modelcar = modelcar;
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

    public String getCarmarka() {
        return carmarka;
    }

    public void setCarmarka(String carmarka) {
        this.carmarka = carmarka;
    }

    public String getCardate() {
        return cardate;
    }

    public void setCardate(String cardate) {
        this.cardate = cardate;
    }

    public String getDvigatel() {
        return dvigatel;
    }

    public void setDvigatel(String dvigatel) {
        this.dvigatel = dvigatel;
    }

    public String getCusovcar() {
        return cusovcar;
    }

    public void setCusovcar(String cusovcar) {
        this.cusovcar = cusovcar;
    }

    public String getZapchastCar() {
        return zapchastCar;
    }

    public void setZapchastCar(String zapchastCar) {
        this.zapchastCar = zapchastCar;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
