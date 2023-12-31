package base;

import trie.Trie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MyDictionary {

    private static MyDictionary a;

    public static synchronized MyDictionary getMyDictionary(){
        if(a == null){
            a = new MyDictionary();
        }
        return a;
    }

    private TreeMap<String, Word> myDictionary = new TreeMap<>();
    private Trie trie = new Trie();

    public TreeMap<String, Word> getDictionary() {
        return this.myDictionary;
    }

    public boolean searchWord(String wordTarget) {
        return this.myDictionary.containsKey(wordTarget.toLowerCase());
    }
    public boolean setFavorite(String word, String pronoun, List<String> meaning) {
        if (!searchWord(word)) {
            Word newWord = new Word(word, pronoun, meaning);
            this.myDictionary.put(word, newWord);
            this.trie.insert(word);
            return true;
        }
        return false;
    }

    public void insertFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data/FavoriteVocabulary.txt"))) {
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
                setFavorite(word, pronoun, meaning);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean removeWord(String wordTarget) {
        if (searchWord(wordTarget)) {
            this.myDictionary.remove(wordTarget.toLowerCase());
            this.trie.delete(wordTarget);
            return true;
        }
        return false;
    }

    public boolean updateWord(String oldWord, String newPronoun , List<String> newMeaning) {
        if (searchWord(oldWord) && !oldWord.trim().isEmpty()) {
            if(!newPronoun.trim().isEmpty()){
                myDictionary.get(oldWord).setWordPronunciation(newPronoun);
            }
            if (!newMeaning.isEmpty()){
                myDictionary.get(oldWord).setWordExplain(newMeaning);
            }
            return true;
        }
        return false;
    }

    public Word getWord(String wordTarget) {
        return myDictionary.get(wordTarget.toLowerCase());
    }

    public List<String> getWordsStartingWith(String prefix) {
        return this.trie.getWordsStartingWith(prefix);
    }

    public void dictionaryExportToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Word> entry : myDictionary.entrySet()) {
                Word word = entry.getValue();
                writer.write(word.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filePath);
        }
    }

    public void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/data/FavoriteVocabulary.txt"))) {
            for (Map.Entry<String, Word> a : myDictionary.entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error when exporting to the file: src/main/resources/data/FavoriteVocabulary.txt");
        }
    }
}