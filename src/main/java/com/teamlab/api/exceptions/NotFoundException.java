package com.teamlab.api.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("商品ないよ");
    }
}
