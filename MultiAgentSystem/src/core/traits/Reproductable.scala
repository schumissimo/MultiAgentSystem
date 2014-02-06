package core.traits

import core.Agent
import core.Environnement

trait Reproductable {

	val TIME_TO_REPRODUCE: Int
	var reproduction = TIME_TO_REPRODUCE

	def reproduce(babyAgent: Agent, environnement: Environnement) {
		environnement.addAgent(babyAgent)
		reproduction = TIME_TO_REPRODUCE
	}

  
}