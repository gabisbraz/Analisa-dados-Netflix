import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
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
                    insertProgram(avlTree);
                    break;
                case 4:
                    // Opção 4: Buscar Programa
                    searchProgram(avlTree);
                    break;
                case 5:
                    // Opção 5: Remover Programa
                    // removeProgram(bstTree, avlTree);
                    break;
                case 6:
                    // Opção 6: Exibir Altura das Árvores
                    int height = avlTree.getHeight();
                    System.out.println("Height of the AVL tree: " + height);
                    int heightRight = avlTree.getHeightRight();
                    System.out.println("Height of the AVL tree: " + heightRight);
                    int heightLeft = avlTree.getHeightLeft();
                    System.out.println("Height of the AVL tree: " + heightLeft);
                    int nodeCount = avlTree.countNodes();
                    System.out.println("Number of nodes in the AVL tree: " + nodeCount);
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
                    }
                    else System.out.println("Dados incompletos, programa não inserido.");
                } else {
                    System.out.println("Dados incompletos, programa não inserido.");
                }
            }
            System.out.println("Dados lidos e inseridos nas árvores.");
            System.out.println("____________________________________________________________________");
            avlTree.printTree();
            System.out.println("____________________________________________________________________");
            // scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // private static void performDataAnalysis(AVLTree avlTree) {
    // // Implementar as análises de dados na AVL aqui
    // }

    private static void insertProgram(AVL avlTree) {
        Scanner scanner = new Scanner(System.in);
    
        // Solicitar os dados do programa Netflix ao usuário
        System.out.println("Inserir um novo programa Netflix:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Tipo de Show (SHOW/MOVIE): ");
        String showType = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Ano de Lançamento: ");
        String releaseYear = scanner.nextLine();
        System.out.print("Classificação Etária: ");
        String ageCertification = scanner.nextLine();
        System.out.print("Duração: ");
        String runtime = scanner.nextLine();
        System.out.print("Gêneros: ");
        String generos = scanner.nextLine();
        System.out.print("Países de Produção: ");
        String productionCountries = scanner.nextLine();
        System.out.print("Temporadas: ");
        String temporadas = scanner.nextLine();
        System.out.print("ID IMDb: ");
        String imdbId = scanner.nextLine();
        System.out.print("Pontuação IMDb: ");
        String imdbScore = scanner.nextLine();
        System.out.print("Votos IMDb: ");
        String imdbVotes = scanner.nextLine();
        System.out.print("Popularidade TMDb: ");
        String tmdbPopularity = scanner.nextLine();
        System.out.print("Pontuação TMDb: ");
        String tmdbScore = scanner.nextLine();
    
        // Determinar a categoria (SHOW ou MOVIE) com base na entrada do usuário
        String categoria = "";
        if ("SHOW".equalsIgnoreCase(showType)) {
            categoria = "ts";
        } else if ("MOVIE".equalsIgnoreCase(showType)) {
            categoria = "tm";
        } else {
            System.out.println("Categoria inválida. Use SHOW ou MOVIE.");
        }
    
        String id;
        do {
            // Gerar um número único aleatório
            Random random = new Random();
            int numeroUnico = random.nextInt(99991) + 10;
    
            // Criar o ID do programa concatenando categoria e número único
            id = categoria + numeroUnico;
        } while (avlTree.searchAVL(new ProgramaNetFlix(id, null, null, null, null, null, null, null, null, null, null, null, null, null, null)) != null);
    
        // Criar o objeto ProgramaNetFlix com os dados inseridos
        ProgramaNetFlix programa = new ProgramaNetFlix(id, titulo, showType, descricao, releaseYear,
                ageCertification, runtime, generos, productionCountries, temporadas, imdbId,
                imdbScore, imdbVotes, tmdbPopularity, tmdbScore);
    
        // Inserir o programa na árvore AVL
        avlTree.insereAVL(programa);
        System.out.println("Programa inserido com sucesso! --> ID: " + id);
    }
    
    private static void searchProgram(AVL avlTree) {
        Scanner scanner = new Scanner(System.in);
    
        // Solicitar o "id" do programa ao usuário
        System.out.print("Digite o ID do programa que deseja pesquisar: ");
        String searchId = scanner.nextLine();
    
        // Iniciar a contagem do tempo
        long startTime = System.nanoTime();
    
        // Realizar a pesquisa na árvore AVL
        NoAVL foundProgram = avlTree.searchAVL(new ProgramaNetFlix(searchId, null, null, null, null, null, null, null, null, null, null, null, null, null, null));
    
        // Registrar o tempo de execução da pesquisa
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
    
        // Verificar se o programa foi encontrado
        if (foundProgram != null) {
            System.out.println("Programa encontrado!");
            System.out.println("Dados do programa:");
            // Apresentar os dados do programa, por exemplo:
            System.out.println("ID: " + foundProgram.getElement().getId());
            System.out.println("Título: " + foundProgram.getElement().getTitulo());
            // Adicione outras informações conforme necessário
    
            // Apresentar o número de comparações e o tempo de execução
            System.out.println("Número de comparações: " + avlTree.getComparisonsCount());
            System.out.println("Tempo de execução (nanossegundos): " + elapsedTime);
        } else {
            System.out.println("Programa não encontrado.");
        }
    }
    
    private static void removeProgram(AVL avlTree) {
        Scanner scanner = new Scanner(System.in);
    
        // Solicitar o "ID" do programa ao usuário
        System.out.print("Digite o ID do programa que deseja remover: ");
        String removeId = scanner.nextLine();
    
        // Criar um programa fictício com o ID fornecido para realizar a remoção
        ProgramaNetFlix programaToRemove = new ProgramaNetFlix(removeId, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    
        // Realizar a remoção do programa da árvore AVL
        boolean removed = avlTree.removeAVL(programaToRemove);
    
        if (removed) {
            System.out.println("Programa removido com sucesso da árvore AVL.");
        } else {
            System.out.println("Programa com o ID especificado não foi encontrado na árvore AVL.");
        }
    }

    // private static void displayTreeHeight(BSTTree bstTree, AVLTree avlTree) {
    // // Exibir a altura das árvores BST e AVL aqui
    // }

}
