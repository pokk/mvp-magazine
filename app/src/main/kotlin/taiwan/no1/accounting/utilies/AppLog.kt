package taiwan.no1.accounting.utilies

import android.util.Log

/**
 * Log Module
 *
 * @author Jieyi Wu
 * @version 1.1.0
 * @since 2015/08/01
 */

object AppLog {
    private val COLON = ":"
    private val LEFT_PARENTHESIS = "("
    private val RIGHT_PARENTHESIS = ")"
    private val NULL_STRING = ""
    private val SPACE_STRING = " "
    private val TAG = "MY_LOG"  // TAG
    private val IS_DEBUG = java.lang.Boolean.TRUE  // Debug mode's switch, default is turn off.
    private val lockLog = Any()  // Avoid the threading's race condition.
    private val strBuilder = StringBuilder()  // String builder.

    // Log priority level.
    private enum class MsgLevel {
        // Verbose
        v,
        // Debug
        d,
        // Info
        i,
        // Warn
        w,
        // Error
        e
    }

    /**
     * Wrap the checking condition and msg log.
     */
    private class LogWrapper {
        /**
         * Check debug mode and sync.
         *
         * @param cls        class name.
         * @param methodName method name.
         * @param msg        message content.
         * @return success or fail.
         */
        fun debugCheck(cls: Class<*>, methodName: String, msg: Any): Boolean {
            // Checking the debug mode.
            if (IS_DEBUG!!) {
                // Avoid the race condition.
                synchronized(lockLog) {
                    return this.logMsg(cls, methodName, msg)
                }
            }
            return true
        }

        /**
         * Invoke the Log method to show the log msg.
         *
         * @param cls        class name.
         * @param methodName method name.
         * @param msg        message content.
         * @return success or fail.
         */
        private fun logMsg(cls: Class<*>, methodName: String, msg: Any): Boolean {
            try {
                val method = cls.getDeclaredMethod(methodName, String::class.java, String::class.java)
                method.invoke(null, TAG, msg)
            }
            catch (e: Exception) {
                e.printStackTrace()
                return false
            }

            return true
        }
    }

    /**
     * VERBOSE log.
     *
     * @param msg output message
     */
    fun v(vararg msg: Any) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.v.name, getLogMsg(msgString))
    }

    /**
     * DEBUG log.
     *
     * @param msg output message
     */
    fun d(vararg msg: Any) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.d.name, getLogMsg(msgString))
    }

    /**
     * INFORMATION log.
     *
     * @param msg output message
     */
    fun i(vararg msg: Any) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.i.name, getLogMsg(msgString))
    }

    /**
     * WARNING log.
     *
     * @param msg output message
     */
    fun w(vararg msg: Any) {
        val msgString = combineInputArguments(*msg)
        LogWrapper().debugCheck(Log::class.java, MsgLevel.w.name, getLogMsg(msgString))
    }

    /**
     * ERROR log.
     *
     * @param msg output message
     */
    fun e(vararg msg: Any) {
        if (1 == msg.size && msg[0] is Exception)
            LogWrapper().debugCheck(Log::class.java, MsgLevel.e.name, getExceptionMsg(msg[0] as Exception))
        else {
            val msgString = combineInputArguments(*msg)
            LogWrapper().debugCheck(Log::class.java, MsgLevel.e.name, getLogMsg(msgString))
        }
    }

    /**
     * Combine arguments to a string.
     *
     * @param values multiple arguments.
     * @return output string message
     */
    private fun combineInputArguments(vararg values: Any): String {
        val stringBuilder = StringBuilder()
        for (o in values) {
            stringBuilder.append(o.toString()).append(SPACE_STRING)
        }
        return stringBuilder.toString()
    }

    /**
     * Combine the meta information and msg.
     *
     * @param msg log message.
     * @return meta information + msg.
     */
    private fun getLogMsg(msg: String?): String = getMetaInfo(null == msg) + COLON + (msg ?: NULL_STRING)

    /**
     * Combine the meta information and exception msg.
     *
     * @param msg exception msg.
     * @return meta information + exception msg.
     */
    private fun getExceptionMsg(msg: Exception): String {
        val stringBuilder = StringBuilder()
        // Stream.of(msg).forEach(str -> stringBuilder.append(str).append("\n"));
        for (str in msg.stackTrace) {
            stringBuilder.append(str).append("\n")
        }

        return stringBuilder.toString()
    }


    /**
     * Get the file name, method name, line number.
     *
     * @param isNullString the input string is null no not.
     * @return the format is "methodName(fileName:line)"
     */
    private fun getMetaInfo(isNullString: Boolean): String {
        // Because the function nest so we can get in stack index 3. 
        val tempIndex = 3
        val stackIndex = if (isNullString) tempIndex + 1 else tempIndex
        val ste = Throwable().stackTrace
        strBuilder.setLength(0)

        return strBuilder.append(ste[stackIndex].methodName)
                .append(LEFT_PARENTHESIS)
                .append(ste[stackIndex].fileName)
                .append(COLON)
                .append(ste[stackIndex].lineNumber)
                .append(RIGHT_PARENTHESIS)
                .toString()
    }
}
