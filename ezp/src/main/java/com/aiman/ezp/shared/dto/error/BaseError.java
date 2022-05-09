package com.aiman.ezp.shared.dto.error;

public class BaseError {


    private String errorCode;
    /*can be used for front end display*/
    private String errorMsg;

    /*in case more in depth desc is needed for integration calls*/
    private String errorDesc;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
