package core

class Systeme(view: Vue, enviro: Environnement) {

	var speed = 100
	var rate = 50
	val vue: Vue = view
	val environnement = enviro

	def run(tour: Int) {
		vue.update
		pause
		evolution
		if (tour != 0)
			run(tour - 1)
		else
			println("terminé")
	}

	//infini
	def run() {
		run(-1)
	}

	def evolution = {
		util.Random.shuffle(environnement.agents) map (a => a action)
		environnement.agents map (a => a.played = false)
	}

	def pause {
		Thread.sleep(speed * rate / 100)
	}
}