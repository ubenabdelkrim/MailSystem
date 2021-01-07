package scala

import part1.mailServiceElements.Message

import MailSys.mailSystem
import scala.collection.JavaConverters.asScalaBufferConverter
import scala.collection.mutable.ListBuffer

trait AComponent {
  def name: String

  var users: Int = 0

  var domains: Int = 0

  def getMail(): List[Message]

  def printTree(): Unit

  def accept(visitor: Visitor)

  def increment(): Unit
}

class Account(val n:String) extends AComponent {
  override def name: String = n

  override def printTree(): Unit = {
    println("|@"+name)
  }

  override def getMail(): List[Message] = {
    var mBox=MailSys.mailSystem.searchMailBoxUser(name)
    if(mBox!=null){
      return mBox.getMailstore.getMessagesList.asScala.toList
    }
    return null
  }

  override def accept(visitor: Visitor): Unit = {
    visitor.visit(this)
  }

  override def increment(): Unit = {
    users+=1
  }
}

class Domain(val n:String) extends AComponent {
  var children: ListBuffer[AComponent] = new ListBuffer[AComponent]()

  def addChild(child: AComponent): Unit = {
    children += child
  }

  def removeChild(child: AComponent): Unit = {
    children-= child
  }

  override def name: String = n

  override def printTree(): Unit = {
    println("|"+name)
    for(x <- children){
      for(x <- children){
        print("|  ")
      }
      x.printTree()
    }
  }

  override def getMail(): List[Message] = {
    var list:List[Message]=List()
    return getMailAux(list)
  }

   def getMailAux(list: List[Message]): List[Message] = {
    if(name.equals("")){
      return MailSys.mailSystem.getMessagesList.asScala.toList
    }
    else{
      for(x <- children){
        if(x.isInstanceOf[Domain]){
          x.asInstanceOf[Domain].getMailAux(list)
        }
        else{
          if(x.isInstanceOf[Account]){
            var l=mailSystem.searchMailBoxUser(x.name)
            if(l!=null){
              list ::: l.getMailstore.getMessagesList.asScala.toList
            }
          }
        }
      }
      return list
    }
  }

  override def accept(visitor: Visitor): Unit = {
    for(x <- children){
      x.accept(visitor)
    }
    visitor.visit(this)
  }

  override def increment():Unit = {
    domains+=1
  }
}
