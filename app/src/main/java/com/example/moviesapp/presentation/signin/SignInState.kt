package com.example.moviesapp.presentation.signin

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
