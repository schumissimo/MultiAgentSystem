package wator

import java.awt.Color

import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Alive
import core.traits.Predator

class Shark(coord: Coordonnees, val environnement: Environnement, color: Color, eat_ : Int, gestation_ : Int, age_ : Int)
	extends Agent(coord, environnement, color) with Alive with Predator {

	override val TIME_TO_EAT = eat_
	timeToEat = TIME_TO_EAT
	override val TIME_TO_REPRODUCE = gestation_
	reproduction = TIME_TO_REPRODUCE
	override val TIME_TO_LIVE = age_
	age = TIME_TO_LIVE

	override def action {
		if (played) {
			return
		}
		played = true
		if (toRemove) {
			die(this, environnement)
			return
		}
		if (isStarved) {
			die(this, environnement)
			return
		}

		evolution

		val voisinsCoord: List[Coordonnees] = environnement.voisins(coordonnees).toList
		voisinsCoord map (v => v.setValid(environnement.taille, true))
		val voisins = for (
			a <- voisinsCoord
		) yield environnement.getAgentFrom(a)

		val nextEat = for (v <- voisins; if (v.isInstanceOf[Tuna])) yield v
		val nextMove = for (v <- voisinsCoord; if (environnement.getAgentFrom(v) == null)) yield v

		//choix de l'action
		if (!nextEat.isEmpty) {
			val next = util.Random.shuffle(nextEat).head
			eat(next, environnement)
			monAction(next.coordonnees)
		} else {
			if (!nextMove.isEmpty) {
				val next = util.Random.shuffle(nextMove).head
				monAction(next)
			}
		}

	}
	override def evolution {
		age += 1
		timeToEat -= 1
		reproduction -= 1
		if (reproduction < 0) {
			reproduction = 0
		}
	}

	def monAction(nextCoord: Coordonnees) {
		if (reproduction == 0) {
			val babyShark = new Shark(coordonnees, environnement, color, TIME_TO_EAT, TIME_TO_REPRODUCE, TIME_TO_LIVE)
			moveTo(this, nextCoord, environnement)
			reproduce(babyShark, environnement)
		} else {
			moveTo(this, nextCoord, environnement)
		}
	}
}