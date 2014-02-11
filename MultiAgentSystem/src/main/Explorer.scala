package main

import core.Vue
import core.Environnement
import discover.Chateau
import core.Systeme
import discover.ExplorerVue

object Explorer {
  def main(args: Array[String]) {
    val chateau: Environnement = new Chateau(30)
    val vue = new ExplorerVue(chateau, false)
    val systeme = new Systeme(vue, chateau)
    vue.visible = true
    chateau.system = systeme
    systeme.run(0)
    
    }
}