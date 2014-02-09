package particles

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Alive
import core.traits.Movable
import core.Direction

class Particle(coord: Coordonnees, direction_ : Direction,  enviro :Environnement, color: Color)
	extends Agent(coord, enviro, color) with Movable {

	var direction = direction_

	override def action() {
		if (played) {
			return
		}
		played = true

		val next: Coordonnees = coordonnees ++ direction

		if (next.isValid(environnement.taille) && (environnement.getAgentFrom(next) == null)) {
			moveTo(this, next, environnement)
		} else {
			direction.setRebound(coordonnees, environnement.taille)
		}

	}

}