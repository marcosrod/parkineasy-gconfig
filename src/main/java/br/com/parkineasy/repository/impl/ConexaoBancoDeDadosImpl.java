package br.com.parkineasy.repository.impl;

import br.com.parkineasy.repository.ConexaoBancoDeDados;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBancoDeDadosImpl implements ConexaoBancoDeDados {
    private static ConexaoBancoDeDadosImpl instancia;
    private Connection connection;
    private Statement statement;
    private String url = "jdbc:mysql://localhost:3306/parkineasy";
    private String usuario = "root";
    private String senha = "root1999";

    private ConexaoBancoDeDadosImpl() {
        try {
            connection = DriverManager.getConnection(url, usuario, senha);
            statement = connection.createStatement();
        } catch (SQLException sqlException) {
            System.err.println("Falha na realização da conexão com o banco de dados: " + sqlException.getMessage());
        }
    }

    public static ConexaoBancoDeDadosImpl getInstancia() {
        try {
            if (instancia == null || instancia.recuperarConnection().isClosed()) {
                instancia = new ConexaoBancoDeDadosImpl();
            }
        } catch (SQLException sqlException) {
            System.err.println("Falha na realização da conexão com o banco de dados: " + sqlException.getMessage());
        }
        return instancia;
    }

    public Connection recuperarConnection() {
        return connection;
    }

    public Statement recuperarStatement() {
        return statement;
    }
}
