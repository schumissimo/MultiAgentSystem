package schelling

import java.awt.Color

import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import core.traits.Satisfiable
import core.traits.Satisfiable

class Population(coord: Coordonnees, val environnement: Environnement, color: Color, taux: Int)
	extends Agent(coord, environnement, color) with Movable with Satisfiable {
	override val satisfyRate = taux

	override def action {
		if (played) {
			return
		}
		played = true

		val mesVoisins = environnement.voisins(coordonnees).toList
		mesVoisins map (v => v.setValid(environnement.taille, true))
		val mesVoisinsAgent = for (v <- mesVoisins) yield environnement.getAgentFrom(v)

		val nonVide = for (a <- mesVoisinsAgent; if (a != null)) yield a
		val nbVide = 8 - nonVide.size
		val likeMe = nonVide.count(c => c.getClass() == getClass())
		actualRate = (likeMe + nbVide) * 100 / 8
		if (!isSatisfied) {
			val next = environnement.getOneFreeCoordonnees
			if (next != null) {
				moveTo(this, next, environnement)
			}
		}
	}

}