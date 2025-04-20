import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class WslInternalPathTester {

    public static void main(String[] args) {
        String linuxPathString = "/home/imenov/IdeaProjects/lab_11/JP_5_2/Highscores.txt";

        Path filePath = Paths.get(linuxPathString);

        System.out.println("Созданный объект Path (внутри WSL): " + filePath);

        boolean exists = Files.exists(filePath);
        System.out.println("Файл существует: " + exists);
    }
}