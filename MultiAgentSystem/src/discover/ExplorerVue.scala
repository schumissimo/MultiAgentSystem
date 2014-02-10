package discover

import core.Environnement
import core.Vue
import java.awt.Color
import core.agents.Wall

class ExplorerVue(env: Environnement, show: Boolean) extends Vue(env, show) {
  //constructor
  def this(env_ : Environnement) = this(env_, true)

  override def update {
    val grilleEnv = environnement.grille
    val actor = environnement.agents.head
    for (row <- 0 until envSize) {
      for (col <- 0 until envSize) {
        if (actor.discorverMap(row)(col)) {
          if (grilleEnv(row)(col) == null) {
            labels(row)(col).background = environnement.color
          } else {
            labels(row)(col).background = grilleEnv(row)(col).color
          }
        } else {
       
          labels(row)(col).background = Color.BLACK
        }
      }
    }
  }
}
