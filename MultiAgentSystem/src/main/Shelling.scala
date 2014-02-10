package main

import java.awt.Color

import core.Environnement
import core.Systeme
import schelling.ShellingView
import schelling.Territoire

object Shelling {
	def main(args: Array[String]) {
		val territoire: Environnement = new Territoire(75, Color.BLACK, 70, 70)
		val vue = new ShellingView(territoire)
		val systeme = new Systeme(vue, territoire)
		vue.visible = true
		systeme.run()
	}
}