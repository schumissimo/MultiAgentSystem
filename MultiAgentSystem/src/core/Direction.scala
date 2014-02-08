package core

class Direction(v_x: Int, v_y: Int) {
	require(v_x <= 1 && v_x >= -1)
	var dx = v_x
	require(v_y <= 1 && v_y >= -1)
	var dy = v_y

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