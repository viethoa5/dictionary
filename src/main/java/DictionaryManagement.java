import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;

public class DictionaryManagement {
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        int numbers = scan.nextInt();
        Dictionary.size += numbers;
        scan.nextLine();
        for (int i = 0; i < numbers; i++) {
            String define = scan.nextLine();
            String meaning = scan.nextLine();
            Word words    = new Word(define,meaning);
            Dictionary.wordArrays.add(words);
        }
    }
    public void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("main/dictionaries.txt"), "UTF-8");

        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String s = scanner.nextLine();
                String[] word = s.split("\\t", 2);
                x.setWord_target(word[0]);
                x.setWord_expland(word[1]);
                Dictionary.wordArrays.add(x);
            }
        }
    }

    public boolean dictionaryLookup(String tar) {
        Word a = (Word)Dictionary.wordArrays.stream().filter((w) -> {
            return tar.equals(w.getWord_target());
        }).findFirst().orElse(null);
        return a != null;
    }
}
