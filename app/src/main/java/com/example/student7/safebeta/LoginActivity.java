package com.example.student7.safebeta;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import com.example.student7.safebeta.data.EmailAndPassword;
import com.example.student7.safebeta.data.User;

@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity {

    @Extra
    User user;


    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText email;

    @ViewById
    EditText password;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);
    }

    @Click
    void loginClicked()    {
        ringProgressDialog.show();
        EmailAndPassword emailAndPassword = new EmailAndPassword();
        emailAndPassword.email = email.getText().toString(); //"example@example.com";
        emailAndPassword.password = password.getText().toString(); //"test00";
        restLoginBackgroundTask.login(emailAndPassword);
    }


    public void loginSuccess(User user) {

        ringProgressDialog.dismiss();
        Toast.makeText(this, "You have logged successful", Toast.LENGTH_LONG).show();
        //MainPage_.intent(this).user(user).start();
        ChooseActivity_.intent(this).start();
    }


    /*public void loginSuccess(User user) {
        ringProgressDialog.dismiss();
        MainPage_.intent(this).user(user).start();
        //MainPage_.intent(this).start();
        Toast.makeText(this, "You have logged successful", Toast.LENGTH_LONG).show();
    }*/

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
    }

   /*
    @Click
    void registerClicked() { Register_.intent(this).start();}
    */
}
