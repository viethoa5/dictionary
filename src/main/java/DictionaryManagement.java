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
}
