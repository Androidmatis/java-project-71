package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String convertToString(Map<String, List<Object>> resultMap) {
        String result = "";
        result += "{\n";
        for (Map.Entry element : resultMap.entrySet()) {
            List<Object> val = (List<Object>) element.getValue();
            switch (val.size()) {
                case 1:
                    result += "\t  "  + element.getKey().toString() + ": " + val.get(0) + "\n";
                    break;
                case 2:
                    result += "\t" + val.get(0) + " "  + element.getKey().toString() + ": " + val.get(1) + "\n";
                    break;
                case 4:
                    result += "\t" + val.get(0) + " "  + element.getKey().toString() + ": " + val.get(1) + "\n";
                    result += "\t" + val.get(2) + " "  + element.getKey().toString() + ": " + val.get(3) + "\n";
                    break;
                default:
            }
        }
        result += "}";
        return result;
    }
}
