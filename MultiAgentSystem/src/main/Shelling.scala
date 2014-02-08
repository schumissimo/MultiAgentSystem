package main

import core.Vue
import core.Environnement
import schelling.Territoire
import core.Systeme
import java.awt.Color

object Shelling {
	def main(args: Array[String]) {
		val territoire: Environnement = new Territoire(75, Color.BLACK, 70, 70)
		val vue = new Vue(territoire)
		val systeme = new Systeme(vue, territoire)
		vue.visible = true
		systeme.run()
	}
}