# CicadaAndroid

## 说明

这是一个Android基于JAVA语言的工具类库，该库集成了常用的工具类，就比如`StringUtil`,'FileUtil','DeviceUtil'等。
## 使用实例
   ```
   StringUtil.getInstance().reverseString("wonium");
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

<<<<<<< HEAD
在你的model目录下的build中增加依赖，替换下面`latestVersion`位最新的版本，当前最新的版本: [![](https://jitpack.io/v/wonium-ethan/CicadaAndroid.svg)](https://jitpack.io/#wonium-ethan/CicadaAndroid)

```
dependencies {
	 implementation 'com.github.wonium-ethan:CicadaAndroid:0.1.8'
=======
在你的model目录下的build中增加依赖，替换下面`latestVersion`最新的版本，当前最新的版本: [![](https://jitpack.io/v/ethan-wonium/CicadaAndroid.svg)](https://jitpack.io/#ethan-wonium/CicadaAndroid)


```
dependencies {
	  implementation 'com.github.ethan-wonium:CicadaAndroid:latestVersion'
>>>>>>> develop
}
```

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
