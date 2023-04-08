package com.example.infs3605groupprojectnew;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    /*    private String id;*/


    public User() {
    }

    public User(String username, String password, String email, String firstname, String lastname/*String id*/) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
//        this.enquiryTitle = enquiryTitle;
//        this.enquiryDesc = enquiryDesc;
        /* this.id = id;*/
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }



    class Enquiry extends User {

        private String enquiryTitle;

        private String enquiryDesc;

        private String enquiryEmail;

        public Enquiry() {

        }



        public Enquiry(String enquiryEmail, String enquiryTitle, String enquiryDesc) {
            this.enquiryTitle = enquiryTitle;
            this.enquiryDesc = enquiryDesc;
            this.enquiryEmail = enquiryEmail;
        }

        public String getEnquiryEmail() {
            return enquiryEmail;
        }

        public void setEnquiryEmail(String enquiryEmail) {
            this.enquiryEmail = enquiryEmail;
        }
        public String getEnquiryTitle() {
            return enquiryTitle;
        }

        public void setEnquiryTitle(String enquiryTitle) {
            this.enquiryTitle = enquiryTitle;
        }


        public String getEnquiryDesc() {
            return enquiryDesc;
        }

        public void setEnquiryDesc(String enquiryDesc) {
            this.enquiryDesc = enquiryDesc;
        }


    }
}

    /*public String getId() {return id;}

    public void setId(String id) {this.id = id;}*/




