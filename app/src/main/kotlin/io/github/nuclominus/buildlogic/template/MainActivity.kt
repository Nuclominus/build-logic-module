package io.github.nuclominus.buildlogic.template

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.github.nuclominus.buildlogic.template.theme.AppTemplateTheme

/**
 * Sample MainActivity with Hilt and Navigation Compose
 *
 * @see [AppTemplateTheme]
 * @see [Navigation]
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppMain {
                finish()
            }
        }
    }
}

@Composable
fun AppMain(onBackPressed: () -> Unit) {
    AppTemplateTheme {
        val navController = rememberNavController()

        BackHandler(true) {
            if (navController.backQueue.size <= 1) {
                onBackPressed()
            } else {
                navController.popBackStack()
            }
        }

        Navigation(
            navController = navController
        )
    }
}

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.DASHBOARD.routeName,
    ) {
        composable(Screen.DASHBOARD.routeName) {
            //init dashboard screen
        }

        // Sample details screen with modelId argument
        composable(
            route = "${Screen.DETAILS.routeName}/{${Constants.MODEL_ID}}",
            arguments = listOf(
                navArgument(Constants.MODEL_ID) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val modelId = backStackEntry.arguments?.getString(Constants.MODEL_ID) ?: ""
            Log.d("DetailsScreen", "ModelId: $modelId")
            // init details screen with modelId
        }
    }
}

// Sample screen enum
private sealed class Screen(val routeName: String) {
    data object DASHBOARD : Screen("dashboard")
    data object DETAILS : Screen("details")
}

// Constants object
object Constants {
    const val MODEL_ID = "model_id"
}