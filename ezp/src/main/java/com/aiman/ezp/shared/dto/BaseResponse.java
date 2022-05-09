package com.aiman.ezp.shared.dto;


import com.aiman.ezp.shared.dto.error.BaseError;

public class BaseResponse<T> {

    private String responseCode;
    private BaseError error = new BaseError();

    public BaseError getError() {
        return error;
    }

    public void setError(BaseError error) {
        this.error = error;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

}
