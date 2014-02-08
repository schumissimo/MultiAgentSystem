package core.traits

trait Satisfiable {

	val satisfyRate: Int
	var actualRate: Int = 0

	def isSatisfied: Boolean = actualRate >= satisfyRate

}