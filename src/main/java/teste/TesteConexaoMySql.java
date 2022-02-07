/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import controle.AlunoControle;
import controle.EnderecoControle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Endereco;
import modelo.persistencia.FabricaDeConexoes;

/**
 *
 * @author gdsant
 */
public class TesteConexaoMySql {

    public static void main(String[] args) {
        Endereco e = new Endereco(108, "Feira de Santana", "Massaranduba", 
                "Rua Quatro", 28); // endereço pego do banco
        
        // usuário altera alguns dados
        e.setCidade("Feira de Santana"); // txtCidade.getText();
        e.setBairro("Alecrim"); // txtBairro.getText();
        e.setRua("Rua 44"); // txtRua.getText()
        e.setNumero(28); // Integer.parseInt(txtNumero.getText())
        
        alterarEndereco(e); // clicou no botão alterar
    }
    
    public static void alterarEndereco(Endereco e){
        try {
            
            Connection conexao = FabricaDeConexoes.getConnection();
            
            String sql = "UPDATE endereco "
                    + "SET cidade = ?, "
                    + "bairro = ?, "
                    + "rua = ?, "
                    + "numero_casa = ? "
                    + "WHERE cod_endereco = ?";
            
            PreparedStatement comandoPreparado = conexao.prepareStatement(sql);
            
            comandoPreparado.setString(1, e.getCidade());
            comandoPreparado.setString(2, e.getBairro());
            comandoPreparado.setString(3, e.getRua());
            comandoPreparado.setInt(4, e.getNumero());
            comandoPreparado.setInt(5, e.getCodEndereco());
            
            comandoPreparado.execute();
            
            comandoPreparado.close();
            conexao.close();                    
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void buscarEnderecoPorCidade(String cidade) {
        try {

            Connection conexao = FabricaDeConexoes.getConnection();

            String sql = "SELECT * FROM endereco WHERE cidade = ?";

            PreparedStatement comandoPreparado = conexao.prepareStatement(sql);

            comandoPreparado.setString(1, cidade);

            ResultSet resultados = comandoPreparado.executeQuery();

            while (resultados.next()) {
                int codEndereco = resultados.getInt("cod_endereco");
                cidade = resultados.getString("cidade");
                String bairro = resultados.getString("bairro");
                String rua = resultados.getString("rua");
                int numeroCasa = resultados.getInt("numero_casa");

                System.out.println(codEndereco + "\t" + cidade + "\t"
                        + bairro + "\t" + rua + "\t" + numeroCasa);
            }

            resultados.close();
            comandoPreparado.close();
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void inserirDadosEmEndereco() {
        try {

            Connection conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost/banco_alunos", "root", "1234");

            String sql = "INSERT INTO endereco VALUES (110, 'cidade teste', "
                    + "'bairro teste', 'rua teste', 33)";

            PreparedStatement comandoPreparado = conexao.prepareStatement(sql);

            comandoPreparado.execute();

            comandoPreparado.close();
            conexao.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void inserirDadosEmEndereco(Endereco e) {
        try {

            Connection conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost/banco_alunos", "root", "1234");

            String sql = "INSERT INTO endereco (cidade, bairro, rua, numero_casa) "
                    + "VALUES (?, ?, ?, ?)";

            String sql2 = "DELETE FROM endereco WHERE cod_endereco = ?";

            PreparedStatement comandoPreparado = conexao.prepareStatement(sql);

            comandoPreparado.setString(1, e.getCidade());
            comandoPreparado.setString(2, e.getBairro());
            comandoPreparado.setString(3, e.getRua());
            comandoPreparado.setInt(4, e.getNumero());

            comandoPreparado.execute();

            comandoPreparado.close();
            conexao.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
