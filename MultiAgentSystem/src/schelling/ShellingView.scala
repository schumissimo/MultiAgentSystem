package schelling

import core.Vue
import core.Environnement

class ShellingView(environnement_ : Environnement, showBorders_ : Boolean) extends Vue(environnement_ ,showBorders_ ) {
  //constructor
	def this(environnement: Environnement) = this(environnement, true)
}