package scala

import part1.mailServiceElements.Message

object Censor {

  def stack_censor(wordsToCens: List[String])(messages: List[Message]):  List[Message] = messages match {
    case Nil => Nil
    case msg::msgList=>
      splitMessage(wordsToCens, msg)::stack_censor(wordsToCens)(msgList)
  }

  def tail_censor(wordsToCens: List[String])(messages: List[Message]):  List[Message] = {
    def tail_censor_aux(wordsToCens: List[String])(messages: List[Message])(finalList:List[Message]):  List[Message] = messages match {
      case Nil => finalList
      case msg::msgList=>
        tail_censor_aux(wordsToCens)(msgList)(splitMessage(wordsToCens, msg)::finalList)
    }
    tail_censor_aux(wordsToCens)(messages)(Nil)
  }

  def splitMessage(wordsToCens: List[String], message: Message):Message = {
    val body = message.getBody
      .split("\\s")
      .toList
      .map(word=> if(wordsToCens.contains(word)) "CENSORED" else word)
    new Message(message.getSubject, body.mkString(" "), message.getSender, message.getReceiver)
  }

}
