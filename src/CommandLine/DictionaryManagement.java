package CommandLine;

import java.util.*;

public class DictionaryManagement {
   public void dictionarySearcher(String string) {
      Dictionary dc = new Dictionary();
      List<Word> result = dc.getDictionary();
      for (Word i : result) {
         String w = i.getWordTarget();
         if (w.startsWith(string)) {
            System.out.println("* Từ ngữ: " + w);
            System.out.println("Phát âm: " + i.getWordPronunciation());
            System.out.println("Ý nghĩa:");
            for (String j : i.getWordExplain()) {
               System.out.println("- " + j);
            }
         }
      }
   }
}
