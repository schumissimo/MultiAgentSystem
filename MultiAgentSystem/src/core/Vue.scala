package core

import java.awt.Color

import scala.swing.BorderPanel
import scala.swing.Dimension
import scala.swing.Frame
import scala.swing.GridPanel
import scala.swing.Label
import scala.swing.ScrollPane
import scala.swing.Swing

class Vue(environnement: Environnement, showBorders: Boolean) extends Frame {
	val envSize = environnement.taille
	val viewSize = 500
	val cellSize = 10
	val gridPanel: GridPanel = new GridPanel(envSize, envSize)
	val labels = Array.ofDim[Label](envSize, envSize)

	// initialisation des labels
	for (i <- 0 until envSize) {
		for (j <- 0 until envSize) {
			labels(i)(j) = new Label {
				if (showBorders) {
					border = Swing.LineBorder(new Color(0, 0, 0))
				}
				preferredSize = new Dimension(cellSize, cellSize)
				maximumSize = new Dimension(cellSize, cellSize)
				minimumSize = new Dimension(cellSize, cellSize)
				background = Color.WHITE
				opaque = true
			}
			gridPanel.contents += labels(i)(j)
		}
	}

	val borderPanel = new BorderPanel
	val scrollPanel = new ScrollPane(gridPanel)
	scrollPanel.preferredSize = new Dimension(viewSize, viewSize)
	scrollPanel.maximumSize = new Dimension(viewSize, viewSize)
	scrollPanel.minimumSize = new Dimension(viewSize, viewSize)
	title = "hey salut les copains"
	contents = borderPanel

	borderPanel.layout(scrollPanel) = BorderPanel.Position.Center
	//borderPanel.layout(eastPanel) = BorderPanel.Position.East
	peer.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE)
	pack

	//constructor
	def this(environnement: Environnement) = this(environnement, true)

	// mise a jour de la vue
	def update {
		val grilleEnv = environnement.grille

		for (row <- 0 until envSize) {
			for (col <- 0 until envSize) {
				if (grilleEnv(row)(col) == null) {
					labels(row)(col).background = environnement.color
				} else {
					labels(row)(col).background = grilleEnv(row)(col).color
				}
			}
		}
	}

}