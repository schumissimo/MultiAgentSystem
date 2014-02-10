package pacman

import core.Agent
import core.Environnement
import core.traits.Movable
import core.Coordonnees
import java.awt.Color
import core.agents.Wall

class Runner(coord: Coordonnees, environnement: Environnement)
  extends Agent(coord, environnement, Color.YELLOW) with Movable {

  override def action {
    val voisins = voisinsWithVonNeumann(coordonnees, false)

    val coordMin = voisins.reduceLeft((l, r) => if (environnement.dijsktra(r.x)(r.y) < environnement.dijsktra(l.x)(l.y)) r else l)
    val min = environnement.dijsktra(coordMin.x)(coordMin.y)
    val nextCoord = for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) == min);if(!environnement.getAgentFrom(v).isInstanceOf[Wall])) yield v
    if (!nextCoord.isEmpty) {
      val next = util.Random.shuffle(nextCoord).head
      val agentNext = environnement.getAgentFrom(next)
      if (agentNext.isInstanceOf[Attractor]) {
        println(this.getClass()+"("+coordonnees+") a attrapé "+agentNext.getClass()+" en ("+agentNext.coordonnees+")")
        environnement.system.stopRun
      } else {
        moveTo(this, next, environnement)
      }

    }

  }
}