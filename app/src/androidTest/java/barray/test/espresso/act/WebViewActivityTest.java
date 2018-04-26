package barray.test.espresso.act;

import android.content.Intent;
import android.support.test.espresso.web.assertion.WebViewAssertions;
import android.support.test.espresso.web.sugar.Web;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * author： xiongdejin
 * date: 2018/4/25
 * describe:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class WebViewActivityTest {
    private static final String MACCHIATO = "Macchiato";
    private static final String DOPPIO = "Doppio";

    @Rule
    public ActivityTestRule<WebViewActivity> mActivityRule = new ActivityTestRule<WebViewActivity>(
            WebViewActivity.class, false, false) {
        @Override
        protected void afterActivityLaunched() {
            Web.onWebView().forceJavascriptEnabled();
        }
    };

    @Test
    public void typeTextInInput_clickButton_SubmitsForm() {
        // 设置初始化intent
        mActivityRule.launchActivity(withWebFormIntent());

        Web.onWebView()
                // 根据id 查找输入框
                .withElement(DriverAtoms.findElement(Locator.ID, "text_input"))
                // 清除输入框内容
                .perform(DriverAtoms.clearElement())
                // 输入新内容 @MACCHIATO
                .perform(DriverAtoms.webKeys(MACCHIATO))
                // 根据id 查找按钮
                .withElement(DriverAtoms.findElement(Locator.ID, "submitBtn"))
                // 点击
                .perform(DriverAtoms.webClick())
                // 根据id 查找元素
                .withElement(DriverAtoms.findElement(Locator.ID, "response"))
                // 校验显示内容
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), Matchers.containsString(MACCHIATO)));
    }

    @Test
    public void typeTextInInput_clickButton_ChangesText() {
        // 设置初始化intent
        mActivityRule.launchActivity(withWebFormIntent());

        Web.onWebView()
                // 根据id 查找元素
                .withElement(DriverAtoms.findElement(Locator.ID, "text_input"))
                // 清除输入框内容
                .perform(DriverAtoms.clearElement())
                // 输入新内容 @DOPPIO
                .perform(DriverAtoms.webKeys(DOPPIO))
                // 根据id 查找按钮
                .withElement(DriverAtoms.findElement(Locator.ID, "changeTextBtn"))
                // 点击
                .perform(DriverAtoms.webClick())
                // 根据id 查找元素
                .withElement(DriverAtoms.findElement(Locator.ID, "message"))
                // 校验显示内容
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), Matchers.containsString(DOPPIO)));
    }

    private static Intent withWebFormIntent() {
        Intent basicFormIntent = new Intent();
        basicFormIntent.putExtra(WebViewActivity.KEY_URL_TO_LOAD, WebViewActivity.WEB_FORM_URL);
        return basicFormIntent;
    }
}
