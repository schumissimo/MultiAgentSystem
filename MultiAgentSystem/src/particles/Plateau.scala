package particles

import java.awt.Color
import scala.util.Random
import core.Environnement
import core.Direction
import core.Coordonnees

class Plateau(taille_ : Int, color_ : Color, particles: Int) extends Environnement(taille_, color_) {

	initGrid(particles)

	private def initGrid(n: Int) {
		val shLis = Random.shuffle(coordGrille)
		val random = new util.Random
		for (b <- 0 until n) {
			val bille = new Particle(shLis(b), Direction.random, this, new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)))
			addAgent(bille)
		}
	}
}