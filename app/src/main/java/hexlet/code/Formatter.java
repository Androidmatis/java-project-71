package hexlet.code;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String stylishFormat(Map<String, List<Object>> resultMap) {
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

    public static String plainFormat(Map<String, List<Object>> resultMap) {
        String result = "";
        for (Map.Entry element : resultMap.entrySet()) {
            List<Object> val = (List<Object>) element.getValue();
            switch (val.size()) {
                case 1:
                    break;
                case 2:
                    if (val.get(0).equals("-")) {
                        result += "Property '"
                                + element.getKey().toString()
                                + "' was removed" + "\n";
                    } else {
                        result += "Property '"
                                + element.getKey().toString()
                                + "' was added with value: "
                                + formationOfMeaningForPlainFormat(val.get(1))
                                + "\n";
                    }
                    break;
                case 4:
                    result += "Property '"
                            + element.getKey().toString()
                            + "' was updated. From "
                            + formationOfMeaningForPlainFormat(val.get(1))
                            + " to "
                            + formationOfMeaningForPlainFormat(val.get(3))
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
