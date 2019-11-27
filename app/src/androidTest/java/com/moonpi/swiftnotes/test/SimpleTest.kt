package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.moonpi.swiftnotes.MainActivity
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.rule.SwiftnotesRule
import com.moonpi.swiftnotes.screens.MainScreen
import com.moonpi.swiftnotes.screens.ScreenUtils
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.tinkoff.allure.android.deviceScreenshot
import ru.tinkoff.allure.annotations.DisplayName
import ru.tinkoff.allure.step

@RunWith(AndroidJUnit4::class)
@DisplayName("Создание заметки")
class SimpleTest : AbstractSwiftnotesTest() {

    @get:Rule
    val rule = SwiftnotesRule(MainActivity::class.java, false)

    @Test
    @DisplayName("Проверка открытия страницы создания")
    fun newNoteHints() {
        rule.launchActivity()

        step("Проверяем отображение страницы") {
            with(ScreenUtils.waitForScreen(MainScreen())) {
                val editScreen = clickAddNote()
                editScreen.assertHints()
            }
        }
    }
}
