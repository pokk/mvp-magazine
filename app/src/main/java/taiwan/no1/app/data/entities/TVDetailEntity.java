package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Created by weian on 2017/1/11.
 */

public class TVDetailEntity {

    /**
     * backdrop_path : /mUkuc2wyV9dHLG0D0Loaw5pO2s8.jpg
     * created_by : [{"id":9813,"name":"David Benioff","profile_path":"/8CuuNIKMzMUL1NKOPv9AqEwM7og.jpg"},{"id":228068,"name":"D. B. Weiss","profile_path":"/caUAtilEe06OwOjoQY3B7BgpARi.jpg"}]
     * episode_run_time : [60]
     * first_air_date : 2011-04-17
     * genres : [{"id":10765,"name":"Sci-Fi & Fantasy"},{"id":10759,"name":"Action & Adventure"},{"id":18,"name":"Drama"}]
     * homepage : http://www.hbo.com/game-of-thrones
     * id : 1399
     * in_production : true
     * languages : ["es","en","de"]
     * last_air_date : 2016-06-26
     * name : Game of Thrones
     * networks : [{"id":49,"name":"HBO"}]
     * number_of_episodes : 51
     * number_of_seasons : 6
     * origin_country : ["US"]
     * original_language : en
     * original_name : Game of Thrones
     * overview : Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.
     * popularity : 28.780826
     * poster_path : /jIhL6mlT7AblhbHJgEoiBIOUVl1.jpg
     * production_companies : [{"name":"Home Box Office (HBO)","id":3268},{"name":"Generator Entertainment","id":5820},{"name":"Television 360","id":12525},{"name":"Bighead Littlehead","id":12526},{"name":"Revolution Sun Studios","id":76043}]
     * seasons : [{"air_date":"2010-12-05","episode_count":13,"id":3627,"poster_path":"/kMTcwNRfFKCZ0O2OaBZS0nZ2AIe.jpg","season_number":0},{"air_date":"2011-04-17","episode_count":10,"id":3624,"poster_path":"/olJ6ivXxCMq3cfujo1IRw30OrsQ.jpg","season_number":1},{"air_date":"2012-04-01","episode_count":10,"id":3625,"poster_path":"/3U8IVLqitMHMuEAgkuz8qReguHd.jpg","season_number":2},{"air_date":"2013-03-31","episode_count":10,"id":3626,"poster_path":"/eVWAat0GqF6s5LLThrI7ClpKr96.jpg","season_number":3},{"air_date":"2014-04-06","episode_count":10,"id":3628,"poster_path":"/dniQ7zw3mbLJkd1U0gdFEh4b24O.jpg","season_number":4},{"air_date":"2015-04-12","episode_count":10,"id":62090,"poster_path":"/yKOltUHsp9X7dXWIm0hNGcIQa4G.jpg","season_number":5},{"air_date":"2016-04-24","episode_count":10,"id":71881,"poster_path":"/zvYrzLMfPIenxoq2jFY4eExbRv8.jpg","season_number":6}]
     * status : Returning Series
     * type : Scripted
     * vote_average : 7.9
     * vote_count : 1174
     */

    private String backdrop_path;
    private String first_air_date;
    private String homepage;
    private int id;
    private boolean in_production;
    private String last_air_date;
    private String name;
    private int number_of_episodes;
    private int number_of_seasons;
    private String original_language;
    private String original_name;
    private String overview;
    private double popularity;
    private String poster_path;
    private String status;
    private String type;
    private double vote_average;
    private int vote_count;
    private List<CreatedByBean> created_by;
    private List<Integer> episode_run_time;
    private List<GenresBean> genres;
    private List<String> languages;
    private List<NetworksBean> networks;
    private List<String> origin_country;
    private List<ProductionCompaniesBean> production_companies;
    private List<SeasonsBean> seasons;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<CreatedByBean> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<CreatedByBean> created_by) {
        this.created_by = created_by;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<NetworksBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksBean> networks) {
        this.networks = networks;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<ProductionCompaniesBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public static class CreatedByBean {
        /**
         * id : 9813
         * name : David Benioff
         * profile_path : /8CuuNIKMzMUL1NKOPv9AqEwM7og.jpg
         */

        private int id;
        private String name;
        private String profile_path;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public void setProfile_path(String profile_path) {
            this.profile_path = profile_path;
        }
    }

    public static class GenresBean {
        /**
         * id : 10765
         * name : Sci-Fi & Fantasy
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class NetworksBean {
        /**
         * id : 49
         * name : HBO
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ProductionCompaniesBean {
        /**
         * name : Home Box Office (HBO)
         * id : 3268
         */

        private String name;
        private int id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static class SeasonsBean {
        /**
         * air_date : 2010-12-05
         * episode_count : 13
         * id : 3627
         * poster_path : /kMTcwNRfFKCZ0O2OaBZS0nZ2AIe.jpg
         * season_number : 0
         */

        private String air_date;
        private int episode_count;
        private int id;
        private String poster_path;
        private int season_number;

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_count() {
            return episode_count;
        }

        public void setEpisode_count(int episode_count) {
            this.episode_count = episode_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public int getSeason_number() {
            return season_number;
        }

        public void setSeason_number(int season_number) {
            this.season_number = season_number;
        }
    }
}
