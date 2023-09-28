package com.ethan.hydrogen.demo.common.log

import android.net.Uri

internal interface ILoggerManager {
    suspend fun init()
    //suspend fun zip(): Uri
}