package schelling

import core.Environnement
import java.awt.Color

class Territoire(val taille_ : Int, val color_ : Color, tauxRemplissage: Int, tauxSatisfaction: Int) extends Environnement(taille_, color_) {

	setPopulation(tauxRemplissage, tauxSatisfaction)

	def setPopulation(populationA: Int, populationB: Int, tauxSatisfaction: Int) {
		val listPlacement = util.Random.shuffle(coordGrille)
		for (a <- 0 until populationA) {
			val green = new Green(listPlacement(a), this, tauxSatisfaction)
			addAgent(green)
		}

		for (b <- 0 until populationB) {
			val red = new Red(listPlacement(b + populationA), this, tauxSatisfaction)
			addAgent(red)
		}

	}

	def setPopulation(tauxRemplissage: Int, tauxSatisfaction: Int) {
		val nb = (taille * taille) * tauxRemplissage / 200
		println(nb)
		setPopulation(nb, nb, tauxSatisfaction)
	}

}