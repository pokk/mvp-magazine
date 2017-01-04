package taiwan.no1.app.ui.customize

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import taiwan.no1.app.utilies.AppLog


/**
 *
 * @author  Jieyi
 * @version 0.0.1
 * @since   1/4/17
 */

class CircleImageView: ImageView {
    val imagePaint = Paint()
    var src_resource: Int = 0

    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context,
            attrs,
            defStyleAttr)


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        AppLog.v(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        AppLog.v(MeasureSpec.getMode(widthMeasureSpec), MeasureSpec.getMode(heightMeasureSpec))
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
//                drawable.intrinsicHeight,
//                Bitmap.Config.ARGB_8888)
//        val c = Canvas(bitmap)
//        drawable.setBounds(0, 0, canvas.width, canvas.height)
//        drawable.draw(c)
//
//        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
//        imagePaint.shader = shader
//        imagePaint.isAntiAlias = true
//
//        c.drawCircle((canvas.width / 2).toFloat(),
//                (canvas.height / 2).toFloat(),
//                (canvas.height / 2).toFloat(),
//                imagePaint)
    }
}