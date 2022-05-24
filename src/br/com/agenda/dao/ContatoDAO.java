package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.models.Contato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ContatoDAO {
    public void create(Contato contato) throws Exception {
        String insertQuery = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;

        try{
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            conn.createStatement();

            // criar uma PreparedStatement para executar uma query
            PreparedStatement pstm  = conn.prepareStatement(insertQuery);

            // adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            // executar a query
            pstm.execute();
            System.out.println("Contato criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // fechar as conexões
            try{
                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public List<Contato> readAll() throws Exception {
        String selectAllQuery = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        try {
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            conn.createStatement();

            // criar uma PreparedStatement para executar uma query
            pstm = conn.prepareStatement(selectAllQuery);

            // executar a query
            rset = pstm.executeQuery();

            // tratar os dados
            while (rset.next()){
                int id = rset.getInt("id");
                String nome = rset.getString("nome");
                int idade = parseInt(rset.getString("idade"));
                Date dataCadastro = rset.getDate("dataCadastro");

                contatos.add(new Contato(id, nome, idade, dataCadastro));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // fechar as conexões
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return contatos;
    }
}
