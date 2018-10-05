package com.intellLogic.validation;

import java.util.regex.Pattern;

public class ValidateNumber {

    public static boolean isValidNumber( String str){

        // Check if its a six digit number
        Pattern pattern = Pattern.compile("\\d{1,6}");
        return !pattern.matcher(str).matches();
    }
}

