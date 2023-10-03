package CommandLine;

import java.util.ArrayList;
import java.util.List;

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
     * Normalize input before initializing.
     * @param input The string to be normalized.
     * @return The normalized string.
     */
    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim().toLowerCase();
    }

    /**
     * Creating a Word object without parameters.
     */
    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
    }

    /**
     * Creating a Word object with 2 parameters and standardize.
     * @param wordTarget The spelling of the word.
     * @param wordExplain The explanation of the word.
     */
    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = normalize(wordTarget);
        this.wordExplain = normalize(wordExplain);
    }

    /**
     * Return the spelling of the word.
     * @return The spelling of the word.
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
