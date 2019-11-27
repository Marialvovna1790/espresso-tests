package com.moonpi.swiftnotes.test

import android.support.test.espresso.Espresso
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
    @DisplayName("Проверка экрана создания заметки")
    fun checkEditNoteScreen() {
        rule.launchActivity()

        step("Проверяем отображение страницы") {
            with(ScreenUtils.waitForScreen(MainScreen())) {
                with(clickAddNote()) {
                    assertHints()
                    val dlg = goBack()
                    with(dlg) {
                        assertMessage("Save changes?")
                        assertOkBtn("Yes")
                        assertNoBtn("No")
                        deviceScreenshot("dialog display")
                        clickNo()
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка сохранения заметки")
    fun checkSaveNote() {
        rule.launchActivity()

        step("Отркытие редактирования заметки") {
            with(ScreenUtils.waitForScreen(MainScreen())) {
                with(clickAddNote()) {
                    step("Редактирование заметки") {
                        val title = "Test title"
                        val body = "Test body"
                        enterBody(body)
                        enterTitle(title)
                        deviceScreenshot("entered note")
                        assertBody(body)
                        assertTitle(title)
                        step("Сохранение заметки") {
                            with(goBack().clickYes()) {
                                deviceScreenshot("main screen with note")
                                assertNoteWithTextExists(title, body)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка меню")
    fun checkMenu() {
        rule.launchActivity()

        with(ScreenUtils.waitForScreen(MainScreen())) {
            step("Главное меню") {
                with(clickOverflowMenu()) {
                    assertContent(
                        arrayListOf(
                            "Backup notes",
                            "Restore notes",
                            "Rate app"
                        )
                    )
                    deviceScreenshot("Overflow menu main")
                    Espresso.pressBack()
                }
            }

            step("Меню экрана редактирования ") {
                with(clickAddNote()) {
                    deviceScreenshot("Overflow menu edit")
                    with(clickOverflowMenu()) {
                        assertContent(
                            arrayListOf(
                                "Note font size",
                                "Hide note body in list"
                            )
                        )
                    }
                }
            }
        }
    }
}
