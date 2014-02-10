package main

import core.Environnement
import core.Vue
import wator.Ocean
import java.awt.Color
import core.Systeme
import wator.WatorView

object Wator {

	def main(args: Array[String]) {
		val ocean: Environnement = new Ocean(50, new Color(28, 107, 160), 200, 800)
		val vue = new WatorView(ocean,false)
		val systeme = new Systeme(vue, ocean)
		vue.visible = true
		systeme.run()
	}
}