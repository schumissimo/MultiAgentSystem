package wator

import core.Environnement
import core.traits.Alive
import core.Coordonnees
import core.Agent
import java.awt.Color
import core.traits.Movable
import core.traits.Reproductable

class Tuna(coord: Coordonnees, val environnement: Environnement, color: Color, gestation_ : Int, age_ : Int)
	extends Agent(coord, environnement, color) with Alive with Movable with Reproductable{

	override val TIME_TO_REPRODUCE = gestation_
	reproduction = TIME_TO_REPRODUCE
	override val TIME_TO_LIVE = age_
	age = TIME_TO_LIVE

	override def action() {
		if (played) {
			return
		}
		played = true
		if (toRemove) {
			return
		}

		evolution

		val voisinsCoord = environnement.voisins(coordonnees)
		voisinsCoord map (v => v.setValid(environnement.taille, true))

		val nextMove = for (v <- voisinsCoord; if (environnement.getAgentFrom(v) == null)) yield v

		if (!nextMove.isEmpty) {
			val next = util.Random.shuffle(nextMove).head
			if (reproduction == 0) {
				val babyTuna = new Tuna(this.coordonnees, environnement, this.color, TIME_TO_REPRODUCE, TIME_TO_LIVE)
				moveTo(this, next, environnement)
				reproduce(babyTuna, environnement)
			} else {
				moveTo(this, next, environnement)
			}
		}
	}

	override def evolution {
		age += 1
		reproduction -= 1
		if (reproduction < 0) {
			reproduction = TIME_TO_REPRODUCE
		}
	}

}