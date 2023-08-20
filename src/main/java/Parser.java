import org.apache.commons.io.IOUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {


    //    public String textMatchAndReplacer(String regexToFind, String replacement){
//        Pattern pattern = Pattern.compile(regexToFind, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(hamletData);
    public String readRawDataToString() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public String arrayToString(String[] input) {
        StringBuilder split = new StringBuilder();
        for (int i = 0; i < input.length; i++) {
            split.append(input[i])
                    .append("\n");
        }
//    System.out.println(split);
        return split.toString();
    }

    public boolean isEven(int i) {
        return i % 2 == 0;
    }

    public String splitAndListArray(String[] input) {
        StringBuilder splitWords = new StringBuilder();

        for (String item : input) {
            String[] splitArray = item.split("type");
            splitWords.append(arrayToString(splitArray));
        }
        return splitWords.toString();
    }

    public String keepEvenHalfOfArrayIndexes(String[] input) {
        StringBuilder evenHalf = new StringBuilder();

        for (int i = 0; i < input.length; i++) {
            if (isEven(i)) {
                evenHalf.append(input[i])
                        .append("\n");
            }
        }
        return evenHalf.toString();
    }

    public Map<String, List<Double>> giveMeItemsMap(String unformattedString) {
        Map<String, List<Double>> giveMeItemsMap = new HashMap<>();

        //"name:(.+?);price:(\\d+\\.\\d+);" -- my regex here is taking out like 10 entries that it shouldn't
        Pattern pattern = Pattern.compile("(?i)name:(.*?);.*?price:(\\d+\\.\\d+|\\d+\\.?|\\.\\d+);", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(unformattedString);

        while (matcher.find()) {
            String itemName = matcher.group(1);
            String itemPrice = matcher.group(2);

            if (!itemName.isEmpty() && !itemPrice.isEmpty()) {
                double itemPriceDouble = Double.parseDouble(itemPrice);
                giveMeItemsMap.computeIfAbsent(itemName, k -> new ArrayList<>()).add(itemPriceDouble);
            }
        }

        return giveMeItemsMap;
    }

    public Integer giveMeTheItemErrors(String unformattedString) {
        Map<String, Double> giveMeItemsMap = new HashMap<>();

        Integer errors = 0;

        Pattern pattern = Pattern.compile("name:(.*?);price:(\\d*\\.?\\d*);", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(unformattedString);

        while (matcher.find()) {
            String itemName = matcher.group(1);
            String itemPrice = matcher.group(2);
            if (itemName.isEmpty() || itemPrice.isEmpty()) {
                errors++;
            } else {
                double productPrice = Double.parseDouble(itemPrice);
                giveMeItemsMap.put(itemName, productPrice);
            }
        }

        return errors;
    }

    public String formatTheListWithStringBuilder(Map<String, Double> mapToFormat, Integer errors) {
        Map<String, Integer> nameCount = new HashMap<>();
        Map<String, List<Double>> pricesToName= new HashMap<>();

        //First we loop through and count the occurences of each product name and price.
        for (Map.Entry<String, Double> entry : mapToFormat.entrySet()) {
            String itemName = entry.getKey().toLowerCase(); //you see no string manipulation here <(*_*)>
            Double itemPrice = entry.getValue();

            nameCount.put(itemName, nameCount.getOrDefault(itemName, 0) + 1);
            //We are grabbing each price here and putting it in a price list so we can hold all the prices that one name might have associated.
            List<Double> prices = pricesToName.computeIfAbsent(itemName, x -> new ArrayList<>());
            prices.add(itemPrice);
        }

        //Lets build our string
        StringBuilder shoppingList = new StringBuilder();

        //It's about to get a little spicy
        for (Map.Entry<String, Integer> nameEntry : nameCount.entrySet()) {
            String productName = nameEntry.getKey();
            int nameSeen = nameEntry.getValue();

            //format the name first
            shoppingList.append("name:    ").append(productName).append("\t\t seen: ").append(nameSeen).append(" times\n");
            shoppingList.append("=============  \t =============\n");


            //Time to format our price for that item.
            List<Double> prices = pricesToName.get(productName);
            for (Double price : prices) {
                shoppingList.append("Price:   ").append(price).append("\t seen: ").append(nameSeen).append(" times\n");
            }

            shoppingList.append("\n");
        }

        return shoppingList.toString();

    }


}
