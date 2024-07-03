import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.nuclominus.buildlogic.template.AppMain
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dashboardScreenTest() {
        composeTestRule.setContent {
            AppMain {
                // Test the dashboard screen
            }
        }

        // Perform actions and assertions
    }
}