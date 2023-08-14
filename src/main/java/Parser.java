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
    System.out.println(split);
    return split.toString();
}

}
