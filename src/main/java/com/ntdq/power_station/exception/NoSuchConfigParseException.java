package com.ntdq.power_station.exception;

public class NoSuchConfigParseException extends RuntimeException{

    private String message;

    public NoSuchConfigParseException(String message){
        super(message);
    }

}
