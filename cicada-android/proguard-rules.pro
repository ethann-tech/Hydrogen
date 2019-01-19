# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#-dontwarn com.google.common.util.**
#-keep com.google.common.util.**
## com.google.guava:guava:24.1-jre
#-keep class com.google.common.io.Resources {
#    public static <methods>;
#}
#-keep class com.google.common.collect.Lists {
#    public static ** reverse(**);
#}
#-keep class com.google.common.base.Charsets {
#    public static <fields>;
#}
#
## SwipeBackLayout
## -keepclassmembers  class com.wonium.cicada.android.ui.weight.SwipeBackLayout{*;}
#
#-keepclassmembers enum * {
#    public static **[] values();
#    public static ** valueOf(java.lang.String);
#}
#-keep public class com.wonium.cicada.android.ui.weight.SwipeBackLayout{*;}