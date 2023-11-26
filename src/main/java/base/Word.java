package base;

public class Word {
    /**
     * Attributes of Word class.
     * String wordTarget: the spelling of the word.
     * String wordExplain: the detailed information of the word.
     * String wordDescription: the short description of the word.
     */
    private String wordTarget;
    private String wordExplain;
    private String wordDescription;

    /**
     * Creating a Word object without parameters.
     */
    public Word() {
        this.wordTarget = "";
        this.wordExplain = "";
        this.wordDescription = "";
    }

    /**
     * Creating a Word object with 3 parameters and standardize.
     */
    public Word(String wordTarget, String wordExplain, String wordDescription) {
        this.wordTarget = normalize(wordTarget);
        this.wordExplain = normalize(wordExplain);
        this.wordDescription = normalize(wordDescription);
    }

    /**
     * Normalize input before initializing.
     */
    private String normalize(String input) {
        if (input == null) {
            return "";
        }
        return input.trim();
    }

    /**
     * Return the spelling of the word.
     */
    public String getWordTarget() {
        return this.wordTarget;
    }

    /**
     * Return the detailed information of the word.
     */
    public String getWordExplain() {
        return this.wordExplain;
    }

    /**
     * Return the description of the word.
     */
    public String getWordDescription() {
        return this.wordDescription;
    }

    /**
     * Set the spelling of the word.
     */
    public void setWordTarget(String wordTarget) {
        this.wordTarget = normalize(wordTarget);
    }

    /**
     * Set the detailed information of the word.
     */
    public void setWordExplain(String wordExplain) {
        this.wordExplain = normalize(wordExplain);
    }

    /**
     * Set the description of the word.
     */
    public void setWordDescription(String wordDescription) {
        this.wordDescription = normalize(wordDescription);
    }
}
