package scala

import part1.mailServiceElements.Message

/**
 * Tratit Visitor
 * 2 methods to implement, one for each Component
 */
trait Visitor {
  /**
   * Visit emthod to visit accounts
   * @param account Account to visit
   */
  def visit(account: Account)

  /**
   * Visit method to visit domains
   * @param domain Domain to visit
   */
  def visit(domain: Domain)
}

/**
 * FilterVisitor Class extends Visitor
 * @param p p Operation, receives Message and return Boolean
 */
class FilterVisitor(p:(Message) => Boolean) extends Visitor {
  var messages: List[Message] = List()

  /**
   * Visit account method to get mail and filter messages by p Operation
   * @param account Account to visit
   */
  override def visit(account: Account): Unit = {
    val mail = account.getMail()
    if(mail!=null) {
      messages = messages ++ mail.filter(p)
    } //concatenation
  }

  /**
   * Visit domain method to domain's chidlren accepts a visitor
   * @param domain Domain to visit
   */
  override def visit(domain: Domain): Unit = {
    domain.children.foreach(_.accept(this))
  }
}

/**
 * CounterVisitor class extends Visitor to count users and domains
 */
class CounterVisitor() extends Visitor{
  var users:Int=0
  var domains:Int=0

  /**
   * Increment users
   * @param account Account to visit
   */
  override def visit(account: Account): Unit = {
    users+=1
  }

  /**
   * Increment domains
   * @param domain Domain to visit
   */
  override def visit(domain: Domain): Unit = {
    domains+=1
  }
}

/**
 * FoldFilterVisitor class extends Visitor
 * if account pass the test, apply foldleft operation to each message for mail
 * @param accumulator
 * @param op
 * @param p
 * @tparam A
 */
class FoldFilterVisitor[A](accumulator:A, op: (A,Message)=>A, p:(Account) => Boolean) extends Visitor {
  var users = scala.collection.mutable.Map[String, A]()

  override def visit(account: Account): Unit = {
    if (p(account)) {
      val mail=account.getMail()
        if(mail!=null){
          mail.groupBy(_.getSender.getUsername)
            .foreach(elem=>{
              users.get(elem._1) match {
                case Some(value) => users.put(elem._1, elem._2.foldLeft(value)(op))   //if exists introduce new value to string key
                case None => users.put(elem._1, elem._2.foldLeft(accumulator)(op))    //if not exists introduce new key to the map with new value
              }
            })
          }
      }
    }

  override def visit(domain: Domain): Unit = {
    //Do nothing, visit only the children from the invoked AComponent
  }
  
}

/**
 * TransformerVisitor extends Visitor
 * @param op  Operation to apply
 */
class TransformerVisitor(op:List[Message] => List[Message]) extends Visitor{
  var messagesList:List[Message] = Nil

  /**
   * Visit method to get mail and apply transformer Operation and concatenate returned list
   * @param account Account to visit
   */
  override def visit(account: Account): Unit = {
    val list=account.getMail()
    if(list!=null){
      messagesList=messagesList++op(list)
    }
  }
  /**
   * Visit domain method to domain's chidlren accepts a visitor
   * @param domain Domain to visit
   */
  override def visit(domain: Domain): Unit = {
    domain.children.foreach(_.accept(this))
  }

}
