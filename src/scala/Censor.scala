package scala

import part1.mailServiceElements.Message

import scala.annotation.tailrec

/**
 * Censor object
 */
object Censor {
  /**
   * Stack censor method
   * Censor messages with stack recursion
   * @param wordsToCens Words to censor
   * @param messages Messages to censor
   * @return censored list
   */
  def stack_censor(wordsToCens: List[String])(messages: List[Message]):  List[Message] = messages match {
    case Nil => Nil
    case msg::msgList=>
      splitMessage(wordsToCens, msg)::stack_censor(wordsToCens)(msgList)
  }

  /**
   * Tail censor method
   * Censor messages with auxiliar method
   * @param wordsToCens Words to censor
   * @param messages Messages to censor
   * @return censored list
   */
  def tail_censor(wordsToCens: List[String])(messages: List[Message]):  List[Message] = {
    /**
     * Auxiliar method to execute tail recursion with list to "accumulate" transformed messages
     * @param wordsToCens Words to censor
     * @param messages Messages to censor
     * @param finalList censored list
     * @return finalList
     */
    @tailrec
    def tail_censor_aux(wordsToCens: List[String])(messages: List[Message])(finalList:List[Message]):  List[Message] = messages match {
      case Nil => finalList
      case msg::msgList=>
        tail_censor_aux(wordsToCens)(msgList)(splitMessage(wordsToCens, msg)::finalList)
    }
    tail_censor_aux(wordsToCens)(messages)(Nil) //execute tail recursion with Nil final list
  }

  /**
   * Method to split messages and censor bodies
   * @param wordsToCens Words to censor
   * @param message Messages to censor
   * @return censored message
   */
  def splitMessage(wordsToCens: List[String], message: Message):Message = {
    val body = message.getBody
      .split("\\s")
      .toList
      .map(word=>
        if(wordsToCens.exists(word.contains(_))) "CENSORED"   //if words contains any string from wordToCens
        else word)
    new Message(message.getSubject, body.mkString(" "), message.getSender, message.getReceiver) //mkString to rebuild bodie
  }

}
