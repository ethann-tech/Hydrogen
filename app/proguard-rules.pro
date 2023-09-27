# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle
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
#-dontwarn com.wonium.cicada.android.ui.weight.**
#-keep class com.wonium.cicada.android.ui.weight.**{*;}
#-keep class com.wonium.cicada.android.ui.weight.SwipeBackLayout{*;}
##-keep class com.wonium.cicada.android.ui.weight.SwipeBackLayout.DragEdge{*;}
#-keep enum com.wonium.cicada.android.ui.weight.SwipeBackLayout$DragEdge{*;}
#-keep  class com.wonium.cicada.android.ui.weight.SwipeBackLayout$ * {
# *;
#}
##-keepclassmembers enum * {
##    *;
##}
##-keepclassmembers class com.wonium.cicada.android.ui.weight.SwipeBackLayout{
##    public void test(com.wonium.cicada.android.ui.weight.SwipeBackLayout$DragEdge);
##}
#避免混淆泛型
-keepattributes Signature

-dontwarn org.**
-dontwarn com.**
-dontwarn afu.**

-obfuscationdictionary          proguard-dic-6.txt
-renamesourcefileattribute      proguard-dic-6.txt
-classobfuscationdictionary     proguard-dic-6.txt
-packageobfuscationdictionary   proguard-dic-6.txt
-repackageclasses               java.io

-dontwarn **

-keepattributes Signature
-keepattributes *Annotation*
-keepclassmembers class * { @org.greenrobot.eventbus.Subscribe <methods>; }

##########################################################################################################
-keep class androidx.fragment.app.FragmentTransaction{ *; }
-keep class androidx.fragment.app.FragmentTransaction$Op{ *; }
-keep class android.support.v4.app.BackStackRecord{ *; }
-keep class android.support.v4.app.BackStackRecord$Op{ *; }


#######################################Glide##############################################################
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule





# 忽略警告

#-ignorewarning
-dontnote android.widget.Space
-dontnote com.wonium.cicada.**
-dontnote com.alibaba.android.**

# 保持所有databinding类
-dontwarn androidx.databinding.**
-dontnote androidx.databinding.**
-dontnote android.databinding.**
-keep class androidx.databinding.** { *; }
-keep class androidx.annotation.**{*;}

#leakcanary
-dontwarn com.squareup.haha.guava.**
-dontwarn com.squareup.haha.perflib.**
-dontwarn com.squareup.haha.trove.**
-dontwarn com.squareup.leakcanary.**
-keep class com.squareup.haha.** { *; }
-keep class com.squareup.leakcanary.** { *; }
-keep class gnu.trove.**{*;}

# material
-keep class com.google.android.material.**


# ################################## ARouter #############################################################
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
# If you use the byType method to obtain Service, add the following rules to protect the interface:
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
# If single-type injection is used, that is, no interface is defined to implement IProvider, the following rules need to be added to protect the implementation
-keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#-keep public class com.google.android.material.**{*;}
################################## QMUI #############################################################
-keep class **_FragmentFinder { *; }
-keep class com.qmuiteam.qmui.arch.record.** { *; }
-keep class androidx.fragment.app.* { *; }
