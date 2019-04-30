package com.virtusa.test;

import com.virtusa.test.exception.NegativeNumberException;
import com.virtusa.test.exception.NumberExceedException;
import com.virtusa.test.exception.ValidNumberException;
import org.junit.Test;

public class NumberToWordConverterTest {

    @Test
    public void testNumberToWordConverter() throws NegativeNumberException, NumberExceedException, ValidNumberException {
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        numberToWordConverter.convert("99999999");
    }

    @Test(expected = NegativeNumberException.class)
    public void testNumberToWordConverterNegativeNumber() throws NegativeNumberException, NumberExceedException, ValidNumberException {
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        numberToWordConverter.convert("-9999999");
    }

    @Test(expected = ValidNumberException.class)
    public void testNumberToWordConverterValidNumber() throws NegativeNumberException, NumberExceedException, ValidNumberException {
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        numberToWordConverter.convert("");
    }

    @Test(expected = NumberExceedException.class)
    public void testNumberToWordConverterNumberExceedsException() throws NegativeNumberException, NumberExceedException, ValidNumberException {
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        numberToWordConverter.convert("99999999999999");
    }

    public void testNumberToWordConverterTwelve() throws NegativeNumberException, NumberExceedException, ValidNumberException {
        NumberToWordConverter numberToWordConverter=new NumberToWordConverter();
        numberToWordConverter.convert("12");
    }
}
