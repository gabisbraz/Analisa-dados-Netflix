import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Criar as árvores BST e AVL
        BST bstTree = new BST();
        AVL avlTree = new AVL();

        // Menu de opções
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Input");
            System.out.println("2. Realizar Análise de Dados (AVL e BST)");
            System.out.println("3. Inserir Programa - AVL e BST");
            System.out.println("4. Buscar Programa - AVL e BST");
            System.out.println("5. Remover Programa - AVL e BST");
            System.out.println("6. Exibir Altura das Árvores - AVL e BST");
            System.out.println("7. Sair");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Opção 1: Ler dados do arquivo
                    readDataFromFile(avlTree, bstTree);
                    break;
                case 2:
                    // Opção 2: Realizar Análise de Dados (AVL)
                    // Top 5: Anos que produziram os programas com melhores imdbScores
                    // Top 3: Gêneros que possuem os melhores imdbScores
                    // Top 10: Para generos de romance, qual os 10 filmes com melhores notas e que possuem ageCertification igual ou menores de 16 anos
                    // Quantidade de de filmes por gênero
                    // Para shows, qual a media de temporadas produzidas?

                    // performDataAnalysis(avlTree, bstTree);
                    break;
                case 3:
                    // Opção 3: Inserir Programa
                    insertProgram(avlTree, bstTree);
                    break;
                case 4:
                    // Opção 4: Buscar Programa
                    searchProgram(avlTree, bstTree);
                    break;
                case 5:
                    // Opção 5: Remover Programa
                    removeProgram(avlTree, bstTree);
                    break;
                case 6:
                    // Opção 6: Exibir Altura das Árvores
                    int alturaAVL = avlTree.getHeight();
                    int alturaBST = bstTree.getHeight();
                    System.out.println("Altura da árvore AVL: " + alturaAVL + " | " + "Altura da árvore BST: " + alturaBST);
                    int qtdNodesAVL = avlTree.countNodes();
                    int qtdNodeBST = bstTree.countNodes();
                    System.out.println("Número de nós da árvore AVL: " + qtdNodesAVL + " | " + qtdNodeBST);
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

    private static void readDataFromFile(AVL avlTree, BST bstTree) {
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
                        if (i != 0 && i != 10 && data[i] != null && data[i].matches("^[A-Za-z]{2}\\d+")) {
                            flag = 1;
                            break;
                        }
                        if (data[i].isEmpty()) {
                            data[i] = null;
                        }
                    }

                    if (flag == 0) {
                        ProgramaNetFlix programa = new ProgramaNetFlix(data[0], data[1], data[2], data[3], data[4],
                                data[5],
                                data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14]);

                        // Inserir na árvore AVL
                        avlTree.insereAVL(programa);
                        bstTree.insert(programa);
                    } else
                        System.out.println("Dados incompletos, programa não inserido.");
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

    // private static void performDataAnalysis(AVLTree avlTree, BST bstTree) {
        
    // }

    private static void insertProgram(AVL avlTree, BST bstTree) {
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
        } while (avlTree.searchAVL(new ProgramaNetFlix(id, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null)) != null);

        // Criar o objeto ProgramaNetFlix com os dados inseridos
        ProgramaNetFlix programa = new ProgramaNetFlix(id, titulo, showType, descricao, releaseYear,
                ageCertification, runtime, generos, productionCountries, temporadas, imdbId,
                imdbScore, imdbVotes, tmdbPopularity, tmdbScore);

        // Inserir o programa na árvore AVL
        avlTree.insereAVL(programa);
        System.out.println("Programa inserido com sucesso na AVL! --> ID: " + id);
        bstTree.insert(programa);
        System.out.println("Programa inserido com sucesso na BST! --> ID: " + id);
        // scanner.close();
    }

    private static void searchProgram(AVL avlTree, BST bstTree) {
        Scanner scanner = new Scanner(System.in);
    
        // Solicitar o "id" do programa ao usuário
        System.out.print("Digite o ID do programa que deseja pesquisar: ");
        String searchId = scanner.nextLine();
    
        // Iniciar a contagem do tempo
        long startTime = System.nanoTime();
    
        // Realizar a pesquisa na árvore AVL
        NoAVL foundProgramInAVL = avlTree.searchAVL(new ProgramaNetFlix(searchId, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null));
    
        // Realizar a pesquisa na árvore BST
        BTNode foundProgramInBST = bstTree.search(new ProgramaNetFlix(searchId, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null));
    
        // Registrar o tempo de execução da pesquisa
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
    
        // Verificar se o programa foi encontrado na AVL
        if (foundProgramInAVL != null & foundProgramInBST != null) {
            System.out.println("Programa encontrado na AVL e na BST!");
            System.out.println("Dados do programa:");
            System.out.println("--> ID: " + foundProgramInAVL.getElement().getId() + " | Título: " + foundProgramInAVL.getElement().getTitulo());
            System.out.println("Número de comparações na AVL: " + avlTree.getComparisonsCount());        } 
        else {
            System.out.println("Programa não encontrado.");
        }
    
        // Apresentar o tempo de execução (nanossegundos)
        System.out.println("Tempo de execução (nanossegundos): " + elapsedTime);
    }
    

    private static void removeProgram(AVL avlTree, BST bstTree) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do programa que deseja remover: ");

        String removeId = scanner.nextLine();
        boolean removedAVL = avlTree.removeNode(removeId);
        boolean removedBST = bstTree.remove(removeId);

        if (removedAVL & removedBST) {
            System.out.println("Programa removido com sucesso da árvore AVL.");
        } else {
            System.out.println("Programa com o ID especificado não foi encontrado na árvore AVL.");
        }
    }

}
