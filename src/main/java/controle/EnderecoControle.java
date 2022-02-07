/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import modelo.Endereco;
import modelo.persistencia.EnderecoDAO;

/**
 *
 * @author gdsant
 */
public class EnderecoControle {

    // método estático cadastrar
    public static int cadastrar(String cidade, String bairro, String rua, int numero)
            throws Exception {

        if (cidade.isEmpty() || bairro.isEmpty() || rua.isEmpty()) {
            return 0;
        }

        Endereco e = new Endereco(cidade, bairro, rua, numero);
        int ultimoEndereco = EnderecoDAO.salvar(e);
        return ultimoEndereco;
    }

    // método estático alterar
    public static boolean alterar(Endereco e) throws Exception {
        if (e.getCidade().isEmpty() || e.getBairro().isEmpty() || e.getRua().isEmpty()
                || e.getNumero() <= 0) {
            return false;
        }

        return EnderecoDAO.alterar(e);
    }

    // método estático buscar endereço por código
    public static Endereco buscarPorCodigo(int codigo) {
        return null; // retorno temporário, falta implementar
    }
}
