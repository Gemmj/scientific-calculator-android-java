# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.

# Keep our main application classes
-keep class com.scientificcalculator.android.** { *; }

# Keep exp4j library classes (mathematical expression evaluation)
-dontwarn net.objecthunter.exp4j.**
-keep class net.objecthunter.exp4j.** { *; }

# Keep SQLite database related classes
-keep class * extends android.database.sqlite.SQLiteOpenHelper
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep ViewBinding classes
-keep class * extends androidx.viewbinding.ViewBinding {
    public static *** inflate(android.view.LayoutInflater);
    public static *** inflate(android.view.LayoutInflater, android.view.ViewGroup, boolean);
    public static *** bind(android.view.View);
}

# Keep data classes and models
-keep class com.scientificcalculator.android.data.** { *; }

# AndroidX and Material Design
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

# Keep RecyclerView adapter classes
-keep class * extends androidx.recyclerview.widget.RecyclerView$Adapter {
    public <init>(...);
}

# General Android rules
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

