# Espresso
### 导包介绍
```java
// 基础
androidTestImplementation 'com.android.support.test:runner:1.0.1'
androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.1'
androidTestImplementation 'com.android.support.test:rules:1.0.1'
// Intent
androidTestImplementation 'com.android.support.test.espresso:espresso-intents:3.0.1'
// RecyclerView
androidTestImplementation 'com.android.support:recyclerview-v7:27.1.1'
androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:3.0.1'
```
### 基础用法

[BasicUsageActivityTest](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/BasicUsageActivityTest.java)

查找View执行操作

Espresso.onView(matcher).perform(action)

查找View校验属性

Espresso.onView(matcher).check(ViewAssertions.matches(matcher))

### AdapterView

[ListViewActivityTest](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/ListViewActivityTest.java)

查找item执行操作

Espresso.onData(matcher).atPosition(int).perform(action)

###  startActivityForResult

[IntentActivityTest](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/IntentActivityTest.java)

**注意**

```
//第一步 导包
androidTestImplementation 'com.android.support.test.espresso:espresso-intents:3.0.1'
//第二步 关联activity -> IntentsTestRule
@Rule
public IntentsTestRule<IntentActivity> intentsTestRule = new IntentsTestRule<>(IntentActivity.class);
//第三步 模拟ActivityResult
private Instrumentation.ActivityResult createImageCaptureActivityResultStub(){
    Bundle bundle = new Bundle();
    bundle.putParcelable(IntentActivity.KEY_IMAGE_DATA, BitmapFactory.decodeResource(
            intentsTestRule.getActivity().getResources(), R.mipmap.ic_launcher));
    Intent resultData = new Intent();
    resultData.putExtras(bundle);
    return new Instrumentation.ActivityResult(Activity.RESULT_OK, resultData);
}
//第四步 设置需要拦截的intent属性 响应 ActivityResult
Intents.intending(IntentMatchers.hasAction(MediaStore.ACTION_IMAGE_CAPTURE)).respondWith(activityResult);
//第五步 发送Intent事件
click()

```

### Permission

[CallPhoneActivityTest](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/CallPhoneActivityTest.java)

```
//添加权限
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    InstrumentationRegistry.getInstrumentation().getUiAutomation().executeShellCommand(
            "pm grant " + InstrumentationRegistry.getTargetContext().getPackageName()
                    + " android.permission.CALL_PHONE");
}
```

### RecyclerView
[RecyclerViewActivityTest](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/RecyclerViewActivityTest.java)

```
//导包
androidTestImplementation 'com.android.support:recyclerview-v7:27.1.1'
androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:3.0.1'

// 查询控件 滑动并点击
Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
        .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, ViewActions.click()));
```

### WebView

[WebViewActivityTest ](https://github.com/shuzhuchengfu2/EspressoDemo/blob/master/app/src/androidTest/java/barray/test/espresso/act/WebViewActivityTest.java)

### 常用api

- ViewMatchers

方法|备注
---|---
withClassName|根据ClassName 查找View
isDisplayed|View是否显示
isCompletelyDisplayed|View是否完全显示
isDisplayingAtLeast|View显示的显示的最小比例
isEnabled|View是否可用
isFocusable|View是否可聚焦的
hasFocus|View是否具有焦点
isSelected|View是否被选中
withId|根据id 查找View
withText|根据text查找View
withHint|根据hint查找View
isChecked|View是否被选中
isNotChecked|View是否没有被选中
isClickable|View是否可以被点击
withAlpha|根据透明度查找View
withParent|根据parent查找View
withChild|根据child查找View
hasChildCount|是否有count个child
withInputType|根据inputType查找View
hasBackground|View是否有背景资源
hasTextColor|View是否有字体颜色


- Matchers

方法|备注
---|---
instanceOf|根据类创建一个实例

- ViewActions

方法|备注
---|---
clearText|清除内容
click|点击
swipeLeft|左滑
swipeRight|右滑
swipeDown|下滑
swipeUp|上滑
closeSoftKeyboard|关闭软键盘
pressBack|返回
doubleClick|双击
longClick|长击
scrollTo|滑动至
typeText|输入内容（不建议使用）
replaceText|输入内容
openLinkWithText|打开链接

- RecyclerViewActions
方法|备注
---|---
scrollToHolder|滑动至某ViewHolder
scrollTo|滑动至某item
scrollToPosition|滑动至某位置

- ViewAssertions

方法|备注
---|---
check|校验
doesNotExist|不存在







