package hexlet.code;

import java.io.File;
import java.util.List;
import java.util.Map;


public class Differ {
    public static String generate(String filepath1, String  filepath2) throws Exception {
        return generate(filepath1, filepath2, "stylish");
    }

    public static String generate(String filepath1, String  filepath2, String format) throws Exception {
        String file1Format = definitionFileFormat(filepath1);
        String file2Format = definitionFileFormat(filepath2);
        if (!file1Format.equals(file2Format)) {
            throw new Exception("Files have different formats !");
        }
        File file1 = definitionFile(filepath1);
        File file2 = definitionFile(filepath2);
        Map<String, List<Object>> resultMap = Parser.filesToMapDiff(file1, file2, file1Format);
        return Formatter.switchFormater(resultMap, format);
    }

    public static String definitionFileFormat(String filepath) throws Exception {
        var splitPath = filepath.split("\\.");
        if (!(splitPath.length > 1)) {
            throw new Exception(filepath + " - file format couldn't be defined");
        }
        return splitPath[splitPath.length - 1];
    }

    public static File definitionFile(String filepath) throws Exception {
        try {
            return new File(filepath);
        } catch (Exception ex) {
            throw new Exception("File '" + filepath + "' does not exist");
        }
    }
}
