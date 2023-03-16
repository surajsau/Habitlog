package jp.suji.habit.ui.habit

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import app.cash.paparazzi.androidHome
import app.cash.paparazzi.detectEnvironment
import com.android.ide.common.rendering.api.SessionParams
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import jp.suji.habit.ui.habit.components.PreviewHabitDayItem
import jp.suji.habit.ui.habit.components.PreviewHabitItem
import jp.suji.habit.ui.habit.components.PreviewHabitYear
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
        renderingMode = SessionParams.RenderingMode.SHRINK,
    )

    @Test
    fun habitScreen() {
        paparazzi.snapshot {
            PreviewHabitScreen()
        }
    }

    @Test
    fun habitDayItem(@TestParameter param: Boolean) {
        paparazzi.snapshot {
            PreviewHabitDayItem(param)
        }
    }

    @Test
    fun habitItem(@TestParameter param: Boolean) {
        paparazzi.snapshot {
            PreviewHabitItem(param)
        }
    }

    @Test
    fun habitYear() {
        paparazzi.snapshot {
            PreviewHabitYear()
        }
    }
}