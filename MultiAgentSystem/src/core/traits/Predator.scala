package core.traits

import core.Agent
import core.Environnement
import core.Coordonnees

trait Predator {

	val TIME_TO_EAT: Int
	var timeToEat = TIME_TO_EAT

	def isStarved: Boolean = timeToEat <= 0

	def eat(agent: Agent, environnement: Environnement) {
		agent.toRemove = true
		timeToEat = TIME_TO_EAT
		environnement.removeAgent(agent)
	}
}