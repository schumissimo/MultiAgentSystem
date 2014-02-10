package main

import core.Vue
import core.Environnement
import core.Systeme
import java.awt.Color
import particles.Plateau
import core.Direction
import particles.ParticlesView

object Particles {

	def main(args: Array[String]) {
		val plateau: Environnement = new Plateau(10, Color.WHITE, 10)
		val vue = new ParticlesView(plateau, false)
		val systeme = new Systeme(vue, plateau)
		vue.visible = true
		systeme.run()
	}
}