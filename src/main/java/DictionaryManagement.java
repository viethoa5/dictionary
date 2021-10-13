import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;

public class DictionaryManagement {
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Amounts of Words ?");
        int numbers = scan.nextInt();
        Dictionary.size += numbers;
        scan.nextLine();
        for (int i = 0; i < numbers; i++) {
            System.out.println("Word Target :");
            String define = scan.nextLine();
            System.out.println("Word explain :");
            String meaning = scan.nextLine();
            Word words = new Word(define, meaning);
            Dictionary.wordArrays.add(words);
        }
    }
    public void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("src/main/dictionaries.txt"), "UTF-8");

        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                Word x = new Word();
                String s = scanner.nextLine();
                String[] word = s.split("\\t", 2);
                x.setWord_target(word[0]);
                x.setWord_explain(word[1]);
                Dictionary.wordArrays.add(x);
            }
        }
    }

    public boolean dictionaryLookup(String tar) {
        Word a = (Word)Dictionary.wordArrays.stream().filter((w) -> {
            return tar.equals(w.getWord_target());
        }).findFirst().orElse(null);
        System.out.println(a.getWord_target() + ":  " + a.getWord_explain());
        return a != null;
    }
}
