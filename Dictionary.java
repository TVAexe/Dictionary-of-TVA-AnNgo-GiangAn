import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Dictionary {
    /**
     * Attribute of the Dictionary class.
     * List<Word> dictionary: a system of vocabulary information.
     */
    private List<Word> dictionary;

    /**
     * Creating a Dictionary object without parameters.
     */
    public Dictionary() {
        this.dictionary = new ArrayList<Word>();
    }

    /**
     * Creating a Dictionary object with parameters.
     * @param dictionary
     */
    public Dictionary(List<Word> dictionary) {
        this.dictionary = new ArrayList<Word>(dictionary);
    }

    /**
     * Add new words from console if non-exist.
     * @param wordTarget
     * @param wordPronunciation
     * @param wordMeaning
     */
    public void addWord(String wordTarget, String wordPronunciation, List<String> wordMeaning)
    {
        Word word = new Word(wordTarget, wordPronunciation, wordMeaning);
        dictionary.add(word);
    }

    /**
     * Add new words from file path if non-exist
     * @param s
     */
    public void addWordFromFile(String path)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
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

    /**
     * Standardize data before processing.
     * @param word
     */
    public void standardizeWord(Word word) {

    }

    /**
     * Search whether a word has exist in the list.
     * @param word
     * @return
     */
    boolean searchWord(Word word) {
        return true;
    }
}
