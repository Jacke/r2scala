package decision_trees

import Math.pow

abstract class Tree {

  def apply(member: Member): Int = this match {
    case EmptyTree => throw new Error
    case Fork(Leaf(prediction, _, _), _, _) => prediction
    case Fork(NonLeaf(metric, threshold, _, _), left, right) =>
      if (member(metric) < threshold) left(member) else right(member)
  }

  private def rhb(relativeDepth: Int, width: Int): Int =
    pow(2, relativeDepth - 1).toInt * (2 * width + 1)

  private def goLeft(node: Node, head: Node): Boolean =
    node.width < rhb(node.depth - head.depth, head.width)

  def :+(node: Node): Tree = this match {
    case EmptyTree => Fork(node, EmptyTree, EmptyTree)
    case Fork(head, left, right) => {
      val relativeDepth = node.depth - head.depth
      if (relativeDepth > 0) {
        if (goLeft(node, head)) Fork(head, left :+ node, right)
        else Fork(head, left, right :+ node)
      }
      if (relativeDepth < 0) {
        if (goLeft(head, node)) Fork(node, Fork(head, left, right), EmptyTree)
        else Fork(node, EmptyTree, Fork(head, left, right))
      }
      else throw new Error("depths are equal")
    }
  }
}

case class Fork(node: Node, left: Tree, right: Tree) extends Tree

object EmptyTree extends Tree

object Tree {
  
  def apply(nodes: List[Node]): Tree = 
    nodes.foldLeft(EmptyTree: Tree)(_ :+ _)
}