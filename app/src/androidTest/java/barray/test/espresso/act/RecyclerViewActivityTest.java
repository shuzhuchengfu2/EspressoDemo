package barray.test.espresso.act;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import barray.test.espresso.R;
import barray.test.espresso.adapter.CustomAdapter;


/**
 * author： xiongdejin
 * date: 2018/4/25
 * describe:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewActivityTest {
    private static final int ITEM_BELOW_THE_FOLD = 40;

    @Rule
    public ActivityTestRule<RecyclerViewActivity> mActivityRule = new ActivityTestRule<>(RecyclerViewActivity.class);


    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // 滑动至 @ITEM_BELOW_THE_FOLD
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, ViewActions.click()));

        // 校验该item 是否显示
        String itemElementText = mActivityRule.getActivity().getResources().getString(
                R.string.item_element_text) + String.valueOf(ITEM_BELOW_THE_FOLD);
        Espresso.onView(ViewMatchers.withText(itemElementText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        // 滑动至中间位置
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // 校验该item 是否显示
        String middleElementText =
                mActivityRule.getActivity().getResources().getString(R.string.middle);
        Espresso.onView(ViewMatchers.withText(middleElementText)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    private static Matcher<CustomAdapter.ViewHolder> isInTheMiddle() {
        return new TypeSafeMatcher<CustomAdapter.ViewHolder>() {
            @Override
            protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
                return customHolder.getIsInTheMiddle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }
}
