package pacman

import java.awt.Color
import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable
import org.omg.CORBA.Environment
import com.sun.org.apache.xerces.internal.impl.dv.xs.YearDV
import core.agents.Wall

class Attractor(coord: Coordonnees, environnement: Environnement, val movable: Boolean)
  extends Agent(coord, environnement, Color.RED) with Movable {

  var nb = 0
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
    calculDijsktra(coordonnees, 0)
    println(nb)
    nb = 0
  }

  def calculDijsktra(coord: Coordonnees, n: Int) {
   // if(n == 2) return
    if(environnement.dijsktra(coord.x)(coord.y) < n)
      return
    val voisins = environnement.voisinsMoore(coord)
    voisins map (v => v.setValid(environnement.taille, false))
   
    val voisinsSansDijkstra = for (v <- voisins; if (environnement.dijsktra(v.x)(v.y) > n && environnement.isEmpty(v))) yield v
    print("attractor " + coord + " (" +n+") "+ voisinsSansDijkstra.isEmpty + " => ")

    if (voisinsSansDijkstra.isEmpty) {
      println(" je sors")
      return
    }
    print(" je reste "+voisinsSansDijkstra.size+" [")
    val start = System.currentTimeMillis
    for (v <- voisinsSansDijkstra) {
    	print("("+v +") "+(environnement.dijsktra(v.x)(v.y))+", ")
      environnement.dijsktra(v.x)(v.y) = n + 1
    }
    println("]")
   
    for (v <- voisinsSansDijkstra) {
      nb += 1
      calculDijsktra(v, n + 1)
    }
    
  }
}