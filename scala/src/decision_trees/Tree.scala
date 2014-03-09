package decision_trees

import Math.pow
import xml_helper.HasAttribute

 //  A class for decision trees
 
abstract class Tree {
  
  // pass a data point down a tree; get a prediction

  def apply(member: Member): Int = this match {
    case EmptyTree => throw new Error
    case Fork(Leaf(prediction, _, _), _, _) => prediction
    case Fork(NonLeaf(metric, threshold, _, _), left, right) =>
      if (member(metric) < threshold) left(member) else right(member)
  }
  
  // returns the right-hand boundary

  private def rhb(relativeDepth: Int, width: Int): Int =
    pow(2, relativeDepth - 1).toInt * (2 * width + 1)
    
  // returns true if the data point should be passed down the left-hand subtree

  private def goLeft(node: Node, head: Node): Boolean =
    node.width < rhb(node.depth - head.depth, head.width)
    
  // returns a new tree with the node added

  def :+(node: Node): Tree = this match {
    case EmptyTree => Fork(node, EmptyTree, EmptyTree)
    case Fork(head, left, right) => {
      val relativeDepth = node.depth - head.depth
      if (relativeDepth > 0) {
        if (goLeft(node, head)) Fork(head, left :+ node, right)
        else Fork(head, left, right :+ node)
      }
      else if (relativeDepth < 0) {
        if (goLeft(head, node)) Fork(node, Fork(head, left, right), EmptyTree)
        else Fork(node, EmptyTree, Fork(head, left, right))
      } 
      else throw new Error("depths are equal")
    }
  }
}



case class Fork(node: Node, left: Tree, right: Tree) extends Tree

object EmptyTree extends Tree {
  override def toString = "EmptyTree"
}

object Tree {

  private def deserialize(node: xml.Node): Node =
    if ((node \ "@leaf_node").text.toInt == 1)
      Leaf(
        (node \ "@prediction").text.toInt,
        (node \ "@depth").text.toInt,
        (node \ "@width").text.toInt)
    else
      NonLeaf(
        (node \ "@metric").text.toInt,
        (node \ "@threshold").text.toDouble,
        (node \ "@depth").text.toInt,
        (node \ "@width").text.toInt)

  def apply(nodes: xml.NodeSeq): Tree =
    nodes.map(deserialize(_)).foldLeft(EmptyTree: Tree)(_ :+ _)
}

object Run extends App {

  val rf_xml = xml.XML.loadFile("C:/eclipse/workspace/r2scala/scala/data/rf.xml")
  val tree_xml = (rf_xml \ "tree").head
  val nodes = (tree_xml \ "node")
  
  val tree = Tree(nodes)  
  println(tree)
  
  val member = Member("guy", List(1,2,3,4), List(6.3, 3.3, 6, 2.5))
  println(member)
  
  println(tree(member))
}