package com.bonusGo.Bonus.Go.payload;

public class LoginMesage {

    String message;
    Boolean status;

    public LoginMesage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginMesage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
