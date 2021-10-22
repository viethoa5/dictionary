import com.sun.speech.freetts.VoiceManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Dictionary {
    public  ArrayList<String> wordtarget = new ArrayList<>();
    public  ArrayList<String> wordmeaning = new ArrayList<>();
    public void insertFromFile() throws IOException {
        Scanner scanner = new Scanner(Paths.get("D:\\dictionary\\src\\dictionaries.txt"), StandardCharsets.UTF_8);

        while (scanner.hasNext()) {
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] word = s.split("\\t", 2);
                wordtarget.add(word[0]);
                wordmeaning.add(word[1]);
            }
        }
    }

    public List<String> WordSearch(String Word_target, List<String> lists) {
        List<String> find_wordlists = Arrays.asList(Word_target.trim().split(" "));
        return lists.stream().filter(input -> {
            return find_wordlists.stream().allMatch(word ->
                    input.toLowerCase().contains(word.toLowerCase()));
        }).collect(Collectors.toList());
    }

    public int posittion_Word(ArrayList<String> lists, String word) {
        int post = -1;
        for(int i = 0; i <= lists.size(); i++) {
            if(lists.get(i).equals(word)) {
                post = i;
                break;
            }
        }
        return post;
    }

    public void editword(ArrayList<String> lists, int postition,String s) {
        lists.remove(postition);
        lists.add(postition,s);
    }

    public void listen(String word) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
        syntheticVoice.allocate();
        syntheticVoice.speak(word);
        syntheticVoice.deallocate();
    }
}
