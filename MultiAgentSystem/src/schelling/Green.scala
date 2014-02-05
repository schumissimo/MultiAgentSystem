package schelling

import core.Environnement
import core.Coordonnees
import java.awt.Color

class Green(coord: Coordonnees, env: Environnement, taux: Int)
	extends Population(coord, env, Color.GREEN, taux)