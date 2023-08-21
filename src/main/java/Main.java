import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        Parser parser = new Parser();

        String output = (new Main()).readRawDataToString();
        String[] outputArray = output.split("##");

        String splitList = parser.splitAndListArray(outputArray);

        String[] testArray = splitList.split("\n");
        System.out.println(Arrays.toString(testArray));

        String tempName = parser.keepEvenHalfOfArrayIndexes(testArray);
        System.out.println(tempName); //The right number of list items here

        Integer errors = parser.giveMeTheItemErrors(tempName);//Have the errors being counted but have not yet formatted them into the parser format method.
        Map<String, List<Double>> itemsMap = parser.giveMeItemsMap(tempName); // Fixed, keeps full item list.
        System.out.println(itemsMap);
//        System.out.println(errors);
////
//        for(Map.Entry<String, List<Double>> entry : itemsMap.entrySet()) {
//            System.out.println("Item: " + entry.getKey() + ", Price: " + entry.getValue());
        String result = parser.formatTheListWithStringBuilder(itemsMap, errors); // Formats but does not account for different spelled/casing items.
        System.out.println(result);
        }
}


