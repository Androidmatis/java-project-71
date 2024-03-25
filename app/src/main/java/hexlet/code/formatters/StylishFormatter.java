package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {
    public static String convertToString(Map<String, List<Object>> resultMap) {
        final int equal = 1;
        final int addAndRemove = 2;
        final int change = 4;
        String result = "";
        result += "{\n";
        for (Map.Entry element : resultMap.entrySet()) {
            List<Object> val = (List<Object>) element.getValue();
            switch (val.size()) {
                case equal:
                    result += "    "  + element.getKey().toString()
                            + ": " + val.get(equal - 1) + "\n";
                    break;
                case addAndRemove:
                    result += "  " + val.get(addAndRemove - 2) + " "
                            + element.getKey().toString() + ": "
                            + val.get(addAndRemove - 1) + "\n";
                    break;
                case change:
                    result += "  " + val.get(addAndRemove - 2) + " "
                            + element.getKey().toString() + ": "
                            + val.get(addAndRemove - 1) + "\n";
                    result += "  " + val.get(addAndRemove) + " "
                            + element.getKey().toString() + ": "
                            + val.get(addAndRemove + 1) + "\n";
                    break;
                default:
            }
        }
        result += "}\n";
        return result;
    }
}
