package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.models.Contato;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.Date;

public class ContatoDAO {
    public void create(Contato contato) throws Exception {
        String insertQuery = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();

            // criar uma PreparedStatement para executar uma query
            pstm = (PreparedStatement) conn.prepareStatement(insertQuery);

            // adicionar os valores que são esperados pela query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

            // executar a query
            pstm.execute();
            System.out.printf("Contato criado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // fechar as conexões
            try{
                if(pstm != null){
                    pstm.close();
                }

                if(conn != null){
                    conn.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
