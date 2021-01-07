package part1.mailServicePack;

import java.io.Serializable;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part2.encodingMessages.MailStoreInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the mail account of users.
 * MailBox contains the user and a mailstore. This mailstore implements a MailStore Interface and can be MailStore On File or MailStore In Memory.
 *
 * @author Usama Benabdelkrim Zakan
 */
public class MailBox implements Serializable, MailBoxInterface {
	private static final long serialVersionUID = 1L;
	/**
	 * The Mailbox owner user
	 */
	private User user;
	
	/**
	 * The mailbox mailstore
	 */
	protected MailStoreInterface mailstore;
	
	/**
	 * The MailBox Constructor
	 * @param user The account's user
	 */
	public MailBox(User user) {
		this.user=user;
	}

	/**
	 * The User getter
	 * @return user The account's user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * The User setter
	 * @param user The account's user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * The MailStore getter
	 * @return mailstore The MailStore implemented to store the messages
	 */
	public MailStoreInterface getMailstore() {
		return mailstore;
	}

	/**
	 * The MailStore setter
	 * @param mailstore The MailStore implemented to store the messages
	 */
	public void setMailstore(MailStoreInterface mailstore) {
		this.mailstore = mailstore;
	}
	
	/**
	 * Method to update the MailStore. This, save the messages list that returns the updateMail method of the MailStore Class.
	 * @param username The account's username
	 */
	public void updateMailStore(String username) {
		mailstore.updateMail(username);
	}
	
	/**
	 * Method to send a mail to the receiver that contains the message
	 * @param message The message to send
	 */
	public void sendMail(Message message) {
		message.setSender(user);
		mailstore.sendMail(message);
	}
	/**
	 * Method to get sent messages
	 * @return messagesList List of sent messages
	 */
	public List<Message> getSentMessages(){
		List<Message> messagesList=new ArrayList<>();

		for(Iterator<Message> iter = mailstore.getIterator(); iter.hasNext();){
			Message message = iter.next();
			if(message.getSender().getUsername().equals(user.getUsername())){
				messagesList.add(message);
			}
		}
		return messagesList;
	}

	@Override
	public List<Message> getSpamList() {
		return null;
	}
}
