package com.example.android11developmentmasterclass

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher
import org.hamcrest.CoreMatchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlaylistFeature : BaseUITest() {

    val mActivity = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        BaristaVisibilityAssertions.assertDisplayed("Android11DevelopmentMasterclass")
    }

    @Test
    fun displayListOfPlayLists() {

        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.playlistRecyclerView, 100)

        onView(
            allOf(
                withId(R.id.playlist_name),
                isDescendantOfA(nthChildOf(withId(R.id.playlistRecyclerView), 0))
            )
        )
//            .check(matches(withText("name 0")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_category),
                isDescendantOfA(nthChildOf(withId(R.id.playlistRecyclerView), 0))
            )
        )
//            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.playlist_image),
                isDescendantOfA(nthChildOf(withId(R.id.playlistRecyclerView), 1))
            )
        )
//            .check(matches(DrawableMatcher.withDrawable(R.mipmap.playlist)))
            .check(matches(isDisplayed()))

    }

}