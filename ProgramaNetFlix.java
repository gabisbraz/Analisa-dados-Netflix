import java.util.List;

public class ProgramaNetFlix {
    private String id;
    private String titulo;
    private String showType;
    private String descricao;
    private String releaseYear;
    private String ageCertification;
    private String runtime;
    private List<String> generos;
    private List<String> productionCountries;
    private String temporadas;
    private String imdbId;
    private String imdbScore;
    private String imdbVotes;
    private String tmdbPopularity;
    private String tmdbScore;

    public ProgramaNetFlix(String id, String titulo, String showType, String descricao, String releaseYear,
            String ageCertification, String runtime, List<String> generos, List<String> productionCountries,
            String temporadas, String imdbId, String imdbScore, String imdbVotes, String tmdbPopularity,
            String tmdbScore) {

        this.id = id;
        this.titulo = titulo;
        this.showType = showType;
        this.descricao = descricao;
        this.releaseYear = releaseYear;
        this.ageCertification = ageCertification;
        this.runtime = runtime;
        this.generos = generos;
        this.productionCountries = productionCountries;
        this.temporadas = temporadas;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getAgeCertification() {
        return ageCertification;
    }

    public void setAgeCertification(String ageCertification) {
        this.ageCertification = ageCertification;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(String temporadas) {
        this.temporadas = temporadas;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(String imdbScore) {
        this.imdbScore = imdbScore;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getTmdbPopularity() {
        return tmdbPopularity;
    }

    public void setTmdbPopularity(String tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public String getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(String tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                "\nTítulo: " + titulo +
                "\nTipo de Show: " + showType +
                "\nDescrição: " + descricao +
                "\nAno de Lançamento: " + releaseYear +
                "\nClassificação Etária: " + ageCertification +
                "\nDuração: " + runtime +
                "\nGêneros: " + generos +
                "\nPaíses de Produção: " + productionCountries +
                "\nTemporadas: " + temporadas +
                "\nID IMDb: " + imdbId +
                "\nPontuação IMDb: " + imdbScore +
                "\nVotos IMDb: " + imdbVotes +
                "\nPopularidade TMDb: " + tmdbPopularity +
                "\nPontuação TMDb: " + tmdbScore;
    } 
}
