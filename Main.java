import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criar as árvores BST e AVL
        // BSTTree bstTree = new BSTTree();
        AVL avlTree = new AVL();

        // Menu de opções
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Ler dados de arquivo");
            System.out.println("2. Realizar Análise de Dados (AVL)");
            System.out.println("3. Inserir Programa");
            System.out.println("4. Buscar Programa");
            System.out.println("5. Remover Programa");
            System.out.println("6. Exibir Altura das Árvores");
            System.out.println("7. Sair");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Opção 1: Ler dados do arquivo
                    readDataFromFile(avlTree);
                    break;
                case 2:
                    // Opção 2: Realizar Análise de Dados (AVL)
                    // performDataAnalysis(avlTree);
                    break;
                case 3:
                    // Opção 3: Inserir Programa
                    // insertProgram(bstTree, avlTree);
                    break;
                case 4:
                    // Opção 4: Buscar Programa
                    // searchProgram(avlTree);
                    break;
                case 5:
                    // Opção 5: Remover Programa
                    // removeProgram(bstTree, avlTree);
                    break;
                case 6:
                    // Opção 6: Exibir Altura das Árvores
                    // displayTreeHeight(bstTree, avlTree);
                    break;
                case 7:
                    // Opção 7: Sair do programa
                    System.exit(0);
                    scanner.close();
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void readDataFromFile(AVL avlTree) {
        // System.out.println("Digite o nome do arquivo CSV:");
        // Scanner scanner = new Scanner(System.in);
        // String fileName = scanner.nextLine();
        String fileName = "data\\titles.csv";

        // Realizar a leitura do arquivo CSV e inserir na árvore
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                int flag;

                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if ((data.length >= 15) && (data[0] != null && data[0].matches("^[A-Za-z]{2}\\d+"))) {
                    flag = 0;
                    for (int i = 0; i < data.length; i++) {
                        if (i != 0 && i !=10 && data[i] != null && data[i].matches("^[A-Za-z]{2}\\d+")){
                            flag = 1;
                            break;
                        }
                        if (data[i].isEmpty()) {
                            data[i] = null;
                        }
                    }

                    if (flag == 0){
                        ProgramaNetFlix programa = new ProgramaNetFlix(data[0], data[1], data[2], data[3], data[4], data[5],
                                data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14]);

                        // Inserir na árvore AVL
                        avlTree.insereAVL(programa);
                        System.out.println(programa.getId());
                        // System.out.println("____________________________________________________________________");
                        // avlTree.printTree(avlTree.getNoRaiz());
                        // System.out.println("____________________________________________________________________");
                    }
                    else System.out.println("Dados incompletos, programa não inserido.");
                } else {
                    System.out.println("Dados incompletos, programa não inserido.");
                }
            }
            System.out.println("Dados lidos e inseridos nas árvores.");
            // scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // private static void performDataAnalysis(AVLTree avlTree) {
    // // Implementar as análises de dados na AVL aqui
    // }

    // private static void insertProgram(BSTTree bstTree, AVLTree avlTree) {
    // // Implementar a inserção de um novo programa nas árvores BST e AVL aqui
    // // Gere um ID único para o programa e insira
    // }

    // private static void searchProgram(AVLTree avlTree) {
    // // Implementar a busca de um programa nas árvores AVL aqui
    // }

    // private static void removeProgram(BSTTree bstTree, AVLTree avlTree) {
    // // Implementar a remoção de um programa nas árvores BST e AVL aqui
    // }

    // private static void displayTreeHeight(BSTTree bstTree, AVLTree avlTree) {
    // // Exibir a altura das árvores BST e AVL aqui
    // }

}
