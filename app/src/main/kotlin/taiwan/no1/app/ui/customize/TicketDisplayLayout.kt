package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import taiwan.no1.app.R


/**
 * Lace edge layout.
 *
 * @author  Jieyi
 * @since   2/8/17
 */

class TicketDisplayLayout: RelativeLayout {
    //region Member Variables
    // Paint's color.
    var color: Int = Color.WHITE
        set(value) {
            field = value
        }
    // The distance between a half circle and a half circle.
    var gap = 15f
        set(value) {
            field = value
        }
    // Circle's radius.
    var radius = 25f
        set(value) {
            field = value
        }
    // The total number of circles on edge.
    private var circleNum: Int = 0
    private var circleRemain: Float = 0f
    private val paint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            this.isDither = true
            this.style = Paint.Style.FILL
            this.color = this@TicketDisplayLayout.color
        }
    }
    //endregion

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        attrs?.let {
            context.obtainStyledAttributes(it, R.styleable.TicketDisplayLayout).apply {
                this@TicketDisplayLayout.color = this.getInt(R.styleable.TicketDisplayLayout_circleColor, Color.WHITE)
                this@TicketDisplayLayout.gap = this.getFloat(R.styleable.TicketDisplayLayout_gap, 0f)
                this@TicketDisplayLayout.radius = this.getFloat(R.styleable.TicketDisplayLayout_radius, 0f)
            }.recycle()
        }
    }
    //endregion

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Counting how many circle we need on the edge.
        if (0f == circleRemain) {
            circleRemain = (h - gap).toInt().rem((2 * radius + gap))
        }
        circleNum = ((h - gap) / (2 * radius + gap)).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        (0..circleNum - 1).map {
            gap + radius + circleRemain / 2 + (gap + radius * 2) * it
        }.forEach {
            canvas.drawCircle(width.toFloat(), it, radius, paint)
        }
    }
}