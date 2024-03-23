package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private File file1;
    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private File file2;
    @Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;
    @Override
    public Integer call() throws Exception {
        switch (format) {
            case "stylish":
                System.out.println(Formaters.stylishFormat(Differ.generate(file1, file2)));
                break;
            default:
                System.out.println(format + " - is an incorrect format.");
        }
        return 0;
    }
    public static void main(String... args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
