import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagement manage = new DictionaryManagement();

    public void showAllWords() {
        System.out.println("\t\t\t\tNo\t|" + "English" + "\t\t\t  | VietNamese ");
        for(int i = 0; i < Dictionary.wordArrays.size(); i++) {
            System.out.print("\t\t\t\t"+(i+1)+"\t| ");
            System.out.printf("%-20s| ", StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_target()));
            System.out.print( StringUtils.capitalize(Dictionary.wordArrays.get(i).getWord_explain()));
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
            String line = new String();
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
        Scanner sc = new Scanner(System.in);
        if(Dictionary.wordArrays.size() == 0) {
            this.manage.insertFromFile();
        }
        System.out.println(" \t\t\t\tOptions: \n "
                + "\t\t\t\t1:Show all Word\n"
                + "\t\t\t\t2:Lookup\n"
                + "\t\t\t\t3:Quick Lookup\n"
                + "\t\t\t\t4:Delete Word\n"
                + "\t\t\t\t5:Export to file\n"
                + "\t\t\t\t6:Add word\n"
                + "\t\t\t\t7:Rewrite meaning\n"
                + "\t\t\t\t8:Exit");
        System.out.println("Your options ?");
        int option = sc.nextInt();
        sc.nextLine();
        switch(option){
            case 1:
                showAllWords();
                dictionaryAdvanced();
                break;
            case 2:
                String tar = sc.next().toLowerCase();
                this.manage.dictionaryLookup(tar);
                dictionaryAdvanced();
                break;
            case 3 :
                dictionarySearcher();
                dictionaryAdvanced();
                break;
            case 4:
                this.manage.deleteWord();
                dictionaryAdvanced();
                break;
            case 5 :
                this.manage.dictionaryExportToFile();
                dictionaryAdvanced();
                break;
            case 6 :
                this.manage.addWord();
                dictionaryAdvanced();
                break;
            case 7  :
                this.manage.editWord();
                dictionaryAdvanced();
                break;
            case 8:
                System.out.println("Have a nice day !Bye");
                break;
            default:
                System.out.println("Oops option isn't available . Choose again : ");
                dictionaryAdvanced();
                break;
        }
    }
}
