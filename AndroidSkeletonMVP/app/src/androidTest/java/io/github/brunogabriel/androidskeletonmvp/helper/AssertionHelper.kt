package io.github.brunogabriel.androidskeletonmvp.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat

/**
 * Created by brunosantos on 2019-10-04.
 */
fun hasItemCount(count: Int): ViewAssertion {
    return ViewAssertion { view, noViewFoundException ->
        noViewFoundException?.let { throw it }
        check(view is RecyclerView) { "The asserted view isn't a RecyclerView instance" }
        checkNotNull(view.adapter) { "No adapter assigned to RecyclerView" }
        assertThat("Recycler view item count", view.adapter!!.itemCount, equalTo(count))
    }
}

fun atPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            description?.appendText("Has item at position $position")
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(item: RecyclerView?): Boolean {
            return itemMatcher.matches(item?.findViewHolderForAdapterPosition(position)?.itemView)
        }
    }
}