package com.tecsup.exam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    
    private static Connection connection = null;
    
    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            createTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    private static void createTable() {
        try (Statement stmt = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS estudiantes (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "nombre VARCHAR(100) NOT NULL," +
                    "apellido VARCHAR(100) NOT NULL," +
                    "email VARCHAR(150) UNIQUE NOT NULL," +
                    "telefono VARCHAR(20)" +
                    ")";
            stmt.execute(createTableSQL);
            
            // Insertar datos de ejemplo
            String insertDataSQL = "INSERT INTO estudiantes (nombre, apellido, email, telefono) VALUES " +
                    "('Juan', 'Pérez', 'juan.perez@email.com', '987654321'), " +
                    "('María', 'García', 'maria.garcia@email.com', '987654322'), " +
                    "('Carlos', 'López', 'carlos.lopez@email.com', '987654323')";
            stmt.execute(insertDataSQL);
            
        } catch (SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
