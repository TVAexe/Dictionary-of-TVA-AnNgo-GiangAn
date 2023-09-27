import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Dictionary {
    private List<Word> dictionary = new ArrayList<Word>();

    public void addWord(String word, String pronoun, List<String> meaning) {
        dictionary.add(new Word(word, pronoun, meaning));
    }

    // Lấy giữ liệu từ đường dẫn đến file 's' , chuẩn hóa gán vào một đối tượng
    // Word, rồi add vào dictionary
    public void addWordFromFile(String s) {
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String w = s0.trim();
                String pr = s0.trim();
                List<String> mean = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    line = line.trim(); // Loại bỏ khoảng trắng ở đầu và cuối dòng
                    if (line.startsWith("- ")) {
                        line = line.substring(2); // Loại bỏ dấu "- " ở đầu
                    }
                    mean.add(line);
                }

                addWord(w, pr, mean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void printTest() {
        for (Word i : dictionary) {
            System.out.println(i.getWordTarget());
            System.out.println(i.getWordPronunciation());
            List<String> test = i.getWordExplain();
            for (String i0 : test) {
                System.out.println(i0);
            }
        }
    }

    public static void main(String[] args) {
        Dictionary entry = new Dictionary();
        entry.addWordFromFile("Vocabulary.txt");
        entry.printTest();

    }
}
