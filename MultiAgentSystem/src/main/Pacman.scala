package main

import core.Vue
import core.Environnement
import pacman.Terrain
import core.Systeme

object Pacman {
def main(args: Array[String]) {
		val terrain: Environnement = new Terrain(5,1,1)
		val vue = new Vue(terrain)
		val systeme = new Systeme(vue, terrain)
		vue.visible = true
		systeme.run(3)
	}
}