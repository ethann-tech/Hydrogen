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
# SwipeBackLayout
#-keep public class com.wonium.cicada.android.ui.weight.SwipeBackLayout{*;}
# 这个library  cicada-android  里面有一个混淆配置文件
# 这个 app  moduel 下面也有一个
# 这个就这样了,保留这个类   然后还是不行 。
# 现在不是没有被混淆吗？ 下面 提示要保留的方法
# 测试一下