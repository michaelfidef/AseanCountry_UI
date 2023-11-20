package negara.asean.aseancountry.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailActivity: Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}