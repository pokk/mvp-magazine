package taiwan.no1.app.ui.customize

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import taiwan.no1.app.utilies.AppLog

/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/2/17
 */

class FittableImageView: ImageView {
    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context,
            attrs,
            defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)

        val width: Int = MeasureSpec.getSize(widthMeasureSpec) - paddingLeft - paddingRight
        val height: Int = MeasureSpec.getSize(heightMeasureSpec) - paddingTop - paddingBottom

        AppLog.w(width, height)
        AppLog.w(widthMode, heightMode)

        if (MeasureSpec.EXACTLY == widthMode) {

        }
        else if (MeasureSpec.EXACTLY == heightMode) {

        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }
}