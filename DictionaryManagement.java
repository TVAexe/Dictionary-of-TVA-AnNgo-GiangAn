import java.util.Scanner;

public class DictionaryManagement {
    Dictionary dc = new Dictionary();

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        int query = sc.nextInt();
        for (int i = 0; i < query; i++) {
            String eng = sc.nextLine();
            String vie = sc.nextLine();
            dc.push(eng, vie);
        }
        sc.close();
    }

}
