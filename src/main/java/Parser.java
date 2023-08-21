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

    public String formatTheListWithStringBuilder(Map<String, List<Double>> mapToFormat, Integer errors) {

        StringBuilder shoppingList = new StringBuilder();

        for (Map.Entry<String, List<Double>> entry : mapToFormat.entrySet()) {
            String productName = entry.getKey();
            List<Double> prices = entry.getValue();
            int nameSeen = prices.size();

            shoppingList.append("name: ").append(productName).append("\t\t seen: ").append(nameSeen).append(" times\n");
            shoppingList.append("=============  \t =============\n");

            Set<Double> uniquePrices = new HashSet<>(prices);

            for (Double price : uniquePrices) {
                int priceSeen = Collections.frequency(prices, price);
                shoppingList.append("Price:   ").append(price).append("\t seen: ").append(priceSeen).append(" times\n");
            }

            shoppingList.append("-------------\t -------------\n \n");
        }

        return shoppingList.toString();
    }


}
