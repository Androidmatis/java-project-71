package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {

    @Test
    public void testDifferJsonGenerate() throws Exception {
        String format = "stylish";
        String expected = "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            +  "}";
        var actual = Differ.generate("src/test/resources/testFile1.json",
                "src/test/resources/testFile2.json",
                format);
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferJsonWithNestedStructuresGenerate() throws Exception {
        String format1 = "stylish";
        String format2 = "plain";
        String expected1 = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none\n"
                + "}";
        String expected2 = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        var actual1 = Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file2.json",
                format1);
        var actual2 = Differ.generate("src/test/resources/file1.json",
                "src/test/resources/file2.json",
                format2);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }

    @Test
    public void testDifferYamlGenerate() throws Exception {
        String format = "stylish";
        String expected = "{\n"
                + "  - follow: false\n"
                + "    host: hexlet.io\n"
                + "  - proxy: 123.234.53.22\n"
                + "  - timeout: 50\n"
                + "  + timeout: 20\n"
                + "  + verbose: true\n"
                +  "}";
        var actual = Differ.generate("src/test/resources/testFile1.yml",
                "src/test/resources/testFile2.yml",
                format);
        assertEquals(expected, actual);
    }

    @Test
    public void testDifferGenerateFixtures() throws Exception {
        String file1JsonPath = "src/test/resources/fixtures/file1.json";
        String file2JsonPath = "src/test/resources/fixtures/file2.json";
        String file1YmlPath = "src/test/resources/fixtures/file1.yml";
        String file2YmlPath = "src/test/resources/fixtures/file2.yml";
        String resultPlainPath = "src/test/resources/fixtures/result_plain.txt";
        String resultStylishPath = "src/test/resources/fixtures/result_stylish.txt";
        String format1 = "stylish";
        String format2 = "plain";
        String expected1 = Files.readString(Path.of(resultStylishPath));
        expected1 = expected1.substring(0, expected1.length() - 1);
        String expected2 = Files.readString(Path.of(resultPlainPath));
        expected2 = expected2.substring(0, expected2.length() - 1);
        var actual1 = Differ.generate(file1JsonPath, file2JsonPath, format1);
        var actual2 = Differ.generate(file1JsonPath, file2JsonPath, format2);
        var actual3 = Differ.generate(file1YmlPath, file2YmlPath, format1);
        var actual4 = Differ.generate(file1YmlPath, file2YmlPath, format2);
        assertEquals(expected1, actual1);
        assertEquals(expected1, actual3);
        assertEquals(expected2, actual2);
        assertEquals(expected2, actual4);
    }
}
