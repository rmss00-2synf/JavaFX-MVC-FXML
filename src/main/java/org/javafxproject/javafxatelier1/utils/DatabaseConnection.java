package org.javafxproject.javafxatelier1.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final Connection connection;

    private DatabaseConnection() {
        try {
            // Charger la configuration depuis le fichier XML
            DatabaseConfig config = loadDatabaseConfig("src/main/resources/database-config.xml");

            System.out.println("Chargement du driver...");
            Class.forName(config.getDriver());
            System.out.println("Driver chargé avec succès.");
            System.out.println("Connexion à la base de données...");

            connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
            System.out.println("Connexion réussie.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver JDBC non trouvé : " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données : " + e.getMessage());
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private DatabaseConfig loadDatabaseConfig(String filePath) throws Exception {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        Element root = doc.getDocumentElement();
        String driver = root.getElementsByTagName("driver").item(0).getTextContent();
        String url = root.getElementsByTagName("url").item(0).getTextContent();
        String username = root.getElementsByTagName("username").item(0).getTextContent();
        String password = root.getElementsByTagName("password").item(0).getTextContent();
        return new DatabaseConfig(driver, url, username, password);
    }
}

@Data @AllArgsConstructor
class DatabaseConfig {
    private final String driver;
    private final String url;
    private final String username;
    private final String password;
}