package ren.imyan.ktx

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-12 14:21
 * @website https://imyan.ren
 */
fun Context.saveBitmap2Galley(bitmap: Bitmap, dir: String = "", name: String): Boolean {

    val isSucceeded: Boolean
    val fos: OutputStream?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/$dir")
        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fos = imageUri?.let { contentResolver.openOutputStream(it) }
    } else {
        val imageDir = Environment.getExternalStoragePublicDirectory("newMedia/qcodes")
        if (!imageDir.exists()) {
            imageDir.mkdirs()
        }
        val file = File(imageDir, name)

        fos = FileOutputStream(file)
    }
    isSucceeded = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    fos?.flush()
    fos?.close()

    return isSucceeded
}