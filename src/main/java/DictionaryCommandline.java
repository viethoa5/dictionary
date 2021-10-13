import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement manage = new DictionaryManagement();
    public void showAllWords() {
        System.out.println("No   | English     | Vietnamese");
        for(int i = 0; i < Dictionary.size; i++) {
                System.out.print(i+1);
                System.out.print("   | " + StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_target()) +"       | ");
                System.out.print(StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_expland()));
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
