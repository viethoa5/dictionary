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



    public boolean dictionaryLookup(String tar) {
        Word a = (Word) Dictionary.wordArrays.stream().filter((w) -> {
            return tar.equals(w.getWord_target());
        }).findFirst().orElse(null);
        System.out.println(a.getWord_target() + ": " + a.getWord_explain());
        return a != null;
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
        System.out.print("Nhập nghĩa từ mới: ");
        String new_explain = sc.nextLine();
        Word newWord = new Word(new_target, new_explain);
        Dictionary.wordArrays.add(newWord);
    }

    public void deleteWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần xóa (tiếng Anh): ");
        String wordDelete = sc.next();
        for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
            if (Dictionary.wordArrays.get(i).getWord_target().equals(wordDelete)) {
                Dictionary.wordArrays.remove(i);
                break;
            }
        }
    }

    public void editWord() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa (tiếng Anh): ");
        String wordEdit = sc.nextLine();
        for (int i = 0; i < Dictionary.wordArrays.size(); i++) {
            if (Dictionary.wordArrays.get(i).getWord_target().equals(wordEdit)) {
                System.out.print("Nhập nghĩa mong muốn: ");
                String new_explain = sc.nextLine();
                Dictionary.wordArrays.get(i).setWord_explain(new_explain);
                break;
            }
        }
    }
}
