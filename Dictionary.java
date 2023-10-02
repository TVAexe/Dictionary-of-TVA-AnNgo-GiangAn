import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Dictionary {
    private List<Word> dictionary = new ArrayList<Word>();
    /**
     * Hàm return list bộ từ vưng bao gồm từ,nghĩa và phát âm.
     */
    public List<Word> getDictionary() {
        return dictionary;
    }
    /**
     * 
     * @param word
     * @param pronoun
     * @param meaning
     * Hàm thực hiện thêm 1 từ vào trong 1 từ điển
     */
    public void addWord(String word, String pronoun, List<String> meaning) {
        dictionary.add(new Word(word, pronoun, meaning));
    }
    /**
     * Hàm nhận vào xâu s là đường dẫn đến file chứa data rồi thực hiện đọc chúng.
     * @param s
     */
    public void addWordFromFile(String s) {
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String w = s0.trim();
                String pr = reader.readLine().trim();
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

    

}
