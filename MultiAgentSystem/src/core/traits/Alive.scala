package core.traits

import core.Agent
import core.Coordonnees
import core.Environnement

trait Alive {

	val TIME_TO_LIVE: Int
	var age: Int = 1

	def die(agent: Agent, environnement: Environnement) {
		environnement.removeAgent(agent)
	}
	
}