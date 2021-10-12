import java.util.Scanner;
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
}
