package scala

import part1.mailServiceElements.Message

trait Visitor {
  def visit(account: Account)
  def visit(domain: Domain)
}

class FilterVisitor(p:(Message) => Boolean) extends Visitor {
  var messages: List[Message] = List()

  override def visit(account: Account): Unit = {
    var mail = account.getMail()
    if(mail!=null) {
      messages = messages ++ mail.filter(p)
    } //concatenation
  }

  override def visit(domain: Domain): Unit = {
    domain.children.foreach(_.accept(this))
  }
}

class CounterVisitor() extends Visitor{
  var users:Int=0
  var domains:Int=0

  override def visit(account: Account): Unit = {
    users+=1
  }

  override def visit(domain: Domain): Unit = {
    domains+=1
  }
}

class FoldFilterVisitor[A](accumulator:A, op: (A,Message)=>A, p:(Account) => Boolean) extends Visitor {
  var users = scala.collection.mutable.Map[String, A]()

  override def visit(account: Account): Unit = {
    if (p(account.asInstanceOf[Account])) {
      val mail=account.getMail()
        if(mail!=null){
          mail.groupBy(_.getSender.getUsername)
            .foreach(k=>{
              users.get(k._1) match {
                case Some(value) => users.put(k._1, k._2.foldLeft(value)(op))
                case None => users.put(k._1, k._2.foldLeft(accumulator)(op))
              }
            })
          }
      }
    }

  override def visit(domain: Domain): Unit = {
    domain.children.foreach(_.accept(this))
  }
  
}

class TransformerVisitor(op:List[Message] => List[Message], var messagesList:List[Message] = Nil) extends Visitor{

  override def visit(account: Account): Unit = {
    val list=account.getMail()
    if(list!=null){
      messagesList=messagesList++op(list)
    }
  }

  override def visit(domain: Domain): Unit = {
    domain.children.foreach(_.accept(this))
  }

}
