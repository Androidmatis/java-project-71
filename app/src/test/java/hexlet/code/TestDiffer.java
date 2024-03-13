package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {
    File file1;
    File file2;
    @BeforeEach
    public void beforeEach() throws IOException {
        file1 = new File("src/test/resources/testFile1.json");
        file2 = new File("src/test/resources/testFile2.json");
    }

    @Test
    public void testDifferGenerate() throws IOException {
        String expected = "{\n" +
                "\t- follow: false\n" +
                "\t  host: hexlet.io\n" +
                "\t- proxy: 123.234.53.22\n" +
                "\t- timeout: 50\n" +
                "\t+ timeout: 20\n" +
                "\t+ verbose: true\n" +
                "}";
        var actual = Differ.generate(file1, file2);
        assertEquals(expected, actual);
    }
}
