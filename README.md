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

查找View执行操作

Espresso.onView(matcher).perform(action)

查找View校验属性

Espresso.onView(matcher).check(ViewAssertions.matches(matcher))




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

- ViewAssertions

方法|备注
---|---
check|校验
doesNotExist|不存在







