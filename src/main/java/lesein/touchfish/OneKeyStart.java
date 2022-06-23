package lesein.touchfish;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author WangJie
 * @date 2022/6/17
 */
public class OneKeyStart {
    public static void main(String[] args) throws IOException {
        String fileName = "";
        Path path = Paths.get(fileName);
        List<String> allLines = Files.readAllLines(path, StandardCharsets.UTF_8);
        Runtime runtime = Runtime.getRuntime();
        for (String line : allLines) {
            String[] split = line.split("/");
            String[] split1 = split[split.length - 1].split("\\.");
            System.out.println("正在为你打开"+split1[0]+"...");
            runtime.exec(line);
        }
    }
}
