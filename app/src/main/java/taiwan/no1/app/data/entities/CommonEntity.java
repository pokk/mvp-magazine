package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Collect all common data class.
 *
 * @author jieyi
 * @since 2017/02/13
 */

public class CommonEntity {
    public static class BaseBean {
        private int id;
        private String name;

        //region Getter and Setter
        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}
        //endregion

        @Override
        public String toString() {
            return "BaseBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class CountriesBean {
        private String iso_3166_1;
        private String name;

        //region Getter and Setter
        public String getIso_3166_1() { return iso_3166_1;}

        public void setIso_3166_1(String iso_3166_1) { this.iso_3166_1 = iso_3166_1;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}
        //endregion

        @Override
        public String toString() {
            return "CountriesBean{" +
                    "iso_3166_1='" + iso_3166_1 + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class LanguagesBean {
        private String iso_639_1;
        private String name;

        //region Getter and Setter
        public String getIso_639_1() { return iso_639_1;}

        public void setIso_639_1(String iso_639_1) { this.iso_639_1 = iso_639_1;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}
        //endregion

        @Override
        public String toString() {
            return "LanguagesBean{" +
                    "iso_639_1='" + iso_639_1 + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static class ResultsListEntity<L> {
        private List<L> results;

        //region Getter and Setter
        public List<L> getResults() {
            return results;
        }

        public void setResults(List<L> results) {
            this.results = results;
        }
        //endregion

        @Override
        public String toString() {
            return "ResultsListEntity{" +
                    "results=" + results +
                    '}';
        }
    }

    public static class FilmBriefEntity {
        private String poster_path;
        private int id;
        private String backdrop_path;
        private double popularity;
        private double vote_average;
        private int vote_count;
        private List<Integer> genre_ids;
        private String original_language;
        private String overview;

        //region Getter and Setter
        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }
        //endregion

        @Override
        public String toString() {
            return "FilmBriefEntity{" +
                    "poster_path='" + poster_path + '\'' +
                    ", id=" + id +
                    ", backdrop_path='" + backdrop_path + '\'' +
                    ", popularity=" + popularity +
                    ", vote_average=" + vote_average +
                    ", vote_count=" + vote_count +
                    ", genre_ids=" + genre_ids +
                    ", original_language='" + original_language + '\'' +
                    ", overview='" + overview + '\'' +
                    '}';
        }
    }
}
