package com.example.newwarehouseapp.presentation.screen_state

sealed interface ScreenState{
    object Loading: ScreenState
    object Content: ScreenState
    object Error: ScreenState
    object Empty: ScreenState
    object NoAccess : ScreenState
}