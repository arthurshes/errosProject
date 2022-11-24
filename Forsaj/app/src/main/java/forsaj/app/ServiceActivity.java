package forsaj.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;

public class ServiceActivity extends AppCompatActivity {
private ImageView phone_method;



private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        phone_method=(ImageView) findViewById(R.id.phone_method);


progressDialog=new ProgressDialog(this);

    }
}