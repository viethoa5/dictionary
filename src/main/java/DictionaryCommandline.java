import java.util.Scanner;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement manage = new DictionaryManagement();

    public void showAllWords() {
        System.out.println("\t\t\t\tNo\t|" + "English" + "\t\t\t  | VietNamese ");
        for(int i = 0; i < Dictionary.size; i++) {
            System.out.print("\t\t\t\t"+(i+1)+"\t| ");
            System.out.printf("%-20s| ", StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_target()));
            System.out.print( StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_expland()));
            System.out.println();
        }
    }

    public void dictionaryBasic() {
        manage.insertFromCommandline();
        showAllWords();
    }

    public void dictionarySearcher() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ cần tìm: ");
        String wordSearch = sc.next();
        wordSearch.toLowerCase();
        try {
            File inFile = new File("dictionaries.txt");
            FileReader fileReader = new FileReader(inFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(wordSearch)) {
                    System.out.println(line);
                }
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dictionaryAdvanced() throws IOException {
        this.manage.insertFromFile();
        this.showAllWords();
        Scanner sc = new Scanner(System.in);
        String tar = sc.next().toLowerCase();
        this.manage.dictionaryLookup(tar);
        this.manage.addWord();
        this.manage.deleteWord();
        this.manage.editWord();
        this.manage.dictionaryExportToFile();
        dictionarySearcher();
    }
}
