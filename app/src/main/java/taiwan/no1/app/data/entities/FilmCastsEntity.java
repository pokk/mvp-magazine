package taiwan.no1.app.data.entities;

import java.util.List;

/**
 * Related the movie with the casts information.
 *
 * @author Jieyi
 * @since 12/29/16
 */

public class FilmCastsEntity {
    // The casts information in a movie.
    private List<CastBean> cast;
    // The crews information in a movie.
    private List<CrewBean> crew;

    //region Getter and Setter
    public List<CastBean> getCast() { return cast;}

    public void setCast(List<CastBean> cast) { this.cast = cast;}

    public List<CrewBean> getCrew() { return crew;}

    public void setCrew(List<CrewBean> crew) { this.crew = crew;}
    //endregion

    public static class CastBean {
        private int cast_id;
        private String character;
        private String credit_id;
        private int id;
        private String name;
        private int order;
        private String profile_path;

        //region Getter and Setter
        public int getCast_id() { return cast_id;}

        public void setCast_id(int cast_id) { this.cast_id = cast_id;}

        public String getCharacter() { return character;}

        public void setCharacter(String character) { this.character = character;}

        public String getCredit_id() { return credit_id;}

        public void setCredit_id(String credit_id) { this.credit_id = credit_id;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public int getOrder() { return order;}

        public void setOrder(int order) { this.order = order;}

        public String getProfile_path() { return profile_path;}

        public void setProfile_path(String profile_path) { this.profile_path = profile_path;}
        //endregion

        @Override
        public String toString() {
            return "CastBean{" +
                    "cast_id=" + cast_id +
                    ", character='" + character + '\'' +
                    ", credit_id='" + credit_id + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", profile_path='" + profile_path + '\'' +
                    '}';
        }
    }

    public static class CrewBean {
        private String credit_id;
        private String department;
        private int id;
        private String job;
        private String name;
        private String profile_path;

        //region Getter and Setter
        public String getCredit_id() { return credit_id;}

        public void setCredit_id(String credit_id) { this.credit_id = credit_id;}

        public String getDepartment() { return department;}

        public void setDepartment(String department) { this.department = department;}

        public int getId() { return id;}

        public void setId(int id) { this.id = id;}

        public String getJob() { return job;}

        public void setJob(String job) { this.job = job;}

        public String getName() { return name;}

        public void setName(String name) { this.name = name;}

        public String getProfile_path() { return profile_path;}

        public void setProfile_path(String profile_path) { this.profile_path = profile_path;}
        //endregion

        @Override
        public String toString() {
            return "CrewBean{" +
                    "credit_id='" + credit_id + '\'' +
                    ", department='" + department + '\'' +
                    ", id=" + id +
                    ", job='" + job + '\'' +
                    ", name='" + name + '\'' +
                    ", profile_path='" + profile_path + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FilmCastsEntity{" +
                "cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}
