package commandline.cmdline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;



public class DictionaryManagement {
    private Dictionary dictionary;
    private String FileName;

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public DictionaryManagement(Dictionary dc) {
        dictionary = dc;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    // lấy dữu liệu từ file s
    public void insertFromFile(String s) {
        FileName = s;
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String s0;
            while ((s0 = reader.readLine()) != null) {
                String w = s0.trim();
                String pr = reader.readLine().trim();
                List<String> mean = new ArrayList<>();
                String line;

                while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                    line = line.trim();
                    if (line.startsWith("- ")) {
                        line = line.substring(2); 
                    }
                    mean.add(line);
                }

                dictionary.addWordForDictionary(w, pr, mean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAll()
    {
       TreeMap<String, Word> test=dictionary.getDictionary();
       for (String key : test.keySet())
       {
            System.out.println(dictionary.getDictionary().get(key).toString());
       }
    }

    // Tra cứu từ
    public void dictionarySearch() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi ban nhap tu can tra cuu: ");
        String word = scanner.nextLine().toLowerCase();
        if (dictionary.searchWord(word)) {
            System.out.println("Giai thich tu ma ban vua nhap: ");
            String a = dictionary.getDictionary().get(word).toString();
            System.out.println(a);
        } else {
            System.out.println("Xin loi! Tu ma ban can tra cuu khong co trong tu dien.");
        }
        // scanner.close();
    }

    // xóa từ khỏi từ điển từ cml
    public void removeWordFromDictionary() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi nhap tu ban muon loai bo: ");
        String word = scanner.nextLine().toLowerCase();
        dictionary.removeWord(word);
        // scanner.close();
    }

    // thêm từ mới từ commandLine
    public void insertFromCommandLine() {
        System.out.println("Hay them tu ban muon ! ");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Moi ban nhap tu tieng anh: ");
        String word = scanner.nextLine();
        System.out.print("Moi ban nhap phien am: ");
        String pronunciation = scanner.nextLine();
        System.out.print("Moi ban nhap nghia cua tu: ");
        String wordExp = scanner.nextLine();
        if (dictionary.searchWord(word)) {
            System.out.println("Tu ban muon them da co trong tu dien! Khong the them tu nay.");
            // scanner.close();
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.addWordForUser(word, pronunciation, wordExplain);
        // scanner.close();
    }

    // sửa từ tiếng anh
    public void editWordTargetForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi: ");
        String oldWordTarget = scanner.nextLine().toLowerCase();
        System.out.print("Ban muon thay doi thanh: ");
        String newWordTarget = scanner.nextLine().toLowerCase();
        if (!dictionary.searchWord(oldWordTarget)) {
            System.out.println("Tu " + oldWordTarget + " khong co trong tu dien.");
            // scanner.close();
            return;
        }
        if (dictionary.searchWord(newWordTarget)) {
            System.out.println("Tu " + newWordTarget + " da co trong tu dien! Khong the sua vao tu dien.");
            // scanner.close();
            return;
        }
        dictionary.getDictionary().get(oldWordTarget).setWordTarget(newWordTarget);
        System.out.println("Tu " + oldWordTarget + " da duoc thay doi thanh: " + newWordTarget + ".");
        // scanner.close();
    }

    // sửa cách phát âm
    public void editWordPronunciation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi cach phat am: ");
        String wordTarget = scanner.nextLine().toLowerCase();
        System.out.print("Ban muon thay doi thanh: ");
        String newPronunciation = scanner.nextLine();
        if (dictionary.searchWord(wordTarget)) {
            dictionary.getDictionary().get(wordTarget).setWordPronunciation(newPronunciation);
            System.out.println("Cach phat am cua tu " + wordTarget + " da duoc thay doi.");
        } else {
            System.out.println("Tu " + wordTarget + " khong co trong tu dien.");
        }
        // scanner.close();
    }

    // sửa nghĩa của từ
    public void editWordExplainForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap tu ma ban muon thay doi nghia: ");
        String word = scanner.nextLine().toLowerCase();
        System.out.print("Nhap nghia ma ban muon thay doi: ");
        String wordExp = scanner.nextLine();
        if (!dictionary.searchWord(word)) {
            System.out.println("Tu " + word + " khong co trong tu dien.");
            // scanner.close();
            return;
        }
        List<String> wordExplain = new ArrayList<>();
        wordExplain.add(wordExp);
        dictionary.getDictionary().get(word).setWordExplain(wordExplain);
        System.out.println("Da thay doi nghia cua tu " + word + "!");
        // scanner.close();
    }

    //ghi đè vào file cũ 
    public void saveDictionary() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FileName))) {
            for (Map.Entry<String, Word> a : dictionary.getDictionary().entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
            //System.out.println("Xuat du lieu thanh cong vao file. " + s);
        } catch (IOException e) {
            //System.err.println("Loi khi xuat vao file. " + s);
            e.printStackTrace();
        }
    }

    // xuất dữ liệu ra file
    public void dictionaryExportToFile() {
        System.out.print("Nhap duong dan toi file.txt ma ban muon xuat du lieu vao : ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(s))) {
            for (Map.Entry<String, Word> a : dictionary.getDictionary().entrySet()) {
                writer.write(a.getValue().toString());
                writer.newLine();
            }
            System.out.println("Xuat du lieu thanh cong vao file. " + s);
        } catch (IOException e) {
            System.err.println("Loi khi xuat vao file. " + s);
            e.printStackTrace();
        }
    }

    //tim kiem cac tu bat dau bang cac ki tu nhap vao
    public void dictionaryLookup(){
        System.out.print("Nhap ki tu ban muon tim kiem ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine().toLowerCase();
        System.out.println("Danh sach cac tu bat dau bang cac ki tu tren: ");
        Boolean b = false;
        for(String key : dictionary.getDictionary().keySet()){
            if(key.startsWith(s)){
                System.out.println(key);
                b = true;
            } 
        }
        if(b == true) return;
        System.out.println("Khong co tu nao bat dau bang cac ki tu tren.");
    }
}