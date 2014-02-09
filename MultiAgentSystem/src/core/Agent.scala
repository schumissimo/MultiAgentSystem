package core

import scala.actors.Actor
import java.awt.Color

abstract class Agent(coordonnees_ : Coordonnees, val environnement: Environnement, val color : Color) {

	var coordonnees = coordonnees_
	var played = false
	var toRemove = false

	def action 

	def evolution = {}

	override def toString(): String = {
		getClass() + " " + coordonnees.x + " " + coordonnees.y
	}
}