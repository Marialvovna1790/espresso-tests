package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.Matchers.allOf
import ru.tinkoff.allure.android.deviceScreenshot

open class BaseScreen(vararg ids: Int) {

    private val viewIds: List<Int> = ids.toList()

    fun matches(): Boolean {
        viewIds.forEach {
            try {
                Espresso.onView(allOf(withId(it), isDisplayed())).check(ViewAssertions.matches(isDisplayed()))
            } catch (ex: NoMatchingViewException) {
                deviceScreenshot("No view found $it")
                return false
            }
        }
        return true
    }
}