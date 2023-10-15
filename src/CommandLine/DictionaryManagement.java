package CommandLine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement{
    private Dictionary dictionary;

    public DictionaryManagement(){
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary dc){
        dictionary = dc;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    //lấy dữu liệu từ file s
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

    //Tra cứu từ 
    public void dictionaryLookup(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi ban nhap tu can tra cuu: ");
        String word = scanner.nextLine().toLowerCase();
        if(dictionary.searchWord(word)){
            System.out.println("Giai thich tu ma ban vua nhap: ");
            String a = dictionary.getDictionary().get(word).toString();
            System.out.println(a);
        }
        else{
            System.out.println("Xin loi! Tu ma ban can tra cuu khong co trong tu dien.");
        }
        scanner.close();
    }

    //xóa từ khỏi từ điển từ cml
    public void removeWordFromDictionary(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi nhap tu ban muon loai bo: ");
        String word = scanner.nextLine().toLowerCase();
        dictionary.removeWord(word);
        scanner.close();
    }

    //thêm từ mới từ commandLine
    public void insertFromCommandLine() {
        System.out.println("Hay them tu ban muon ! ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi ban nhap tu tieng anh: ");
        String word = scanner.nextLine();
        System.out.print("Moi ban nhap phien am: ");
        String pronunciation = scanner.nextLine();
        System.out.print("Moi ban nhap nghia cua tu: ");
        String wordExp = scanner.nextLine();
        if(dictionary.searchWord(word)){
            System.out.println("Tu ban muon them da co trong tu dien! Khong the them tu nay.");
            scanner.close();
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.addWordForUser(word, pronunciation, wordExplain);
        scanner.close();
    }

    //sửa từ tiếng anh
    public void editWordTargetForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi: ");
        String oldWordTarget = scanner.nextLine().toLowerCase();
        System.out.print("Ban muon thay doi thanh: ");
        String newWordTarget = scanner.nextLine().toLowerCase();
        if (!dictionary.searchWord(oldWordTarget)) {
            System.out.println("Tu " + oldWordTarget + " khong co trong tu dien.");
            scanner.close();
            return;
        }
        if(dictionary.searchWord(newWordTarget)) {
            System.out.println("Tu " + newWordTarget + " da co trong tu dien! Khong the sua vao tu dien.");
            scanner.close();
            return;
        }
        dictionary.getDictionary().get(oldWordTarget).setWordTarget(newWordTarget);
        System.out.println("Tu " + oldWordTarget + " da duoc thay doi thanh: " + newWordTarget + ".");
        scanner.close();
    }

    //sửa cách phát âm
    public void editWordPronunciation( String newPronunciation){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi cach phat am: ");
        String wordTarget = scanner.nextLine().toLowerCase();
        if (dictionary.searchWord(wordTarget)) {
            dictionary.getDictionary().get(wordTarget).setWordPronunciation(newPronunciation);
            System.out.println("Cach phat am cua tu " + wordTarget + " da duoc thay doi.");
        } 
        else {
            System.out.println("Tu " + wordTarget + " khong co trong tu dien.");
        }
        scanner.close();
    }

    //sửa nghĩa của từ
    public void editWordExplainForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi nghia: ");
        String word = scanner.nextLine().toLowerCase();
        System.out.print("Nhap nghia ma ban muon thay doi: ");
        String wordExp = scanner.nextLine();
        if(!dictionary.searchWord(word)){
            System.out.println("Tu " + word + " khong co trong tu dien.");
            scanner.close();
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.getDictionary().get(word).setWordExplain(wordExplain);
        System.out.println("Da thay doi nghia cua tu " + word + "!");
        scanner.close();
    }

    //xuất dữ liệu ra file 
    public void dictionaryExportToFile(String s){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {
            for(Map.Entry<String,Word> a : dictionary.getDictionary().entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
            System.out.println("Xuat du lieu thanh cong vao file " + s);
        } catch (IOException e) {
            System.err.println("Loi khi xuat vao file " + s);
            e.printStackTrace();
        }
    }
}