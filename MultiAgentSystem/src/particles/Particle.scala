package particles

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Alive
import core.traits.Movable
import core.Direction

class Particle(coord: Coordonnees, direction_ : Direction, val environnement: Environnement, color: Color)
	extends Agent(coord, environnement, color) with Movable {

	var direction = direction_
	println("init " + direction)
	override def action() {
		if (played) {
			return
		}
		played = true

		println("coord " + coordonnees)
		val next: Coordonnees = coordonnees ++ direction
		println("next " + next)
		//next.setValid(environnement.taille, false)
		println("next valide" + next)

		if (next.isValid(environnement.taille)) {
			if (environnement.getAgentFrom(next) == null) {
				moveTo(this, next, environnement)
			} else {
				println("avant :" + direction)
				direction.setRebound(coordonnees, environnement.taille)
				println("apres :" + direction)
				println("rebond")
			}
		} else {
			direction.setRebound(coordonnees, environnement.taille)
		}

	}

}