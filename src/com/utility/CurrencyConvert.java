/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utility;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Admin
 */
public class CurrencyConvert {
    public static String convertToVND(BigDecimal amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,### VND");
        return decimalFormat.format(amount);
    }
    public static BigDecimal convertToBigDecimal(String amountString) {
        String cleanAmountString = amountString.replaceAll("[ VND,]", "");

        try {
            return new BigDecimal(cleanAmountString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null; 
        }
    }
}
