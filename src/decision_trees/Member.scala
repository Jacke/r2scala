package decision_trees

case class Member(id: String, metrics: List[String], values: List[Double]) {
  
  require(metrics.length == values.length)

  private val map = metrics.zip(values).toMap
  
  def apply(metric: String) = map(metric)
}