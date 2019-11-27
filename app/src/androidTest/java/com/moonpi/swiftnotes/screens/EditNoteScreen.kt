package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.widget.ImageButton
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.instanceOf
import ru.tinkoff.allure.android.deviceScreenshot

class EditNoteScreen(
    private val noteTitle: Int = R.id.titleEdit,
    private val noteBody: Int = R.id.bodyEdit
) : BaseScreen(noteTitle, noteBody) {

    fun goBack(): SaveDialog {
        val upButton =
            onView(allOf(instanceOf(ImageButton::class.java), withParent(withId(R.id.toolbarEdit))))
        upButton.perform(click())
        return ScreenUtils.waitForScreen(SaveDialog())
    }

    fun assertHints() {
        onView(
            allOf(
                withId(noteTitle),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withHint("Title")))
        onView(
            allOf(
                withId(noteBody),
                isDisplayed()
            )
        ).check(ViewAssertions.matches(withHint("Note")))
        deviceScreenshot("page_display")
    }

    fun enterTitle(title: String) {
        onView(withId(noteTitle)).perform(ViewActions.typeText(title))
    }

    fun assertTitle(title: String) {
        onView(withId(noteTitle)).check(ViewAssertions.matches(withText(title)))
    }

    fun enterBody(body: String) {
        onView(withId(noteBody)).perform(ViewActions.typeText(body))
    }

    fun assertBody(body: String) {
        onView(withId(noteBody)).check(ViewAssertions.matches(withText(body)))
    }
}