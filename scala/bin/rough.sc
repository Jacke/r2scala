import scala.xml

import decision_trees._

object rough {

  val rf_xml = xml.XML.loadFile("C:/eclipse/workspace/decision_trees/rf.xml")
                                                  //> rf_xml  : scala.xml.Elem = <random_forest>
                                                  //| <tree id="1">
                                                  //| <node leaf_node="0" width="0" depth="0" prediction="0" threshold="2.45" metr
                                                  //| ic="3"/>
                                                  //| <node leaf_node="1" width="0" depth="1" prediction="1" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="1" depth="1" prediction="0" threshold="1.65" metr
                                                  //| ic="4"/>
                                                  //| <node leaf_node="0" width="2" depth="2" prediction="0" threshold="1.35" metr
                                                  //| ic="4"/>
                                                  //| <node leaf_node="0" width="3" depth="2" prediction="0" threshold="4.85" metr
                                                  //| ic="3"/>
                                                  //| <node leaf_node="1" width="4" depth="3" prediction="2" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="5" depth="3" prediction="0" threshold="7.1" metri
                                                  //| c="1"/>
                                                  //| <node leaf_node="0" width="6" depth="3" prediction="0" threshold="5.4" metri
                                                  //| c="1"/>
                                                  //| <node leaf_node="1" width="7" depth="3" prediction="3" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="10" depth="4" prediction="0" threshold="6.15" met
                                                  //| ric="1"/>
                                                  //| <
                                                  //| Output exceeds cutoff limit.

  val tree_xml = (rf_xml \ "tree").head           //> tree_xml  : scala.xml.Node = <tree id="1">
                                                  //| <node leaf_node="0" width="0" depth="0" prediction="0" threshold="2.45" metr
                                                  //| ic="3"/>
                                                  //| <node leaf_node="1" width="0" depth="1" prediction="1" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="1" depth="1" prediction="0" threshold="1.65" metr
                                                  //| ic="4"/>
                                                  //| <node leaf_node="0" width="2" depth="2" prediction="0" threshold="1.35" metr
                                                  //| ic="4"/>
                                                  //| <node leaf_node="0" width="3" depth="2" prediction="0" threshold="4.85" metr
                                                  //| ic="3"/>
                                                  //| <node leaf_node="1" width="4" depth="3" prediction="2" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="5" depth="3" prediction="0" threshold="7.1" metri
                                                  //| c="1"/>
                                                  //| <node leaf_node="0" width="6" depth="3" prediction="0" threshold="5.4" metri
                                                  //| c="1"/>
                                                  //| <node leaf_node="1" width="7" depth="3" prediction="3" threshold="0" metric=
                                                  //| "0"/>
                                                  //| <node leaf_node="0" width="10" depth="4" prediction="0" threshold="6.15" met
                                                  //| ric="1"/>
                                                  //| <node leaf_node="1"
                                                  //| Output exceeds cutoff limit.

  val node_xml = (tree_xml \ "node").head         //> node_xml  : scala.xml.Node = <node leaf_node="0" width="0" depth="0" predict
                                                  //| ion="0" threshold="2.45" metric="3"/>

  def deserialize(node: xml.Node) = {
    def extractInt(key: String) = (node \ "@key").text.toInt
    def extractDouble(key: String) = (node \ "@key").text.toDouble
    val leaf_node = extractInt("leaf_node")
    if (leaf_node == 1) Leaf(extractInt("prediction"), extractInt("depth"), extractInt("width"))
    else NonLeaf((node \ "@metric").text, extractDouble("threshold"), extractInt("depth"), extractInt("width"))
  }                                               //> deserialize: (node: scala.xml.Node)Product with Serializable with decision_t
                                                  //| rees.Node
}