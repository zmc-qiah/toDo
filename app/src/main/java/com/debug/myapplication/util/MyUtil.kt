import android.content.Context // ktlint-disable filename
import android.os.Vibrator
import org.jxxy.debug.corekit.common.BaseApplication

fun enen(time: Long = 100) {
    val vibrator = BaseApplication.context().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    vibrator.vibrate(time)
}
