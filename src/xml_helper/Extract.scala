package xml_helper

import scala.xml.Node

object Extract {

  def extractString(extract: String)(node: Node): String =
    (node \ ("@" + extract)).text
    
  def extractInt(extract: String)(node: Node): Int =
    (node \ ("@" + extract)).text.toInt
    
  def extractDouble(extract: String)(node: Node): Double =
    (node \ ("@" + extract)).text.toDouble
}