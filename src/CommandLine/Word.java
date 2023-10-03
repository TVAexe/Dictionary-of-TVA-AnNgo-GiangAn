package CommandLine;

/**
 * The Word class represents a word with its spelling and explanation.
 * Each Word object has two properties: wordTarget and wordExplain.
 * The wordTarget property is a string that holds the spelling of the word.
 * The wordExplain property is a string that holds the explanation of the word.
 * The Word class provides getter and setter methods for both wordTarget and wordExplain.
 * It also provides a method to normalize input strings.
 */
public class Word {
    /**
     * Attributes of Word class.
     * String wordTarget: the spelling of the word.
     * String wordExplain: the information of the word.
     */
    private String wordTarget;
    private String wordExplain;

    /**
     * Creating a Word object without parameters.
     */
    public Word() {
        this.wordTarget = "";
        this.wordPronunciation = "";
        this.wordExplain = new ArrayList<>();
    }

    /**
     * Creating a Word object with 3 parameters.
     * @param wordTarget
     * @param wordPronunciation
     * @param wordExplain
     */
    public Word(String wordTarget, String wordPronunciation, List<String> wordExplain) {
        this.wordTarget = wordTarget;
        this.wordPronunciation = wordPronunciation;
        this.wordExplain = new ArrayList<String>(wordExplain);
    }

    /**
     * Return the spelling of the word.
     * @return
     */
    public String getWordTarget() {
        return this.wordTarget;
    }

    /**
     * Return the pronunciation of the word.
     * @return
     */
    public String getWordPronunciation() {
        return this.wordPronunciation;
    }

    /**
     * Return the list of meaning of the word.
     * @return
     */
    public List<String> getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Set the spelling of the word.
     * @param wordTarget
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    /**
     * Set the pronunciation of the word.
     * @param wordPronunciation
     */
    public void setWordPronunciation(String wordPronunciation) {
        this.wordPronunciation = wordPronunciation;
    }
    
    /**
     * Set the list of meaning of the word.
     * @param 
     */
    public void setWordExplain(List<String> wordExplain)
    {
        this.wordExplain = new ArrayList<String>(wordExplain);
    }

    
}
