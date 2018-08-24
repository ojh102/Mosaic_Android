package com.teamnexters.mosaic.utils

import android.content.Context
import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream

/**
 * Created by daesoon.choi on 2018. 8. 24..
 */
fun saveBitmaptoFileCache(context: Context, bitmap: Bitmap): File {
    val fileCache = File(context.externalCacheDir.path, "${System.currentTimeMillis()}mosaic.jpg")
    fileCache.createNewFile()

    val out = FileOutputStream(fileCache)

    bitmap.compress(Bitmap.CompressFormat.JPEG,100,out)

    return fileCache
}