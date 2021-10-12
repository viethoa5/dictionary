import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
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
}
