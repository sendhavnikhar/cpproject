package com.example.linkchat_final.model;

public class user {
    String ProfilePic, username, mail, password, cpassword, userid, lastMassage;

    public user(String profilePic, String username, String mail, String password, String userid, String lastMassage,String key) {
        ProfilePic = profilePic;
        this.username = username;
        this.mail = mail;
        this.password = password;
        this.userid = userid;
        this.lastMassage = lastMassage;

    }

    public user() {
    }


    //sign up
    public user(String mail, String password) {

        this.mail = mail;
        this.password = password;

    }

    public user(String mail, String password, String cpassword) {

        this.mail = mail;
        this.password = password;
        this.cpassword = cpassword;

    }


    public String getProfilePic() {
        return ProfilePic;
    }

    public void setProfilePic(String profilePic) {
        ProfilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getUserid(String key) {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }
}
