package com.kent.test

import java.io.File

object Test2 extends App {
  val a = new File("/Users/kent/unbackup/github_repository/akkaflow/README.md11")
  println(a.getName)
  //println(a.length())
  
  val str = "sh /tmp/tmp/${param:stadate2}/test.sh"
  println(str.split("\\s+").length)
  println(str.split("\\s+").foreach { x => println("**"+x+"**") })
  
  println(str.contains("dfdf"))
}