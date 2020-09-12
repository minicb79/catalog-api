package com.minicdesign.catalog.api.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String msg) {
        super(msg);
    }

    public ItemNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }
}
