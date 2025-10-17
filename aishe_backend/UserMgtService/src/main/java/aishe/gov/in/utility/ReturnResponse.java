package aishe.gov.in.utility;

import java.math.BigInteger;


public class ReturnResponse {

    private String message;

    private Integer status;

    private BigInteger total;

    private Object data;

    public ReturnResponse() {
    }
    
    public ReturnResponse(Integer status, String message) {
        super();
        this.message = message;
        this.status = status;
    }

    public ReturnResponse(Integer status, String message, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ReturnResponse(Integer status, String message, Object data, BigInteger total) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.total = total;
    }

    // General error message about nature of error

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }
}
