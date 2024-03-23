package hexlet.code;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;
import java.util.ArrayList;

public class Parser {
    public static Map<String, List<Object>> filesToMapDiff(Map<String, Object> file1Contents,
                                                           Map<String, Object> file2Contents) {
        TreeMap<String, Object> contents = new TreeMap<>(file2Contents);
        contents.putAll(file1Contents);
        LinkedHashMap<String, List<Object>> resultMap = new LinkedHashMap<>();
        for (Map.Entry element : contents.entrySet()) {
            List<Object> objMap = new ArrayList<>();
            if (file1Contents.containsKey(element.getKey().toString())
                    && file2Contents.containsKey(element.getKey().toString())) {
                if (Objects.equals(file1Contents.get(element.getKey().toString()),
                        file2Contents.get(element.getKey().toString()))) {
                    objMap.add(element.getValue());
                    resultMap.put(element.getKey().toString(), objMap);
                } else {
                    objMap.add("-");
                    objMap.add(element.getValue());
                    objMap.add("+");
                    objMap.add(file2Contents.get(element.getKey().toString()));
                    resultMap.put(element.getKey().toString(), objMap);
                }
            } else if (file1Contents.containsKey(element.getKey().toString())) {
                objMap.add("-");
                objMap.add(element.getValue());
                resultMap.put(element.getKey().toString(), objMap);
            } else if (file2Contents.containsKey(element.getKey().toString())) {
                objMap.add("+");
                objMap.add(element.getValue());
                resultMap.put(element.getKey().toString(), objMap);
            }
        }
        return resultMap;
    }
}
