import java.util.NoSuchElementException

abstract class MyList {

  def head:Int
  def tail:MyList
  def isEmpty:Boolean
  def add(x:Int):MyList
  def printElements:String
  override def toString:String = "[ "+printElements+" ]"

}

object Empty extends MyList{

  def head:Int = throw new NoSuchElementException
  def tail:MyList = throw new NoSuchElementException
  def isEmpty:Boolean = true
  def add(x:Int):MyList = new ImmutableList(x,Empty)
  def printElements: String = ""
}

class ImmutableList(h:Int, t:MyList) extends MyList{

  def head:Int = h
  def tail:MyList = t
  def isEmpty:Boolean = false
  def add(x:Int):MyList = new ImmutableList(x,this)
  def printElements: String = {
    if (t.isEmpty) ""+h
    else h +" "+t.printElements
  }


}

object ImmutableListTest extends App{

  val list1 = new ImmutableList(1,new ImmutableList(2,new ImmutableList(3,Empty)))
  println(list1.tail.head)
  println(list1.add(4).head)
  println(list1.toString)
}