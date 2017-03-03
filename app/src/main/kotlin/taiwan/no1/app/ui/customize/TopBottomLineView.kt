package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.*
import android.support.annotation.ColorRes
import android.support.annotation.FloatRange
import android.util.AttributeSet
import android.widget.LinearLayout
import taiwan.no1.app.R

/**
 * LinearLayout with top and bottom line.
 *
 * @author  Jieyi
 * @since   3/3/17
 */

class TopBottomLineView: LinearLayout {
    //region Member Variables
    // Paint's color.
    @ColorRes
    var color: Int = Color.WHITE
        set(value) {
            field = value
        }
    @FloatRange(from = .0, to = .5)
    var blurLength: Float = 15f
        set(value) {
            field = value
        }
    var strokeWidth: Float = 10f
        set(value) {
            field = value
        }
    var linePadding: Int = 10
    var topOrBottom: Int = 0
    private lateinit var colorGradient: IntArray
    private lateinit var colorPos: FloatArray
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.style = Paint.Style.FILL
            this.color = this@TopBottomLineView.color
            this.strokeWidth = this@TopBottomLineView.strokeWidth
            this.shader = LinearGradient(0f + this@TopBottomLineView.linePadding,
                    this@TopBottomLineView.measuredHeight / 2f,
                    this@TopBottomLineView.measuredWidth.toFloat() - this@TopBottomLineView.linePadding,
                    this@TopBottomLineView.measuredHeight / 2f,
                    this@TopBottomLineView.colorGradient,
                    this@TopBottomLineView.colorPos,
                    Shader.TileMode.CLAMP)
        }
    }
    //endregion

    private enum class LineCategory {
        top, bottom, both
    }

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.TopBottomLineView).apply {
                this@TopBottomLineView.color = this.getInt(R.styleable.TopBottomLineView_centerColor, Color.BLACK)
                this@TopBottomLineView.blurLength = this.getFloat(R.styleable.TopBottomLineView_blurLength, 0f)
                this@TopBottomLineView.strokeWidth = this.getFloat(R.styleable.TopBottomLineView_strokeWidth, 0f)
                this@TopBottomLineView.linePadding = this.getInteger(R.styleable.TopBottomLineView_linePadding, 0)
                this@TopBottomLineView.topOrBottom = this.getInteger(R.styleable.TopBottomLineView_topOrBottom,
                        LineCategory.both.ordinal)
            }.recycle()
        }

        this.colorGradient = intArrayOf(Color.argb(0, 0, 0, 0), this@TopBottomLineView.color,
                this@TopBottomLineView.color, Color.argb(0, 0, 0, 0))
        this.colorPos = floatArrayOf(0f, this.blurLength, 1 - this.blurLength, 1f)
    }
    //endregion

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (LineCategory.top.ordinal == this.topOrBottom || LineCategory.both.ordinal == this.topOrBottom)
            canvas.drawLine(0f + this.linePadding, 0f + this.blurLength / 2,
                    this.measuredWidth.toFloat() - this.linePadding, 0f + this.blurLength / 2, this.paint)
        if (LineCategory.bottom.ordinal == this.topOrBottom || LineCategory.both.ordinal == this.topOrBottom)
            canvas.drawLine(0f + this.linePadding, this.measuredHeight - this.blurLength,
                    this.measuredWidth.toFloat() - this.linePadding, this.measuredHeight - this.blurLength, this.paint)
    }
}
