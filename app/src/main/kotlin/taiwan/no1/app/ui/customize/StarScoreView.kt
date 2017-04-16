package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.*
import android.graphics.Color.YELLOW
import android.util.AttributeSet
import android.view.View
import taiwan.no1.app.R

/**
 * @author  jieyi
 * @since   1/25/17
 */

class StarScoreView: View {
    //region Member Variables
    private val scorePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = YELLOW
        }
    }
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var scoreMaxNumber: Int = 5
        set(value) {
            field = value
            invalidate()
        }
    var score: Double = .0
        set(value) {
            field = value
            invalidate()
        }
    private var scoreBoardBitmap: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.ic_star_border)
        set(value) {
            field = value
            invalidate()
        }
    private var scoreBitmap: Bitmap = BitmapFactory.decodeResource(this.resources, R.drawable.ic_star)
        set(value) {
            field = value
            invalidate()
        }
    private var desiredWidth: Int = this.scoreBoardBitmap.width * this.scoreMaxNumber
    private var desiredHeight: Int = this.scoreBoardBitmap.height
    //endregion

    //region Constructors
    constructor(context: Context): this(context, null, 0)

    constructor(context: Context, attrs: AttributeSet?): this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    //endregion

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode: Int = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize: Int = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode: Int = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize: Int = MeasureSpec.getSize(heightMeasureSpec)

        // Measure width.
        this.mWidth = when (widthMode) {
        // Must be this size.
            MeasureSpec.EXACTLY -> widthSize
        // Can't be bigger than...
            MeasureSpec.AT_MOST -> Math.min(desiredWidth, widthSize)
        // Be whatever you want.
            else -> desiredWidth
        }

        // Measure height.
        this.mHeight = when (heightMode) {
        // Must be this size.
            MeasureSpec.EXACTLY -> heightSize
        // Can't be bigger than...
            MeasureSpec.AT_MOST -> Math.min(desiredHeight, heightSize)
        // Be whatever you want.
            else -> desiredHeight
        }

        setMeasuredDimension(this.mWidth, this.mHeight)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        (0..this.scoreMaxNumber - 1).forEach {
            if (it < Math.floor(this.score))
                canvas.drawBitmap(this.scoreBitmap, this.scoreBitmap.width * it.toFloat(), 0f, this.scorePaint)
            else if (it >= Math.ceil(this.score))
                canvas.drawBitmap(this.scoreBoardBitmap,
                        this.scoreBoardBitmap.width * it.toFloat(),
                        0f,
                        this.scorePaint)
            else {
                val decimal: Double = this.score - Math.floor(this.score)
                canvas.drawBitmap(this.scoreBitmap,
                        Rect(0, 0, (this.scoreBitmap.width * decimal).toInt(), this.scoreBitmap.height),
                        Rect(this.scoreBitmap.width * it,
                                0,
                                (this.scoreBitmap.width * it.toFloat() + this.scoreBitmap.width * decimal).toInt(),
                                this.scoreBitmap.height),
                        this.scorePaint)
                canvas.drawBitmap(this.scoreBoardBitmap,
                        Rect((this.scoreBoardBitmap.width * decimal).toInt(),
                                0,
                                this.scoreBoardBitmap.width,
                                this.scoreBoardBitmap.height),
                        Rect(this.scoreBoardBitmap.width * it + (this.scoreBoardBitmap.width * decimal).toInt(),
                                0,
                                this.scoreBoardBitmap.width * (it + 1),
                                this.scoreBoardBitmap.height),
                        this.scorePaint)
            }
        }
    }
}