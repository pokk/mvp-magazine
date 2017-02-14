package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * The related movie, tv and combined of a cast data class.
 *
 * @author Jieyi
 * @since 2017/01/04
 */

public class CreditsInFilmEntity {
    // Casting a role in a related movie or tv list.
    private List<CastInFilmBean> cast;
    // Taking a crew in a related movie or tv list. 
    private List<CrewInFlimBean> crew;

    //region Getter and Setter
    public List<CastInFilmBean> getCast() { return cast;}

    public void setCast(List<CastInFilmBean> cast) { this.cast = cast;}

    public List<CrewInFlimBean> getCrew() { return crew;}

    public void setCrew(List<CrewInFlimBean> crew) { this.crew = crew;}
    //endregion

    public static class CastInFilmBean {
        private boolean adult;
        private String character;
        private String credit_id;
        private int id;
        private String original_title;
        private String poster_path;
        private String release_date;
        private String title;
        private String media_type;
        private int episode_count;
        private String first_air_date;
        private String name;
        private String original_name;

        //region Getter and Setter
        public boolean isAdult() { return adult;}

        public void setAdult(boolean adult) { this.adult = adult;}

        public String getCharacter() { return character;}

        public void setCharacter(String character) { this.character = character;}

        public String getCredit_id() { return credit_id;}

        public void setCredit_id(String credit_id) { this.credit_id = credit_id;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getOriginal_title() { return original_title;}

        public void setOriginal_title(String original_title) { this.original_title = original_title;}

        public String getPoster_path() { return poster_path;}

        public void setPoster_path(String poster_path) { this.poster_path = poster_path;}

        public String getRelease_date() { return release_date;}

        public void setRelease_date(String release_date) { this.release_date = release_date;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getMedia_type() { return media_type;}

        public void setMedia_type(String media_type) { this.media_type = media_type;}

        public int getEpisode_count() { return episode_count;}

        public void setEpisode_count(int episode_count) { this.episode_count = episode_count;}

        public String getFirst_air_date() { return first_air_date;}

        public void setFirst_air_date(String first_air_date) { this.first_air_date = first_air_date;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getOriginal_name() { return original_name;}

        public void setOriginal_name(String original_name) { this.original_name = original_name;}
        //endregion

        @Override
        public String toString() {
            return "CastInFilmBean{" +
                    "adult=" + adult +
                    ", character='" + character + '\'' +
                    ", credit_id='" + credit_id + '\'' +
                    ", id=" + id +
                    ", original_title='" + original_title + '\'' +
                    ", poster_path='" + poster_path + '\'' +
                    ", release_date='" + release_date + '\'' +
                    ", title='" + title + '\'' +
                    ", media_type='" + media_type + '\'' +
                    ", episode_count=" + episode_count +
                    ", first_air_date='" + first_air_date + '\'' +
                    ", name='" + name + '\'' +
                    ", original_name='" + original_name + '\'' +
                    '}';
        }
    }

    public static class CrewInFlimBean {
        private boolean adult;
        private String credit_id;
        private String department;
        private int id;
        private String job;
        private String original_title;
        private String poster_path;
        private String release_date;
        private String title;
        private String media_type;

        //region Getter and Setter
        public boolean isAdult() { return adult;}

        public void setAdult(boolean adult) { this.adult = adult;}

        public String getCredit_id() { return credit_id;}

        public void setCredit_id(String credit_id) { this.credit_id = credit_id;}

        public String getDepartment() { return department;}

        public void setDepartment(String department) { this.department = department;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getJob() { return job;}

        public void setJob(String job) { this.job = job;}

        public String getOriginal_title() { return original_title;}

        public void setOriginal_title(String original_title) { this.original_title = original_title;}

        public String getPoster_path() { return poster_path;}

        public void setPoster_path(String poster_path) { this.poster_path = poster_path;}

        public String getRelease_date() { return release_date;}

        public void setRelease_date(String release_date) { this.release_date = release_date;}

        public String getTitle() { return title;}

        public void setTitle(String title) { this.title = title;}

        public String getMedia_type() { return media_type;}

        public void setMedia_type(String media_type) { this.media_type = media_type;}
        //endregion

        @Override
        public String toString() {
            return "CrewInFlimBean{" +
                    "adult=" + adult +
                    ", credit_id='" + credit_id + '\'' +
                    ", department='" + department + '\'' +
                    ", id=" + id +
                    ", job='" + job + '\'' +
                    ", original_title='" + original_title + '\'' +
                    ", poster_path='" + poster_path + '\'' +
                    ", release_date='" + release_date + '\'' +
                    ", title='" + title + '\'' +
                    ", media_type='" + media_type + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CreditsInFilmEntity{" +
                "cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}
