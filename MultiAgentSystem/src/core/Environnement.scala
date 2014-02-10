package core

import java.awt.Color
import core.agents.Digger
import core.agents.Wall

abstract class Environnement(val taille : Int, val color : Color) {
  
  
  val grille = Array.ofDim[Agent](taille, taille)
  var agents: List[Agent] = Nil
  lazy val dijsktra = Array.fill(taille, taille)(taille * taille)

  var system: Systeme = null

  lazy val coordGrille = {
    for (
      x <- 0 until taille;
      y <- 0 until taille
    ) yield new Coordonnees(x, y)
  }

  def move(agent: Agent, coordonnees: Coordonnees) {
    removeAgent(agent)
    agent.coordonnees = coordonnees
    addAgent(agent)
  }

  def removeAgent(agent: Agent) {
    grille(agent.coordonnees.x)(agent.coordonnees.y) = null
    agents = agents.filter(_ != agent)
  }

  def addAgent(agent: Agent): Unit = {
    grille(agent.coordonnees.x)(agent.coordonnees.y) = agent
    agents = agent :: agents
  }

  def setAgentInactive(agent: Agent) {
    grille(agent.coordonnees.x)(agent.coordonnees.y) = agent
  }

  def voisinsMoore(coordonnees: Coordonnees): Set[Coordonnees] =
    for {
      dx <- Set(-1, 0, 1)
      dy <- Set(-1, 0, 1)
      if (!(dy == 0 && dx == 0))
    } yield new Coordonnees(coordonnees.x + dx, coordonnees.y + dy)

  def voisinsVonNeumann(coordonnees: Coordonnees): Set[Coordonnees] = for {
    dx <- Set(-1, 0, 1)
    dy <- Set(-1, 0, 1)
    if ((dy == 0) ^ (dx == 0))
  } yield new Coordonnees(coordonnees.x + dx, coordonnees.y + dy)

  def getAgentFrom(coordonnees: Coordonnees): Agent = {
    grille(coordonnees.x)(coordonnees.y)
  }

  def getOneFreeCoordonnees: Coordonnees = {
    val randomList = util.Random.shuffle(coordGrille)
    for (c <- randomList) {
      if (getAgentFrom(c) == null)
        return c
    }
    return null
  }

  def getColor: Color = return color

  def isEmpty(coordonnees: Coordonnees): Boolean = grille(coordonnees.x)(coordonnees.y) == null

  def dijsktraInit = {
    val t = taille * taille
    for (r <- 0 until taille) {
      for (c <- 0 until taille) {
        dijsktra(r)(c) = t
      }
    }
  }

  def setAllWall {
    for (r <- 0 until taille) {
      for (c <- 0 until taille) {
        setAgentInactive(new Wall(new Coordonnees(r, c), this))
      }
    }
  }

  def setLabyrinthe(nbDigger: Int) {
    val randomList = util.Random.shuffle(coordGrille)
    for (i <- 0 until (nbDigger * 2) / 3) {
      agents = (new Digger(randomList(i), this)) :: agents
    }
    for (t <- 1 to (nbDigger * 8 / 4)) {
      agents map (d => d.action)
    }
  }

  def removeaAgentsLabyrinthe {
    agents map (a => removeAgent(a))
  }
}