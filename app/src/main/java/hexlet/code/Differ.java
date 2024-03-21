package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.error.MarkedYAMLException;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public class Differ {

    public static String generate(File file1, File  file2) throws IOException {
        Map<String, Object> file1Contents;
        Map<String, Object> file2Contents;
        ObjectMapper mapperJSON = new ObjectMapper();
        ObjectMapper mapperYAML = new YAMLMapper();
        try {
            file1Contents = mapperJSON.readValue(file1, Map.class);
            file2Contents = mapperJSON.readValue(file2, Map.class);
        } catch (JsonParseException ex) {
            try {
                file1Contents = mapperYAML.readValue(file1, Map.class);
                file2Contents = mapperYAML.readValue(file2, Map.class);
            } catch (MarkedYAMLException ex1) {
                return "One or both files are not JSON or YAML files.";
            }
        }
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

        return Parser.concatMapToResultString(resultMap);
    }
}
