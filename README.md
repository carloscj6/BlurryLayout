# BlurryLayout
Create a beautiful blurry background with simple code and less work.

Use this layout to place views above it while keeping the background awesome. Use your image and colors to come up 
with a cool layout that matches your needs
## Screenshot
<img src="/Screenshots/device-2018-10-11-002239.png" width="300px">

## Setup
First include the library in your project with:

```gradle
dependencies {
    implementation 'com.revosleap.layout:blurrylayout:1.0.1'
}
```

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
### Xml Attributes
You can set `xml` atttributes to you layout as follows:

create namespace declaration ` xmlns:app="http://schemas.android.com/apk/res-auto"`
then add attributes as:
```xml
    app:blurColor="@android:color/white" <!--takes Color value int-->
    app:blurOpacity=".4" <!--takes value float-->
    app:blurImage="@drawable/together" <!--takes image drawable-->
    app:blurRadius="13" <!--takes value int-->
```
**Sample**
```xml
<com.revosleap.blurrylayout.BlurryLayout

    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/blurLayout"
    tools:context=".Blurry"
    app:blurColor="@android:color/white"
    app:blurOpacity=".4"
    app:blurImage="@drawable/together"
    app:blurRadius="13" >
```
    
### Programatically
In your activity class you need to pass the image to be used as background;

Create an instance of the class like:
> Java
```java
BlurryLayout blurLayout = findViewById(R.id.blurLayout);
```
or
> Kotlin
```kotlin
val blurLayout = findViewById(R.id.blurLayout) as BlurryLayout
```

### Image blur

Set image and blur radius as:
> Java
```java
blurLayout.blurBackground(bitmap,10);
```
or 
> Kotlin
```kotlin
blurLayout.blurBackground(bitmap, 10)
```
**Note:** blur radius takes ```int```. It determines the amount of the blur effect.

### Blur color
Set as:
> Java
```java 
blurLayout.blurColor(Color.WHITE);
```
or 
> Kotlin
```kotlin
blurLayout.blurColor = Color.WHITE
```

This is the color of the background blur. 

Can be determined by the color of views above it. Example,
textviews with light color will need a darker blur color and vice versa. 
**Note:** blur color takes `int`.

### Blur opacity
set as:
> Java
```java 
blurLayout.blurOpacity(0.3f);
```
or
> Kotlin 
```kotlin
blurLayout.blurOpacity = 0.3F
```
Determines the opacity of the blurry backgroud. Higher values mean the blur color is more visible and vice versa.
It takes `float`

##
Coded with :blue_heart: by [revosleap](https://revosleap.com/).
