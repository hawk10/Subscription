package com.aiman.ezp.domain.subscription.service.type;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
* the factory will be used to store the impl classes in a map
* based on key of subType it will retrieve and execute the correct impl class
* */
@Service
public class SubTypeFactory {

    public static Map<String, SubType> SUB_TYPE_IMPL_MAP = new HashMap<>();

}
