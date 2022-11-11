package com.kristina.user.sectors.exception;

public class UnauthorizedException extends Exception{

    public UnauthorizedException(){
        super("Permission denied!");
    }
}
