package part2.encodingMessages;

import part1.mailServiceElements.Message;

/**
 * Strategy pattern to process messages
 * Extends MailStoreDecorator
 */
public abstract class ProcessingStrategy extends MailStoreDecorator{
	/**
	 * Processing Strategy Constructor
	 * @param mailStore mailStore to process
	 */
	public ProcessingStrategy(MailStoreInterface mailStore) {
		super(mailStore);
	}

	/**
	 * Method to send Mail processing the message
	 * @param message Message to send
	 */
	public void sendMail(Message message) {
		message.setBody(code(message.getBody()));
		super.sendMail(message);
	}
	/**
	 * Method to update Mail processing the messages
	 * @param username username to update the messages
	 */
	public void updateMail(String username) {
		super.updateMail(username);
		for(Message m : super.getMessagesList()){
			m.setBody(decode(m.getBody()));
		}
	}

	/**
	 * Abstract method to code the body
	 * @param string body to process
	 * @return processed body
	 */
	abstract public String code(String string);
	/**
	 * Abstract method to decode the body
	 * @param string body to process
	 * @return processed body
	 */
	abstract public String decode(String string);
}
