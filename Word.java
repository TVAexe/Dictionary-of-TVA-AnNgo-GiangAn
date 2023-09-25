import java.util.ArrayList;
import java.util.List;

public class Word {
    private String wordTarget;
    private List<String> wordExplain = new ArrayList<>();
    private String wordType;
    public Word(String eng,String type,List<String> vie)
    {
        wordTarget = eng;
        wordExplain = vie;
        wordType = type;
    }

    public String getWordTaget()
    {
        return wordTarget;
    }
        public List<String> getWordExplain()
    {
        return wordExplain;
    }
        public String getWordType()
    {
        return wordType;
    }

    public void setWordTaget(String a)
    {
        wordTarget = a;
    }
    public void setWordType(String a)
    {
        wordType = a;
    }
    public void setWordExplain(String a)
    {
        wordExplain.add(a);
    }
}
