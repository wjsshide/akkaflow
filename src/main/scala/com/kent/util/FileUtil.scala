package com.kent.util

import java.io.File
import java.io.FileInputStream
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream

object FileUtil {
  /**
	 * 得到特定目录下指定扩展名的文件集合
	 * 
	 */
	 def listFilesWithExtensions(file: File, extensions: List[String]): List[File] = {
     var fileList: List[File] = List()
	   if(file != null){
	     if(file.isDirectory()){
	       fileList = fileList ++ file.listFiles().flatMap { listFilesWithExtensions(_, extensions) }.toList
	     }else{
	       if(checkFileExtension(file, extensions)) fileList = fileList:+file
	     }
	   }
     fileList
	 }
  /**
	  * 检出指定文件是否扩展名为extensions
	  * @param file
	  * @param extension
	  * @return
	  */
	 def checkFileExtension(file: File, extensions: List[String]):Boolean = {
		 if ((file.getName() != null) && (file.getName().length() > 0)) { 
       val i = file.getName().lastIndexOf('.');
       if (i > -1 && i < file.length() - 1) { 
         val size = extensions.filter { file.getName().substring(i + 1) == _ }.size
         if(size > 0) return true
       } 
		 }
		 return false;
	 }
  	/**
  	 * 从指定路径中解析出文件名 
  	 */
  	def getFileName(path: String): String = {
      val f = new File(path)
      f.getName
    }
	 def main(args: Array[String]): Unit = {
	   val files = listFilesWithExtensions(new File("/Users/kent/Documents/github_repository/akkaflow"), List("xml"))
	   files.foreach { x => println(x.getName) }
	 }
	/**
	 * 读取文件
	 */
	def readFile(fileName: String):Array[Byte] = readFile(new File(fileName))
	/**
	 * 读取文件
	 */
  def readFile(file: File):Array[Byte] = {
    val is = new FileInputStream(file)
    val os = new ByteArrayOutputStream()
    val buffer = new Array[Byte](8192)
    var n = 0
    do {
      n = is.read(buffer, 0, buffer.length)
      if(n != -1) os.write(buffer, 0, n)
    } while(n != -1)
    is.close()
    return os.toByteArray()
  }
  /**
   * 写入文件
   */
  def writeFile(path: String, content:Array[Byte]) = {
     val fos = new FileOutputStream(path)
     fos.write(content)
     fos.flush()
     fos.close()
  }
	 
}