package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Parser {
    public static Map<String, Object> filesToMapDiff(Map<String, Object> file1Contents,
                                                     Map<String, Object> file2Contents) {
        TreeMap<String, Object> contents = new TreeMap<>(file2Contents);
        contents.putAll(file1Contents);
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        for (Map.Entry element : contents.entrySet()) {
            if (file1Contents.containsKey(element.getKey().toString())
                    && file2Contents.containsKey(element.getKey().toString())) {
                if (Objects.equals(file1Contents.get(element.getKey().toString()),
                        file2Contents.get(element.getKey().toString()))) {
                    resultMap.put("  " + element.getKey().toString(), element.getValue());
                } else {
                    resultMap.put("- " + element.getKey().toString(), element.getValue());
                    resultMap.put("+ " + element.getKey().toString(), file2Contents.get(element.getKey().toString()));
                }
            } else if (file1Contents.containsKey(element.getKey().toString())) {
                resultMap.put("- " + element.getKey().toString(), element.getValue());
            } else if (file2Contents.containsKey(element.getKey().toString())) {
                resultMap.put("+ " + element.getKey().toString(), element.getValue());
            }
        }
        return resultMap;
    }
}
