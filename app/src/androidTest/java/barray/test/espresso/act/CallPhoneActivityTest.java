package barray.test.espresso.act;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.ComponentNameMatchers;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import barray.test.espresso.R;


/**
 * author： xiongdejin
 * date: 2018/4/25
 * describe:
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CallPhoneActivityTest {
    private static final String VALID_PHONE_NUMBER = "123-345-6789";

    private static final Uri INTENT_DATA_PHONE_NUMBER = Uri.parse("tel:" + VALID_PHONE_NUMBER);

    private static String PACKAGE_ANDROID_DIALER = "com.android.phone";

    static {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            PACKAGE_ANDROID_DIALER = "com.android.server.telecom";
        }
    }

    @Rule
    public IntentsTestRule<CallPhoneActivity> mActivityRule = new IntentsTestRule<>(CallPhoneActivity.class);

    @Before
    public void stubAllExternalIntents() {
        //拦截Intent
        Intents.intending(Matchers.not(IntentMatchers.isInternal()))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }

    @Before
    public void grantPhonePermission() {
        //添加权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
                    "pm grant " + InstrumentationRegistry.getTargetContext().getPackageName()
                            + " android.permission.CALL_PHONE");
        }
    }

    @Test
    public void typeNumber_ValidInput_InitiatesCall() {
        //输入号码
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_caller_number))
                .perform(ViewActions.replaceText(VALID_PHONE_NUMBER), ViewActions.closeSoftKeyboard());
        //拨打
        Espresso.onView(ViewMatchers.withId(R.id.button_call_number)).perform(ViewActions.click());
        //校验Intent
        Intents.intended(AllOf.allOf(
                IntentMatchers.hasAction(Intent.ACTION_CALL),
                IntentMatchers.hasData(INTENT_DATA_PHONE_NUMBER),
                IntentMatchers.toPackage(PACKAGE_ANDROID_DIALER)));
    }

    @Test
    public void pickContactButton_click_SelectsPhoneNumber() {
        //拦截跳转 hasShortClassName() 文件路径-包名
        Intents.intending(IntentMatchers.hasComponent(ComponentNameMatchers.hasShortClassName(".act.ContactsActivity")))
                .respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK,
                        ContactsActivity.createResultData(VALID_PHONE_NUMBER)));
        //点击跳转
        Espresso.onView(ViewMatchers.withId(R.id.button_pick_contact)).perform(ViewActions.click());
        //校验回调
        Espresso.onView(ViewMatchers.withId(R.id.edit_text_caller_number))
                .check(ViewAssertions.matches(ViewMatchers.withText(VALID_PHONE_NUMBER)));
    }


}
