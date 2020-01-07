package com.example.cryptochange;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {
    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;
    EditText editTextEmail;
    EditText editTextPassword;

    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonLogin;

    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setAnimation();
//        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//        }
        changeStatusBarColor();
        setContentView(R.layout.activity_login);
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        mNetworkReceiver = new BroadCastReceiverClass();
//        registerNetworkBroadcastForNougat();
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()) {

                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

//                    if (Email.isEmpty()){
//                        Snackbar.make(buttonLogin, "Please Enter Email!", Snackbar.LENGTH_LONG).show();
//                    } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//                        Snackbar.make(buttonLogin, "Please enter valid email!", Snackbar.LENGTH_LONG).show();
//
//                    }
//                    else
                        if (!sqliteHelper.isEmailExists(Email)){
                        Snackbar.make(buttonLogin,"Not Registered!! Register Here",Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
                                    }
                                })
                                .show();


                    }  else  if (currentUser != null) {
                        if (MyApplication.getInstance().getNetworkConnected()) {
                            editTextEmail.setText("");
                            editTextPassword.setText("");
                           Intent intent =new Intent(LoginActivity.this, MainActivity.class);
//                            if(Build.VERSION.SDK_INT>20){
//                                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this);
//                                startActivity(intent,options.toBundle());
//                            }else {
                                startActivity(intent);
//                            }
                            finish();
                        }else{
                            Snackbar.make(buttonLogin, "Network not Connected", Snackbar.LENGTH_LONG).show();
                        }

                    }
                    else {

                        Snackbar.make(buttonLogin, "Enter valid Password", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textView4);
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        textInputLayoutEmail = findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword =findViewById(R.id.textInputLayoutPassword);
        buttonLogin = findViewById(R.id.imageButton2);

    }



    public boolean validate() {
        boolean valid = false;

        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (Email.isEmpty()){
            valid = false;
            textInputLayoutEmail.setError("Please enter  email!");
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        }
        else {
            valid = true;
            textInputLayoutEmail.setError(null);
            if (Password.isEmpty()) {
                valid = false;
                textInputLayoutPassword.setError("Please enter password!");

            } else {
                if (Password.length() < 7) {
                    valid = false;
                    textInputLayoutPassword.setError("Password is to short!");

                }
//                else if (!Password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
//                    valid = false;
//                    textInputLayoutPassword.setError("password must contain (A-Z),(a-z),(0-9),(!@#$%^&*)");
//                }
                else {
                    valid = true;
                    textInputLayoutPassword.setError(null);
                }
            }
        }



        return valid;
    }
    public void dialog(boolean value){

//        if(value){
//            tv_check_connection.setText("We are back !!!");
//            tv_check_connection.setBackgroundColor(Color.GREEN);
//            tv_check_connection.setTextColor(Color.WHITE);

//            Handler handler = new Handler();
//            Runnable delayrunnable = new Runnable() {
//                @Override
//                public void run() {
//                    tv_check_connection.setVisibility(View.GONE);
//                }
//            };
//            handler.postDelayed(delayrunnable, 3000);
//        }else {
//            tv_check_connection.setVisibility(View.VISIBLE);
//            tv_check_connection.setText("Could not Connect to internet");
//            tv_check_connection.setBackgroundColor(Color.RED);
//            tv_check_connection.setTextColor(Color.WHITE);
//        }
    }


    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
//    public void setAnimation() {
//        if (Build.VERSION.SDK_INT > 20) {
//            android.transition.Slide slide = new Slide();
//            Slide slide1=new Slide();
//            slide1.setSlideEdge(Gravity.END);
//            slide.setDuration(500);
//            slide.setSlideEdge(Gravity.START);
//            slide.setDuration(500);
//            slide.setInterpolator(new AccelerateDecelerateInterpolator());
//            getWindow().setExitTransition(slide);
//            getWindow().setEnterTransition(slide1);
//        }
//    }
    public void onLoginClick(View View){
        startActivity(new Intent(this,RegistrationActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
//        unregisterNetworkChanges();
    }

}

