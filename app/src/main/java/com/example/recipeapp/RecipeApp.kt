package com.example.recipeapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp( modifier: Modifier, navController: NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.RecipeScreen.route){
        composable (route = Screen.RecipeScreen.route) {
            RecipeScreen(viewstate = viewstate, navigateToDetail = {

                //this part is responsible for passing data from current screen to detail screen
                //It utilizes the savedStateHandle, which is a component of the compose navigation framework

                navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                navController.navigate(Screen.DetailScreen.route)
            } )
        }

        composable(route = Screen.DetailScreen.route){
            val caterory = navController.previousBackStackEntry?. savedStateHandle?.
             get<Category>("cat") ?: Category("", "", "", "")
            CategoryDetailScreen(category = caterory)
        }

    }
}