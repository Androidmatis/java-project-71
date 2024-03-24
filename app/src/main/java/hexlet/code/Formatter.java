package hexlet.code;

import hexlet.code.formatters.JSONFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String switchFormater(Map<String, List<Object>> resultMap, String format) throws Exception {
        return switch (format) {
            case "stylish" -> StylishFormatter.convertToString(resultMap);
            case "plain" -> PlainFormatter.convertToString(resultMap);
            case "json" -> JSONFormatter.convertToString(resultMap);
            default -> new IOException(format + " - is an incorrect format.").toString();
        };
    }
}
