package forsaj.app.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import forsaj.app.R;

public class ShopCLientActivity extends AppCompatActivity {
    MediaPlayer mediaPlayerOtpravleno;
    private EditText marka_car_edit,model_car_edit,date_car_edit,city_edit,adress_edit,cusov_edit,dvigatel_edit,zapcast_edit;
    private AppCompatButton button_load_client_and_shop;
    private DatabaseReference zakazZRef;
    private FirebaseAuth mAuth;
    private String modelcar,carmarka,city,adress,saveCurrentDate,saveCurrentTime,ProductRandomKey,cardate,cusovcar,dvigatel,zapchastCar;
private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_client);
progressDialog=new ProgressDialog(this);
button_load_client_and_shop=(AppCompatButton) findViewById(R.id.button_load_client_and_shop);
marka_car_edit=(EditText) findViewById(R.id.marka_car_edit);
model_car_edit=(EditText) findViewById(R.id.model_car_edit);
date_car_edit=(EditText) findViewById(R.id.date_car_edit);
city_edit=(EditText) findViewById(R.id.city_edit);
adress_edit=(EditText) findViewById(R.id.adress_edit);
cusov_edit=(EditText) findViewById(R.id.cusov_edit);
mAuth=FirebaseAuth.getInstance();
dvigatel_edit=(EditText) findViewById(R.id.dvigatel_edit);
        zapcast_edit=(EditText) findViewById(R.id.zapcast_edit);
zakazZRef= FirebaseDatabase.getInstance().getReference().child("zakaz");
mediaPlayerOtpravleno=MediaPlayer.create(this,R.raw.zvuk);



////кнопка отправки заявки////

button_load_client_and_shop.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ValidateNewZakaz();
    }
});
////кнопка отправки заявки конец////
    }
////отправа заявки,инициализация стрингов////
    private void ValidateNewZakaz() {
carmarka=marka_car_edit.getText().toString();
modelcar=model_car_edit.getText().toString();
city=city_edit.getText().toString();
adress=adress_edit.getText().toString();
cardate=date_car_edit.getText().toString();
cusovcar=cusov_edit.getText().toString();
        dvigatel=dvigatel_edit.getText().toString();
zapchastCar=zapcast_edit.getText().toString();
if (TextUtils.isEmpty(carmarka)){
    Toast.makeText(this, "Добавьте марку машины", Toast.LENGTH_SHORT).show();
}if (TextUtils.isEmpty(modelcar)){
            Toast.makeText(this, "Добавьте модель машины", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(cusovcar)){
            Toast.makeText(this, "Добавьте тип кузова машины", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(dvigatel)){
            Toast.makeText(this, "Добавьте инфлрмацию о двигателе машины", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(adress)){
            Toast.makeText(this, "Добавьте адрес доставки", Toast.LENGTH_SHORT).show();
        }if (TextUtils.isEmpty(city)){
            Toast.makeText(this, "Добавьте населенный пункт", Toast.LENGTH_SHORT).show();
        }if (TextUtils.isEmpty(cardate)){
            Toast.makeText(this, "Добавьте год выпуска машины", Toast.LENGTH_SHORT).show();


        }





        if (TextUtils.isEmpty(zapchastCar)){
            Toast.makeText(this, "Добавьте название запчасти", Toast.LENGTH_SHORT).show();


        }

////конец инициализации////




////запуск метода отправки данных////
        else {
            Loadzayvka();
        }

    }
////метод отправки данных начало////
    private void Loadzayvka() {










        progressDialog.setTitle("Загрузка данных");
        progressDialog.setMessage("Пожалуйста подождите");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();








////создание индивидуального id заявок с помощью времени и даты загрузки начало////

        Calendar calendar=Calendar.getInstance();

        SimpleDateFormat currentDate=new SimpleDateFormat("ddMMyyyy");
        saveCurrentDate=currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime=new SimpleDateFormat("HHmmss");
        saveCurrentTime=currentTime.format(calendar.getTime());


        ProductRandomKey=saveCurrentDate+saveCurrentTime;

        SaveProductInfoToDatabase();

////создание индивидуального id заявок с помощью времени и даты загрузки конец////
    }
////сохранение заявки в базе данных начало////
    private void SaveProductInfoToDatabase() {







        HashMap<String,Object> productMap=new HashMap<>();
        productMap.put("productId",ProductRandomKey);
        productMap.put("date",saveCurrentDate);
        productMap.put("time",saveCurrentTime);
        productMap.put("uid",mAuth.getCurrentUser().getUid());
        productMap.put("carmarka",carmarka);
        productMap.put("modelcar",modelcar);
        productMap.put("cardate",cardate);
        productMap.put("dvigatel",dvigatel);
        productMap.put("cusovcar",cusovcar);
        productMap.put("zapchastCar",zapchastCar);
        productMap.put("adress",adress);
        productMap.put("city",city);

        zakazZRef.push().updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(ShopCLientActivity.this, "Заявка отправлена", Toast.LENGTH_SHORT).show();
                    mediaPlayerOtpravleno.start();

                }else{
                    progressDialog.dismiss();
                    String message=task.getException().toString();
                    Toast.makeText(ShopCLientActivity.this, "Ошибка"+message, Toast.LENGTH_SHORT).show();
                }
            }
        });
        ////сохранение заявки в базе данных конец////
    }
////метод отправки данных конец////


    }
