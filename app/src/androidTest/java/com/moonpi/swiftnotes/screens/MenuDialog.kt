package com.moonpi.swiftnotes.screens

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.v7.widget.MenuPopupWindow
import com.moonpi.swiftnotes.R
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.Matchers
import ru.tinkoff.allure.android.deviceScreenshot

class MenuDialog(val title: Int = R.id.title) : BaseScreen(R.id.group_divider) {

    override fun matches(): Boolean {
        try {
            Espresso.onView(Matchers.instanceOf(MenuPopupWindow.MenuDropDownListView::class.java))
                .check(
                    ViewAssertions.matches(isDisplayed())
                )
        } catch (ex: NoMatchingViewException) {
            deviceScreenshot("No popup found")
            return false
        }
        return true
    }

    fun assertContent(content: List<String>) {
        for (i in content.indices) {
            onData(anything())
                .inAdapterView(instanceOf(MenuPopupWindow.MenuDropDownListView::class.java))
                .atPosition(i)
                .check(
                    ViewAssertions.matches(
                        hasDescendant(
                            withText(content[i])
                        )
                    )
                )
        }
    }
}