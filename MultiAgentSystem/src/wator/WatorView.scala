package wator

import core.Vue
import core.Environnement

class WatorView(environnement_ : Environnement, showBorders_ : Boolean) extends Vue(environnement_ ,showBorders_ ) {
  //constructor
	def this(environnement: Environnement) = this(environnement, true)
}
