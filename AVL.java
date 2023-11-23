import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AVL {
    private NoAVL raiz;
    private int nElem; // N�mero de elementos (n�s) na �rvore

    public AVL() {
        raiz = null;
        nElem = 0;
        comparisonsCount = 0;
    }

    public AVL(ProgramaNetFlix programa) {
        raiz = new NoAVL(programa, 0, null, null, null);
        nElem = 1;
    }

    private int comparisonsCount;

    public int getComparisonsCount() {
        return comparisonsCount;
    }

    public NoAVL getNoRaiz() {
        return raiz;
    }

    private void setNoRaiz(NoAVL novoNoRaiz) {
        raiz = novoNoRaiz;
    }

    public int getNElem() {
        return nElem;
    }

    public void setNElem(int nElem) {
        this.nElem = nElem;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public NoAVL searchAVL(ProgramaNetFlix programa) {
        return searchNoAVL(raiz, programa);
    }

    private NoAVL searchNoAVL(NoAVL noAtual, ProgramaNetFlix programa) {
        if (noAtual == null) {
            return null;
        }

        int compareResult = programa.getId().compareTo(noAtual.getElement().getId());
        comparisonsCount++;

        if (compareResult == 0) {
            return noAtual;
        } else if (compareResult < 0) {
            return searchNoAVL(noAtual.getLeft(), programa);
        } else {
            return searchNoAVL(noAtual.getRight(), programa);
        }
    }

    public void insereAVL(ProgramaNetFlix programa) {
        setNoRaiz(insereNoAVL(raiz, programa));
        setNElem(getNElem() + 1);
    }

    private NoAVL insereNoAVL(NoAVL noAtual, ProgramaNetFlix programa) {
        if (noAtual == null) {
            return new NoAVL(programa, 0, null, null, null);
        }

        int compareResult = programa.getId().compareTo(noAtual.getElement().getId());

        if (compareResult < 0) {
            noAtual.setLeft(insereNoAVL(noAtual.getLeft(), programa));
        } else if (compareResult > 0) {
            noAtual.setRight(insereNoAVL(noAtual.getRight(), programa));
        } else {
            // Handle duplicate IDs if needed
        }

        noAtual.setFb(calcularFatorBalanceamento(noAtual));
        return balancear(noAtual);
    }

    private int calcularFatorBalanceamento(NoAVL no) {
        if (no == null) {
            return 0;
        }

        int alturaEsquerda = calcularAlturaAVL(no.getLeft());
        int alturaDireita = calcularAlturaAVL(no.getRight());

        return alturaEsquerda - alturaDireita;
    }

    public int getHeight() {
        return calcularAlturaAVL(raiz);
    }

    private int calcularAlturaAVL(NoAVL no) {
        if (no == null) {
            return 0;
        }

        int alturaEsquerda = calcularAlturaAVL(no.getLeft());
        int alturaDireita = calcularAlturaAVL(no.getRight());

        return 1 + Math.max(alturaEsquerda, alturaDireita);
    }

    private NoAVL balancear(NoAVL no) {
        int fatorBalanceamento = no.getFb();

        if (fatorBalanceamento < -1) {
            if (calcularFatorBalanceamento(no.getRight()) > 0) {
                no.setRight(rotacaoDireita(no.getRight()));
            }
            return rotacaoEsquerda(no);
        } else if (fatorBalanceamento > 1) {
            if (calcularFatorBalanceamento(no.getLeft()) < 0) {
                no.setLeft(rotacaoEsquerda(no.getLeft()));
            }
            return rotacaoDireita(no);
        }

        return no;
    }

    private NoAVL rotacaoEsquerda(NoAVL desbA) {
        NoAVL aux = desbA.getRight();
        desbA.setRight(aux.getLeft());
        aux.setLeft(desbA);
        aux.setFb(0);
        desbA.setFb(0);
        return aux;
    }

    private NoAVL rotacaoDireita(NoAVL desbA) {
        NoAVL aux = desbA.getLeft();
        desbA.setLeft(aux.getRight());
        aux.setRight(desbA);
        aux.setFb(0);
        desbA.setFb(0);
        return aux;
    }

    public void printTree() {
        printTreeRecursively(raiz, null);
    }

    private void printTreeRecursively(NoAVL currentNode, NoAVL parent) {
        if (currentNode == null) {
            return;
        }

        String parentId = (parent != null) ? parent.getElement().getId() : "null";
        String leftChildId = (currentNode.getLeft() != null) ? currentNode.getLeft().getElement().getId() : "null";
        String rightChildId = (currentNode.getRight() != null) ? currentNode.getRight().getElement().getId() : "null";

        System.out.println("Node ID: " + currentNode.getElement().getId() +
                ", Parent Node ID: " + parentId +
                ", Left Child ID: " + leftChildId +
                ", Right Child ID: " + rightChildId);

        // Recursively print the left and right subtrees
        printTreeRecursively(currentNode.getLeft(), currentNode);
        printTreeRecursively(currentNode.getRight(), currentNode);
    }

    public int countNodes() {
        return countNodesRecursively(raiz);
    }

    private int countNodesRecursively(NoAVL currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftCount = countNodesRecursively(currentNode.getLeft());
        int rightCount = countNodesRecursively(currentNode.getRight());

        return leftCount + rightCount + 1;
    }

    public int getHeightLeft() {
        return calcularAlturaAVL(raiz.getLeft());
    }

    public int getHeightRight() {
        return calcularAlturaAVL(raiz.getRight());
    }

    public boolean removeNode(String programId) {
        if (raiz != null) {
            setNoRaiz(removeNodeById(raiz, programId));
            setNElem(getNElem() - 1);
            return true;
        }
        return false;
    }

    private NoAVL removeNodeById(NoAVL currentNode, String programId) {
        if (currentNode == null) {
            return currentNode;
        }

        int compareResult = programId.compareTo(currentNode.getElement().getId());

        if (compareResult < 0) {
            currentNode.setLeft(removeNodeById(currentNode.getLeft(), programId));
        } else if (compareResult > 0) {
            currentNode.setRight(removeNodeById(currentNode.getRight(), programId));
        } else {
            // Node to be deleted found
            if (currentNode.getLeft() == null) {
                return currentNode.getRight();
            } else if (currentNode.getRight() == null) {
                return currentNode.getLeft();
            }

            // Node with two children: get the in-order successor
            currentNode.setElement(findInOrderSuccessor(currentNode.getRight()));
            currentNode.setRight(removeNodeById(currentNode.getRight(), currentNode.getElement().getId()));
        }

        currentNode.setFb(calcularFatorBalanceamento(currentNode));
        return balancear(currentNode);
    }

    private ProgramaNetFlix findInOrderSuccessor(NoAVL currentNode) {
        ProgramaNetFlix inOrderSuccessor = currentNode.getElement();
        while (currentNode.getLeft() != null) {
            inOrderSuccessor = currentNode.getLeft().getElement();
            currentNode = currentNode.getLeft();
        }
        return inOrderSuccessor;
    }

    public List<ProgramaNetFlix> getTopProgramasPorGeneroEClassificacao(String genero, String maxClassificacao,
            int topQuantidade) {
        List<ProgramaNetFlix> programas = new ArrayList<>();
        getTopProgramasPorGeneroEClassificacaoRecursivo(raiz, genero, maxClassificacao, topQuantidade, programas);
        ordenarPorMelhorNota(programas); // Classificar por imdbScore no final
        return programas.subList(0, Math.min(programas.size(), topQuantidade));
    }

    private void getTopProgramasPorGeneroEClassificacaoRecursivo(NoAVL no, String genero, String classificacao,
            int topQuantidade, List<ProgramaNetFlix> programas) {
        if (no != null && programas.size() < topQuantidade) {
            getTopProgramasPorGeneroEClassificacaoRecursivo(no.getLeft(), genero, classificacao, topQuantidade,
                    programas);

            ProgramaNetFlix programa = no.getElement();
            if (programa != null && programa.getGeneros().contains(genero)
                    && classificacao.equals(programa.getAgeCertification())) {
                if (programa.getImdbScore() != null) {
                    adicionarProgramaSeMelhorNota(programa, programas, topQuantidade);
                }
            }

            getTopProgramasPorGeneroEClassificacaoRecursivo(no.getRight(), genero, classificacao, topQuantidade,
                    programas);
        }
    }

    private void ordenarPorMelhorNota(List<ProgramaNetFlix> programas) {
        programas.sort((p1, p2) -> {
            String nota1Str = p1.getImdbScore();
            String nota2Str = p2.getImdbScore();

            // Ignora filmes com pontuação nula
            if (nota1Str == null && nota2Str == null) {
                return 0; // Notas nulas, considera iguais
            } else if (nota1Str == null) {
                return 1; // Nota1 é nula, coloca p1 no final
            } else if (nota2Str == null) {
                return -1; // Nota2 é nula, coloca p2 no final
            }

            // Ambas as notas são não nulas, compara normalmente
            double nota1 = Double.parseDouble(nota1Str);
            double nota2 = Double.parseDouble(nota2Str);
            return Double.compare(nota2, nota1);
        });
    }

    private void adicionarProgramaSeMelhorNota(ProgramaNetFlix novoPrograma, List<ProgramaNetFlix> programas,
            int topQuantidade) {
        if (programas.size() < topQuantidade) {
            programas.add(novoPrograma);
            ordenarPorMelhorNota(programas);
        } else {
            ProgramaNetFlix piorNota = programas.get(programas.size() - 1);
            String novaNotaStr = novoPrograma.getImdbScore();
            String piorNotaStr = piorNota.getImdbScore();

            // Verifica se as strings não são nulas antes de converter para double
            if (novaNotaStr != null && piorNotaStr != null) {
                double novaNota = Double.parseDouble(novaNotaStr);
                double piorNotaValor = Double.parseDouble(piorNotaStr);

                if (novaNota > piorNotaValor) {
                    programas.remove(piorNota);
                    programas.add(novoPrograma);
                    ordenarPorMelhorNota(programas);
                }
            }
        }
    }

    // top 5 filmes por País e Ano, imdbScore decrescente
    public List<ProgramaNetFlix> getTopMoviesPorPaisEAno(String pais, String ano, int topCount) {
        List<ProgramaNetFlix> programas = new ArrayList<>();
        getTopMoviesPorPaisEAno(raiz, pais, ano, programas, topCount);
        ordenarPorMelhorNota(programas); // Classificar por imdbScore no final
        return programas.subList(0, Math.min(programas.size(), topCount));
    }

    private void getTopMoviesPorPaisEAno(NoAVL node, String pais, String ano, List<ProgramaNetFlix> programas,
            int topCount) {
        if (node != null && programas.size() < topCount) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null && "MOVIE".equalsIgnoreCase(programa.getShowType()) &&
                    programa.getProductionCountries().contains(pais) &&
                    programa.getReleaseYear().equals(ano)) {
                if (programa.getImdbScore() != null) {
                    programas.add(programa);
                }
            }
            getTopMoviesPorPaisEAno(node.getRight(), pais, ano, programas, topCount);
            getTopMoviesPorPaisEAno(node.getLeft(), pais, ano, programas, topCount);
        }
    }

    public List<String> getTopReleaseYearsWithHighestScores(int topCount) {
        Map<String, Double> releaseYearScoresMap = new HashMap<>();
        populateReleaseYearScoresMap(raiz, releaseYearScoresMap);

        List<String> topReleaseYears = new ArrayList<>();
        releaseYearScoresMap.entrySet()
                .stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                .limit(topCount)
                .forEach(entry -> topReleaseYears.add(entry.getKey()));

        return topReleaseYears;
    }

    private void populateReleaseYearScoresMap(NoAVL node, Map<String, Double> releaseYearScoresMap) {
        if (node != null) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null && programa.getReleaseYear() != null && programa.getImdbScore() != null) {
                String releaseYear = programa.getReleaseYear();
                double imdbScore = Double.parseDouble(programa.getImdbScore());

                // Atualiza a pontuação para o releaseYear
                if (releaseYearScoresMap.containsKey(releaseYear)) {
                    double currentScore = releaseYearScoresMap.get(releaseYear);
                    releaseYearScoresMap.put(releaseYear, currentScore + imdbScore);
                } else {
                    releaseYearScoresMap.put(releaseYear, imdbScore);
                }
            }

            populateReleaseYearScoresMap(node.getLeft(), releaseYearScoresMap);
            populateReleaseYearScoresMap(node.getRight(), releaseYearScoresMap);
        }
    }

    public List<ProgramaNetFlix> getTopProgramsByGenre(int topCount) {
        Map<String, Double> genreScoresMap = new HashMap<>();
        populateGenreScoresMap(raiz, genreScoresMap);

        List<ProgramaNetFlix> topPrograms = new ArrayList<>();
        for (Map.Entry<String, Double> entry : genreScoresMap.entrySet()) {
            String genre = entry.getKey();
            double totalScore = entry.getValue();

            // Obter os top programas para o gênero
            List<ProgramaNetFlix> topProgramsForGenre = getTopProgramsForGenre(raiz, genre, topCount);

            // Adicionar informações à lista
            for (ProgramaNetFlix programa : topProgramsForGenre) {
                topPrograms.add(programa);
            }
        }

        return topPrograms;
    }

    private void populateGenreScoresMap(NoAVL node, Map<String, Double> genreScoresMap) {
        if (node != null) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null) {
                for (String genre : programa.getGeneros()) {
                    double imdbScore = programa.getImdbScore() != null ? Double.parseDouble(programa.getImdbScore())
                            : 0;

                    // Atualiza a pontuação para o gênero
                    if (genreScoresMap.containsKey(genre)) {
                        double currentScore = genreScoresMap.get(genre);
                        genreScoresMap.put(genre, currentScore + imdbScore);
                    } else {
                        genreScoresMap.put(genre, imdbScore);
                    }
                }
            }

            populateGenreScoresMap(node.getLeft(), genreScoresMap);
            populateGenreScoresMap(node.getRight(), genreScoresMap);
        }
    }

    private List<ProgramaNetFlix> getTopProgramsForGenre(NoAVL node, String genre, int topCount) {
        List<ProgramaNetFlix> programs = new ArrayList<>();
        getTopProgramsForGenreRecursively(node, genre, programs, topCount);
        return programs;
    }

    private void getTopProgramsForGenreRecursively(NoAVL node, String genre, List<ProgramaNetFlix> programs,
            int topCount) {
        if (node != null && programs.size() < topCount) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null && programa.getGeneros().contains(genre)) {
                programs.add(programa);
            }

            getTopProgramsForGenreRecursively(node.getLeft(), genre, programs, topCount);
            getTopProgramsForGenreRecursively(node.getRight(), genre, programs, topCount);
        }
    }

    public void printBestProgramForEachGenre() {
        Map<String, ProgramaNetFlix> bestProgramsByGenre = new HashMap<>();
        List<String> genres = Arrays.asList("action", "animation", "comedy", "crime", "documentation",
                "drama", "european", "fantasy", "history", "horror", "music", "romance", "scifi",
                "thriller", "war", "western");

        for (String genre : genres) {
            findBestProgramForGenreAndUpdateMap(raiz, genre, bestProgramsByGenre);
            ProgramaNetFlix bestProgram = bestProgramsByGenre.get(genre);

            if (bestProgram != null) {
                System.out.println("Genre: " + genre +
                        " --> Title: " + bestProgram.getTitulo() +
                        " --> IMDb Score: " + bestProgram.getImdbScore());
            } else {
                System.out.println("Genre: " + genre + " --> No program found");
            }
        }
    }

    private void findBestProgramForGenreAndUpdateMap(NoAVL node, String genre,
            Map<String, ProgramaNetFlix> bestProgramsByGenre) {
        if (node != null) {
            ProgramaNetFlix program = node.getElement();

            if (program != null && program.getGeneros().contains(genre)) {
                double currentScore = program.getImdbScore() != null ? Double.parseDouble(program.getImdbScore()) : 0;
                double bestScore = bestProgramsByGenre.containsKey(genre)
                        ? Double.parseDouble(bestProgramsByGenre.get(genre).getImdbScore())
                        : 0;

                if (currentScore > bestScore) {
                    bestProgramsByGenre.put(genre, program);
                }
            }

            findBestProgramForGenreAndUpdateMap(node.getLeft(), genre, bestProgramsByGenre);
            findBestProgramForGenreAndUpdateMap(node.getRight(), genre, bestProgramsByGenre);
        }
    }

    public void countMoviesAndShowsByGenre() {
        for (String genre : Arrays.asList("action", "animation", "comedy", "crime", "documentation",
                "drama", "european", "fantasy", "history", "horror", "music", "romance",
                "scifi", "thriller", "war", "western")) {
            int[] counts = new int[2]; // Array to hold movieCount at index 0 and showCount at index 1

            countMoviesAndShowsByGenreRecursively(raiz, genre.toLowerCase(), counts);

            System.out.println(genre.toUpperCase() + ": " + counts[0] + " (Movies) and " + counts[1] + " (Shows)");
        }
    }

    private void countMoviesAndShowsByGenreRecursively(NoAVL node, String genre, int[] counts) {
        if (node != null) {
            ProgramaNetFlix program = node.getElement();

            if (program != null && program.getGeneros() != null && program.getGeneros().contains(genre)) {
                String showType = program.getShowType();

                if ("movie".equalsIgnoreCase(showType)) {
                    counts[0]++; // Increment movieCount
                } else if ("show".equalsIgnoreCase(showType)) {
                    counts[1]++; // Increment showCount
                }
            }

            // Continue traversal for both left and right subtrees
            countMoviesAndShowsByGenreRecursively(node.getLeft(), genre, counts);
            countMoviesAndShowsByGenreRecursively(node.getRight(), genre, counts);
        }
    }

    public List<ProgramaNetFlix> getShowsWithNumTemporadas(int numTemporadasInput) {
        List<ProgramaNetFlix> shows = new ArrayList<>();
        getShowsWithNumTemporadasRecursively(raiz, numTemporadasInput, shows);
        return shows;
    }

    private void getShowsWithNumTemporadasRecursively(NoAVL node, int numTemporadasInput, List<ProgramaNetFlix> shows) {
        if (node != null) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null && "SHOW".equalsIgnoreCase(programa.getShowType())) {
                // int numTemporadas = Integer.parseInt(programa.getTemporadas());
                double numTemporadasDouble = Double.parseDouble(programa.getTemporadas());
                int numTemporadas = (int) numTemporadasDouble;
                if (numTemporadas == numTemporadasInput) {
                    shows.add(programa);
                }
            }

            getShowsWithNumTemporadasRecursively(node.getLeft(), numTemporadasInput, shows);
            getShowsWithNumTemporadasRecursively(node.getRight(), numTemporadasInput, shows);
        }
    }

        public void saveDataToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            saveDataRecursively(raiz, writer);
            System.out.println("Dados salvos com sucesso no arquivo: " + fileName);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados no arquivo: " + e.getMessage());
        }
    }

    private void saveDataRecursively(NoAVL node, BufferedWriter writer) throws IOException {
        if (node != null) {
            ProgramaNetFlix programa = node.getElement();
            if (programa != null) {
                // Customize the format based on your data structure
                String data = programa.getId() + "," +
                programa.getTitulo() + "," +
                programa.getShowType() + "," +
                programa.getDescricao() + "," +
                programa.getReleaseYear() + "," +
                programa.getAgeCertification() + "," +
                programa.getRuntime() + "," +
                String.join(",", programa.getGeneros()) + "," +
                String.join(",", programa.getProductionCountries()) + "," +
                programa.getTemporadas() + "," +
                programa.getImdbId() + "," +
                programa.getImdbScore() + "," +
                programa.getImdbVotes() + "," +
                programa.getTmdbPopularity() + "," +
                programa.getTmdbScore();
                writer.write(data);
                writer.newLine();
            }

            saveDataRecursively(node.getLeft(), writer);
            saveDataRecursively(node.getRight(), writer);
        }
    }

}
