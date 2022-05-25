package br.com.agenda.app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.models.Contato;

public class App {
    public static void main(String[] args) throws Exception {

        // TODO: input do usuário
//        Scanner input = new Scanner(System.in);
//        System.out.printf("Como deseja interagir com o banco de dados?\n1. Criar contato\n2. Ler contato específico\n3. Ler todos os contatos\n4. Atualizar contato\n5. Deletar contato");
//        int op = input.nextInt();
//
//        switch(op){
//            case 1:
//                // pega dados do input do usuário
//                System.out.println("Digite o nome do contato: ");
//                String nome = input.nextLine();
//                System.out.printf("Digite a idade do contato ");
//                int idade = input.nextInt();
//
//            default:
//                System.out.printf("Escolha uma opção válida");
//        }
        ContatoDAO contatoDAO = new ContatoDAO();

        // criar contato
//        Contato novoContato = new Contato();
//        contatoDAO.criarContato(novoContato);


        // pesquisar todos os contatos
//        List<Contato> todosOsContatos = contatoDAO.consultarTodosOsContatos();
//
//        for (Contato contato : todosOsContatos){
//            System.out.println(contato.getId());
//            System.out.println(contato.getNome());
//            System.out.println(contato.getIdade());
//            System.out.println(contato.getDataCadastro());
//        }

        // pesquisar contato por nome
//        Contato pesquisaContato = new Contato();
//        Contato retornoContato = new Contato();
//        pesquisaContato.setNome("Luiz");
//        retornoContato = contatoDAO.consultarContatoPorNome(pesquisaContato);
//        System.out.println(retornoContato.getId());
//        System.out.println(retornoContato.getNome());
//        System.out.println(retornoContato.getIdade());
//        System.out.println(retornoContato.getDataCadastro());

        // atualizar contato
        Contato novoContato = new Contato();
        novoContato.setNome("Amanda");
        novoContato.setIdade(46);
        novoContato.setDataCadastro(new Date());
        novoContato.setId(1);

        contatoDAO.atualizarContato(novoContato);

    }
}
