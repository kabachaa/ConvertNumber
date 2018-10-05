package com.intellLogic.service;

import com.intellLogic.validation.ValidateNumber;
import com.intellLogic.validation.ValidationException;

public class ConvertToWordService {
    private static final String[] tens = {"", " ten", " twenty", " thirty", " forty", " fifty",
            " sixty", " seventy", " eighty", " ninety"};

    private static final String[] digitToWords = {"", " one", " two", " three", " four", " five", " six",
            " seven", " eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen",
            " sixteen", " seventeen", " eighteen", " nineteen"};

    private static final String AND = " and ";

    private static final String[] hundredThousand = {" hundred ", " thousand "};

    public String writeInWords(String s) throws ValidationException {
        StringBuffer wordRepresentation = new StringBuffer();
        // Check if its a negative number
        if (s.contains("-")) {
            wordRepresentation.append(" Negative ");
            // remove the "-" from string
            s = s.replace("-", "");
        }
        //trim the string for an accurte read of read of the number
        s = s.trim();

        // check if the only digits are entered
        if (ValidateNumber.isValidNumber(s)) {
            ValidationException ve = new ValidationException("Invalid Number");
            throw ve;
        }
        int length = s.length();

        StringBuffer tempStringBuffer = convertToWord(length, s);

        wordRepresentation.append(tempStringBuffer.toString());
        return wordRepresentation.toString();
    }

    private StringBuffer convertToWord(int length, String strNumber) {
        StringBuffer str = new StringBuffer();
        if (getIntegerValue(strNumber) == 0) {
            return str.append("Zero");
        }

        switch (length) {
            case 1:
                str.append(digitToWords[getIntegerValue(strNumber)]);
                break;
            case 2:
                str.append(getTenthNumber(strNumber));
                break;
            case 3:
                str.append(digitToWords[getIntegerValue(strNumber.substring(0, 1))]);
                str.append(hundredThousand[0]);
                // only display and when when 2nd and 3rd  digit are not zero
                if (!(strNumber.substring(1, 3)).equalsIgnoreCase("00")) {
                    str.append(AND);
                    str.append(getTenthNumber(strNumber.substring(1, 3)));
                }
                break;
            case 4:
                str.append(digitToWords[getIntegerValue(strNumber.substring(0, 1))]);
                str.append(hundredThousand[1]);
                str.append(digitToWords[getIntegerValue(strNumber.substring(1, 2))]);
                // only display hundred when 2 digit is not zero
                if (!(strNumber.substring(1, 2)).equalsIgnoreCase("0")) {
                    str.append(hundredThousand[0]);
                }
                // only display and when last digit is not zero
                if (!(strNumber.substring(3, 4)).equalsIgnoreCase("0")) {
                    str.append(AND);
                }
                str.append(getTenthNumber(strNumber.substring(2, 4)));
                break;
            case 5:
                str.append(getTenthNumber(strNumber.substring(0, 2)));
                str.append(hundredThousand[1]);
                str.append(digitToWords[getIntegerValue(strNumber.substring(2, 3))]);
                // only display hundred when 3 digit is not zero
                if (!(strNumber.substring(2, 3)).equalsIgnoreCase("0")) {
                    str.append(hundredThousand[0]);
                }
                // only display and when last digit is not zero
                if (!(strNumber.substring(4, 5)).equalsIgnoreCase("0")) {
                    str.append(AND);
                }
                str.append(getTenthNumber(strNumber.substring(3, 5)));
                break;
            case 6:
                str.append(digitToWords[getIntegerValue((strNumber.substring(0, 1)))]);
                str.append(hundredThousand[0]);
                str.append(getTenthNumber(strNumber.substring(1, 3)));
                str.append(hundredThousand[1]);
                str.append(digitToWords[getIntegerValue(strNumber.substring(3, 4))]);
                // only display hundred when 4 digit is not zero
                if (!(strNumber.substring(3, 4)).equalsIgnoreCase("0")) {
                    str.append(hundredThousand[0]);
                }
                // only display and when last digit is not zero
                if (!(strNumber.substring(5, 6)).equalsIgnoreCase("0")) {
                    str.append(AND);
                }
                str.append(getTenthNumber(strNumber.substring(4, 6)));
                break;
            default:
                str.append("");
                break;

        }
        return str;
    }

    // convert string to integer
    private int getIntegerValue(String strNumber) {

        return Integer.valueOf(strNumber);
    }

    // Get the teen  numbers in word or the greater then 20 but less than hundred
    private String getTenthNumber(String strNumber) {
        String str = "";
        int x = getIntegerValue(strNumber);
        if (x < 20) {
            str = digitToWords[x];
        } else {
            str = tens[getIntegerValue(strNumber.substring(0, 1))];
            str = str + digitToWords[getIntegerValue(strNumber.substring(1, 2))];
        }
        return str;
    }


}
