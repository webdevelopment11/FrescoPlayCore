package com.fresco;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegEx {

    public String findCardTypeNumbers(String conversation, String cardType) {
        Pattern pattern;
        Matcher matcher;
        StringBuilder res = new StringBuilder();
        if (cardType.equals("JCB")) {
            pattern = Pattern.compile("(2131|1800)\\d{11}|(35\\d{14})");
            matcher = pattern.matcher(conversation);
            while (matcher.find()) {
                res.append(matcher.group() + " ");
            }
            return res.toString().trim();
        } else if (cardType.equals("Visa")) {
            pattern = Pattern.compile("\\b(4\\d{12,15})\\b");
            matcher = pattern.matcher(conversation);
            while (matcher.find()) {
                res.append(matcher.group() + " ");
            }
            return res.toString().trim();
        } else if (cardType.equals("American Express")) {
            pattern = Pattern.compile("((34)|(37))\\d{13}");
            matcher = pattern.matcher(conversation);
            if (matcher.find()) {
                return matcher.group();
            } else {
                return "";
            }
        } else if (cardType.equals("Discover")) {
            pattern = Pattern.compile("(6011\\d{12})|(65\\d{14})");
            matcher = pattern.matcher(conversation);
            while (matcher.find()) {
                res.append(matcher.group() + " ");
            }
            return res.toString().trim();
        } else {
            return "Not a valid card type";
        }
    }
} 
