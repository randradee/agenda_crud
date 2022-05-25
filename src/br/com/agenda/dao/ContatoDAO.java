package br.com.agenda.dao;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.models.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ContatoDAO {
    public void criarContato(Contato contato) throws Exception {
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

    public List<Contato> consultarTodosOsContatos() throws Exception {
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

                Contato contato = new Contato();

                contato.setId(id);
                contato.setNome(nome);
                contato.setIdade(idade);
                contato.setDataCadastro(dataCadastro);

                contatos.add(contato);
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

    public Contato consultarContatoPorNome(Contato contato) {
        String query = "SELECT * FROM contatos WHERE nome=?";

        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rset = null;

        Contato contatoRetornado = null;
        try {
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            conn.createStatement();


            // criar uma PreparedStatement para executar uma query
            pstm = conn.prepareStatement(query);
            pstm.setString(1, contato.getNome());

            // executar a query
            rset = pstm.executeQuery();

            if(rset != null && rset.next()){

                int id = rset.getInt("id");
                String queryNome = rset.getString("nome");
                int idade = parseInt(rset.getString("idade"));
                Date dataCadastro = rset.getDate("dataCadastro");

                contatoRetornado = new Contato();

                contatoRetornado.setId(id);
                contatoRetornado.setNome(queryNome);
                contatoRetornado.setIdade(idade);
                contatoRetornado.setDataCadastro(dataCadastro);


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
        return contatoRetornado;
        }

    public void atualizarContato(Contato contato) {
        String query = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;


        try {
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            conn.createStatement();

            // criar uma PreparedStatement para executar a query
            pstm = conn.prepareStatement(query);
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.setInt(4, contato.getId());

            // executar a query
            pstm.execute();
            System.out.println("Contato atualizado com sucesso!");
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
    }

    public void apagarContato(Contato contato) {
        String query = "DELETE FROM contatos WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            // criar uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            conn.createStatement();

            // criar uma PreparedStatement para executar a query
            pstm = conn.prepareStatement(query);
            pstm.setInt(1, contato.getId());

            // executar a query
            pstm.execute();
            System.out.println("Contato apagado com sucesso!");
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
    }

}
