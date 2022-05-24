package br.com.agenda.app;

import java.util.Date;
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

        Contato contato = new Contato("Renato", 29, new Date());

        ContatoDAO novoContato = new ContatoDAO();
        novoContato.create(contato);
    }
}
