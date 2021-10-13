import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement  manage = new DictionaryManagement();

    public void showAllWords() {
        System.out.println("\t\t\t\tNo\t|" + "English" + "\t\t\t  | VietNamese ");
        for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
            System.out.print("\t\t\t\t" + (i + 1) + "\t| ");
            System.out.printf("%-20s| ", StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_target()));
            System.out.print(StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_explain()));
            System.out.println();
        }
    }

    public void dictionaryBasic() {
        manage.insertFromCommandline();
        showAllWords();
    }
    public void dictionaryAdvanced() throws IOException {
        this.manage.insertFromFile();
        this.showAllWords();
        Scanner sc = new Scanner(System.in);
        String tar = sc.next().toLowerCase();
        this.manage.dictionaryLookup(tar);
    }
}

