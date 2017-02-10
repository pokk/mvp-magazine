package taiwan.no1.app.ui.customize

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import taiwan.no1.app.R

/**
 * An [ImageView] layout that maintains a consistent width to height aspect ratio.
 *
 * @author  Jieyi
 * @since   2/10/17
 */

class AdjustHeightImageView: ImageView {
    //region Member Variables
    var heightRatio: Float = 0f
        set(value) {
            if (value != field) {
                field = value
                this.requestLayout()
            }
        }
        get() = field
    //endregion

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int): super(context, attrs, defStyle) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.AdjustHeightImageView).apply {
                this@AdjustHeightImageView.heightRatio = this.getFloat(R.styleable.AdjustHeightImageView_heightRatio,
                        1f)
            }.recycle()
        }
    }
    //endregion

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (this.heightRatio > 0) {
            // Set the image views size.
            val width: Int = View.MeasureSpec.getSize(widthMeasureSpec)
            val height: Int = (width * this.heightRatio).toInt()

            this.setMeasuredDimension(width, height)
        }
        else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}
