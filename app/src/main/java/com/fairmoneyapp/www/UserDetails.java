package com.fairmoneyapp.www;

import java.util.Date;

public class UserDetails {
    public String id;
    public String phone;
    public String lastName;
    public String firstName;
    public Location location;
    public String email;
    public String gender;
    public String title;
    public Date registerDate;
    public String picture;
    public Date dateOfBirth;
    public class  Location{
        public String state;
        public String street;
        public String city;
        public String timezone;
        public String country;
    }
}
