package pacman

import core.Agent
import core.Environnement
import core.traits.Movable
import core.Coordonnees
import java.awt.Color

class Runner(coord: Coordonnees, environnement: Environnement)
  extends Agent(coord, environnement, Color.YELLOW) with Movable {

  override def action {
    val voisins = environnement.voisinsMoore(coordonnees)
    voisins map (v => v.setValid(environnement.taille, false))

    val coordMin = voisins.reduceLeft((l, r) => if (environnement.dijsktra(r.x)(r.y) < environnement.dijsktra(l.x)(l.y)) r else l)
    val min = environnement.dijsktra(coordMin.x)(coordMin.y)
    val nextCoord = for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) == min)) yield v
    if (!nextCoord.isEmpty) {
      val next = util.Random.shuffle(nextCoord).head
      if (environnement.getAgentFrom(next).isInstanceOf[Attractor]) {
        environnement.system.stopRun
      } else {
        moveTo(this, next, environnement)
      }

    }

  }
}