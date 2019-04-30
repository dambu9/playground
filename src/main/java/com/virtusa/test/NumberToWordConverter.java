package com.virtusa.test;

import com.virtusa.test.exception.NegativeNumberException;
import com.virtusa.test.exception.NumberExceedException;
import com.virtusa.test.exception.ValidNumberException;

public class NumberToWordConverter {

    private static final  String[] UNITS = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven","Eight", "Nine", "Ten", "Eleven", "Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen","Nineteen"};
    private static final  String[] TEN_MULTIPLES = new String[]{"", "Ten","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety","Hundred"};
    private static final  String[] IDENTIFIERS = new String[]{"Hundred","Thousand","Million"};
    private static final  String NEG_EXCEPTION_MSG = "Number cannot be negative";
    private static final  String NUM_EXCEEDS_EXCEPTION_MSG = "Number exceeds limit";
    private static final  String VAL_NUM_EXCEPTION_MSG = "Not a valid number";

    public void convert(String num) throws NegativeNumberException, NumberExceedException, ValidNumberException{
        if(num.length() == 0){
            System.out.println(VAL_NUM_EXCEPTION_MSG);// Empty String
            throw new ValidNumberException(VAL_NUM_EXCEPTION_MSG);
        }
        if(num.length() > 1 && num.indexOf('-') != -1){
            System.out.println(NEG_EXCEPTION_MSG);// negative values
            throw new NegativeNumberException(NEG_EXCEPTION_MSG);
        }
        if(num.length() > 9){
            System.out.println(NUM_EXCEEDS_EXCEPTION_MSG);// supports till 999,999,999
            throw new NumberExceedException(NUM_EXCEEDS_EXCEPTION_MSG);
        }
        this.printWords(num);

    }

    private int convertCharToInteger(char c){
        return Integer.parseInt(String.valueOf(c)); // convert char to integer
    }

    private int convertStringToInteger(String c){
        return Integer.parseInt(c); // convert string to integer
    }

    private String getUnits(int val){
        return UNITS[val];
    }

    private String getTenMultiples(int val){
        return TEN_MULTIPLES[val];
    }

    //return multiples of value
    private String getScale(int length){
        if(length > 6){
            return IDENTIFIERS[2]; // million if string value is greater than 7
        }else if (length > 3){
            return IDENTIFIERS[1]; // thousand if string value is greater than 3
        }
        return "";
    }

    /*
     * if string is less than hundred then it will print number value
     * ex : 99,
     * Ninety Nine
     * ex : 12
     * Twelve
     * */
    private void constructLessThanHundred(String subString){
        //less than twenty
        // if the value is less than twenty then follows as below
        if(subString.length() == 2 && (subString.charAt(0) == '1' || subString.charAt(0) == '0')){
            Integer integer=this.convertStringToInteger(subString.charAt(0) +""+ subString.charAt(1) ); // parsing char to int
            System.out.print("\t"+this.getUnits(integer));
        }else if(subString.length() == 2 && (subString.charAt(0) != '1' && subString.charAt(0) != '0')){
            System.out.print("\t"+this.getTenMultiples(this.convertCharToInteger(subString.charAt(0))));// if not twenty
            System.out.print("\t"+this.getUnits(this.convertCharToInteger(subString.charAt(1))));
        }else{
            System.out.print("\t"+this.getUnits(this.convertCharToInteger(subString.charAt(0))));
        }
    }

    /*
     * if string is less than hundred then it will print number value
     * ex : 999,
     * Ninety
     * */
    private void constructHundred(String subString){
        if(subString.length() == 3){
            System.out.print("\t"+this.getUnits(this.convertCharToInteger(subString.charAt(0))) + " " + IDENTIFIERS[0]);
            System.out.print("\t and");
        }
    }

    /*
    * Number identified on 3 parts one is hundred and less than number
    * ex: 999 (9 will be hundred part) and 99 will be less than hundred value
    * this method will check string contains multiple parts
    * multiplesIdentifier will be identify the string to check whether string is multiples of 3
    * example : 999,999,999,999
    * String will split on basis of 3, 999 will be of number in which multiples identifier of 3
    * if number is 99,999,999 for the first 2 numbers divisor will be of 2 multiples identifier will be of 2
    * */

    private void printWords(String number){

        String subString;
        int multiplesIdentifier = 3;// initial notation will be of 3
        if(number.length() % 3 != 0){
            multiplesIdentifier = (number.length() % 3); // String will be split into multiple part
        }
        if(number.length() > 3){
            subString = number.substring(0,multiplesIdentifier); // splitting string on basis of multiplesIdentifier
        }else{
            subString = number; // if length of the number is less than 3 then whole number will go for processing
        }
        this.constructHundred(subString); // passing multiples identifier
        if(subString.length() == 3){
            constructLessThanHundred(subString.substring(1, 3));
        }else{
            constructLessThanHundred(subString);
        }
        System.out.print("\t" + this.getScale(number.length()));// identifier will be appended to generated multiples

        if(number.length() > 3){
            printWords(number.substring(multiplesIdentifier, number.length())); // recursive loop follows for remaining substring
        }

    }

    public static void main(String... args) throws NegativeNumberException, NumberExceedException, ValidNumberException{
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        //splitting string into character array
        String sample = "64776512";
        numberToWordConverter.convert(sample);
    }
}
