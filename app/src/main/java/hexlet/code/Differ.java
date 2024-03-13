package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Differ {
    public static String generate(File file1, File  file2) throws IOException {;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> file1Contents = mapper.readValue(file1, Map.class);
        Map<String, Object> file2Contents = mapper.readValue(file2, Map.class);
        TreeMap<String, Object> contents = new TreeMap<>(file2Contents);
        contents.putAll(file1Contents);
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        for (Map.Entry element : contents.entrySet()) {
            if (file1Contents.containsKey(element.getKey()) && file2Contents.containsKey(element.getKey())) {
                if (file1Contents.get(element.getKey()).equals(file2Contents.get(element.getKey()))) {
                    resultMap.put("  " + element.getKey().toString(), element.getValue());
                } else if (!file2Contents.get(element.getKey()).equals(element.getValue())) {
                    resultMap.put("- " + element.getKey().toString(), element.getValue());
                    resultMap.put("+ " + element.getKey().toString(), file2Contents.get(element.getKey()));
                }
            } else if (file1Contents.containsKey(element.getKey())) {
                resultMap.put("- " + element.getKey().toString(), element.getValue());
            } else if (file2Contents.containsKey(element.getKey())) {
                resultMap.put("+ " + element.getKey().toString(), element.getValue());
            }
        }
        String result = "";
        result += "{\n";
        for (Map.Entry element : resultMap.entrySet()) {
            result += "\t" + element.getKey().toString() + ": " + element.getValue() + "\n";
        }
        result += "}";
        return result;
    }
}
