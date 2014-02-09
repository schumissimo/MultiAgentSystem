package core.agents

import java.awt.Color

import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Digging

class Digger (coord_ : Coordonnees, enviro: Environnement)
  extends Agent(coord_ , enviro, Color.YELLOW) with Digging {
  
  override def action  = {
    val voisins = environnement.voisinsVonNeumann(coordonnees)
    voisins map (v => v.setValid(environnement.taille, false))
    val valide = for (v <- voisins ; if (environnement.getAgentFrom(v).isInstanceOf[Wall])) yield v
    if(!valide.isEmpty){
      val next = util.Random.shuffle(valide).head
      dig(this, next, environnement)
    }
  }

}