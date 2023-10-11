package CommandLine;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * The Dictionary class represents a dictionary with a list of words.
 * Each Dictionary object has one property: dictionary.
 * The dictionary property is a list of Word objects.
 * The Dictionary class provides getter and setter methods for the dictionary property.
 * It also provides methods to process words to the dictionary.
 */
public class Dictionary {
    /**
     * Attributes of Dictionary class.
     * Map<String, Word> dictionary: the map from word targets to words in the dictionary.
     */
    private Map<String, Word> dictionary;
    
    /**
     * Creating a Dictionary object without parameters.
     */
    public Dictionary() {
        this.dictionary = new HashMap<>();
    }

    /**
     * Creating a Dictionary object with parameter.
     * @param words The map of words to initialize the dictionary with.
     */
    public Dictionary(Map<String, Word> words) {
        this.dictionary = new HashMap<>(words);
    }

    /**
     * Return the map of words in the dictionary.
     * @return The map of words in the dictionary.
     */
    public Map<String, Word> getDictionary() {
        return this.dictionary;
    }

    /**
     * Set the dictionary with a new map of words.
     * @param words The new map of words.
     */
    public void setDictionary(Map<String, Word> words) {
        this.dictionary = new HashMap<>(words);
    }

    /**
     * Return the size of the dictionary.
     * @return The size of the dictionary.
     */
    public int getSize() {
        return this.dictionary.size();
    }

    /**
     * Search for a word in the dictionary by its spelling.
     * @param wordTarget The spelling of the word to search for.
     * @return true if the word is found, false otherwise.
     */
    public boolean searchWord(String wordTarget) {
        return this.dictionary.containsKey(wordTarget);
    }
    
    public void addWordForDictionary(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            this.dictionary.put(word, new Word(word, pronoun, meaning));
            return;
        } else {
            System.out.println("The word " + word + " already exists in the dictionary.");
            return;
        }
    }    

    /**
     * Add a word to the dictionary with its spelling, pronunciation and meanings.
     * @param word The spelling of the word.
     * @param pronoun The pronunciation of the word.
     * @param meaning The meanings of the word.
     */
    public void addWordForUser(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            this.dictionary.put(word, new Word(word, pronoun, meaning));
            System.out.println("The word " + word + " has been added to the dictionary.");
            return;
        } else {
            System.out.println("The word " + word + " already exists in the dictionary.");
            return;
        }
    }

    /**
     * Remove a word from the dictionary by its spelling.
     * @param wordTarget The spelling of the word to remove.
     */
    public void removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            this.dictionary.remove(wordTarget);
            System.out.println("The word " + wordTarget + " has been removed from the dictionary.");
            return;
        } else {
            System.out.println("The word " + wordTarget + " does not exist in the dictionary.");
            return;
        }
    }

    /**
     * Return the explanation of a word in the dictionary by its spelling.
     * @param wordTarget The spelling of the word.
     * @return The explanation of the word if found, a message otherwise.
     */
    public String getWordExplain(String wordTarget) {
        if (searchWord(wordTarget)) {
            Word word = this.dictionary.get(wordTarget);
            return word.toString();
        } else {
            return "The word " + wordTarget + " does not exist in the dictionary.";
        }
    }

    /**
     * Edit the spelling of a word in the dictionary.
     * @param oldWordTarget The old spelling of the word.
     * @param newWordTarget The new spelling of the word.
     */
    public void editWordTarget(String oldWordTarget, String newWordTarget) {
        if (!searchWord(oldWordTarget)) {
            System.out.println("The word " + oldWordTarget + " does not exist in the dictionary.");
            return;
        }
        if(searchWord(newWordTarget)) {
            System.out.println("The new word has existed in the dictionary.");
            return;
        }
        Word word = dictionary.get(oldWordTarget);
        dictionary.remove(oldWordTarget);
        word.setWordTarget(newWordTarget);
        dictionary.put(newWordTarget, word);
        System.out.println("The spelling of the word " + oldWordTarget + " has been updated to " + newWordTarget + ".");
    }

    /**
     * Edit the explanation of a word in the dictionary by its spelling.
     * @param wordTarget The spelling of the word.
     * @param newWord The new Word object.
     */
    public void editWordExplain(String wordTarget, Word newWord) {
        if (searchWord(wordTarget)) {
            Word word = dictionary.get(wordTarget);
            word.setWordExplain(newWord.getWordExplain());
            System.out.println("The explanation of the word " + wordTarget + " has been updated.");
        } else {
            System.out.println("The word " + wordTarget + " does not exist in the dictionary.");
        }
    }
}