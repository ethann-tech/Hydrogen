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

package com.wonium.hydrogen.utils;
import android.graphics.Color;
public class ColorUtil {

    private ColorUtil() {
        if (Inner.INSTANCE != null) {
            throw new RuntimeException("该实例已存在，请通过getInstance方法获取");
        }
    }

    private static class Inner {
        private static final ColorUtil INSTANCE = new ColorUtil();
    }

    /**
     * 实例对象
     */
    public static ColorUtil getInstance() {
        return Inner.INSTANCE;
    }

    public int intToColor(int color) {
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);
        return Color.rgb(red, green, blue);
    }

    /**
     * 生成两个颜色之间的过渡颜色
     *
     * @param currColor   起始颜色
     * @param targetColor 结束颜色
     * @return int 两个颜色的过渡色
     */
    public int getTransitionalColorsBetweenTwoColors(int currColor, int targetColor) {
        int currentColor;
        int currRed = (currColor & 0xff0000) >> 16;
        int currGreen = (currColor & 0x00ff00) >> 8;
        int currBlue = (currColor & 0x0000ff);

        int targetRed = (targetColor & 0xff0000) >> 16;
        int targetGreen = (targetColor & 0x00ff00) >> 8;
        int targetBlue = (targetColor & 0x0000ff);

        if (currRed < targetRed) {
            currRed += 10;
            if (currRed > 255) {
                currRed = 255;
            }
        }
        if (currGreen < targetGreen) {
            currGreen += 10;
            if (currGreen > 255) {
                currGreen = 255;
            }
        }
        if (currBlue < targetBlue) {
            currBlue += 10;
            if (currBlue > 255) {
                currBlue = 255;
            }
        }

        if (currRed > targetRed) {
            currRed -= 10;
            if (currRed < 0) {
                currRed = 0;
            }
        }
        if (currGreen > targetGreen) {
            currGreen -= 10;
            if (currGreen < 0) {
                currGreen = 0;
            }
        }
        if (currBlue > targetBlue) {
            currBlue -= 10;
            if (currBlue < 0) {
                currBlue = 0;
            }
        }
        currentColor = Color.rgb(currRed, currGreen, currBlue);
        return currentColor;
    }

    public int getNextClorByCurrentColor(int currentColor, int[] arrays) {
        for (int i = 0; i < arrays.length; i++) {
            if (intToColor(currentColor) == intToColor(arrays[i])) {
                if (i == arrays.length - 1) {
                    currentColor = arrays[0];
                    break;
                } else {
                    currentColor = arrays[i + 1];
                    break;
                }
            }
        }
        return currentColor;
    }

    /**
     * 返回过度色序列
     *
     * @param colors 基准色序列
     * @return overColor int[]  过度色序列
     */
    public int[] getGradientColors(Integer... colors) {
        if (colors.length == 0) {
            return new int[0];
        }
        int curColor = colors[0];
        // 生成过度色
        int[] overColors = new int[colors.length * 30];
        for (int i = 0; i < colors.length; i++) {
            // 目标色
            int targetColor = colors[(i + 1) % colors.length];
            for (int j = 0; j < 30; j++) {
                overColors[i * 30 + j] = curColor;
                curColor = getTransitionalColorsBetweenTwoColors(curColor, targetColor);
            }
        }
        return overColors;
    }

}
