package discover

import core.Environnement
import java.awt.Color

class Chateau(taille_ : Int)
  extends Environnement(taille_, new Color(175, 175, 175)) {

  setAllWall
  setLabyrinthe(taille)
  removeaAgentsLabyrinthe
  
  addAgent(new Discoverer(getOneFreeCoordonnees,this))
  
}