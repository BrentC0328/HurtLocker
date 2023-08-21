import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserTest {



    @Test
    public void arrayToStringTest() {
        Parser parser = new Parser();
        String[] stringArray = new String[]{"ChickenNugget", "Zebra", "Potato"};
        String expected = "ChickenNugget\nZebra\nPotato\n";

        String actual = parser.arrayToString(stringArray);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void isEvenTest() {

        Parser parser = new Parser();
        Assert.assertTrue(parser.isEven(2));
    }

    @Test
    public void splitAndListArrayTest() {
        Parser parser = new Parser();
        String[] stringArray = new String[]{"ChickenNugget", "type", "Zebra", "Potato", "type"};
        String expected = "ChickenNugget\nZebra\nPotato\n";

        String actual = parser.splitAndListArray(stringArray);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void keepEvenHalfOfArrayIndexesTest() {
        Parser parser = new Parser();
        String[] stringArray = new String[]{"ChickenNugget", "type", "Zebra", "Potato", "type"};
        String expected = "ChickenNugget\nZebra\ntype\n";

        String actual = parser.keepEvenHalfOfArrayIndexes(stringArray);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void giveMeItemsMapTest() {
        Parser parser = new Parser();
        String apples = """
                naMe:apPles;price:0.25;
                naMe:apPles;price:0.23;
                naMe:apPles;prIce:0.25;
                naMe:apPles;pRice:0.23;""";

        List<Double> doubleList = new ArrayList<>();
        doubleList.add(0.25);
        doubleList.add(0.23);
        doubleList.add(0.25);
        doubleList.add(0.23);
        Map<String, List<Double>> expected = new HashMap<>();
        expected.put("apPles", doubleList);

        Map<String, List<Double>> actual = parser.giveMeItemsMap(apples);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void giveMeTheItemErrorsTest() {
        Parser parser = new Parser();
        String apples = """
                naMe:;price:0.25;
                naMe:apPles;price:0.23;
                naMe:apPles;prIce:;
                naMe:apPles;pRice:0.23;""";
        Integer expected = 2;

        Integer actual = parser.giveMeTheItemErrors(apples);

        Assert.assertEquals(expected,actual);


    }

    @Test
    public void formatTheListWithStringBuilderTest() {
        //Given
        //When
        //Then

        //TBD once formatter is figured out.
    }
}