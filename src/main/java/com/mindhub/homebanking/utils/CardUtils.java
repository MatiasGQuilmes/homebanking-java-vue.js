package com.mindhub.homebanking.utils;

public final class CardUtils {

    private CardUtils(){

    };

    public static int getCvv() {
        int cvv = (int) ((Math.random() * (100-1000)) + 100);
        return cvv;
    }

    public static String getCardNumber() {
        String cardNumber = (int) ((Math.random() * (1000-10000)) + 1000)
                + " " + (int) ((Math.random() * (1000-10000)) + 1000)
                + " " + (int) ((Math.random() * (1000-10000)) + 1000)
                + " " + (int) ((Math.random() * (1000-10000)) + 1000);
        return cardNumber;
    }

}
