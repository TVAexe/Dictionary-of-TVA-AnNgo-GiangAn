package base;

import trie.Trie;
import java.util.List;

public class Dictionary {
    /**
     * The attribute of the Dictionary class.
     */
    private Trie trie;

    /**
     * Initialize Dictionary class without parameters.
     */
    public Dictionary() {
        this.trie = new Trie();
    }

    /**
     * Insert a new word.
     */
    public void insert(String word) {
        trie.insert(word);
    }

    /**
     * Delete a word from the Trie.
     */
    public void delete(String word) {
        trie.delete(word);
    }

    /**
     * Edit word.
     */
    public void edit(String oldWord, String newWord) {
        trie.edit(oldWord, newWord);
    }

    /**
     * Get all words starting with prefix.
     */
    public List<String> getWordsStartingWith(String prefix) {
        return trie.getWordsStartingWith(prefix);
    }

    /**
     * Search for a word in the Trie.
     */
    public boolean search(String word) {
        return trie.search(word);
    }
}
