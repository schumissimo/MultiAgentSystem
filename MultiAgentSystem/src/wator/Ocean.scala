package wator

import java.awt.Color
import core.Environnement
import core.Coordonnees
import core.Coordonnees
import scala.util.Random

class Ocean(val taille_ : Int, val color_ : Color, sharks: Int, tunas: Int) extends Environnement(taille_, color_) {

	initGrid(tunas, sharks)

	private def initGrid(tunas: Int, sharks: Int) {
		val shLis = Random.shuffle(coordGrille)
		for (t <- 0 until tunas) {
			val tuna = new Tuna(shLis(t), this, Color.ORANGE, 2, 1)
			addAgent(tuna)
		}
		for (s <- 0 until sharks) {
			val shark = new Shark(shLis(s + sharks), this, Color.DARK_GRAY, 1, 3, 1)
			addAgent(shark)
		}
	}
}