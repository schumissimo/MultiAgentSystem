package core

class Systeme(view: Vue, enviro: Environnement) {

	var speed = 10
	var rate = 10
	val vue: Vue = view
	val environnement = enviro
	
	vue.update

	def run(tour: Int) {
		pause
		evolution
		if (tour == 0){
			vue.update
			println("terminé")
		}
		else {
			vue.update
			run(tour - 1)
		}
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