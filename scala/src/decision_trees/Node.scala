package decision_trees

import Math.pow

abstract class Node {
  val depth: Int
  val width: Int
}

case class NonLeaf(metric: Int, threshold: Double, depth: Int, width: Int) extends Node

case class Leaf(prediction: Int, depth: Int, width: Int) extends Node

