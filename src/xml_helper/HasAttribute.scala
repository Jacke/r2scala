package xml_helper

import scala.xml.{Node, MetaData}

object HasAttribute {
  
  private def constructor(string: String, node: Node, comparer: MetaData => Boolean) = 
    node.attributes.exists(comparer(_))
  
  def key(key: String)(node: Node) =
    constructor(key, node, _.key == key)
  
  def value(value: String)(node: Node) = 
    constructor(value, node, _.value.text == value)
  
  def pair(key: String, value: String)(node: Node) {
    node.attributes.exists(att => att.key == key && att.value.text == value)
  }
}