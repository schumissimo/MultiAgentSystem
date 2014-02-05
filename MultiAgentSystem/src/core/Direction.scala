package core

class Direction(v_x: Int, v_y: Int) {
	require(v_x <= 1 && v_x >= -1)
	var dx = v_x
	require(v_y <= 1 && v_y >= -1)
	var dy = v_y

	def setRebound(coord: Coordonnees, taille: Int) = {
		//		(coord.x, coord.y) match {
		//			case (0, 0) => { dx = -dx; dy = -dy }
		//			case (0, 0) if (n == taille) => { dx = -dx; dy = -dy }
		//			case (0, _) => dx = -dx
		//			case (n, _) if (n == taille) => dx = -dx
		//			case (_, 0) => dy = -dy
		//			case (_, n) if (n == taille) => dy = -dy
		//			case (_, _) => { dy = -dy; dx = -dx }
		//		}
		var change = false;
		if (coord.x == 0 || coord.x == taille) {
			dx = -dx;
			change = true;
		}
		if (coord.y == 0 || coord.y == taille) {
			dy = -dy;
			change = true;
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
		new Direction((random.nextInt(100) % 3) - 1, (random.nextInt(100) % 3) - 1)
	}
}