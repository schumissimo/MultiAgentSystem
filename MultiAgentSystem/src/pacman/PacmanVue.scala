package pacman

import core.Vue
import core.Environnement

class PacmanVue(environnement_ : Environnement, showBorders: Boolean) extends Vue(environnement_ ,showBorders) {
  //constructor
	def this(environnement: Environnement) = this(environnement, true)
}