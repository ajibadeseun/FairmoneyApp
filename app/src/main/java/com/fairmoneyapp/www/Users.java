package com.fairmoneyapp.www;

import java.util.List;

public class Users {
    public List<User> data;


    public static class User{
        public String id;
        public String firstName;
        public String lastName;
        public String title;
        public String email;
        public String picture;
    }
}
