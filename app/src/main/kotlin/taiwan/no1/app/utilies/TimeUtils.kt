package taiwan.no1.app.utilies

/**
 * Time convert utility.
 *
 * @author  jieyi
 * @version 0.0.1
 * @since   2017/01/24
 */

object TimeUtils {
    data class DateTime(var day: Int = 0, var hours: Int = 0, var mins: Int = 0, var secs: Int = 0)
    enum class TimeType {
        Min
    }

    fun number2Time(number: Double, type: TimeType): DateTime = when (type) {
        TimeType.Min -> number2TimeFromMins(number)
        else -> number2TimeFromMins(number)
    }

    private fun number2TimeFromMins(number: Double): DateTime = DateTime().apply {
        day = (number / 24 / 60).toInt()
        hours = (number / 60).toInt()
        mins = (number % 60).toInt()
    }
}