package pacman

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import org.omg.CORBA.Environment
import core.agents.Wall
import scala.collection.mutable.HashSet

class Attractor(coord: Coordonnees, env : Environnement, val movable: Boolean)
  extends Agent(coord, env, Color.RED) with Movable {

  override def action {
    if (movable) {
      val voisins = voisinsWithVonNeumann(coordonnees, false)
      val voisinsLibre = for (
        v <- voisins;
        if (!(environnement.getAgentFrom(v).isInstanceOf[Wall]));
        if (environnement.isEmpty(v))
      ) yield v
      if (!voisinsLibre.isEmpty) {
        moveTo(this, util.Random.shuffle(voisinsLibre).head, environnement)
      }
    }

    calculDijkstra(coordonnees)
  }

  
}