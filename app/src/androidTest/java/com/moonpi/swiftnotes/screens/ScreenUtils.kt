package com.moonpi.swiftnotes.screens

object ScreenUtils {
    private val MAX_TRY_COUNT_SCREEN_MATCH = 3

    fun <T : BaseScreen> waitForScreen(screen: T): T {
        for (i in 1..MAX_TRY_COUNT_SCREEN_MATCH) {
            if (screen.matches()) {
                return screen
            }
        }
        throw ScreenNotAppearedException(screen.javaClass.simpleName)
    }
}
