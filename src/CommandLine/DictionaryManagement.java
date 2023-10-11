package CommandLine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement{
    private Dictionary dictionary;

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    //* Read data from a file and add words to the dictionary.
    // * @param s The path to the file.
     //*/
    public void insertFromFile(String s) {
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String w = s0.trim();
                String pr = reader.readLine().trim();
                List<String> mean = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    line = line.trim(); // Remove whitespace at the beginning and end of the line
                    if (line.startsWith("- ")) {
                        line = line.substring(2); // Remove "- " at the beginning
                    }
                    mean.add(line);
                }

                dictionary.addWordForDictionary(w, pr, mean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dictionaryLookup(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi ban nhap tu can tra cuu: ");
        String word = scanner.nextLine();
        if(dictionary.searchWord(word)){
            String a = dictionary.getDictionary().get(word).toString();
            System.out.println(a);
        }
    }

    public void removeWordFromDictionary(String word){

    }

    public void addWordForUser(String word, String pronunciation, List<String> wordExplain){

    }

    public void updateWordForUser(String word, String pronunciation, String wordExplain){

    }

    public void dictionaryExportToFile(String s){

    }

}    