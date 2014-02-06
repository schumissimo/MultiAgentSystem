package core.traits

import core.Agent
import core.Coordonnees
import core.Environnement

trait Alive {

	val TIME_TO_LIVE: Int
	var age: Int = 1

	val TIME_TO_REPRODUCE: Int
	var reproduction = TIME_TO_REPRODUCE

	def die(agent: Agent, environnement: Environnement) {
		environnement.removeAgent(agent)
	}

	def reproduce(babyAgent: Agent, environnement: Environnement) {
		environnement.addAgent(babyAgent)
		reproduction = TIME_TO_REPRODUCE
	}

}