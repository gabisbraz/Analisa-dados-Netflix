import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        BST bstTree = new BST();
        AVL avlTree = new AVL();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Input");
            System.out.println("2. Realizar Análise de Dados (AVL e BST)");
            System.out.println("3. Inserir Programa - AVL e BST");
            System.out.println("4. Buscar Programa - AVL e BST");
            System.out.println("5. Remover Programa - AVL e BST");
            System.out.println("6. Exibir Altura das Árvores - AVL e BST");
            System.out.println("7. Salvar dados em arquivo CSV!");
            System.out.println("8. Encerrar aplicação!");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    readDataFromFile(avlTree, bstTree);
                    break;
                case 2:
                    performDataAnalysis(avlTree);
                    break;
                case 3:
                    insertProgram(avlTree, bstTree);
                    break;
                case 4:
                    searchProgram(avlTree, bstTree);
                    break;
                case 5:
                    removeProgram(avlTree, bstTree);
                    break;
                case 6:
                    int alturaAVL = avlTree.getHeight();
                    int qtdNodesAVL = avlTree.countNodes();
                    int alturaBST = bstTree.getHeight();
                    int qtdNodeBST = bstTree.countNodes();
                    System.out.println("Altura da árvore AVL: " + alturaAVL + " | " + "Altura da árvore BST: " + alturaBST);
                    System.out.println("Número de nós da árvore AVL: " + qtdNodesAVL + " | Número de nós da árvore BST: " + qtdNodeBST);
                    break;
                case 7:
                    System.out.println("Digite o nome do arquivo para salvar os dados: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String fileName = scanner2.nextLine();
                    avlTree.saveDataToFile(fileName);
                    break;
                case 8:
                    System.exit(0);
                    scanner.close();
                    break;
                default:
                    System.out.println("Escolha inválida. Tente novamente.");
                    break;
            }
        }
    }

    private static void  readDataFromFile(AVL avlTree, BST bstTree) {
        System.out.println("Digite o nome do arquivo CSV:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        String fileName2 = "data\\" + fileName;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName2))) {
            String line;
            List<String> data7List = new ArrayList<>();
            List<String> data8List = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                int flag;
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if ((data.length >= 15) && (data[0] != null && data[0].matches("^[A-Za-z]{2}\\d+"))) {
                    flag = 0;
                    for (int i = 0; i < data.length; i++) {
                        if (i != 0 && i != 10 && data[i] != null && data[i].matches("^[A-Za-z]{2}\\d+")) {
                            flag = 1;
                            break;
                        }
                        if (data[i].isEmpty())
                            data[i] = null;
                    }
                    if (data[7] != null)
                        data7List = Arrays.asList(data[7].replaceAll("\\[|\\]|'", "").split("\\s*,\\s*"));
                    if (data[8] != null)
                        data8List = Arrays.asList(data[8].replaceAll("\\[|\\]|'", "").split("\\s*,\\s*"));
                    if (flag == 0) {
                        ProgramaNetFlix programa = new ProgramaNetFlix(data[0], data[1], data[2], data[3], data[4],
                                data[5], data[6], data7List, data8List, data[9], data[10], data[11], data[12], data[13],
                                data[14]);
                        avlTree.insereAVL(programa);
                        bstTree.insert(programa);
                    }
                    // else
                    // System.out.println("Dados incompletos, programa não inserido.");
                }
                // else {
                // System.out.println("Dados incompletos, programa não inserido.");
                // }
            }
            System.out.println("Dados lidos e inseridos nas árvores.");
            // System.out.println("____________________________________________________________________");
            // avlTree.printTree();
            // System.out.println("____________________________________________________________________");
        } catch (IOException e) {
            System.out.println("Algo deu errado!");
            // e.printStackTrace();
        }
    }

    private static void performDataAnalysis(AVL avlTree) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------- Análise 1 -----------------------------\nTOP 10 PROGRAMAS POR GÊNERO E CLASSIFICAÇÃO ETÁRIA");
        System.out.println("Gêneros disponíveis: action, animation, comedy, crime, documentation, drama, european, fantasy, history, horror, music, romance, scifi, scifi, thriller, war, western \n");
        System.out.println("Digite um gênero para análise: ");
        String genero = scanner.nextLine();
        System.out.println("Classificações indicativas disponíveis: PG-13, R, NC-17, TV-PG, G, PG, TV-MA, TV-14, TV-Y, TV-G, TV-Y7");
        System.out.println("Digite uma classificação indicativa para análise: ");
        String classificacao = scanner.nextLine();
        List<ProgramaNetFlix> topProgramas = avlTree.getTopProgramasPorGeneroEClassificacao(genero, classificacao, 10);
        if (!topProgramas.isEmpty()) {
            System.out.println("Top 10 filmes para o gênero '" + genero + "' com classificação '" + classificacao + "' e melhores IMDb Scores:");
            int count = 0;
            for (ProgramaNetFlix programa : topProgramas) {
                if (count < 10) {
                    System.out.println("ID: " + programa.getId() + " | Título: " + programa.getTitulo() +
                            " | IMDb Score: " + programa.getImdbScore());
                    count++;
                } else break; // Já encontrou os Top 10
            }
        } else System.out.println("Nenhum filme encontrado para o gênero '" + genero + "' com classificação '" + classificacao + "'.");

        System.out.println("----------------------------- Análise 2 -----------------------------\nTOP 5 PROGRAMAS POR PAÍS E IMDBSCORE");
        System.out.println("Digite um país para análise (Sigla do país em maiúscula): ");
        String pais = scanner.nextLine();
        System.out.println("Digite um ano para análise: ");
        String ano = scanner.nextLine();
        List<ProgramaNetFlix> topMovies = avlTree.getTopMoviesPorPaisEAno(pais, ano, 5);
        topMovies.sort(Comparator.comparing(ProgramaNetFlix::getImdbScore).reversed());
        if (!topMovies.isEmpty()) {
            System.out.println("Top 5 filmes (MOVIE) lançados em '" + pais + "' no ano '" + ano + "' com melhores IMDb Scores:");
            int count = 0;
            for (ProgramaNetFlix movie : topMovies) {
                if (count < 5) {
                    System.out.println("ID: " + movie.getId() + " | Título: " + movie.getTitulo() +
                            " | IMDb Score: " + movie.getImdbScore());
                    count++;
                } else break;
            }
        } else System.out.println("Nenhum filme (MOVIE) encontrado para o país '" + pais + "' no ano '" + ano + "'.");

        System.out.println("----------------------------- Análise 3 -----------------------------\nMELHORES PROGRAMAS POR GÊNERO");
        avlTree.printBestProgramForEachGenre();

        System.out.println("----------------------------- Análise 4 -----------------------------\nQUANTIDADE DE FILMES E SÉRIES QUE CADA GÊNERO POSSUI");
        avlTree.countMoviesAndShowsByGenre();
    
        System.out.println("----------------------------- Análise 5 -----------------------------\nSÉRIES QUE POSSUEM O NÚMERO DE TEMPORADAS IGUAL A N");
        System.out.println("Digite o número de temporadas desejado: ");
        int numTemporadasInput = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        
        List<ProgramaNetFlix> showsWithNumTemporadas = avlTree.getShowsWithNumTemporadas(numTemporadasInput);
        if (!showsWithNumTemporadas.isEmpty()) {
            System.out.println("Shows com " + numTemporadasInput + " temporadas:");
            for (ProgramaNetFlix show : showsWithNumTemporadas) {
                System.out.println("ID: " + show.getId() + " | Título: " + show.getTitulo() +
                        " | Temporadas: " + show.getTemporadas());
            }
        } else {
            System.out.println("Nenhum show encontrado com " + numTemporadasInput + " temporadas.");
        }
    }

    private static void insertProgram(AVL avlTree, BST bstTree) {
        Scanner scanner = new Scanner(System.in);
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
        if ("SHOW".equalsIgnoreCase(showType))
            categoria = "ts";
        else if ("MOVIE".equalsIgnoreCase(showType))
            categoria = "tm";
        else
            System.out.println("Categoria inválida. Use SHOW ou MOVIE.");

        List<String> generosList = Arrays.asList(generos.split(",\\s*"));
        List<String> productionCountriesList = Arrays.asList(productionCountries.split(",\\s*"));
        String id;

        do {
            Random random = new Random();
            int numeroUnico = random.nextInt(99991) + 10;
            id = categoria + numeroUnico;
        } while (avlTree.searchAVL(new ProgramaNetFlix(id, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null)) != null);

        ProgramaNetFlix programa = new ProgramaNetFlix(id, titulo, showType, descricao, releaseYear, ageCertification,
                runtime, generosList, productionCountriesList, temporadas, imdbId, imdbScore, imdbVotes, tmdbPopularity,
                tmdbScore);
        avlTree.insereAVL(programa);
        System.out.println("Programa inserido com sucesso na AVL! --> ID: " + id);
        bstTree.insert(programa);
        System.out.println("Programa inserido com sucesso na BST! --> ID: " + id);
    }

    private static void searchProgram(AVL avlTree, BST bstTree) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do programa que deseja pesquisar: ");
        String searchId = scanner.nextLine();
        long startTimeAVL = System.nanoTime();
        NoAVL foundProgramInAVL = avlTree.searchAVL(new ProgramaNetFlix(searchId, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null));
        long endTimeAVL = System.nanoTime();
        long startTimeBST = System.nanoTime();
        BTNode foundProgramInBST = bstTree.search(new ProgramaNetFlix(searchId, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null));
        long endTimeBST = System.nanoTime();
        long elapsedTimeAVL = endTimeAVL - startTimeAVL;
        long elapsedTimeBST = endTimeBST - startTimeBST;

        if (foundProgramInAVL != null & foundProgramInBST != null) {
            System.out.println("Programa encontrado na AVL e na BST!");
            System.out.println("Dados do programa:");
            System.out.println("--> ID: " + foundProgramInAVL.getElement());
            System.out.println("Número de comparações na AVL: " + avlTree.getComparisonsCount() + " | Tempo de execução (nanossegundos): " + elapsedTimeAVL);
            System.out.println("Número de comparações na BST: " + bstTree.getComparisonsCount() + " | Tempo de execução (nanossegundos): " + elapsedTimeBST);
        } else {
            System.out.println("Programa não encontrado.");
        }
    }

    private static void removeProgram(AVL avlTree, BST bstTree) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do programa que deseja remover: ");
        String removeId = scanner.nextLine();
        boolean removedAVL = avlTree.removeNode(removeId);
        boolean removedBST = bstTree.remove(removeId);
        if (removedAVL & removedBST)
            System.out.println("Programa removido com sucesso das árvores AVL e BST.");
        else
            System.out.println("Programa com o ID especificado não foi encontrado na árvore AVL.");
    }

}
