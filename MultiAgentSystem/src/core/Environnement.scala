package core

import java.awt.Color

abstract class Environnement(taille_ : Int, color_ : Color) {
	val taille = taille_
	val grille = Array.ofDim[Agent](taille, taille)
	val color = color_
	var agents: List[Agent] = Nil

	lazy val coordGrille = {
		for (
			x <- 0 until taille;
			y <- 0 until taille
		) yield new Coordonnees(x, y)
	}

	def move(agent: Agent, coordonnees: Coordonnees) {
		removeAgent(agent)
		agent.coordonnees = coordonnees
		addAgent(agent)
	}

	def removeAgent(agent: Agent) {
		grille(agent.coordonnees.x)(agent.coordonnees.y) = null
		agents = agents.filter(_ != agent)
	}

	def addAgent(agent: Agent): Unit = {
		grille(agent.coordonnees.x)(agent.coordonnees.y) = agent
		agents = agent :: agents
	}

	def voisins(coordonnees: Coordonnees): Set[Coordonnees] =
		for {
			dx <- Set(-1, 0, 1)
			dy <- Set(-1, 0, 1)
			if (dy != 0 || dx != 0)
		} yield new Coordonnees(coordonnees.x + dx, coordonnees.y + dy)

	def getAgentFrom(coordonnees: Coordonnees): Agent = {
		grille(coordonnees.x)(coordonnees.y)
	}

	def getOneFreeCoordonnees: Coordonnees = {
		val randomList = util.Random.shuffle(coordGrille)
		for (c <- randomList) {
			if (getAgentFrom(c) == null)
				return c
		}
		return null
	}
}