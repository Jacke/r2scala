import scala.xml

import decision_trees._

object rough {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(143); 

  val rf_xml = xml.XML.loadFile("C:/eclipse/workspace/r2scala/scala/data/rf.xml");System.out.println("""rf_xml  : scala.xml.Elem = """ + $show(rf_xml ));$skip(42); 

  val tree_xml = (rf_xml \ "tree").head;System.out.println("""tree_xml  : scala.xml.Node = """ + $show(tree_xml ));$skip(36); 

  val nodes = (tree_xml \ "node");System.out.println("""nodes  : scala.xml.NodeSeq = """ + $show(nodes ));$skip(85); val res$0 = 
                                                  
   nodes.map(Tree.deserialize(_));System.out.println("""res0: scala.collection.immutable.Seq[Product with Serializable with decision_trees.Node] = """ + $show(res$0))}
}
