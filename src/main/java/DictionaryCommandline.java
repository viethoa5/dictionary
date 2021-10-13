import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
public class DictionaryCommandline {
    DictionaryManagement manage = new DictionaryManagement();
    public void showAllWords() {
        System.out.println("\t\t\t\tNo\t|" + "English" + "\t\t\t  | VietNamese ");
        for(int i = 0; i < Dictionary.size; i++) {
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
}
