import scala.xml

import decision_trees._

object rough {

  val rf_xml = xml.XML.loadFile("C:/eclipse/workspace/r2scala/scala/data/rf.xml")
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

  val nodes = (tree_xml \ "node")                 //> nodes  : scala.xml.NodeSeq = NodeSeq(<node leaf_node="0" width="0" depth="0"
                                                  //|  prediction="0" threshold="2.45" metric="3"/>, <node leaf_node="1" width="0"
                                                  //|  depth="1" prediction="1" threshold="0" metric="0"/>, <node leaf_node="0" wi
                                                  //| dth="1" depth="1" prediction="0" threshold="1.65" metric="4"/>, <node leaf_n
                                                  //| ode="0" width="2" depth="2" prediction="0" threshold="1.35" metric="4"/>, <n
                                                  //| ode leaf_node="0" width="3" depth="2" prediction="0" threshold="4.85" metric
                                                  //| ="3"/>, <node leaf_node="1" width="4" depth="3" prediction="2" threshold="0"
                                                  //|  metric="0"/>, <node leaf_node="0" width="5" depth="3" prediction="0" thresh
                                                  //| old="7.1" metric="1"/>, <node leaf_node="0" width="6" depth="3" prediction="
                                                  //| 0" threshold="5.4" metric="1"/>, <node leaf_node="1" width="7" depth="3" pre
                                                  //| diction="3" threshold="0" metric="0"/>, <node leaf_node="0" width="10" depth
                                                  //| ="4" prediction="0" threshold="6.15" metric="1"/>, <node leaf_node="1" width
                                                  //| ="11" depth="4" predicti
                                                  //| Output exceeds cutoff limit.
                                                  
   nodes.map(Tree.deserialize(_))                 //> java.lang.NumberFormatException: For input string: ""
                                                  //| 	at java.lang.NumberFormatException.forInputString(Unknown Source)
                                                  //| 	at java.lang.Integer.parseInt(Unknown Source)
                                                  //| 	at java.lang.Integer.parseInt(Unknown Source)
                                                  //| 	at scala.collection.immutable.StringLike$class.toInt(StringLike.scala:22
                                                  //| 9)
                                                  //| 	at scala.collection.immutable.StringOps.toInt(StringOps.scala:31)
                                                  //| 	at decision_trees.Tree$.extractInt$1(Tree.scala:44)
                                                  //| 	at decision_trees.Tree$.deserialize(Tree.scala:46)
                                                  //| 	at rough$$anonfun$main$1$$anonfun$1.apply(rough.scala:13)
                                                  //| 	at rough$$anonfun$main$1$$anonfun$1.apply(rough.scala:13)
                                                  //| 	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike
                                                  //| .scala:244)
                                                  //| 	at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike
                                                  //| .scala:244)
                                                  //| 	at scala.collection.Iterator$class.foreach(Iterator.scala:727)
                                                  //| 	at scala.collection.AbstractIterator.foreach(Iterator.scala:1157)
                                                  //| 	at scala.collection.Iterable
                                                  //| Output exceeds cutoff limit.
}