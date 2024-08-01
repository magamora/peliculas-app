package com.example.moviesapp.utils

import android.os.Bundle
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import com.example.moviesapp.domain.Result
import com.google.gson.Gson



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(navController: NavHostController, titleResource: Int) {
    TopAppBar(
        title = { Text(stringResource(id = titleResource)) },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.AutoMirrored.Rounded.ArrowBack, "")
            }
        })
}


class MovieArgType : JsonNavType<Result>() {
    override fun fromJsonParse(value: String): Result =
        Gson().fromJson(value, Result::class.java)

    override fun Result.getJsonParse(): String = Gson().toJson(this)

}


abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}