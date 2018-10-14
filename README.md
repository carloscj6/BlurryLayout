# BlurryLayout
Create a beautiful blurry background with simple code and less work.

Use this layout to place views above it while keeping the background awesome. Use your image and colors to come up 
with a cool layout that matches yorur needs
## Screenshot
<img src="/Screenshots/device-2018-10-11-002239.png" width="250px">

## Setup
In your project level gradle include:

`maven {
            url  "https://dl.bintray.com/carloscj6/layout"
        }`
        
        
Then include the library in your app level gradle with:

`implementation 'com.revosleap.layout:blurrylayout:1.0.0'`

In your `layout.xml` file, set the parent layout as:

```xml
<com.revosleap.blurrylayout.BlurryLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:id="@+id/blurLayout"
    tools:context=".Blurry">
</com.revosleap.blurrylayout.BlurryLayout>
```

In your activity class you need to pass the image to be used as background;

### Image blur

Set image and blur radius as


```java blurLayout.blurBackground(bitmap,10);```

**Note:** blur radius takes ```java int```. It determines the amount of the blur effect.

### Blur color
Set as ```java blurLayout.blurColor(Color.WHITE);```.
This is the color of the background blur. 

Can be determined by the color of views above it. Example,
textviews with light color will need a darker blur color and vice versa. 
**Note:** blur color takes `int`.

### Blur opacity
set as ```java blurLayout.blurOpacity(0.3f);```

Determines the opacity of the blurry backgroud. Higher values mean the blur color is more visible and vice versa.
It takes ``` java float```

