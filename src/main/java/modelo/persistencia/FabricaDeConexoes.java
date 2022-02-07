/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author gdsant
 */
public class FabricaDeConexoes {

    public static Connection getConnection() throws Exception {
        Connection conexao = DriverManager.getConnection(
                "jdbc:mysql://localhost/banco_alunos", "root", "1234");

        return conexao;
    }
}
