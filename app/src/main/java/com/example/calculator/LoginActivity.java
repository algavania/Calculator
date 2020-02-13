package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText txt_userlogin, txt_passwordlogin;
    Button btn_continue;
    TextView tv_user, tv_userlogin, tv_passwordlogin;

    //Single login variables
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Declares variables
        txt_userlogin = findViewById(R.id.txt_user_login);
        txt_passwordlogin = findViewById(R.id.txt_password_login);
        btn_continue = findViewById(R.id.btn_continue);
        tv_user = findViewById(R.id.tv_user);
        tv_userlogin = findViewById(R.id.tv_user_login);
        tv_passwordlogin = findViewById(R.id.tv_password_login);

        //Single login
        pref = getSharedPreferences("login", MODE_PRIVATE);

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_userlogin.getText().toString().equals("Alga") && txt_passwordlogin.getText().toString().equals("123")){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("username", txt_userlogin.getText().toString());
                    editor = pref.edit();
                    editor.putString("username", txt_userlogin.getText().toString());
                    editor.apply();
                    finish();
                    startActivity(intent);
                }else{
                    txt_userlogin.setError("Invalid Username");
                    txt_passwordlogin.setError("Invalid Password");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Do you really want to exit?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then they're  allowed to exit from application
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}