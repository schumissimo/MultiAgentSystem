package main

import core.Vue
import core.Environnement
import pacman.Terrain
import core.Systeme
import org.omg.CORBA.Environment
import pacman.PacmanVue

object Pacman {
def main(args: Array[String]) {
		val terrain: Environnement = new Terrain(25,1,2)
		val vue = new PacmanVue(terrain)
		val systeme = new Systeme(vue, terrain)
		vue.visible = true
		terrain.system = systeme
		systeme.run(1)
	}
}