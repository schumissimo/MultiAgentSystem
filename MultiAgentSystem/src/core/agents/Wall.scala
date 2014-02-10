package core.agents

import core.Agent
import core.Environnement
import core.Coordonnees
import java.awt.Color

class Wall(coord: Coordonnees, env: Environnement)
  extends Agent(coord, env, new Color(102, 51, 0)) {

  override def action = {}
}