package CommandLine;

import java.util.ArrayList;
import java.util.List;

/**
 * The Word class represents a word with its spelling, pronunciation and explanation.
 * Each Word object has three properties: wordTarget, wordPronunciation and wordExplain.
 * The wordTarget property is a string that holds the spelling of the word.
 * The wordPronunciation property is a string that holds the pronunciation of the word.
 * The wordExplain property is a list of strings that holds the explanations of the word.
 * The Word class provides getter and setter methods for all three properties.
 * It also provides a method to normalize input strings.
 */
public class Word {
    /**
     * Attributes of Word class.
     * String wordTarget: the spelling of the word.
     * String wordPronunciation: the pronunciation of the word.
     * List<String>wordExplain: some meanings of the word.
     */
    private String wordTarget;
    private String wordPronunciation;
    private List<String> wordExplain;

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
        this.wordPronunciation = "";
        this.wordExplain = new ArrayList<>();
    }

    /**
     * Creating a Word object with 3 parameters and standardize.
     * @param wordTarget The spelling of the word.
     * @param wordPronunciation The pronunciation of the word.
     * @param wordExplain The explanations of the word.
     */
    public Word(String wordTarget, String wordPronunciation, List<String> wordExplain) {
        this.wordTarget = normalize(wordTarget);
        this.wordPronunciation = normalize(wordPronunciation);
        this.wordExplain = new ArrayList<String>(wordExplain);
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
     * @return The pronunciation of the word.
     */
    public String getWordPronunciation() {
        return this.wordPronunciation;
    }

    /**
     * Return the list of meanings of the word.
     * @return The explanations of the word.
     */
    public List<String> getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Set the spelling of the word and standardize it.
     * @param wordTarget The new spelling of the word.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = normalize(wordTarget);
    }
    
    /**
     * Set the pronunciation of the word and standardize it.
     * @param wordPronunciation The new pronunciation of the word.
     */
    public void setWordPronunciation(String wordPronunciation) {
        this.wordPronunciation = normalize(wordPronunciation);
    }
    
    /**
     * Set the list of meanings of the word and standardize them.
     * @param 
     */
    public void setWordExplain(List<String>wordExplain) {
        this.wordExplain = new ArrayList<String>(wordExplain);
    }

    /**
     * Get the information of the word.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(wordTarget).append("\n");
        sb.append(wordPronunciation).append("\n");
        for (String meaning : wordExplain) {
            sb.append(meaning).append("\n");
        }
        return sb.toString();
    }
}