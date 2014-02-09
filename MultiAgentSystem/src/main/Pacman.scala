package main

import core.Vue
import core.Environnement
import pacman.Terrain
import core.Systeme
import org.omg.CORBA.Environment

object Pacman {
def main(args: Array[String]) {
		val terrain: Environnement = new Terrain(20,2,3)
		val vue = new Vue(terrain)
		val systeme = new Systeme(vue, terrain)
		vue.visible = true
		terrain.system = systeme
		systeme.run()
	}
}