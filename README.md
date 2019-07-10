# HydrogenAndroid

## 说明

这是一个基于JAVA语言的Android工具类库，该库集成了常用的工具类,组件等。就比如`StringUtil`,'FileUtil','DeviceUtil',CircleImageView,LoadingDialog;

## 使用实例
   ```
   StringUtil.getInstance().reverseString("HydrogenAndroid");
   ```
## 使用
### 步骤1 添加 JitPack repository 到项目的根目录
   ```
    allprojects {
        repositories {
             maven { url 'https://jitpack.io' }
        }
    }
   ```
### 步骤2
在你的model目录下的build中增加依赖，替换下面`latestVersion`最新的版本，当前最新的版本:[![](https://jitpack.io/v/wonium-ethan/HydrogenAndroid.svg)](https://jitpack.io/#wonium-ethan/HydrogenAndroid)

```
dependencies {
	  implementation 'com.github.ethan-wonium:HydrogenAndroid:latestVersion'
```
### 工具库说明
工具类 | 描述
---   | ---
[ActivityManagerUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ActivityManagerUtil.java)|Activity管理类
[MathUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/MathUtil.java)| 数值计算工具类)
[BitmapUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/BitmapUtil.java)| 获取Bitmap和对Bitmap的操作
[BlueToothUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/BlueToothUtil.java)| 蓝牙相关的操作
[ByteUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ByteUtil.java)| Byte字节处理工具类
[CloseUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/CloseUtil.java)| IO流的关闭操作
[ColorUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ColorUtil.java)| 颜色的生成转换滤色等操作
[CRCUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/CRCUtil.java) | CRC校验工具类
[DataCleanUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/DataCleanUtil.java) | 获取/清除缓存工具类
[DateUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/DateUtil.java) | 日期工具类
[DensityUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/DensityUtil.java) | 屏幕信息获取数值的转换
[DeviceUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/DeviceUtil.java) | 获取设备信息类
[FileUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/FileUtil.java) | 对文件读写的操作
[InputMethodManagerUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/InputMethodManagerUtil.java) | 软键盘的打开和隐藏操作
[IntentUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/IntentUtil.java) | Intent打开一些系统页面的操作
[Md5Util](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/Md5Util.java) | MD5加密工具
[MediaUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/MediaUtil.java) | 多媒体资源的操作
[NetWorkUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/NetWorkUtil.java) | 网络状态
[SharedPreferencesUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/SharedPreferencesUtil.java) | SharedPreferences读写操作
[ShellUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ShellUtil.java) | Shell 工具类
[StatusBarUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/StatusBarUtil.java) | 状态栏沉浸工具类
[StringUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/StringUtil.java) | 字符串的操作
[ThreadPoolUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ThreadPoolUtil.java) | 线程池的简单操作
[ToastUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ToastUtil.java) | Toast工具类
[VerifyUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/VerifyUtil.java) | 常用的验证方法
[ZLibUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ZLibUtil.java) | ZLibUtil

### 简单封装的Adapter
Adapter | 描述
---     | ---
[BaseListAdapter](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/adapter/BaseListAdapter.java) | BaseListAdapter支持ListView，GridView
[BaseRecyclerViewAdapter](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/adapter/BaseRecyclerViewAdapter.java) | BaseRecyclerViewAdapter 是对RecyclerView的adapter 的简单封装
[BaseSpinnerAdapter](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/adapter/BaseSpinnerAdapter.java) | BaseSpinnerAdapter 是针对Spinner View 己成BaseAdapter 简单封装的Adapter
[BaseViewHolder](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/adapter/BaseViewHolder.java) | BaseViewHolder 结合DataBinding封装的一个RecyclerView 的ViewHolder




## LICENSE

```text
Copyright  $today.year.  Ethan

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
