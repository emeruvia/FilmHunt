package dev.emg.filmhunt.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import dev.emg.filmhunt.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AppTest {

  @Rule
  @JvmField
  var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

  @Test
  fun appTest() {
    val appCompatImageView = onView(
      allOf(
        withId(R.id.search_button), withContentDescription("Search"),
        childAtPosition(
          allOf(
            withId(R.id.search_bar),
            childAtPosition(
              withId(R.id.search_bar),
              0
            )
          ),
          1
        ),
        isDisplayed()
      )
    )
    appCompatImageView.perform(click())

    val recyclerView = onView(
      allOf(
        withId(R.id.recyclerview),
        childAtPosition(
          withId(R.id.container),
          1
        )
      )
    )
    recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

    pressBack()
  }

  private fun childAtPosition(
    parentMatcher: Matcher<View>, position: Int
  ): Matcher<View> {

    return object : TypeSafeMatcher<View>() {
      override fun describeTo(description: Description) {
        description.appendText("Child at position $position in parent ")
        parentMatcher.describeTo(description)
      }

      public override fun matchesSafely(view: View): Boolean {
        val parent = view.parent
        return parent is ViewGroup && parentMatcher.matches(parent)
            && view == parent.getChildAt(position)
      }
    }
  }
}
