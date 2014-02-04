package core

class Coordonnees(x_ : Int, y_ : Int) {
	var x = x_
	var y = y_

	override def toString(): String = {
		x + " " + y
	}

	def setValid(taille: Int, isTorique: Boolean) {
		if (isTorique) {
			x = (x + taille) % taille
			y = (y + taille) % taille
		}
	}
}

