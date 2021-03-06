package br.com.agenda.app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.models.Contato;

public class App {
    public static void main(String[] args) throws Exception {
        ContatoDAO contatoDAO = new ContatoDAO();
        Scanner scan = new Scanner(System.in).useDelimiter("\\n");
        int op = -1;
        do {
            switch (op) {
                case 1 -> {
                    Contato novoContato = new Contato();
                    System.out.println("Digite a idade do contato");
                    int idade = scan.nextInt();
                    novoContato.setIdade(idade);
                    scan.nextLine();
                    System.out.println("Digite o nome do contato");
                    String nome = scan.nextLine();
                    novoContato.setNome(nome);
                    novoContato.setDataCadastro(new Date());
                    contatoDAO.criarContato(novoContato);
                }
                case 2 -> {
                    scan.nextLine();
                    System.out.println("Digite o nome do contato");
                    String nomePesquisa = scan.nextLine();
                    Contato pesquisaContato = new Contato();
                    pesquisaContato.setNome(nomePesquisa);
                    Contato retornoContato;
                    retornoContato = contatoDAO.consultarContatoPorNome(pesquisaContato);
                    System.out.println("Nome do contato: " + retornoContato.getNome() + ", idade do contato: " + retornoContato.getIdade() +
                            ", data de cadastro: " + retornoContato.getDataCadastro());
                }
                case 3 -> {
                    List<Contato> todosOsContatos = contatoDAO.consultarTodosOsContatos();
                    for (Contato contato : todosOsContatos) {
                        System.out.println("ID do contato: " + contato.getId());
                        System.out.println("Nome do contato: " + contato.getNome());
                        System.out.println("Idade do contato: " + contato.getIdade());
                        System.out.println("Data de cadastro: " + contato.getDataCadastro());
                        System.out.println("");
                    }
                }
                case 4 -> {
                    Contato contatoAtualizar = new Contato();
                    System.out.println("Qual id do contato que deseja atualizar?");
                    int novoID = scan.nextInt();
                    System.out.println("Digite a nova idade do contato");
                    int novaIdade = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Digite o novo nome do contato");
                    String novoNome = scan.nextLine();

                    contatoAtualizar.setId(novoID);
                    contatoAtualizar.setIdade(novaIdade);
                    contatoAtualizar.setNome(novoNome);
                    contatoAtualizar.setDataCadastro(new Date());
                    contatoDAO.atualizarContato(contatoAtualizar);
                }
                case 5 -> {
                    Contato deletarContato = new Contato();
                    System.out.println("Digite o id do contato que deseja deletar: ");
                    int id = scan.nextInt();
                    deletarContato.setId(id);
                    contatoDAO.apagarContato(deletarContato);
                }
            }

            printMenu();
            op = scan.nextInt();

        } while(op != 6);
        scan.close();
        System.out.println("Obrigado por utilizar este aplicativo!");

    }
    public static void printMenu(){
        System.out.println("Como deseja interagir com sua agenda?\n"
                + "1. Criar novo contato\n"
                + "2. Pesquisar contato por nome\n"
                + "3. Mostrar todos os contatos\n"
                + "4. Atualizar contato\n"
                + "5. Apagar contato\n"
                + "6. Sair\n");
    }
}
