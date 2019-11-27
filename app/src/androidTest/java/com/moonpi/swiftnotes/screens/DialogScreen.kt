package com.moonpi.swiftnotes.screens

import android.R
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.allOf

class DialogScreen(val buttonNo: Int = R.id.button2,
                   val buttonYes: Int = R.id.button1,
                   val message: Int = R.id.message): BaseScreen(buttonNo, buttonYes, message) {

    fun assertMessage(expectedText: String) {
        onView(allOf(withId(message), isDisplayed())).check(ViewAssertions.matches(withText(expectedText)))
    }

    fun assertOkBtn(expectedText: String) {
        onView(allOf(withId(buttonYes), isDisplayed())).check(ViewAssertions.matches(withText(expectedText)))
    }

    fun assertNoBtn(expectedText: String) {
        onView(allOf(withId(buttonNo), isDisplayed())).check(ViewAssertions.matches(withText(expectedText)))
    }

    fun clickNo() {

    }

    fun clickYes() {

    }
}