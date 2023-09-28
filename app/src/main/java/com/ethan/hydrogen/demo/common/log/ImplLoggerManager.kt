package com.ethan.hydrogen.demo.common.log

import android.content.Context
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import com.ethan.hydrogen.demo.common.log.ILoggerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext //import com.ethan.logbacktest.BuildConfig
//import io.github.uhsk.kit.format
//import kotlinx.coroutines.*
//import org.apache.commons.compress.archivers.zip.Zip64Mode
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
//import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
//import org.apache.commons.io.FileUtils
import org.slf4j.LoggerFactory

internal class ImplLoggerManager(private val mContext:Context) : ILoggerManager {
    //override suspend fun init() {
    //    CoroutineScope(Dispatchers.IO).launch {
    //        async {
    //            val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
    //            loggerContext.reset()
    //            loggerContext.putProperty("LOG_DIR", mContext.externalCacheDir!!.path)
    //            val joranConfigurator = JoranConfigurator()
    //            joranConfigurator.context = loggerContext
    //            joranConfigurator.doConfigure(mContext.assets.open("configs/logback.xml"))
    //        }
    //
    //    }
    //
    //}

    override suspend fun init() = withContext(context = Dispatchers.IO) {
        val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        loggerContext.reset()
        loggerContext.putProperty("LOG_DIR", mContext.externalCacheDir!!.path)
        val joranConfigurator = JoranConfigurator()
        joranConfigurator.context = loggerContext
        joranConfigurator.doConfigure(mContext.assets.open("configs/logback.xml"))
        return@withContext
    }

    //override suspend fun zip(): Uri = withContext(context = Dispatchers.IO) {
    //    val todayLogFile = File(mContext.externalCacheDir, String.format(Locale.ENGLISH, "logs/%s.log", Date().format(pattern = "yyyy-MM-dd")))
    //    val outputFile = File(mContext.externalCacheDir, "shared/today-log.zip")
    //    if (outputFile.exists()) {
    //        outputFile.delete()
    //    }
    //    FileUtils.forceMkdirParent(outputFile)
    //
    //    val zipArchiveOutputStream = ZipArchiveOutputStream(outputFile)
    //    zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded)
    //
    //    val zipArchiveEntry = ZipArchiveEntry(todayLogFile, todayLogFile.name)
    //    zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry)
    //    zipArchiveOutputStream.write(todayLogFile.readBytes())
    //    zipArchiveOutputStream.closeArchiveEntry()
    //    zipArchiveOutputStream.finish()
    //    zipArchiveOutputStream.close()
    //
    //    return@withContext FileProvider.getUriForFile(mContext, "${BuildConfig.APPLICATION_ID}.providers.file", outputFile)
    //}

}