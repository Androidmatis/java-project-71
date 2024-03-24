package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {
    static final int ADDANDREMOVE = 2;
    public static String convertToString(Map<String, List<Object>> resultMap) {
        final int equal = 1;
        final int addAndRemove = 2;
        final int change = 4;
        String result = "";
        for (Map.Entry element : resultMap.entrySet()) {
            List<Object> val = (List<Object>) element.getValue();
            switch (val.size()) {
                case equal:
                    break;
                case addAndRemove:
                    if (val.get(addAndRemove - 2).equals("-")) {
                        result += "Property '"
                                + element.getKey().toString()
                                + "' was removed" + "\n";
                    } else {
                        result += "Property '"
                                + element.getKey().toString()
                                + "' was added with value: "
                                + formationOfMeaningForPlainFormat(val.get(addAndRemove - 1))
                                + "\n";
                    }
                    break;
                case change:
                    result += "Property '"
                            + element.getKey().toString()
                            + "' was updated. From "
                            + formationOfMeaningForPlainFormat(val.get(addAndRemove - 1))
                            + " to "
                            + formationOfMeaningForPlainFormat(val.get(addAndRemove + 1))
                            + "\n";
                    break;
                default:
            }
        }
        return result.substring(0, result.length() - 1);
    }

    public static String formationOfMeaningForPlainFormat(Object obj) {
        return switch (defineObjectClass(obj)) {
            case "Number", "Boolean" -> obj.toString();
            case "String" -> "'" + obj.toString() + "'";
            case "complex value" -> "[complex value]";
            case "null" -> "null";
            default -> "none";
        };
    }

    public static String defineObjectClass(Object obj) {
        if (obj instanceof Number) {
            return "Number";
        } else if (obj instanceof Boolean) {
            return "Boolean";
        } else if (obj instanceof String) {
            return "String";
        } else if (obj == null) {
            return "null";
        }
        return "complex value";
    }
}
