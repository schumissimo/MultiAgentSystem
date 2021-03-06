package core

class Coordonnees(var x : Int, var y : Int) extends Equals {

  override def toString(): String = {
    x + " " + y
  }

  def setValid(taille: Int, isTorique: Boolean) {
    if (isTorique) {
      x = (x + taille) % taille
      y = (y + taille) % taille
    } else {
      x match {
        case -1 => x = 0
        case n if (n == taille) => x = taille - 1
        case _ =>
      }
      y match {
        case -1 => y = 0
        case n if (n == taille) => y = taille - 1
        case _ =>
      }
    }
  }

  def ++(direction: Direction): Coordonnees = {
    new Coordonnees(x + direction.dx, y + direction.dy)
  }

  def isValid(taille: Int): Boolean = {
    x >= 0 && x < taille && y >= 0 && y < taille
  }

  override def equals(obj: Any): Boolean = {
    val coo = obj.asInstanceOf[Coordonnees]
    return (coo.x == x && coo.y == y)
  }

  override def hashCode() = {
    val prime = 41
    prime + x + y
  }

  def canEqual(other: Any) = {
     val coo = other.asInstanceOf[Coordonnees]
    (coo.x == x && coo.y == y)
  }

}

