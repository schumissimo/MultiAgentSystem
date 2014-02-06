package pacman

import java.awt.Color

import core.Environnement

class Terrain(val taille_ : Int,nbAttractor: Int,nbRunner:Int) 
extends Environnement(taille_, new Color(0,100,0)) {

  
  
  val rdm = util.Random.shuffle(coordGrille)
 for(w <- 1 to 10){
	 val wall = new Wall(rdm(w),this)
	 setAgent(wall)
 }
  addAgent(new Attractor(getOneFreeCoordonnees,this,true))
  
   
}