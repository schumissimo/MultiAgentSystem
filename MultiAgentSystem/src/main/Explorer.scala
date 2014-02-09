package main

import core.Vue
import core.Environnement
import discover.Chateau
import core.Systeme

object Explorer {
  def main(args: Array[String]) {
    val chateau: Environnement = new Chateau(20)
    val vue = new Vue(chateau)
    val systeme = new Systeme(vue, chateau)
    vue.visible = true
    chateau.system = systeme
    systeme.run()
  }
}