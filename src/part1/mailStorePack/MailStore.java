package part1.mailStorePack;

import java.io.File;
import java.io.Serializable;
import part1.mailServiceElements.DateComparator;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.ReceiverComparator;
import part1.mailServiceElements.SenderComparator;
import part2.encodingMessages.MailStoreInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * This abstract class defines the different methods of the MailStore and allows to implement different type of back-end
 * @author Usama Benabdelkrim Zakan
 */
public abstract class MailStore implements Serializable, MailStoreInterface {
	private static final long serialVersionUID = 1L;
	protected List<Message> messagesList;
	protected File file;

	public MailStore(){
		messagesList=new ArrayList<>();
	}
	
	/**
	 * Messages List Getter
	 * @return messagesList Messages List
	 */
	public List<Message> getMessagesList() {
		return messagesList;
	}
	/**
	 * Messages List Setter
	 * @param messagesList Messages List
	 */
	public void setMessagesList(List<Message> messagesList) {
		this.messagesList = messagesList;
	}
	/**
	 * File getter
	 * @return file File that stores the information
	 */
	public File getFile() {
		return file;
	}
	/**
	 * File setter
	 * @param file File that stores the information
	 */
	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Method to send message
	 * @param message Message to send
	 */
	abstract public void sendMail(Message message);
	/**
	 * Method to update MailStore of the user 
	 * @param username Username
	 */
	abstract public void updateMail(String username);

	/**
	 * Iterator Getter
	 * @return iterator Iterator to iterate the MailStore
	 */
	public Iterator<Message> getIterator() {
		return messagesList.iterator();
	}
	/**
	 * Method to filter the MailStore
	 * @param predicate Predicate to filter messages
	 * @return messagesList Messages list filtered
	 */
	public List<Message> filter(Predicate<Message> predicate) {
		return messagesList.stream().filter(predicate).collect(Collectors.toList());
	}
	
	public List<Message> filterPerDate(String date){
		Predicate<Message> predicate= t -> t.getDate().equals(date);
		return filter(predicate);
	}
	
	public List<Message> filterPerSender(String username){
		Predicate<Message> predicate= t -> t.getSender().getUsername().equals(username);
		return filter(predicate);
	}

	public List<Message> filterPerReceiver(String username){
		Predicate<Message> predicate= t -> t.getReceiver().getUsername().equals(username);
		return filter(predicate);
	}

	public List<Message> filterPerSenderName(String name){
		Predicate<Message> predicate= t -> t.getSender().getName().equals(name);
		return filter(predicate);
	}
	
	public List<Message> filterPerWord(String word){
		Predicate<Message> predicate= t -> t.getBody().contains(word);
		return filter(predicate);
	}
	
	public List<Message> filterPerLessThanNWords(int nWords){
		Predicate<Message> predicate= t -> t.getNumberWords() < nWords;
		return filter(predicate);
	}

	public List<Message> filterPerSubject(String subject){
		Predicate<Message> predicate= t -> t.getSubject().contains(subject);
		return filter(predicate);
	}

	public List<Message> filterByReceiverBirthYear(int year){
		Predicate<Message> predicate= t -> t.getReceiver().getBirthYear() < year;
		return filter(predicate);
	}

	public List<Message> sort(Comparator<? super Message> comparator){
		return messagesList.stream().sorted(comparator).collect(Collectors.toList());
	}

	public List<Message> sortPerDate(){
		return sort(new DateComparator());
	}

	public List<Message> sortPerSender(){
		return sort(new SenderComparator());
	}

	public List<Message> sortPerReceiver(){
		return sort(new ReceiverComparator());
	}

	/**
	 * Method to list by date and print them
	 */
	public void listByDate(){
		sortPerDate();
		for(Iterator<Message> iter = getIterator(); iter.hasNext();){
			Message message = iter.next();
			System.out.println(message);
		}
	}
	/**
	 * Method to list by date and print them
	 */
	public void listBySenderUsername(){
		sortPerSender();
		for(Iterator<Message> iter = getIterator(); iter.hasNext();){
			Message message = iter.next();
			System.out.println(message);
		}
	}
	/**
	 * Method to list by receiver username
	 */
	public void listByReceiverUsername(){
		sortPerReceiver();
		for(Iterator<Message> iter = getIterator(); iter.hasNext();){
			Message message = iter.next();
			System.out.println(message);
		}
	}

	/**
	 * Get Number of Words in mailstore
	 * @return result
	 */
	public int getNumberWords(){
		int count=0;
		for(Message m : messagesList){
			count+=m.getNumberWords();
		}
		return count;
	}

	/**
	 * ToString method
	 * @return string
	 */
	public String toString(){
		StringBuilder toString = new StringBuilder();
		for(Message m : messagesList){
			toString.append("\n").append(m.toString());
		}
		return toString.toString();
	}
}
