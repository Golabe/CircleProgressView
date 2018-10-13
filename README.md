# CircleProgressView


**A cirlce progress  view** 

<img src="https://github.com/Golabe/CircleProgressView/blob/master/image/1.PNG?raw=true" style="zoom:50%" />
<img src="https://github.com/Golabe/CircleProgressView/blob/master/image/2.PNG?raw=true" style="zoom:50%" />

### Usage
### Gradle
### Add it in your app build.gradle at the end of repositories:

> compile 'top.golabe.circleprogressview:library:0.0.1'



### In layout.xml
```
<top.golabe.library.CircleProgressView
        android:id="@+id/circleProgressView"
        android:layout_centerInParent="true"
        app:progress="100"
        app:progress_bar_width="1dp"
        app:progress_bg_color="#689feb"
        app:progress_text_size="14sp"
        app:progress_bar_color="#ffffff"
        app:progress_text_color="#ffffff"
        android:layout_width="100dp"
        android:layout_height="100dp" />
```
### In code

```
        mCircleProgressView.setProgress();
        mCircleProgressView.setProgressBarColor();
        mCircleProgressView.setProgressBarWidth();
        mCircleProgressView.setProgressBgColor();
        mCircleProgressView.setProgressMax();
        mCircleProgressView.setProgressMin();
        mCircleProgressView.setProgressTextColor();
        mCircleProgressView.setProgressTextSize();
```

### Attr

``` <declare-styleable name="CircleProgressView">
        <attr name="progress_bar_color" format="color" />
        <attr name="progress_bg_color" format="color" />
        <attr name="progress_max" format="integer" />
        <attr name="progress_min" format="integer" />
        <attr name="progress_text_color" format="color" />
        <attr name="progress" format="integer" />
        <attr name="progress_text_size" format="dimension"/>
        <attr name="progress_bar_width" format="dimension"/>
    </declare-styleable>
```