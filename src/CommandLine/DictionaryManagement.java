// /**
//      * Read data from a file and add words to the dictionary.
//      * @param s The path to the file.
//      */
//     public void addWordFromFile(String s) {
//         try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
//             String s0;
//             while ((s0 = reader.readLine()) != null) {
//                 String w = s0.trim();
//                 String pr = reader.readLine().trim();
//                 List<String> mean = new ArrayList<>();
//                 String line;

//                 while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
//                     line = line.trim(); // Remove whitespace at the beginning and end of the line
//                     if (line.startsWith("- ")) {
//                         line = line.substring(2); // Remove "- " at the beginning
//                     }
//                     mean.add(line);
//                 }

//                 addWord(w, pr, mean);
//             }
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }