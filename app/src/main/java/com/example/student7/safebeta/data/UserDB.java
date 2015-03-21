package com.example.student7.safebeta.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDB implements Serializable {


    public Integer idNumber;
    public String lastName;
    public String name;
    public  String password;
    public String email;
    public Integer phoneNumber;



}

