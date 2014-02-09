package core

import scala.actors.Actor
import java.awt.Color

abstract class Agent(coordonnees_ : Coordonnees, val environnement: Environnement, val color : Color) {

	var coordonnees = coordonnees_
	var played = false
	var toRemove = false
	val discorverMap = Array.fill(environnement.taille,environnement.taille)(true)

	def action 

	def evolution = {}

	override def toString(): String = {
		getClass() + " " + coordonnees.x + " " + coordonnees.y
	}
	
	def discoverInit(boolean : Boolean){
	  for(r <- 0 until environnement.taille){
	    for(c <- 0 until environnement.taille){
	      discorverMap(r)(c) = boolean
	    }
	  }
	}
}