package discover

import java.awt.Color

import core.Agent
import core.Coordonnees
import core.Environnement
import core.traits.Movable

class Discoverer(coord: Coordonnees, environnement: Environnement)
  extends Agent(coord, environnement, Color.BLUE) with Movable{

  discoverInit(false)
  
  override def action {
    discorverMap(coordonnees.x)(coordonnees.y) = true
    
  }
}