package com.java.common;


import java.util.ArrayList;

public class StringUtils {
    public static String[] splitStringByRegex(String regex, String text) {
        ArrayList<String> textArray = new ArrayList<>();
        for (String s:text.split(regex)){
            if(!s.equals("")){
                textArray.add(s);
            }
        }
        String[] textArrayNew = new String[textArray.size()];
        for (int i = 0; i < textArray.size(); i++) {
            textArrayNew[i] = (String) textArray.toArray()[i];
        }
        return textArrayNew;
    }

}
