package core.traits

import core.Agent
import core.Environnement
import core.Coordonnees

trait Movable {

	def moveTo(agent: Agent, coordonnees: Coordonnees, environnement: Environnement) {
		environnement.move(agent, coordonnees)
	}
}