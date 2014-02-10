package core

import scala.actors.Actor
import java.awt.Color
import scala.collection.mutable.Set
import scala.collection.mutable.HashSet

abstract class Agent(var coordonnees : Coordonnees, val environnement: Environnement, val color : Color) {

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
	
	def voisinsWithMoore(coordonnees: Coordonnees,isTorique:Boolean) = {
	  val voisins = environnement.voisinsMoore(coordonnees)
      voisins map (v => v.setValid(environnement.taille, isTorique))
	  voisins
	}

	def voisinsWithVonNeumann(coordonnees: Coordonnees,isTorique:Boolean) = {
		//      val voisins = environnement.voisinsMoore(coordonnees)
		val voisins = environnement.voisinsVonNeumann(coordonnees)
		voisins map (v => v.setValid(environnement.taille, isTorique))
		voisins
	}
	
	def calculDijkstra(coordonnees: Coordonnees){
	  environnement.dijsktraInit
	  environnement.dijsktra(coordonnees.x)(coordonnees.y) = 0
	  val set = new HashSet + coordonnees
	  calculDijkstra(set, 0)
	}
	
	def calculDijkstra(voisinsMarque: Set[Coordonnees], n: Int) {

    if (voisinsMarque.isEmpty)
      return

    var prochainAFaireParle: Set[Coordonnees] = new HashSet
    for (v <- voisinsMarque) {
      for (voisinQuiJouera <- marqueVoisin(v, n)) 
        prochainAFaireParle += voisinQuiJouera
    }

    calculDijkstra(prochainAFaireParle, n + 1)
  }

  private def marqueVoisin(coord: Coordonnees, n: Int): Set[Coordonnees] = {

    val voisinsSansDijkstra = getVoisinMarquable(coord, n)

    for (v <- voisinsSansDijkstra) {
      environnement.dijsktra(v.x)(v.y) = n + 1
    }
    val res = new HashSet ++ voisinsSansDijkstra
    res
  }

  private def getVoisinMarquable(coord: core.Coordonnees, n: Int): scala.collection.immutable.Set[core.Coordonnees] = {
		 val voisins = voisinsWithVonNeumann(coord, false)
    for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) > n && environnement.isEmpty(v))) yield v
  }

}