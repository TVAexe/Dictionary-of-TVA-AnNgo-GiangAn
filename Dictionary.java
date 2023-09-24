import java.util.*;

public class Dictionary {
    private List<Word> dictionary = new ArrayList<Word>();
    public void push(String eng,String vie)
    {
        dictionary.add(new Word(eng,vie));
    }
    public void all()
    {
        for (Word i:dictionary)
        {
            System.out.printf(null, null);
        }
    }
    /* fhsdj*/
}
