package com.intellLogic;

import com.intellLogic.service.ConvertToWordService;

public class Main {

    public static void main(String[] args) {

        ConvertToWordService convertToWord = new ConvertToWordService();
        try {
            if ((args[0]!= null || args[0]!= "")) {
                System.out.println( convertToWord.writeInWords(args[0]));
            } else {
                System.out.println("Please enter a valid number Between -(999999) to (999999)");
            }
        }catch (Exception e){
            System.out.println(" Something went Wrong " + e.toString());
        }


    }


}
