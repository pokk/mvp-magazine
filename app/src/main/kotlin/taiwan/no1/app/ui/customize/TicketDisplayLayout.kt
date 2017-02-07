package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout


/**
 * Lace edge layout.
 *
 * @author  Jieyi
 * @since   2/8/17
 */

class TicketDisplayLayout: RelativeLayout {
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.isDither = true
        this.color = Color.rgb(12, 34, 56)
        this.style = Paint.Style.FILL
    }
    private val gap = 15f
    private val radius = 25f
    private var circleNum: Int = 0

    private var remain: Float = 0.toFloat()

    constructor(context: Context?): super(context)
    constructor(context: Context?, attrs: AttributeSet?): super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int): super(context,
            attrs,
            defStyleAttr,
            defStyleRes)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (0f == remain) {
            remain = (h - gap).toInt() % (2 * radius + gap)
        }
        circleNum = ((h - gap) / (2 * radius + gap)).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        (0..circleNum - 1).map { gap + radius + remain / 2 + (gap + radius * 2) * it }
                .forEach { canvas.drawCircle(width.toFloat(), it, radius, paint) }
    }
}