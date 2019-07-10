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
[ActivityManagerUtil](https://github.com/wonium-ethan/HydrogenAndroid/tree/master/hydrogen-android/src/main/java/com/wonium/cicada/utils/ActivityManagerUtil.java) |Activity管理类

## LICENSE

```text
Copyright  $today.year.  ethan

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
