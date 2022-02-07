/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import modelo.Endereco;

/**
 *
 * @author gdsant
 */
public class EnderecoDAO {

    /**
     * Salva um endereço no banco de dados, que posteriormente será atribuído a
     * um aluno.
     *
     * @param e objeto da classe endereço, cujas informações serão registrados
     * no banco de dados.
     * @return o código do endereço que foi registrado no banco de dados para
     * que seja atribuído ao aluno correspondente.<br>
     * 0 se não foi possível salvar.
     */
    public static int salvar(Endereco e) throws Exception {

        Connection conexao = FabricaDeConexoes.getConnection();

        String sql = "INSERT INTO endereco (cidade, bairro, rua, numero_casa) "
                + "VALUES (?, ?, ?, ?)";

        PreparedStatement comandoPreparado = conexao.prepareStatement(sql);

        comandoPreparado.setString(1, e.getCidade());
        comandoPreparado.setString(2, e.getBairro());
        comandoPreparado.setString(3, e.getRua());
        comandoPreparado.setInt(4, e.getNumero());

        comandoPreparado.execute();

        comandoPreparado.close();
        conexao.close();

        return getEnderecoRecemCriado();
    }

    /**
     * Retorna código do endereço recém criado.
     *
     * @return o último código de endereço (o que tem o maior valor) registrado
     * no banco de dados.
     */
    public static int getEnderecoRecemCriado() throws Exception {
        Connection conexao = FabricaDeConexoes.getConnection();

        String sql = "SELECT max(cod_endereco) as endereco_recem_criado FROM endereco";

        PreparedStatement comandoPreparado = conexao.prepareStatement(sql);

        ResultSet resultado = comandoPreparado.executeQuery();

        resultado.next();

        int ultimoCodEndereco = resultado.getInt("endereco_recem_criado");

        resultado.close();
        comandoPreparado.close();
        conexao.close();

        return ultimoCodEndereco;
    }

    /**
     * Altera endereço de aluno.
     *
     * @param e o endereço modificado.
     * @return true: se foi possível realizar a alteração.<br>
     * false; se não foi possível realizar a alteração.
     */
    public static boolean alterar(Endereco e) throws Exception {
        Connection conexao = FabricaDeConexoes.getConnection();
        
        String sql = "UPDATE endereco SET cidade = ?, bairro = ?, rua = ?,"
                + " numero = ? WHERE cod_endereco = ?";
        
        PreparedStatement comandoPreparado = conexao.prepareStatement(sql);
        
        comandoPreparado.setString(1, e.getCidade());
        comandoPreparado.setString(2, e.getBairro());
        comandoPreparado.setString(3, e.getRua());
        comandoPreparado.setInt(4, e.getNumero());
        comandoPreparado.setInt(5, e.getCodEndereco());
        
        comandoPreparado.execute();
        comandoPreparado.close();
        conexao.close();
        
        return true;
    }

    /**
     * Realiza a busca pelo endereço com um código determinado.
     *
     * @param codEndereco código do endereço que se deseja buscar.
     * @return objeto da classe Endereço com os dados encontrados<br>
     * null: se não foi possível encontrar endereço.
     */
    public static Endereco buscarPorCodigo(int codEndereco) throws Exception {
        return null; // retorno temporário, falta implementar
    }
}
