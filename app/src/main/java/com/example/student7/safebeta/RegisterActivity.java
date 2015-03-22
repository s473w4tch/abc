package com.example.student7.safebeta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;


import com.example.student7.safebeta.data.EmailAndPassword;

import com.example.student7.safebeta.data.User;
import com.example.student7.safebeta.data.Account;
import com.example.student7.safebeta.data.UserDB;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity (R.layout.activity_register)
@OptionsMenu(R.menu.menu_choose)
public class RegisterActivity extends Activity {
    @Extra
    Account account;
    @Extra
    User user;
    @ViewById
    EditText email;

    @ViewById
    EditText first_name;

    @ViewById
    EditText last_name;

    @ViewById
    EditText new_password;
    @ViewById
    EditText phone_number;
    @ViewById
    EditText id_number;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;
    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    ProgressDialog ringProgressDialog;
    @AfterViews
    void init() {

        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Loading...");
        ringProgressDialog.setIndeterminate(true);

    }

    @Click
    void btnRegisterClicked() {
        ringProgressDialog.show();
        Account account = new Account();
        account.email = email.getText().toString();
        account.first_name = first_name.getText().toString();
        account.last_name = last_name.getText().toString();
        account.new_password = new_password.getText().toString();

        if (new_password.getText().toString().length() < 6) {
            Toast.makeText(this, "The password must be at least 6 characters!", Toast.LENGTH_LONG).show();
        } else if (email.getText().length() > 3 && first_name.getText().length() > 3
                && last_name.getText().length() > 3) {

            try {
                restBackgroundTask.createUser(account);
            } catch (NullPointerException e) {
                showError(e);

            }
            EmailAndPassword emailAndPassword = new EmailAndPassword();
            emailAndPassword.email = email.getText().toString(); //"example@example.com";
            emailAndPassword.password = new_password.getText().toString(); //"test00";
            restLoginBackgroundTask.login(emailAndPassword);

            UserDB userDB = new UserDB();
            userDB.email = email.getText().toString();
            userDB.name = first_name.getText().toString();
            userDB.lastName = last_name.getText().toString();
            userDB.password = new_password.getText().toString();
            userDB.phoneNumber = Integer.parseInt(phone_number.getText().toString());
            userDB.idNumber = Integer.parseInt(id_number.getText().toString());
           // userDB.userdIdApi = user.id;

            try {
                restBackgroundTask.createUserDB(userDB);

            } catch (NullPointerException e) {
                showError(e);

            }
            ringProgressDialog.dismiss();
            //Toast.makeText(this, "You have create account successful ", Toast.LENGTH_LONG).show();
           // finish();





        } else {
            Toast.makeText(this, "Prosze wypełnić wszystkie pola", Toast.LENGTH_LONG).show();
        }
        /**/

    }
    public void loginSuccess1(User user) {

        ringProgressDialog.dismiss();
        Toast.makeText(this, "You have logged successful " + user.email.toString(), Toast.LENGTH_LONG).show();
        //MainPage_.intent(this).user(user).start();
        //ChooseActivity_.intent(this).start();
    }

    public void showError(Exception e) {
        ringProgressDialog.dismiss();
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }


}