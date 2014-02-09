package core

class Systeme(view: Vue, enviro: Environnement) {

  var speed = 100
  var rate =  300
  val vue: Vue = view
  val environnement = enviro
  var stop = false

  vue.update

  def run(tour: Int) {
    pause
    evolution
    if (tour == 1 || stop) {
      vue.update
      println("terminé")
    } else {
      vue.update
      run(tour - 1)
    }
  }
  
  def stopRun {
    stop = true
    environnement.agents map (a => a.played = true)
    println("stop")
    
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