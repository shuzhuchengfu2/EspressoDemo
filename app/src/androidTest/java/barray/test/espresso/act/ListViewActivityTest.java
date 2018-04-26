package barray.test.espresso.act;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import barray.test.espresso.R;
import barray.test.espresso.act.ListViewActivity;
import barray.test.espresso.domain.User;

/**
 * author： xiongdejin
 * date: 2018/4/18
 * describe:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ListViewActivityTest {
    @Rule
    public ActivityTestRule<ListViewActivity> activityTestRule = new ActivityTestRule<>(ListViewActivity.class);

    @Test
    public void testListScroll() {
        //检查是否滑动至 99
        Espresso.onData(Matchers.instanceOf(User.class)).atPosition(99)
                .check(ViewAssertions.matches(ViewMatchers.isCompletelyDisplayed()));
    }

    @Test
    public void testClickItem(){
        //滑动至 50 点击
        Espresso.onData(Matchers.instanceOf(User.class)).atPosition(50).perform(ViewActions.click());
        //点击弹框 取消
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancel))
                .inRoot(RootMatchers.withDecorView(IsNot.not(activityTestRule.getActivity().getWindow().getDecorView())))
                .perform(ViewActions.click());
        //50 item 存在
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.tv_age),ViewMatchers.withText("50")))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        //滑动至 50 点击
        Espresso.onData(Matchers.instanceOf(User.class)).atPosition(50).perform(ViewActions.click());
        //点击弹框 确定
        Espresso.onView(ViewMatchers.withId(R.id.btn_ok))
                .inRoot(RootMatchers.withDecorView(IsNot.not(activityTestRule.getActivity().getWindow().getDecorView())))
                .perform(ViewActions.click());
        //50 item 不存在
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.tv_age),ViewMatchers.withText("50")))
                .check(ViewAssertions.doesNotExist());
    }
}
