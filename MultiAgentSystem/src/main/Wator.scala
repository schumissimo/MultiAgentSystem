package main

import core.Environnement
import core.Vue
import wator.Ocean
import java.awt.Color
import core.Systeme

object Wator {

	def main(args: Array[String]) {
		val ocean: Environnement = new Ocean(50, new Color(28, 107, 160), 200, 1000)
		val vue = new Vue(ocean)
		val systeme = new Systeme(vue, ocean)
		vue.visible = true
		systeme.run()
	}
}