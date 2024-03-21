package hexlet.code;

import java.util.Map;

public class Parser {
    public static String concatMapToResultString(Map<String, Object> resultMap) {
        String result = "";
        result += "{\n";
        for (Map.Entry element : resultMap.entrySet()) {
            result += "\t" + element.getKey().toString() + ": " + element.getValue() + "\n";
        }
        result += "}";
        return result;
    }
}
