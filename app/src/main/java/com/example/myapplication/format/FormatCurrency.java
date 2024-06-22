package com.example.myapplication.format;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatCurrency {
    public static String formatCurrency(int money) {
        Locale locale = new Locale("vi", "VN");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

        return currencyFormat.format(money);
    }
}
