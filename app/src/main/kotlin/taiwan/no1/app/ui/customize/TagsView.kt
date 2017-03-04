package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import taiwan.no1.app.R

/**
 *
 * @author  Jieyi
 * @since   1/26/17
 */

class TagsView: View {
    //region Member Variables
    var color: Int = Color.BLUE
        set(value) {
            field = value
        }

    var fontSize: Float = 30f
        set(value) {
            field = value
        }

    var fontPadding: Float = 10f
        set(value) {
            field = value
        }

    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).also {
            it.color = this.color
            it.style = Paint.Style.STROKE
            it.textSize = this.fontSize
            it.strokeWidth = 3f
        }
    }

    //    private val tagFontLength: Float
//    private val tagFontHeight: Float
    private var rectLeftArc: RectF = RectF()
    private var rectRightArc: RectF = RectF()

    var tagList: List<String> = listOf("Fantasy", "Action")
    //endregion

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.TagsView).also {
                this.color = it.getInt(R.styleable.TagsView_fontColor, Color.BLUE)
                this.fontSize = it.getFloat(R.styleable.TagsView_fontSize, 30f)
                this.fontPadding = it.getFloat(R.styleable.TagsView_fontPadding, 10f)
            }.recycle()
        }
        var bounds: Rect = Rect()
    }
    //endregion

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var bounds: Rect = Rect()
        var boundsHeight: Float

        // TODO: 3/4/17 Make multi tag.
        this.paint.getTextBounds(tagList[0], 0, tagList[0].length, bounds)
        boundsHeight = bounds.height() + this.fontPadding * 2

        this.rectLeftArc = RectF(0f, 0f, boundsHeight, boundsHeight)
        this.rectRightArc = RectF(bounds.width().toFloat(), 0f, bounds.width().toFloat() + boundsHeight,
                boundsHeight)
        canvas.drawArc(rectLeftArc, 90f, 180f, false, this.paint)
        canvas.drawArc(rectRightArc, 270f, 180f, false, this.paint)
        canvas.drawLine(boundsHeight / 2, 0f, bounds.width().toFloat() + boundsHeight / 2, 0f, this.paint)
        canvas.drawLine(boundsHeight / 2,
                boundsHeight,
                bounds.width().toFloat() + boundsHeight / 2,
                boundsHeight,
                this.paint)

        canvas.drawText(this.tagList[0], boundsHeight / 2, boundsHeight - this.fontPadding * 2, this.paint)
    }
}