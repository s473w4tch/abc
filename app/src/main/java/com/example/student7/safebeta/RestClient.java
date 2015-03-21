package com.example.student7.safebeta;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;


import com.example.student7.safebeta.data.Account;
import com.example.student7.safebeta.data.EmailAndPassword;
import com.example.student7.safebeta.data.User;
import com.example.student7.safebeta.data.UserDB;


@Rest(rootUrl = "https://dsp-safetest.cloud.dreamfactory.com:443/rest",
        converters = { MappingJackson2HttpMessageConverter.class })

@RequiresHeader({"X-Dreamfactory-Application-Name"})
public interface RestClient extends RestClientHeaders {

    @Post("/user/session")
    User email (EmailAndPassword emailAndPassword);

/*
    @Post("/user/register/?login=true")
    RegisterUser registerNewUser(RegisterUser registerUser);
*/
    @Post("/user/register/?login=true")
    Account createUser(Account account);

    @Post("/safetest/uzyt")
    UserDB createUserDB(UserDB userDB);


}