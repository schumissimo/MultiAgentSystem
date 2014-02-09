package core.traits

import core.Agent
import core.Environnement
import core.Coordonnees
import core.agents.Wall

trait Digging {

  def dig(agent : Agent, coord:Coordonnees, environnement: Environnement){
    val wall = environnement.getAgentFrom(agent.coordonnees)
    environnement.removeAgent(wall)
    environnement.move(agent, coord)
  }
}