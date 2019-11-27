package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.withId
import com.moonpi.swiftnotes.R
import com.moonpi.swiftnotes.screens.BaseScreen

class MainScreen(private val toolbarMainId: Int = R.id.toolbarMain,
                 private val addNoteBtn: Int = R.id.newNote) : BaseScreen(toolbarMainId, addNoteBtn) {

    fun assertNoteWithTextExists(text: String) {

    }

    fun clickOverflowMenu() {

    }

    fun clickAddNote(): EditNoteScreen {
        onView(withId(addNoteBtn)).perform(click())
        return ScreenUtils.waitForScreen(EditNoteScreen())
    }
}