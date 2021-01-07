package scala

import part1.mailServiceElements.{Message, User}
import part1.mailServicePack.MailService
import part1.mailStorePack.MailStoreInMemory

import scala.MailSys.mailSystem

/**
 * @author Usama Benabdelkrim Zakan
 * MailSys object
 */
object MailSys {
    val mailStore = new MailStoreInMemory()
    val mailSystem = new MailService(mailStore)
  }

  object ScalaApp{
  def main(args: Array[String]) {
    //1. Composite structure
    val root = new Domain("")
    val cat = new Domain("cat")
    val urv = new Domain("urv")
    val etse = new Domain("etse ")
    val estudiants = new Domain("estudiants")
    val user1 = new Account("user1")
    val user2 = new Account("user2")
    val user3 = new Account("user3")
    val user4 = new Account("user4")

    root.addChild(cat)
    cat.addChild(urv)
    urv.addChild(etse)
    urv.addChild(estudiants)
    cat.addChild(user1)
    urv.addChild(user2)
    etse.addChild(user3)
    estudiants.addChild(user4)

    //2. Print tree
    root.printTree()

    val u1 = new User(user1.name, "user1", 2000)
    val u2 = new User(user2.name, "user2", 2000)
    val u3 = new User(user3.name, "user3", 2000)
    val u4 = new User(user3.name, "user4", 2000)
    mailSystem.createUser(u1)
    mailSystem.createUser(u2)
    mailSystem.createUser(u3)

    mailSystem.createMailStore()
    mailSystem.searchMailBoxUser(u1.getName).sendMail(new Message("hello", "Hello user2, this is user1!",u2))
    mailSystem.searchMailBoxUser(u1.getName).sendMail(new Message("hello", "Hello user1, this is you!", u1))
    mailSystem.searchMailBoxUser(u1.getName).sendMail(new Message("greetings", "Regards", u4))
    mailSystem.searchMailBoxUser(u1.getName).sendMail(new Message("spam", "spam spam", u3))
    mailSystem.searchMailBoxUser(u2.getName).sendMail(new Message("spam", "spam spam", u1))
    //3. Get mail
    println("\nAll mail: " + root.getMail)

    //5. FilterVisitor
    println("FilterVisitor")
    val v = new FilterVisitor(m => !m.getBody.contains("spam"))
    root.accept(v)
    println("Filtered: \n"+ v.messages)

    //6. CounterVisitor
    println("CounterVisitor")
    val c = new CounterVisitor()
    root.accept(c)
    println("Users: " + c.users + " Domains: " + c.domains)

    //7. FolderFilterVisitor
    println("FolderFilterVisitor")
    val f = new FoldFilterVisitor[Int](0, (acc, m) => acc + m.getBody.length,
      account => account.name.contains("user"))
    root.accept(f)
    println("Character count per user: " + f.users)
    //8-9. TransformerVisitor with stack censor
    var censorStack=Censor.stack_censor(List("spam", "you"))_
    var censorVisitor=new TransformerVisitor(censorStack)
    root.accept(censorVisitor)
    println("Censored Messages (Stack): "+censorVisitor.messagesList)

    //8-9. TransformerVisitor with tail censor
    censorStack=Censor.tail_censor(List("spam", "you")) _
    censorVisitor=new TransformerVisitor(censorStack)
    root.accept(censorVisitor)
    println("Censored Messages(Tail): "+censorVisitor.messagesList)
  }
}

