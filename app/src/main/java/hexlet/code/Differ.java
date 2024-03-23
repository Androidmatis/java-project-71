package hexlet.code;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Differ {

    public static String generate(File file1, File  file2, String format) throws IOException {
        Map<String, Object> file1Contents;
        Map<String, Object> file2Contents;
        ObjectMapper mapperJSON = new ObjectMapper();
        ObjectMapper mapperYAML = new YAMLMapper();
        try {
            file1Contents = mapperJSON.readValue(file1, Map.class);
            file2Contents = mapperJSON.readValue(file2, Map.class);
        } catch (JsonParseException ex) {
            file1Contents = mapperYAML.readValue(file1, Map.class);
            file2Contents = mapperYAML.readValue(file2, Map.class);
        }
        Map<String, List<Object>> resultMap = Parser.filesToMapDiff(file1Contents, file2Contents);
        String result = switch (format) {
            case "stylish" -> Formatter.stylishFormat(resultMap);
            case "plain" -> Formatter.plainFormat(resultMap);
            default -> new IOException(format + " - is an incorrect format.").toString();
        };
        return result;
    }
}
