package com.nic.aishe.master.util;

import org.springframework.stereotype.Component;

@Component
public class NullValueHandler {
    public String nullValueHandler(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : value;
        return null;
    }

    public Integer nullValueHandleInteger(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : Integer.valueOf(value);
        return null;
    }

    public Boolean nullValueHandlerBoolean(String value) {
        if (null != value)
            return value.equalsIgnoreCase("null") ? null : Boolean.valueOf(value);
        return null;
    }
}
