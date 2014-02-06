package core

class Systeme(view: Vue, enviro: Environnement) {

	var speed = 50
	var rate = 100
	val vue: Vue = view
	val environnement = enviro

	def run(tour: Int) {
		vue.update
		pause
		evolution
		if (tour != 1)
			run(tour - 1)
		else {
			vue.update
			println("terminé")
		}
	}

	//infini
	def run() {
		run(0)
	}

	def evolution = {
		util.Random.shuffle(environnement.agents) map (a => a action)
		environnement.agents map (a => a.played = false)
	}

	def pause {
		Thread.sleep(speed * rate / 100)
	}
}