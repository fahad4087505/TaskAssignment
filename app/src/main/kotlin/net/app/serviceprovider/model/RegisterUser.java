package net.app.serviceprovider.model;

import android.util.Patterns;


public class RegisterUser {

    private String strName;
    private String strPhone;

    public String getStrPhone() {
        return strPhone;
    }

    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    private String strEmailAddress;
    private String strPassword;

    public RegisterUser(String userName,String EmailAddress, String Password,String phoneNum) {
        strName = userName;
        strEmailAddress = EmailAddress;
        strPassword = Password;
        strPhone = phoneNum;

    }

    public String getStrEmailAddress() {
        return strEmailAddress;
    }

    public String getStrPassword() {
        return strPassword;
    }

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(getStrEmailAddress()).matches();
    }


    public boolean isPasswordLengthGreaterThan5() {
        return getStrPassword().length() > 5;
    }

}