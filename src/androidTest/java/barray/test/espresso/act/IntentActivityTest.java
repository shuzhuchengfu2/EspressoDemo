package barray.test.espresso.act;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;


import org.hamcrest.Description;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import barray.test.espresso.R;

/**
 * author： xiongdejin
 * date: 2018/4/18
 * describe:
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class IntentActivityTest {
    @Rule
    public ActivityTestRule<IntentActivity> activityTestRule = new ActivityTestRule<>(IntentActivity.class);

    @Before
    public void stubCameraIntent(){
        Instrumentation.ActivityResult activityResult = createImageCaptureActivityResultStub();
        Intents.intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);
    }

    @Test
    public void checkIntent(){
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(IsNot.not(hasDrawable())));
        Espresso.onView(ViewMatchers.withId(R.id.button_take_photo)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(hasDrawable()));
    }

    private Instrumentation.ActivityResult createImageCaptureActivityResultStub(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(IntentActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                activityTestRule.getActivity().getResources(), R.mipmap.ic_launcher));
        Intent resultData = new Intent();
        resultData.putExtras(bundle);
        return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
    }

    public BoundedMatcher<View, ImageView> hasDrawable() {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has drawable");
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return imageView.getDrawable() != null;
            }
        };
    }
}
