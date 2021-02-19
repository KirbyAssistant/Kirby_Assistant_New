package ren.imyan.ktx

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import ren.imyan.ktx.model.SaveState
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-12 14:21
 * @website https://imyan.ren
 */
fun Context.saveBitmap2Galley(bitmap: Bitmap, dir: String = "", name: String): SaveState {

    val isSucceeded: Boolean
    val fos: OutputStream?
    val filePath: String?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name)
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/$dir")
        val imageUri =
            contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        fos = imageUri?.let { contentResolver.openOutputStream(it) }
        filePath = imageUri?.let { getRealPathFromURI(this, it) }
    } else {
        val imageDir = Environment.getExternalStoragePublicDirectory("newMedia/qcodes")
        if (!imageDir.exists()) {
            imageDir.mkdirs()
        }
        val file = File(imageDir, name)
        fos = FileOutputStream(file)
        filePath = file.path
    }
    isSucceeded = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    fos?.flush()
    fos?.close()

    return SaveState(isSucceeded, filePath.toString())
}

private fun getRealPathFromURI(context: Context,contentUri: Uri): String? {
    var res: String? = null
    val projection = arrayOf(MediaStore.Images.Media.DATA)
    val cursor = context.contentResolver.query(contentUri, projection, null, null, null)
    if (cursor?.moveToFirst() == true) {
        val index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        res = cursor.getString(index)
    }
    cursor?.close()
    return res
}