package core

class Direction(var dx: Int, var dy: Int) {
	require(dx <= 1 && dx >= -1)
	require(dy <= 1 && dy >= -1)

	def setRebound(coord: Coordonnees, taille: Int) = {

		var change = false
		if (coord.x == 0 || coord.x == (taille - 1)) {
			dx = -dx;
			change = true
		}
		if (coord.y == 0 || coord.y == (taille - 1)) {
			dy = -dy;
			change = true
		}
		if (!change) {
			dx = -dx;
			dy = -dy;
		}
	}

	override def toString: String = dx + " " + dy

}

object Direction {

	def random: Direction = {
		val random = new util.Random
		val x = (random.nextInt(100) % 3) - 1
		val y = (random.nextInt(100) % 3) - 1
		if (x == 0 && y == 0) {
			return Direction.random
		}
		new Direction(x, y)
	}
}