package barray.test.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsNot;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import barray.test.espresso.act.BasicUsageActivity;

/**
 * author： xiongdejin
 * date: 2018/4/17
 * describe:基础用法
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class BasicUsageActivityTest {
    @Rule
    public ActivityTestRule<BasicUsageActivity> activityTestRule = new ActivityTestRule<>(BasicUsageActivity.class);

    @Test
    public void testClickMe() {
        // 查找内容为 @click_me 控件，并点击
        Espresso.onView(ViewMatchers.withText(R.string.click_me)).perform(ViewActions.click());
        // 查找 id 为 @tv_status 控件，并当前控件为显示状态 当前控件显示的内容 为 @app_name
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.tv_status), ViewMatchers.isDisplayed()))
                .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)));

    }

    @Test
    public void testShowName(){
        String name = "张三";
        // 查找 id 为 @edit_name 控件 替换内容 @name  //ps:typeText 有些版本不适用
        Espresso.onView(ViewMatchers.withHint(R.string.edit_name)).perform(ViewActions.replaceText(name));
        // 查找 id 为 @show_name 控件 点击
        Espresso.onView(ViewMatchers.withText(R.string.show_name)).perform(ViewActions.click());
        // 查找 显示 @name 窗口 不是当前Activity所属 窗口
        Espresso.onView(ViewMatchers.withText(name))
                .inRoot(RootMatchers.withDecorView(IsNot.not(activityTestRule.getActivity().getWindow().getDecorView())))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
