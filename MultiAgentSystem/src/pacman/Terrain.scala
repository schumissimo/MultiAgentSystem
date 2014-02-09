package pacman

import java.awt.Color
import core.Environnement
import org.omg.CORBA.Environment

class Terrain(taille_ : Int, nbAttractor: Int, nbRunner: Int)
  extends Environnement(taille_, new Color(0, 100, 0)) {

  setAllWall
  setLabyrinthe(taille)
  removeaAgentsLabyrinthe
  initAgent(nbAttractor, nbRunner)

  def initAgent(attractor: Int, runner: Int) {
    for (a <- 1 to nbAttractor) {
      addAgent(new Attractor(getOneFreeCoordonnees, this, true))
    }
    for (r <- 1 to nbRunner) {
      addAgent(new Runner(getOneFreeCoordonnees, this))
    }
  }
}