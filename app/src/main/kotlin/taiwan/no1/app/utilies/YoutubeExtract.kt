package taiwan.no1.app.utilies

import android.net.Uri
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.SparseArray
import android.webkit.MimeTypeMap
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.UnsupportedEncodingException
import java.net.URL
import java.net.URLDecoder
import java.util.*
import java.util.Arrays.asList
import javax.net.ssl.HttpsURLConnection

/**
 * @author Jieyi
 * @since 1/15/17
 */

class YoutubeExtract(private val mVideoIdentifier: String) {
    companion object {
        //region Fields
        val YOUTUBE_VIDEO_QUALITY_SMALL_240 = 36
        val YOUTUBE_VIDEO_QUALITY_MEDIUM_360 = 18
        val YOUTUBE_VIDEO_QUALITY_HD_720 = 22
        val YOUTUBE_VIDEO_QUALITY_HD_1080 = 37
        //endregion

        //region Private
        @Throws(UnsupportedEncodingException::class)
        private fun getQueryMap(queryString: String, charsetName: String): HashMap<String, String> {
            val map = HashMap<String, String>()

            val fields = queryString.split("&".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()

            for (field in fields) {
                val pair = field.split("=".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()
                if (pair.size == 2) {
                    val key = pair[0]
                    val value = URLDecoder.decode(pair[1], charsetName).replace('+', ' ')
                    map.put(key, value)
                }
            }

            return map
        }
        //endregion
    }

    //region Variables
    private val mElFields: MutableList<String> = ArrayList(asList("embedded", "detailpage", "vevo", ""))
    private var mConnection: HttpsURLConnection? = null
    private var mCancelled: Boolean = false
    var preferredVideoQualities: List<Int>? = null
    //endregion

    init {
        preferredVideoQualities = asList(YOUTUBE_VIDEO_QUALITY_MEDIUM_360,
                YOUTUBE_VIDEO_QUALITY_SMALL_240,
                YOUTUBE_VIDEO_QUALITY_HD_720,
                YOUTUBE_VIDEO_QUALITY_HD_1080)
    }

    //region Public
    fun startExtracting(listener: YouTubeExtractorListener?) {
        var elField = mElFields[0]
        mElFields.removeAt(0)
        if (elField.length > 0) {
            elField = "&el=" + elField
        }

        val language = Locale.getDefault().language

        val link = String.format(
                "https://www.youtube.com/get_video_info?video_id=%s%s&ps=default&eurl=&gl=US&hl=%s",
                mVideoIdentifier,
                elField,
                language)

        val youtubeExtractorThread = HandlerThread("YouTubeExtractorThread",
                THREAD_PRIORITY_BACKGROUND)
        youtubeExtractorThread.start()

        val youtubeExtractorHandler = Handler(youtubeExtractorThread.looper)

        val listenerHandler = Handler(Looper.getMainLooper())

        youtubeExtractorHandler.post {
            try {
                mConnection = URL(link).openConnection() as HttpsURLConnection
                mConnection!!.setRequestProperty("Accept-Language", language)

                val reader = BufferedReader(InputStreamReader(mConnection!!.inputStream))
                val builder = StringBuilder()

                if (!mCancelled) {
                    reader.readLine().forEach {
                        builder.append(it)
                    }
                }

                reader.close()

                if (!mCancelled) {
                    val result = getYouTubeResult(builder.toString())

                    listenerHandler.post {
                        if (!mCancelled && listener != null) {
                            listener.onSuccess(result)
                        }
                    }
                }
            }
            catch (e: Exception) {
                listenerHandler.post {
                    if (!mCancelled && listener != null) {
                        listener.onFailure(Error(e))
                    }
                }
            }
            finally {
                if (mConnection != null) {
                    mConnection!!.disconnect()
                }

                youtubeExtractorThread.quit()
            }
        }
    }

    fun cancelExtracting() {
        mCancelled = true
    }

    @Throws(UnsupportedEncodingException::class, YouTubeExtractorException::class)
    private fun getYouTubeResult(html: String): YouTubeExtractorResult {
        val video = getQueryMap(html, "UTF-8")

        var videoUri: Uri? = null

        if (video.containsKey("url_encoded_fmt_stream_map")) {
            val streamQueries = ArrayList(asList(*video["url_encoded_fmt_stream_map"]!!.split(",".toRegex()).dropLastWhile(
                    String::isEmpty).toTypedArray()))

            val adaptiveFmts = video["adaptive_fmts"]
            val split = adaptiveFmts!!.split(",".toRegex()).dropLastWhile(String::isEmpty).toTypedArray()

            streamQueries.addAll(asList(*split))

            val streamLinks = SparseArray<String>()
            for (streamQuery in streamQueries) {
                val stream = getQueryMap(streamQuery, "UTF-8")
                val type = stream["type"]!!.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
                var urlString: String? = stream["url"]

                if (urlString != null && MimeTypeMap.getSingleton().hasMimeType(type)) {
                    val signature = stream["sig"]

                    if (signature != null) {
                        urlString = urlString + "&signature=" + signature
                    }

                    if (getQueryMap(urlString, "UTF-8").containsKey("signature")) {
                        streamLinks.put(Integer.parseInt(stream["itag"]), urlString)
                    }
                }
            }

            for (videoQuality in preferredVideoQualities!!) {
                if (streamLinks.get(videoQuality, null) != null) {
                    val streamLink = streamLinks.get(videoQuality)
                    videoUri = Uri.parse(streamLink)
                    break
                }
            }

            val mediumThumbUri = if (video.containsKey("iurlmq")) Uri.parse(video["iurlmq"]) else null
            val highThumbUri = if (video.containsKey("iurlhq")) Uri.parse(video["iurlhq"]) else null
            val defaultThumbUri = if (video.containsKey("iurl")) Uri.parse(video["iurl"]) else null
            val standardThumbUri = if (video.containsKey("iurlsd")) Uri.parse(video["iurlsd"]) else null

            return YouTubeExtractorResult(videoUri!!,
                    mediumThumbUri!!,
                    highThumbUri!!,
                    defaultThumbUri!!,
                    standardThumbUri!!)
        }
        else {
            throw YouTubeExtractorException("Status: " + video["status"] + "\nReason: " + video["reason"] + "\nError code: " + video["errorcode"])
        }
    }
    //endregion

    class YouTubeExtractorResult constructor(val videoUri: Uri,
                                             val mediumThumbUri: Uri,
                                             val highThumbUri: Uri,
                                             val defaultThumbUri: Uri,
                                             val standardThumbUri: Uri)

    inner class YouTubeExtractorException(detailMessage: String): Exception(detailMessage)

    interface YouTubeExtractorListener {
        fun onSuccess(result: YouTubeExtractorResult)
        fun onFailure(error: Error)
    }
}