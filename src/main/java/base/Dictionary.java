package base;

import java.util.Map;
import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import trie.Trie;

/**
 * The Dictionary class represents a dictionary with a list of words.
 * Each Dictionary object has one property: dictionary.
 * The dictionary property is a list of Word objects.
 * The Dictionary class provides getter and setter methods for the dictionary
 * property.
 * It also provides methods to process words to the dictionary.
 */
public class Dictionary {
    /**
     * Attributes of Dictionary class.
     */
    private static Dictionary a;

    public static synchronized Dictionary getMyDictionary(){
        if(a == null){
            a = new Dictionary();
        }
        return a;
    }

    private TreeMap<String, Word> dictionary = new TreeMap<>();
    private Trie trie = new Trie();

    public TreeMap<String, Word> getDictionary() {
        return this.dictionary;
    }

    /**
     * Load data from a file into the dictionary.
     */
    public void insertFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/Vocabulary.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.trim();
                String pronoun = reader.readLine().trim();
                List<String> meaning = new ArrayList<>();
                String meaningLine;

                while ((meaningLine = reader.readLine()) != null && !meaningLine.trim().isEmpty()) {
                    if (meaningLine.startsWith("- ")) {
                        meaningLine = meaningLine.substring(2);
                    }
                    meaning.add(meaningLine);
                }
                addWordForDictionary(word, pronoun, meaning);
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: src/main/resources/data/Vocabulary.txt");
        }
    }

    /**
     * Search for a word in the dictionary by its spelling.
     */
    public  boolean searchWord(String wordTarget) {
        return this.dictionary.containsKey(wordTarget.toLowerCase());
    }

    /**
     * Add a word to the dictionary.
     * Return true if the word was added successfully, false otherwise.
     */
    public boolean addWordForDictionary(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            Word newWord = new Word(word, pronoun, meaning);
            this.dictionary.put(word, newWord);
            this.trie.insert(word);
            return true;
        }
        return false;
    }

    /**
     * Remove a word from the dictionary.
     * Return true if the word was removed successfully, false otherwise.
     */
    public boolean removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            this.dictionary.remove(wordTarget.toLowerCase());
            this.trie.delete(wordTarget);
            return true;
        }
        return false;
    }

    /**
     * Update a word in the dictionary.
     * Return true if the word was updated successfully, false otherwise.
     */
    public boolean updateWord(String oldWord, String newPronoun , List<String> newMeaning) {
        if (searchWord(oldWord) && !oldWord.trim().isEmpty()) {
            if(!newPronoun.trim().isEmpty()){
                dictionary.get(oldWord).setWordPronunciation(newPronoun);
            }
            if (!newMeaning.isEmpty()){
                dictionary.get(oldWord).setWordExplain(newMeaning);
            }
            return true;
        }
        return false;
    }


    /**
     * Get a list of words that start with a given string.
     */
    public List<String> getWordsStartingWith(String prefix) {
        return this.trie.getWordsStartingWith(prefix);
    }

    /**
     * Get a word from the dictionary.
     */
    public Word getWord(String wordTarget) {
        return dictionary.get(wordTarget.toLowerCase());
    }

    /**
     * Export the dictionary to a file.
     */
    public void dictionaryExportToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Word> entry : dictionary.entrySet()) {
                Word word = entry.getValue();
                writer.write(word.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    /**
     * Overwrite to the old file
     */
    public void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data/Vocabulary.txt"))) {
            for (Map.Entry<String, Word> a : dictionary.entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error when exporting to the file. ");
        }
    }
}
