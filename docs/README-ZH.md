# CircleLoadingView

[English Version](https://github.com/jhw-dev/CircleLoadingView/blob/master/README.md)

这是一个为了满足许多热衷于做安卓版爱疯的厂商的库。虽然她很简单，只是模拟了 iOS 下载 App 时候的动画，但是觉得可能会对许多苦逼的研发同胞们带来帮助。怀着情怀和对码农同胞们的爱，我们决定把她开源出来，如果能帮助到别人，也是好事一件。


![CircleLoadingView-screenshot](https://raw.githubusercontent.com/jhw-dev/CircleLoadingView/master/docs/screenshot.gif)

### 配置方法

#### Android Studio

``` xml
  allprojects {
      repositories {
          jcenter()
          maven { url "https://jitpack.io" }
      }
  }

  dependencies {
      compile 'com.github.jhw-dev:CircleLoadingView:v1.3'
  }

```

#### Eclipse

> 我觉得这个时候还在用 Eclipse 做 Android 开发的，要么是大神，要么就该换公司了，不如给我发个简历?

### 使用方法

#### XML 定义

``` xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.tengchong.android.CircleLoadingView
        android:id="@+id/loading"
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:cl_circleRadius="40dp"
        app:cl_circleStrokeSize="5dp"
        app:cl_fillAnimationDuration="200"
        app:cl_src="@drawable/avatar"/>

</RelativeLayout>

```

#### 用代码动态使用以及设定进度方式

``` java

        CircleLoadingView loadingView =new CircleLoadingView();
        loadingView.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
        loadingView.setPercent(88);

```

### 自定义属性

#### 目前版本比较简单，仅支持在 XML 文件中自定义，另外，Drawable 只支持 BitmapDrawable，相信你懂的。

* cl_circleRadius [dimension] --> 中间那个圆的半径
* cl_circleStrokeSize [dimension] --> 圆的边框大小 
* cl_fillAnimationDuration [integer] --> 最后扩散动画的时间 
* cl_src [dimension] --> 背景的 BitmapDrawable 资源

### MIT License

```
The MIT License (MIT)

Copyright (c) 2015 聚会玩

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
