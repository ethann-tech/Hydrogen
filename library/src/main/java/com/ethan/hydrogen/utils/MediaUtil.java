/*
 * Copyright (C) 2019 Ethan.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethan.hydrogen.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

import java.io.IOException;

import androidx.annotation.Keep;


/**
 * @ClassName: MediaUtil
 * @Description: 音视频工具类
 * @Author: Wonium
 * @E-mail: wonium@qq.com
 * @Blog: https://blog.wonium.com
 * @CreateDate: 2018/12/11 17:34
 * @UpdateUser: 添加更新者
 * @UpdateDate: 2018/12/11 17:34
 * @UpdateDescription: 更新描述
 * @Version:
 */
@Keep
public class MediaUtil {
    /**
     * 多媒体工具类
     */

    private MediaUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例不存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final MediaUtil INSTANCE = new MediaUtil();
    }

    public static MediaUtil getInstance() {
        return Inner.INSTANCE;
    }

    /**
     * @param context 上下文
     * @param path    文件存放路径 例如  nternal_background/bg00001.mp4;
     * @return
     */
    public Bitmap getFrameAtTimeFromAssetsVideo(Context context, String path) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            AssetFileDescriptor fileDescriptor = context.getAssets().openFd(path);
            retriever.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retriever.getFrameAtTime();
    }
}
