package com.moonpi.swiftnotes.screens
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.screens.BaseScreen
import org.hamcrest.CoreMatchers.allOf
import ru.tinkoff.allure.android.deviceScreenshot

class EditNoteScreen(private val noteTitle: Int =  R.id.titleEdit,
                     private val noteBody: Int = R.id.bodyEdit) : BaseScreen(noteTitle, noteBody) {

    fun goBack() {
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click())
    }

    fun assertHints() {
        onView(allOf(withId(noteTitle), isDisplayed())).check(ViewAssertions.matches(withHint("Title")))
        onView(allOf(withId(noteBody), isDisplayed())).check(ViewAssertions.matches(withHint("Note")))
        deviceScreenshot("page_display")
    }
}