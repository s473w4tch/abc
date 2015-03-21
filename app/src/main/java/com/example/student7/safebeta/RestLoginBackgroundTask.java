package com.example.student7.safebeta;


import com.example.student7.safebeta.data.EmailAndPassword;
import com.example.student7.safebeta.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

@EBean
public class RestLoginBackgroundTask {

    @RootContext
    LoginActivity activity;

    @RestService
    RestClient restClient;

    @Background
    void login(EmailAndPassword emailAndPassword) {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "safetest");
            User user = restClient.email(emailAndPassword);
            publishResult(user);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(User user) {
        activity.loginSuccess(user);
    }

    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}