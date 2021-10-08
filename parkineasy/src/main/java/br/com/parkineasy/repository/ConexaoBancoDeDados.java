package br.com.parkineasy.repository;

import java.sql.Connection;
import java.sql.Statement;

public interface ConexaoBancoDeDados {

    Connection recuperarConnection();

    Statement recuperarStatement();
}
