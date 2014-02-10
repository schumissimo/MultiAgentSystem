package discover

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import scala.collection.mutable.HashSet
import core.agents.Wall

class Discoverer(coordonnees_ : Coordonnees, environnement_ : Environnement)
  extends Agent(coordonnees_, environnement_, Color.BLUE) with Movable {

  var coordonneesPossible = environnement.coordGrille
  discoverInit(false)
  var dest: Coordonnees = null
  
  override def action {
	discoverVoisins
    if (dest != null && coordonnees != dest) {
      nextChoice
    } else {
      calculDijkstra(coordonnees)
      val next = getClosestNotDiscoverYet
      if (!next.isEmpty) {
        dest = util.Random.shuffle(next).head
        calculDijkstra(dest)
        nextChoice
      } else {
        environnement.system.stopRun
      }

    }
  }

  private def nextChoice {
    val voisins = voisinsWithVonNeumann(coordonnees, false)
    val coordMin = voisins.reduceLeft((l, r) => if (environnement.dijsktra(r.x)(r.y) < environnement.dijsktra(l.x)(l.y)) r else l)
    val min = environnement.dijsktra(coordMin.x)(coordMin.y)
    val nextCoord = for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) == min); if (!environnement.getAgentFrom(v).isInstanceOf[Wall])) yield v
    if (!nextCoord.isEmpty) {
      val next = util.Random.shuffle(nextCoord).head
      moveTo(this, next, environnement)
    }
  }

  private def discoverVoisins {
    val voisins = voisinsWithMoore(coordonnees, false)
    voisins map (v => discorverMap(v.x)(v.y) = true)
    discorverMap(coordonnees.x)(coordonnees.y) = true
  }

  private def getClosestNotDiscoverYet = {
    val notDiscoverYet = for (
      v <- coordonneesPossible;
      if (!discorverMap(v.x)(v.y));
      if (environnement.dijsktra(v.x)(v.y) < environnement.taille * environnement.taille)
    ) yield v

    if (notDiscoverYet.isEmpty) {
      Set()
    } else {
      val min = notDiscoverYet.reduceLeft((l, r) => if (environnement.dijsktra(r.x)(r.y) <= environnement.dijsktra(l.x)(l.y)) r else l)
      val minValue = environnement.dijsktra(min.x)(min.y)
      val available = for (ny <- notDiscoverYet; if (environnement.dijsktra(ny.x)(ny.y) == minValue)) yield ny
      available.toSet[Coordonnees]
    }
  }

}