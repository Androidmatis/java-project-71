package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JSONFormatter {
    public static String convertToString(Map<String, List<Object>> resultMap) throws Exception {
        ObjectMapper om = new ObjectMapper();
        String result = om.writeValueAsString(resultMap);
        return result;
    }
}
