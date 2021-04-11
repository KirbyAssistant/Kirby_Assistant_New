package ren.demo.util

import java.io.File
import java.io.FileNotFoundException
import java.text.DecimalFormat
import java.util.*

/**
 * @author EndureBlaze/炎忍 https://github.com/EndureBlaze
 * @data 2021-04-11 16:14
 * @website https://imyan.ren
 */
object FileUtil {

    enum class SizeType {
        B, KB, MB, GB
    }

    fun newFile(path: String, name: String): File? {
        if (path.isEmpty() || name.isEmpty()) {
            return null
        } else {
            try {
                // 判断目录是否存在，如果不存在，递归创建目录
                val dir = File(path)
                if (dir.exists()) {
                    dir.mkdirs()
                }
                //组织文件路径
                val stringBuilder = StringBuilder(path).apply {
                    if (!path.endsWith("/")) {
                        append("/")
                    }
                    append(name)
                }
                //创建文件并返回文件对象
                return File(stringBuilder.toString()).apply {
                    if (!this.exists()) {
                        createNewFile()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }
    }

    /**
     * 获取文件指定文件的指定单位的大小
     *
     * @param path 文件路径
     * @param sizeType 获取大小的类型1为B、2为KB、3为MB、4为GB
     * @return double值的大小
     */
    fun getFileOrDirSize(path: String, sizeType: SizeType): Double {
        val file = File(path)
        var blockSize = 0L
        try {
            blockSize = if (file.isDirectory) {
                getDirSize(file)
            } else {
                getFileSize(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return formatFileSize(blockSize, sizeType)
    }

    /**
     * 调用此方法自动计算指定文件或指定文件夹的大小
     *
     * @param path 文件路径
     * @return 计算好的带B、KB、MB、GB的字符串
     */
    fun getAutoFileOrFilesSize(path: String): String {
        val file = File(path)
        var blockSize = 0L
        try {
            blockSize = if (file.isDirectory) {
                getDirSize(file)
            } else {
                getFileSize(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return formatFileSize(blockSize)
    }

    /**
     * 获取指定文件大小
     *
     * @param file 要计算的 File 对象
     * @return Long 计算好得到文件大小
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun getFileSize(file: File): Long {
        var size: Long = 0
        if (file.exists()) {
            size = file.length()
        } else {
            file.createNewFile()
        }
        return size
    }

    /**
     * 获取指定文件夹的大小
     *
     * @param file 要计算的 File 对象
     * @return Long 计算好得到文件大小
     * @throws FileNotFoundException
     */
    @Throws(FileNotFoundException::class)
    private fun getDirSize(file: File): Long {
        var size: Long = 0
        val files = file.listFiles()
        for (_file in Objects.requireNonNull(files)) {
            size = if (_file.isDirectory) {
                size + getDirSize(_file)
            } else {
                size + getFileSize(_file)
            }
        }
        return size
    }

    /**
     * 转换文件大小
     * @param fileSize 文件的大小值
     * @param sizeType 需要转换的类型
     * @return 转换的结果
     */
    private fun formatFileSize(fileSize: Long, sizeType: SizeType): Double {
        val df = DecimalFormat("#.00")
        return when (sizeType) {
            SizeType.B -> df.format(fileSize.toDouble()).toDouble()
            SizeType.KB -> df.format(fileSize.toDouble() / 1024).toDouble()
            SizeType.MB -> df.format(fileSize.toDouble() / 1048576).toDouble()
            SizeType.GB -> df.format(fileSize.toDouble() / 1073741824).toDouble()
        }
    }

    /**
     * 转换文件大小并且自动带上单位
     * @param fileSize 文件的大小值
     * @return 转换的结果
     */
    private fun formatFileSize(fileSize: Long): String {
        val df = DecimalFormat("#.00")
        val wrongSize = "0 B"
        if (fileSize == 0L) {
            return wrongSize
        }
        return when {
            fileSize < 1024 -> {
                "${df.format(fileSize.toDouble())} B"
            }
            fileSize < 1048576 -> {
                "${df.format(fileSize.toDouble() / 1024)} KB"
            }
            fileSize < 1073741824 -> {
                "${df.format(fileSize.toDouble() / 1048576)} MB"
            }
            else -> {
                "${df.format(fileSize.toDouble() / 1073741824)} GB"
            }
        }
    }

    /**
     * 删除单个文件
     * @param path 被删除文件的文件路径
     * @return 文件删除成功返回 true，否则返回 false
     */
    @JvmStatic
    fun delFile(path: String): Boolean {
        val file = File(path)
        if (file.isFile && file.exists()) {
            return file.delete()
        }
        return false
    }

    /**
     * 删除文件夹和目录下的文件
     * @param filePath 被删除的目录路径
     * @param isDelThisDir 是否删除当前剩下的空目录
     * @return 目录删除成功返回 true，否则返回 false
     */
    @JvmStatic
    fun delDir(filePath: String, isDelThisDir: Boolean): Boolean {
        var flag: Boolean
        var path = filePath
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!path.endsWith(File.separator)) {
            path += File.separator
        }
        val dirFile = File(path)
        if (!dirFile.exists() || !dirFile.isDirectory) {
            return false
        }
        flag = true
        val files = dirFile.listFiles()
        //遍历删除文件夹下的所有文件(包括子目录)
        for (file in Objects.requireNonNull(files)) {
            if (file.isFile) {
                //删除子文件
                flag = delFile(file.absolutePath)
                if (!flag) break
            } else {
                //删除子目录
                flag = delDir(file.absolutePath, isDelThisDir)
                if (!flag) break
            }
        }
        if (!flag)
            return false
        //是否删除当前空目录
        if (isDelThisDir)
            return dirFile.delete()
        return true
    }

    /**
     * 根据路径删除指定的目录或文件，无论存在与否
     * @param path 要删除的目录或文件
     * @param isDelThisDir 是否删除当前剩下的空目录
     * @return 删除成功返回 true，否则返回 false。
     */
    fun delFolder(path: String, isDelThisDir: Boolean): Boolean {
        val file = File(path)
        return if (!file.exists()) {
            false
        } else {
            if (file.isFile) {
                // 为文件时调用删除文件方法
                delFile(path)
            } else {
                // 为目录时调用删除目录方法
                delDir(path, isDelThisDir)
            }
        }
    }

    /**
     * 判断文件是否存在
     * @param path 要判断的文件路径
     * @return 如果存在返回 true，反之返回 false
     */
    fun isfFileIsExists(path: String): Boolean {
        try {
            val f = File(path)
            if (!f.exists()) {
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }
}

