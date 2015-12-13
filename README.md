# CircleLoadingView
----------------

An image-view with circle loading animation

![CircleLoadingView-screenshot](https://raw.githubusercontent.com/jhw-dev/CircleLoadingView/master/docs/screenshot.gif)

### Integration

The lib is available on Maven Central, you can find it with [Gradle]()

``` xml

  allprojects {
      repositories {
          jcenter()
          maven { url "https://jitpack.io" }
      }
  }

  dependencies {
     compile 'com.tengchong.android:circleloadingview:v1.0'
  }

```

### Usage

#### CircleLoadingView

Declare a CircleLoadingView inside your XML layout file

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

Or use CircleLoadingView from code dynamically && change percent of the circle.

``` java

        CircleLoadingView loadingView =new CircleLoadingView();
        loadingView.setImageBitmap(
                BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
        loadingView.setPercent(88);

```

### Customization

You can change several attributes in the XML file:

* cl_circleRadius [dimension] --> the circle's radius
* cl_circleStrokeSize [dimension] --> the circle's stroke size
* cl_fillAnimationDuration [integer] --> duration of the animation when reached 100%
* cl_src [dimension] --> Image drawable src.# Notice: CircleLoadingView only supports bitmapDrawable!

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
