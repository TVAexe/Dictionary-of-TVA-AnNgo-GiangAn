package CommandLine;

import java.util.Scanner;

public class DictionaryCommandLine {
    public void dictionaryAdvanced() {
        Scanner sc = new Scanner(System.in);
        DictionaryManagement test = new DictionaryManagement();
        while (true) {
            System.out.println("Welcome to my application!");
            System.out.println("[0]: Exit");
            System.out.println("[1]: Add a word to the dictionary");
            System.out.println("[2]: Remove a word from the dictionary");
            System.out.println("[3]: Update for a word in the dictionary");
            System.out.println("[4]: Display all words in the dictionary");
            System.out.println("[5]: Search for a word in the dictionary");
            System.out.println("[6]: Lookup words in the dictionary");
            System.out.println("[7]: Game");
            System.out.println("[8]: Export dictionary to file");
            System.out.print("Your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case (0):
                    sc.close();
                    break;
                case (1):
                    test.insertFromCommandLine();
                case (2):
                    test.removeWordFromDictionary();
                case (3):
                    System.out.println("Do you want to edit:" +
                            "1. The WordTarget\n" +
                            "2.The WordPronounciation\n"
                            + "3. The WordExplain\n");
                    System.out.print("Your choice: ");
                    int edit = sc.nextInt();
                    switch (edit) {
                        case (1):
                            test.editWordTargetForUser();
                            break;
                        case (2):
                            test.editWordPronunciation(sc.nextLine());
                            break;
                        case (3):
                            test.editWordExplainForUser();
                            break;
                        default:
                            System.out.println("Wrong choice!");
                            break;
                    }

                case (4):

                case (5):

                case (6):
                    test.dictionaryLookup();

                case (7):

                case (8):
                    test.dictionaryExportToFile(sc.nextLine());
            }
        }
    }
}
