package taiwan.no1.app.utilies

import android.graphics.Color

/**
 *
 * @author  jieyi
 * @since   5/29/17
 */

/**
 * @param color opaque RGB integer color for ex: -11517920
 * @param ratio ratio of transparency for ex: 0.5f
 *
 * @return transparent RGB integer color
 */
fun getColorWithAplha(color: Int, ratio: Float): Int {
    var transColor = 0
    val alpha = Math.round(Color.alpha(color) * ratio)
    val r = Color.red(color)
    val g = Color.green(color)
    val b = Color.blue(color)
    transColor = Color.argb(alpha, r, g, b)
    return transColor
}