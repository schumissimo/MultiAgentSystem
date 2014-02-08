package pacman

import core.Agent
import core.Environnement
import core.traits.Movable
import core.Coordonnees
import java.awt.Color

class Runner(coord: Coordonnees, environnement: Environnement)
  extends Agent(coord, environnement, Color.YELLOW) with Movable  {

  override def action {
    val voisins = environnement.voisins(coordonnees)
    voisins map (v => v.setValid(environnement.taille, false))
    
    val min = voisins.reduceLeft((l,r) => if (environnement.dijsktra(r.x)(r.y) < environnement.dijsktra(l.x)(l.y)) r else l)
  }
}