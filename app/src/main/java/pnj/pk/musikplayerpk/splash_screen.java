package pnj.pk.musikplayerpk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash_screen extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        sharedPreferences = getSharedPreferences("islogin",MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sharedPreferences.getBoolean("login",false)){
                    Intent intent = new Intent(splash_screen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(splash_screen.this,loginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },300);
    }
}