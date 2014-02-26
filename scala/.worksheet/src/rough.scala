import scala.xml

import decision_trees._

object rough {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(139); 

  val rf_xml = xml.XML.loadFile("C:/eclipse/workspace/decision_trees/rf.xml");System.out.println("""rf_xml  : scala.xml.Elem = """ + $show(rf_xml ));$skip(42); 

  val tree_xml = (rf_xml \ "tree").head;System.out.println("""tree_xml  : scala.xml.Node = """ + $show(tree_xml ));$skip(44); 

  val node_xml = (tree_xml \ "node").head;System.out.println("""node_xml  : scala.xml.Node = """ + $show(node_xml ));$skip(425); 

  def deserialize(node: xml.Node) = {
    def extractInt(key: String) = (node \ "@key").text.toInt
    def extractDouble(key: String) = (node \ "@key").text.toDouble
    val leaf_node = extractInt("leaf_node")
    if (leaf_node == 1) Leaf(extractInt("prediction"), extractInt("depth"), extractInt("width"))
    else NonLeaf((node \ "@metric").text, extractDouble("threshold"), extractInt("depth"), extractInt("width"))
  };System.out.println("""deserialize: (node: scala.xml.Node)Product with Serializable with decision_trees.Node""")}
}
