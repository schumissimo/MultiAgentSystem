package schelling

import java.awt.Color

import core.Coordonnees
import core.Environnement

class Red(coord: Coordonnees, env: Environnement, taux: Int)
	extends Population(coord, env, Color.RED, taux)