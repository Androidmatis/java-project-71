package hexlet.code;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Objects;
import java.util.ArrayList;

public class Parser {
    public static Map<String, List<Object>> filesToMapDiff(File file1, File file2, String fileFormat) throws Exception {
        Map<String, Object> file1Contents;
        Map<String, Object> file2Contents;
        switch (fileFormat) {
            case "json":
                ObjectMapper mapperJSON = new ObjectMapper();
                file1Contents = mapperJSON.readValue(file1, Map.class);
                file2Contents = mapperJSON.readValue(file2, Map.class);
                break;
            case "yaml", "yml":
                ObjectMapper mapperYAML = new YAMLMapper();
                file1Contents = mapperYAML.readValue(file1, Map.class);
                file2Contents = mapperYAML.readValue(file2, Map.class);
                break;
            default:
                throw new Exception("Unknown File format: '" + fileFormat + "' !");
        }
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
