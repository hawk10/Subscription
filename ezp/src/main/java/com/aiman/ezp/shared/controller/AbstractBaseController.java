package com.aiman.ezp.shared.controller;

import com.aiman.ezp.shared.constant.ResponseCode;
import com.aiman.ezp.shared.dto.BaseRequest;
import com.aiman.ezp.shared.dto.BaseResponse;
import com.aiman.ezp.shared.dto.error.BaseError;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstractBaseController {

    /*Based on the required Fields data,
     * check the incoming request for the mandatory fields via reflection
     * getRequiredFields is overwritten in the concerete class to pass in the actualy required fields*/
    public <T> BaseResponse doCommonValidation(BaseRequest baseRequest, BaseResponse baseResponse, Class<T> clazz) throws  IllegalAccessException,
            InvocationTargetException {

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(getRequiredFields().contains(field.getName())) {
                Object o = runGetter(field, baseRequest);
                if(ObjectUtils.isEmpty(o)) {
                    baseResponse.setResponseCode(ResponseCode.FAIL);
                    BaseError baseError = new BaseError();
                    baseError.setErrorCode(ResponseCode.MISSING_REQUEST_DATA);
                    baseResponse.setError(baseError);
                }
            }
        }
        return baseResponse;
    }

    public abstract List<String> getRequiredFields();

    public static Object runGetter(Field field, BaseRequest o) throws InvocationTargetException, IllegalAccessException {
        for (Method method : o.getClass().getMethods())
        {
            if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
            {
                if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
                {
                    return method.invoke(o);
                }
            }
        }


        return null;
    }

    public BaseResponse populateSystemError(BaseResponse baseResponse, Exception e){

        BaseError baseError = new BaseError();
        baseError.setErrorCode(ResponseCode.SYSTEM_ERROR);
        baseResponse.setError(baseError);
        baseError.setErrorMsg(e.getMessage());
        baseResponse.setResponseCode(ResponseCode.FAIL);
        return baseResponse;
    }



}
