    package com.example.calculator;

    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.DialogInterface;
    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    public class MainActivity extends AppCompatActivity implements View.OnClickListener {

        EditText txt_firstNumber;
        EditText txt_secondNumber;

        TextView tv_result, tv_username;

        Button btn_add;
        Button btn_subtract;
        Button btn_multiply;
        Button btn_divide;
        Button btn_logout;

        //Single login variables
        static SharedPreferences sharedPreferences;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            txt_firstNumber = findViewById(R.id.txt_firstNumber);
            txt_secondNumber = findViewById(R.id.txt_secondNumber);

            tv_result = findViewById(R.id.tv_result);
            tv_username = findViewById(R.id.tv_usernamecalc);

            btn_add = findViewById(R.id.btn_add);
            btn_subtract = findViewById(R.id.btn_subtract);
            btn_multiply = findViewById(R.id.btn_multiply);
            btn_divide = findViewById(R.id.btn_divide);
            btn_logout = findViewById(R.id.btn_logout);

            btn_add.setOnClickListener(this);
            btn_subtract.setOnClickListener(this);
            btn_multiply.setOnClickListener(this);
            btn_divide.setOnClickListener(this);
            btn_logout.setOnClickListener(this);

            String name;
            Bundle bd = getIntent().getExtras();
            if(bd == null){
                name = "Alga";
            }else{
                name = bd.getString("username");
            } tv_username.setText(name);

        }

        @Override
        public void onClick(View v) {
            try {
                switch (v.getId()) {
                    case R.id.btn_add:
                        String firstNumber = txt_firstNumber.getText().toString();
                        String secondNumber = txt_secondNumber.getText().toString();
                        int finalFirstNumber = Integer.parseInt(firstNumber);
                        int finalSecondNumber = Integer.parseInt(secondNumber);
                        tv_result.setText("The result is: " + (finalFirstNumber + finalSecondNumber));
                        break;
                    case R.id.btn_subtract:
                        String firstNumber1 = txt_firstNumber.getText().toString();
                        String secondNumber1 = txt_secondNumber.getText().toString();
                        int finalFirstNumber1 = Integer.parseInt(firstNumber1);
                        int finalSecondNumber1 = Integer.parseInt(secondNumber1);
                        tv_result.setText("The result is: " + (finalFirstNumber1 - finalSecondNumber1));
                        break;
                    case R.id.btn_multiply:
                        String firstNumber2 = txt_firstNumber.getText().toString();
                        String secondNumber2 = txt_secondNumber.getText().toString();
                        int finalFirstNumber2 = Integer.parseInt(firstNumber2);
                        int finalSecondNumber2 = Integer.parseInt(secondNumber2);
                        tv_result.setText("The result is: " + (finalFirstNumber2 * finalSecondNumber2));
                        break;
                    case R.id.btn_divide:
                        String firstNumber3 = txt_firstNumber.getText().toString();
                        String secondNumber3 = txt_secondNumber.getText().toString();
                        float finalFirstNumber3 = Float.parseFloat(firstNumber3);
                        float finalSecondNumber3 = Float.parseFloat(secondNumber3);
                        tv_result.setText("The result is: " + (finalFirstNumber3 / finalSecondNumber3));
                        break;
                    case R.id.btn_logout:
                        sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                        sharedPreferences.edit().clear().commit();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        finish();
                        startActivity(intent);
                }
            } catch (Exception e) {
                txt_firstNumber.setError("You must insert a number here.");
                txt_secondNumber.setError("You must insert a number here.");
            }
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
