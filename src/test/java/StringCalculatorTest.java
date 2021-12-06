import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    private StringCalculator stringCalculator;

    @BeforeEach
    public void setup() {
        stringCalculator = new StringCalculator();
    }

    // step 1
    @Test
    void addEmptyStringReturnsZero() throws Exception {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void addStringWithZeroReturnsZero() throws Exception {
        assertEquals(0, stringCalculator.add("0"));
    }

    @Test
    void addStringWithOneReturnsOne() throws Exception {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    void addStringWithOneAndTwoReturnsThree() throws Exception {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    void addStringWithSevenAndNineReturnsSixteen() throws Exception {
        assertEquals(16, stringCalculator.add("7,9"));
    }

    // step 2
    @Test
    void ShouldHandleUnknownAmountOfNumbers() throws Exception {
        assertEquals(9, stringCalculator.add("0,1,3,5"));
    }

    // step 3
    @Test
    void stringWithNewLinesAndOneTwoThreeReturnsSix() throws Exception {
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    void stringWithWrongInput() throws Exception {
        assertEquals(1, stringCalculator.add("1,\n"));
    }

    // step 4

    @Test
    void stringWithDelimiterLineAndOneTwoThreeIsSplitByDelimiterAndReturnsSix() throws Exception {
        assertEquals(6, stringCalculator.add("//;\n1;2;3"));
    }

    // step 5
    @Test
    void throwExceptionWhenTheresOneNegativeNumber() {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> stringCalculator.add("1\n2,-3,8"))
                .withMessage("negatives not allowed : -3");
    }

    @Test
    void throwExceptionWhenTheresMoreThanOneNegativeNumbers() {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> stringCalculator.add("1\n2,-3,-2,8"))
                .withMessage("negatives not allowed : -3,-2");
    }

    // step 6
    @Test
    void shouldIgnoreNumbersBiggerThan1000() throws Exception {
        assertEquals(2, stringCalculator.add("2,1001,2000"));
    }

    /*// step 7

    @Test
    void shouldAcceptCustomDelimiter() throws Exception {
        assertEquals(6, stringCalculator.add("//[***]\n1***2***3"));
    }

    // step 8
    @Test
    void ShouldAllowMultipleDelimiters() throws Exception {
        assertEquals(6, stringCalculator.add("//[*][%]\n1*2%3"));
    }

    // step 9
    @Test
    void ShouldAllowMultipleDelimitersWithLengthLongerThanOneChar() throws Exception {
        assertEquals(6, stringCalculator.add("//[***][%%]\\n1***2%%3"));
    }*/
}