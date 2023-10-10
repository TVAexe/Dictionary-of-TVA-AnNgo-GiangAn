package CommandLine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandLine {
    /**
     * Hàm thực hiệnn show tất cả các từ có trong từ điển
     * 
     * @param dc là đối tượng Dictionary
     */
    public void showAllWords(Dictionary dc) {
        List<Word> result = dc.getDictionary();
        for (Word i : result) {
            String w = i.getWordTarget();
            String a = i.getWordPronunciation();
            List<String> m = i.getWordExplain();
            System.out.println("Từ ngữ: " + w);
            System.out.println("Phát âm: " + a);
            System.out.println("Ý nghĩa:");
            for (String j : m) {
                System.out.println("- " + j);
            }
        }
    }

    public void insertFromCommandline()
    {
        System.out.print("Nhập số lượng từ muốn thêm: ");
        Scanner sc = new Scanner(System.in);
        int n= sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Nhập từ: ");
            String word = sc.next();
            System.out.print("Nhập phát âm: ");
            String pronunciation = sc.next();
            System.out.print("Nhập nghĩa: ");
            List<String> meaning = new ArrayList<>();
            String line;
            while ((line = sc.nextLine())!= null &&!line.trim().isEmpty()) {
                line = line.trim(); // Loại bỏ khoảng trắng ở đầu và cuối dòng
                if (line.startsWith("- ")) {
                    line = line.substring(2); // Loại bỏ dấu "- " ở đầu
                }
                meaning.add(line);
            }
            Dictionary dc = new Dictionary();
            dc.addWord(word, pronunciation, meaning);
        }
    }   

    public void delete(String word) {
        Dictionary dc = new Dictionary();
        List<Word> result = dc.getDictionary();
        for (Word i : result) {
            if (i.getWordTarget().equals(word)) {
                result.remove(i);
            }
        }
    }

    public void update(String word, String pronunciation, List<String> meaning) {
        Dictionary dc = new Dictionary();
        List<Word> result = dc.getDictionary();
        for (Word i : result) {
            if (i.getWordTarget().equals(word)) {
                i.setWordPronunciation(pronunciation);
                i.setWordExplain(meaning);
            }
        }
    }

    

}
