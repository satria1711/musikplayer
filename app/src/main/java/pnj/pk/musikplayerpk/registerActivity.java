package pnj.pk.musikplayerpk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pnj.pk.musikplayerpk.DB.FavoritesDBHandler;

public class registerActivity extends AppCompatActivity {

    EditText editNama,editUser,editPass;
    Button actionSignup;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editNama = findViewById(R.id.editNama);
        editPass = findViewById(R.id.editPassRes);
        editUser = findViewById(R.id.editUserRes);
        actionSignup = findViewById(R.id.actionSignup);

        actionSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpan();
            }
        });

    }

    void simpan() {
        if(!editNama.getText().toString().isEmpty()
                && !editUser.getText().toString().isEmpty()
                && !editPass.getText().toString().isEmpty()) {
            database = new FavoritesDBHandler(this).getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("nama",editNama.getText().toString());
            contentValues.put("user",editUser.getText().toString());
            contentValues.put("pass",editPass.getText().toString());

            long insert = database.insert("user",null,contentValues);
            if(insert != -1){
                Intent intent = new Intent(this, loginActivity.class);
                startActivity(intent);
                database.close();
                finish();
                Toast.makeText(this,"Register Berhasil",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"Register Gagal",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Masukkan data anda",Toast.LENGTH_SHORT).show();
        }
    }
}