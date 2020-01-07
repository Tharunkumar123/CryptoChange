package com.example.cryptochange;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.cryptochange.R.anim.slide_out_right;

public class RegistrationActivity extends AppCompatActivity {
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;
    ImageView image;
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    Button buttonRegister;

    SqliteHelper sqliteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        changeStatusBarColor();
        setContentView(R.layout.activity_registration);

        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();



        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();


                    if (Email.isEmpty()){
                        Snackbar.make(buttonRegister, "Please enter Email", Snackbar.LENGTH_LONG).show();
                    }
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                        Snackbar.make(buttonRegister, "Please enter valid Email", Snackbar.LENGTH_LONG).show();
                    }
                    else if (!sqliteHelper.isEmailExists(Email)) {

                        if (MyApplication.getInstance().getNetworkConnected()) {
                            sqliteHelper.addUser(new User(null, UserName, Email, Password));
                            Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                            Handler handler = new Handler();
                            Runnable delayrunnable = new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            };
                            handler.postDelayed(delayrunnable, 2000);
                        }else{
                            Snackbar.make(buttonRegister, "Network not Connected", Snackbar.LENGTH_LONG).show();
                        }

                    }
                    else {

                        Snackbar.make(buttonRegister, "User already exists. Please Login! ", Snackbar.LENGTH_LONG)
                                .setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                                finish();
                            }
                        })
                                .show();
                    }


                }
            }
        });
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(RegistrationActivity.this , LoginActivity.class));
//
//            }
//        });
    }

    private void initTextViewLogin() {
        TextView textViewLogin =  findViewById(R.id.textView8);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initViews() {
        editTextEmail =  findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        buttonRegister = (Button) findViewById(R.id.imageButton);
        image=findViewById(R.id.imageView7);

    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public boolean validate() {
        boolean valid = false;

        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter username!");
        } else {
            if (UserName.length() < 5) {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");

            } else {
                valid = true;
                textInputLayoutUserName.setError(null);
                if (Email.isEmpty()){
                    valid = false;
                    textInputLayoutEmail.setError("Please enter email!");
                }
               else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    valid = false;
                    textInputLayoutEmail.setError("Please enter valid email!");
                } else {
                    valid = true;
                    textInputLayoutEmail.setError(null);
                    if (Password.isEmpty()) {
                        valid = false;
                        textInputLayoutPassword.setError("Please enter password!");
                    }
                    else {
                        if (Password.length() < 7) {
                            valid = false;
                            textInputLayoutPassword.setError("Password is to short!");

                        }else if (!Password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")){
                            valid = false;
                            textInputLayoutPassword.setError("password must contain (A-Z),(a-z),(0-9),(!@#$%^&*)");
                        }
                        else {
                            valid = true;
                            textInputLayoutPassword.setError(null);
                        }
                    }
                }
            }
        }






        return valid;
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.stay, slide_out_right);
//        overridePendingTransition(R.anim.stay,android.R.anim.slide_out_right);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Animation animation= AnimationUtils.loadAnimation(this, slide_out_right);
overridePendingTransition(0, slide_out_right);
    }
}
