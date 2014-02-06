package pacman

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import org.omg.CORBA.Environment

class Attractor(coord: Coordonnees, environnement: Environnement, val movable : Boolean)
  extends Agent(coord, environnement, Color.RED) with Movable {

  println(movable +" bouge?")
  override def action {
    if(movable){
      println("je bouge")
      val voisins = environnement.voisins(coordonnees)
    voisins map (v => v.setValid(environnement.taille, true))  
      if(!voisins.isEmpty)
    	  moveTo(this, util.Random.shuffle(voisins).head, environnement)
    }
    environnement.dijsktraInit
    environnement.dijsktra(coordonnees.x)(coordonnees.y) = 0
    calculDijsktra(coordonnees, 0)
  }

  def calculDijsktra(coord: Coordonnees, n: Int) {
    val voisins = environnement.voisins(coord)
    voisins map (v => v.setValid(environnement.taille, false))

    for (v <- voisins) {
      if ((environnement.dijsktra(v.x)(v.y) > n) && environnement.isEmpty(v)) {
        environnement.dijsktra(v.x)(v.y) = n + 1
        calculDijsktra(v, n + 1)

      }
    }

  }
}