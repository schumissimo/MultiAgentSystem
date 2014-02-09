package pacman

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import org.omg.CORBA.Environment
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV
import core.agents.Wall
import scala.collection.mutable.Set
import scala.collection.mutable.HashSet

class Attractor(coord: Coordonnees, environnement: Environnement, val movable: Boolean)
  extends Agent(coord, environnement, Color.RED) with Movable {

  override def action {
    if (movable) {
      val voisins = environnement.voisinsMoore(coordonnees)
      voisins map (v => v.setValid(environnement.taille, false))
      val voisinsLibre = for (
        v <- voisins;
        if (!(environnement.getAgentFrom(v).isInstanceOf[Wall]));
        if (environnement.isEmpty(v))

      ) yield v
      if (!voisinsLibre.isEmpty) {
        moveTo(this, util.Random.shuffle(voisinsLibre).head, environnement)
      }
    }
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
    new HashSet ++ voisinsSansDijkstra
  }

  private def getVoisinMarquable(coord: core.Coordonnees, n: Int): scala.collection.immutable.Set[core.Coordonnees] = {
    val voisins = environnement.voisinsMoore(coord)
    voisins map (v => v.setValid(environnement.taille, false))
    for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) > n && environnement.isEmpty(v))) yield v
  }

}