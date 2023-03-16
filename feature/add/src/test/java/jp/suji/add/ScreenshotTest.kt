package jp.suji.add

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import jp.suji.habit.ui.add.PreviewAddTaskScreen
import jp.suji.habit.ui.add.components.PreviewColorChip
import jp.suji.habit.ui.add.components.PreviewIconChip
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(TestParameterInjector::class)
class ScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        environment = detectEnvironment().copy(
            platformDir = "${androidHome()}/platforms/android-33",
            compileSdkVersion = 33
        ),
        deviceConfig = DeviceConfig.PIXEL_6.copy(softButtons = false),
        renderingMode = SessionParams.RenderingMode.SHRINK
    )

    @Test
    fun addHabitScreen() {
        paparazzi.snapshot {
            PreviewAddTaskScreen()
        }
    }

    @Test
    fun colorChip(@TestParameter param: Boolean) {
        paparazzi.snapshot {
            PreviewColorChip(param = param)
        }
    }

    @Test
    fun iconChip(@TestParameter param: Boolean) {
        paparazzi.snapshot {
            PreviewIconChip(param = param)
        }
    }
}