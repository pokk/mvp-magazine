package taiwan.no1.app.constant

/**
 *
 * @author  Jieyi
 * @since   1/11/17
 */

object Constant {
    enum class Gender(val jobName: String) {
        Unknown("Unknown"),
        Female("Actress"),
        Male("Actor"),
    }

    enum class Genres(val id: Int, val categoryName: String) {
        Action(28, "Action"),
        Adventure(12, "Adventure"),
        Animation(16, "Animation"),
        Comedy(35, "Comedy"),
        Crime(80, "Crime"),
        Documentary(99, "Documentary"),
        Drama(18, "Drama"),
        Family(10751, "Family"),
        Fantasy(14, "Fantasy"),
        History(36, "History"),
        Horror(27, "Horror"),
        Music(10402, "Music"),
        Mystery(9648, "Mystery"),
        Romance(10749, "Romance"),
        Science_Fiction(878, "Science Fiction"),
        TV_Movie(10770, "TV Movie"),
        Thriller(53, "Thriller"),
        War(10752, "War"),
        Western(37, "Western"),
    }
}