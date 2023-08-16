import org.apache.commons.io.IOUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {



//    public String textMatchAndReplacer(String regexToFind, String replacement){
//        Pattern pattern = Pattern.compile(regexToFind, Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(hamletData);
public String readRawDataToString() throws Exception{
    ClassLoader classLoader = getClass().getClassLoader();
    String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    return result;
}

public String arrayToString(String[] input){
    StringBuilder split = new StringBuilder();
    for(int i = 0; i < input.length; i++){
        split.append(input[i])
                .append("\n");
    }
//    System.out.println(split);
    return split.toString();
}

public boolean isEven(int i){
    return i % 2 == 0;
}
public String splitAndListArray(String[] input){
    StringBuilder splitWords = new StringBuilder();

    for(String item : input){
        String[] splitArray = item.split("type");
        splitWords.append(arrayToString(splitArray));
    }
    return splitWords.toString();
}

public String keepEvenHalfOfArrayIndexes(String[] input){
    StringBuilder evenHalf = new StringBuilder();

    for (int i = 0; i < input.length; i++){
        if (isEven(i)){
            evenHalf.append(input[i])
                    .append("\n");
        }
    }
    return evenHalf.toString();
}






}
