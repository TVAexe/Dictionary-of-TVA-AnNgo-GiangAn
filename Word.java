import java.util.ArrayList;
import java.util.List;

public class Word {
    private String wordTarget;
    private List<String> wordExplain = new ArrayList<>();
    private String wordType;
    public Word(String eng,List<String> vie)
    {
        wordTarget = eng;
        wordExplain = vie;
    }

    public String getWordTaget()
    {
        return wordTarget;
    }
        public List<String> getWordExplain()
    {
        return wordExplain;
    }
    public void setWordTaget(String a)
    {
        wordTarget = a;
    }
    public void setWordExplain(String a)
    {
        wordExplain.add(a);
    }
}
