package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.List;

public class DictionaryManagement {
    /**
     * Attribute of the DictionaryManagement class.
     */
    private Dictionary dictionary;
    private final String dbPath = "src/main/resources/data/Vocabulary.db";

    /**
     * Load data from the database into the dictionary.
     */
    public void loadDataFromDatabase() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT word FROM av");
            while(rs.next()) {
                String wordTarget = rs.getString("word").trim().toLowerCase();
                this.dictionary.insert(wordTarget);
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete a word from the database and the Trie.
     */
    public boolean deleteFromDatabase(String word) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();
            if (this.dictionary.search(word)) {
                statement.executeUpdate("DELETE FROM av WHERE word = '" + word + "'");
                this.dictionary.delete(word);
                return true;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Insert a word into the database and the Trie.
     */
    public boolean insertIntoDatabase(String word, String html, String description) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();
            if (!this.dictionary.search(word)) {
                String sql = "INSERT INTO av(word, html, description) VALUES(?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, word);
                preparedStatement.setString(2, html);
                preparedStatement.setString(3, description);
                preparedStatement.executeUpdate();
                this.dictionary.insert(word);
                return true;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    /**
     * Get a list of words that start with a given string.
     */
    public List<String> getWordsStartingWith(String prefix) {
        return this.dictionary.getWordsStartingWith(prefix);
    }

    /**
     * Update a word in the database.
     */
    public boolean updateInDatabase(String word, String newHtml, String newDescription) {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            Statement statement = connection.createStatement();
            if (this.dictionary.search(word)) {
                String sql = "UPDATE av SET html = ?, description = ? WHERE word = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, newHtml);
                preparedStatement.setString(2, newDescription);
                preparedStatement.setString(3, word);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
