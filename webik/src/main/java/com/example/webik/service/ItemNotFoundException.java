package com.example.webik.service;

public class ItemNotFoundException extends Throwable {
    @Override
    public String toString() {
        return "item is not found";
    }
}
