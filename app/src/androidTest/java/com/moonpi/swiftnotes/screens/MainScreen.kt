package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.screens.BaseScreen
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.anything

class MainScreen(
    private val toolbarMainId: Int = R.id.toolbarMain,
    private val addNoteBtn: Int = R.id.newNote
) : BaseScreen(toolbarMainId, addNoteBtn) {

    fun assertNoteWithTextExists(title: String, body: String) {
        onData(anything())
            .inAdapterView(withId(R.id.listView))
            .atPosition(0).check(
                ViewAssertions.matches(
                    allOf(
                        hasDescendant(
                            allOf(
                                withId(R.id.titleView),
                                withText(title)
                            )
                        ),
                        hasDescendant(
                            allOf(
                                withId(R.id.bodyView),
                                withText(body)
                            )
                        )
                    )
                )
            )
    }

    fun clickOverflowMenu() {

    }

    fun clickAddNote(): EditNoteScreen {
        onView(withId(addNoteBtn)).perform(click())
        return ScreenUtils.waitForScreen(EditNoteScreen())
    }
}