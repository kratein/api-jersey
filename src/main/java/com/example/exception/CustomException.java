package com.example.exception;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException implements Serializable {

    // code d'erreur
    private int code;
    public final static String ERROR_STRING = "An error has occured.";
    public final static String ERROR_NULL = ERROR_STRING + " Object you want to insert is null.";
    public final static String ERROR_DUPLICATE_ID = ERROR_STRING + " Duplicate primary key.";
    public final static String ERROR_NOTFOUND = ERROR_STRING + " ID not found.";

    public CustomException(int code) {
        super();
        this.code = code;
    }

    public CustomException(String message, int code) {
        super(message);
        this.code = code;
    }

    public CustomException(Throwable cause, int code) {
        super(cause);
        this.code = code;
    }

    public CustomException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

  // getter et setter
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "{ \"message\":\""+ this.getMessage() +"\"}";
    }
}
