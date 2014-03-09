package decision_trees

case class Member(id: String, metrics: List[Int], values: List[Double]) {
  
  require(metrics.length == values.length)

  private val map = metrics.zip(values).toMap
  
  def apply(metric: Int) = map(metric)
}