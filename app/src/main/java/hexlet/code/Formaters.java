package hexlet.code;

import java.util.Map;

public class Formaters {
    public static String stylishFormat(Map<String, Object> resultMap) {
        String result = "";
        result += "{\n";
        for (Map.Entry element : resultMap.entrySet()) {
            result += "\t" + element.getKey().toString() + ": " + element.getValue() + "\n";
        }
        result += "}";
        return result;
    }
}
