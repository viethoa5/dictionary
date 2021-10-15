import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        int numbers = scan.nextInt();
        scan.nextLine();
        for (int i = 0; i < numbers; i++) {
            String define = scan.nextLine();
            String meaning = scan.nextLine();
            Word words = new Word(define, meaning);
            Dictionary.wordArrays.add(words);
        }
    }



    public void dictionaryLookup() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\t\t\t\tNhập từ hoàn chỉnh mà bạn cần tìm nghĩa");
        String  searchword = scan.nextLine();
                searchword = searchword.toLowerCase();
        for(int i = 0; i < Dictionary.wordArrays.size(); i++) {
            if(Dictionary.wordArrays.get(i).getWord_target().equals(searchword)) {
                System.out.println("\t\t\t\tResult: " + Dictionary.wordArrays.get(i).getWord_explain() + "\n");
                return;
            }
        }
        System.out.println("\t\t\t\tYour word haven't been added in our dictionary yet");
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
    public void dictionaryExportToFile() {
        try {
            FileWriter writer = new FileWriter("dictionaries.txt");
            for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
                writer.write(Dictionary.wordArrays.get(i).getWord_target() + " " + Dictionary.wordArrays.get(i).getWord_explain() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần thêm vào (tiếng Anh): ");
        String new_target = sc.nextLine();
               new_target = new_target.toLowerCase();
        System.out.print("Nhập nghĩa từ mới: ");
        String new_explain = sc.nextLine();
               new_explain = new_explain.toLowerCase();
        Word newWord = new Word(new_target, new_explain);
        Dictionary.wordArrays.add(newWord);
    }

    public void deleteWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần xóa (tiếng Anh): ");
        String wordDelete = sc.next();
               wordDelete = wordDelete.toLowerCase();
        for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
            if (Dictionary.wordArrays.get(i).getWord_target().equals(wordDelete)) {
                Dictionary.wordArrays.remove(i);
                return;
            }
        }
        System.out.println("\t\t\t\tYour word haven't been add in our dictionary yet");
    }

    public void editWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa (tiếng Anh): ");
        String wordEdit = sc.nextLine();
               wordEdit = wordEdit.toLowerCase();
        for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
            if (Dictionary.wordArrays.get(i).getWord_target().equals(wordEdit)) {
                System.out.print("Nhập nghĩa mong muốn: ");
                String new_explain = sc.nextLine();
                Dictionary.wordArrays.get(i).setWord_explain(new_explain);
                return;
            }
        }
        System.out.println("\t\t\t\tYour word haven't been added in our dictionary yet");
    }
}
