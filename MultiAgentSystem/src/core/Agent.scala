package core

import scala.actors.Actor
import java.awt.Color

class Agent(coordonnees_ : Coordonnees, environnement: Environnement, color_ : Color) {

	var coordonnees = coordonnees_
	val color = color_
	var played = false
	var toRemove = false

	def action = {}

	def evolution = {}

	override def toString(): String = {
		getClass() + " " + coordonnees.x + " " + coordonnees.y
	}
}