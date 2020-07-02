package pnj.pk.musikplayerpk;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import pnj.pk.musikplayerpk.DB.FavoritesDBHandler;
import pnj.pk.musikplayerpk.Model.user;

public class loginActivity extends AppCompatActivity {

    EditText editUser,editPass;
    Button actionLogin,actionSignup;
    SharedPreferences sharedPreferences;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUser = findViewById(R.id.editUser);
        editPass = findViewById(R.id.editpass);
        actionLogin = findViewById(R.id.actionLogin);
        actionSignup = findViewById(R.id.actionRegister);
        sharedPreferences = getSharedPreferences("islogin",MODE_PRIVATE);

        actionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        actionSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    void login(){
        if(!editUser.getText().toString().isEmpty()
                && !editPass.getText().toString().isEmpty()){
            ArrayList<user> datas = new ArrayList<>();

            database = new FavoritesDBHandler(this).getReadableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("user",editUser.getText().toString());
            contentValues.put("pass",editPass.getText().toString());

            Cursor cursor = database.rawQuery("SELECT * FROM user Where user=? AND pass=?",new String[]{editUser.getText().toString(),editPass.getText().toString()});

            if(cursor.moveToFirst()) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("login",true);
                editor.commit();

                Toast.makeText(this,"Login Berhasil",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                cursor.close();
                database.close();

            }else{
                Toast.makeText(this,"Username dan Password Salah",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Username dan Password tidak boleh kosong",Toast.LENGTH_SHORT).show();
        }
    }

}