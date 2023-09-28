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
     * Return all the data in the dictionary.
     */
    public List<Word> getDictionary() {
        return this.dictionary;
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
        this.dictionary.add(word);
    }

    /**
     * Add new words from file path if non-exist
     * @param path 
     */
    public void addWordFromFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String spell = s0.trim();
                String pronunciation = reader.readLine().trim();
                List<String> meaning = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    //Delete space at the beginnning and the end of the string.
                    line = line.trim(); 

                    //Delete the character '-'.
                    if (line.startsWith("- ")) {
                        line = line.substring(2); 
                    }
                    meaning.add(line);
                }
                addWord(spell, pronunciation, meaning);
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
    boolean searchWord(Word other) {
        for (Word word : this.dictionary) {
            if (word.getWordTarget().equals(other.getWordTarget())) {
                return true;
            }
        }
        return false;
    }
}
