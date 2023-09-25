import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Dictionary {
    private List<Word> dictionary = new ArrayList<Word>();
    public void addWord(String eng,String type,List<String> vie)
    {
        dictionary.add(new Word(eng,type,vie));
    }
    // Lấy giữ liệu từ đường dẫn đến file 's' , chuẩn hóa gán vào một đối tượng Word, rồi add vào dictionary
    public void addWordFromFile(String s)
    {
        try(BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String line;
            String wordTaget="";
            List<String> wordExplain = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                if(line.startsWith("@")) {
                    wordTaget = line.substring(1).trim();
                }
                else if(line.startsWith("-")) {
                    wordExplain.add(line.substring(1).trim());
                }
                else if(line.isEmpty() && wordTaget != null && !wordExplain.isEmpty())
                {
                    Word word = new Word(wordTaget ,wordExplain);
                    dictionary.add(word);
                    wordTaget = "";
                    wordExplain.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
