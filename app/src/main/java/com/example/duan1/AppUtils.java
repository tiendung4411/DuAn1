package com.example.duan1;

// AppUtils.java
import java.text.NumberFormat;
import java.util.Locale;

public class AppUtils {

    public static String formatNumberWithDots(int number) {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMAN);
        return numberFormat.format(number);
    }

    // You can add more utility functions here if needed
}
