package part1.mailServicePack;

import part4.MailStoreAnnotation;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part2.encodingMessages.MailStoreInterface;
import part3.redisMailStore.MailStoreFactory;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class consists exclusively in the Mail Service back-end. 
 * It contains a {@link MailBox mail box} list and different methods to {@link MailService#addNewMailBox(MailBoxInterface) add}/{@link MailService#removeMailBox(MailBoxInterface) remove} Mail Boxes or {@link MailService#searchMailBoxUser(String) search} a user's mailbox. This last method uses streams to filter the multiple users.
 *
 * @author Usama Benabdelkrim Zakan
 */

@MailStoreAnnotation(store = "part1.mailStorePack.MailStoreOnFile", log = true)
public class MailService implements Serializable, MailServiceInterface{
	/**
	 * MailBox list
	 */
	private List<MailBoxInterface> mailBoxList;
	private String mailStore;

	private static final long serialVersionUID = 1L;

	/**
	 * Mail Service Constructor to initialize MailBox list
	 */
	public MailService() {
		this.mailBoxList=new ArrayList<>();
	}

	public MailService(MailStoreInterface mailStoreInterface) {
		this.mailStore=mailStoreInterface.getClass().getName();
		this.mailBoxList=new ArrayList<>();
	}
	/**
	 * Method to create mail store according to class annotation usign the MailStoreFactory
	 */
	public void createMailStore(){
		for(MailBoxInterface mailBox : mailBoxList){
			mailStore=getAnnotation().store();
			MailStoreInterface mailStoreInterface=new MailStoreFactory(mailStore).getMailStore();
			mailBox.setMailstore(mailStoreInterface);
		}
	}
	/**
	 * Method to create mail store according to class annotation usign the MailStoreFactory
	 */
	public void createMailStore(MailStoreInterface mailStoreInterface){
		for(MailBoxInterface mailBox : mailBoxList){
			mailBox.setMailstore(mailStoreInterface);
		}
	}
	/**
	 * Annotation getter
	 * @return MailStoreAnnotation
	 */
	public static MailStoreAnnotation getAnnotation(){
		Class<MailService> obj=MailService.class;
		return obj.getAnnotation(MailStoreAnnotation.class);
	}


	/**
	 * Method to add the new MailBox
	 * @param mailbox The new MailBox
	 */
	public void addNewMailBox(MailBoxInterface mailbox) {
		this.mailBoxList.add(mailbox);
	}
	
	/**
	 * Method to remove a MailBox
     * @param mailBox The MailBox to remove
     */
	public void removeMailBox(MailBoxInterface mailBox) {
		this.mailBoxList.remove(mailBox);
	}
	
	/**
	 * MailBox list getter
     * @return mailBoxList The MailBox list of the Mail Service
     */
	public List<MailBoxInterface> getMailBoxList() {
		return mailBoxList;
	}
	
	/**
	 * MailBox list setter.
	 * @param mailBoxList The MailBoxList to set
     */
	public void setMailBoxList(List<MailBoxInterface> mailBoxList) {
		this.mailBoxList = mailBoxList;
	}
	/**
	 * Method to return the messages list of all mailboxes
	 * @return messagesList
	 */
	public List<Message> getMessagesList(){
		List<Message> messagesList = new ArrayList<>();
		for (MailBoxInterface mailBox : mailBoxList) {
			messagesList=Stream.of(messagesList, mailBox.getMailstore().filterPerSender(mailBox.getUser().getUsername())).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return messagesList;
	}
	/**
	 * Method to return the users list
	 * @return usersList
	 */
	public List<User> getUsersList(){
		List<User> usersList = new ArrayList<>();
		for (MailBoxInterface mailBox : mailBoxList) {
			usersList.add(mailBox.getUser());
		}
		return usersList;
	}
	/**
	 * Method to return the number of messages
	 * @return nMessages
	 */
	public int getNMessages(){
		return getMessagesList().size();
	}
	/**
	 * Method to return the average of messages per user
	 * @return average
	 */
	public double getAverageMessagesPerUser(){
		return getNMessages()/mailBoxList.size();
	}
	/**
	 * Method to group messages per subject
	 * @param subject Subject
	 * @return messagesList
	 */
	public List<Message> groupMessagesPerSubject(String subject){
		List<Message> newList=null;
		for (MailBoxInterface mailBox : mailBoxList)
			newList = Stream.of(newList, mailBox.getMailstore().getMessagesList()).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
		Map<String, List<Message>> map = newList != null ? newList.stream().collect(Collectors.groupingBy(Message::getSubject)) : null;
		return map != null ? map.getOrDefault(subject, newList) : null;
	}
	/**
	 * Method to get number of messages by name
	 * @param name User name
	 * @return nMessages
	 */
	public int getNumberMessagesPerUser(String name){
		int count = 0;
		for (MailBoxInterface mailBox : mailBoxList) {
			if(mailBox.getUser().getName().equals(name)){
				count+=mailBox.getMailstore().getNumberWords();
			}
		}
		return count;
	}
	/**
	 * Method to check if user exists in the mail system
	 * @param name User's name
	 * @return boolean
	 */
	public boolean containsUser(String name){
		return mailBoxList.stream().anyMatch(o -> o.getUser().getUsername().equals(name));
	}

	/**
	 * Method to search the mailbox of specific user
	 * @param username The username to search
	 * @return mailbox The user's MailBox
	 */
	public MailBoxInterface searchMailBoxUser(String username) {

		for (MailBoxInterface mailBoxInterface : mailBoxList) {
			if (mailBoxInterface.getUser().getUsername().equals(username)) {
				return mailBoxInterface;
			}
		}
		return null;
	}
	/**
	 * Method to create user
	 * @param user User to create
	 * @return mailbox The user's MailBox
	 */
	public MailBox createUser(User user) {
		MailBox mailBox = new MailBox(user);
		mailBoxList.add(mailBox);
		return mailBox;
	}
	/**
	 * Method to login
	 * @param username username to login
	 * @return mailbox The user's MailBox
	 */
	public MailBoxInterface logIn(String username){
		if(getAnnotation().log()){
			MailBoxInterface mailBox=searchMailBoxUser(username);
			try {
				mailBox.updateMailStore(username);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				e.printStackTrace();
			}
			return mailBox;
		}
		return null;
	}
	/**
	 * Method to filter by date
	 * @param date Date to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByDate(String date) {
		List<Message> messagesList = new ArrayList<>();

		for (MailBoxInterface mailBox : mailBoxList) {
			messagesList=Stream.of(messagesList, mailBox.getMailstore().filterPerDate(date)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return messagesList;
	}
	/**
	 * Method to filter by username
	 * @param username username to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByUsername(String username) {
		List<Message> newList = new ArrayList<>();

		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterPerSender(username)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to filter by name
	 * @param name name to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByName(String name){
		List<Message> newList = new ArrayList<>();

		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterPerSenderName(name)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to filter by word
	 * @param word word to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByWord(String word){
		List<Message> newList = new ArrayList<>();

		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterPerWord(word)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to filter messages with les than n Words
	 * @param nWords nWords to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByLessThanNWords(int nWords){
		List<Message> newList = new ArrayList<>();

		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterPerLessThanNWords(nWords)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to filter messages by subject
	 * @param subject subject to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterPerSubject(String subject){
		List<Message> newList = new ArrayList<>();
		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterPerSubject(subject)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to filter messages by word and n Words
	 * @param word word to filter
	 * @param nWords nWords to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByWordAndNWord(String word, int nWords){
		List<Message> mListFiltered = new ArrayList<>();
		for(MailBoxInterface mailBox: mailBoxList)
			Stream.of(mListFiltered, filterByWordAndNWord(word, nWords, mailBox));
		return mListFiltered.stream().distinct().collect(Collectors.toList());
	}
	/**
	 * Auxiliar method to filter messages by word and less than n words
	 * @param word word to filter
	 * @param nWords nWords to filter
	 * @param mailBox mailBox to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByWordAndNWord(String word, int nWords, MailBoxInterface mailBox) {
		List<Message> mList= mailBox.getMailstore().filterPerWord(word);
		List<Message> mList2= mailBox.getMailstore().filterPerLessThanNWords(nWords);
		return mList.stream().distinct().filter(mList2::contains).collect(Collectors.toList());
	}
	/**
	 * Method to filter messages by word and sender username
	 * @param word words to filter
	 * @param username username to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByWordAndSender(String word, String username){
		List<Message> mListFiltered = new ArrayList<>();
		for(MailBoxInterface mailBox: mailBoxList){
			mListFiltered=Stream.of(mListFiltered, filterByWordAndSender(word, username, mailBox)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		//No se ha conseguido eliminar duplicados con hashset
		return mListFiltered;
	}
	/**
	 * Auxiliar method to filter messages by word and sender username
	 * @param word word to filter
	 * @param username username to filter
	 * @param mailBox mailbox to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterByWordAndSender(String word, String username, MailBoxInterface mailBox){
		List<Message> mList= mailBox.getMailstore().filterPerWord(word);
		List<Message> mList2= mailBox.getMailstore().filterPerSender(username);
		mList=mList.stream().filter(mList2::contains).collect(Collectors.toList());
		return mList;

	}
	/**
	 * Auxiliar method to filter messages by word and sender birthyear
	 * @param subject subject to filter
	 * @param birthYear birthyear to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterBySubjectAndSenderBirthYear(String subject, int birthYear){
		List<Message> mListFiltered = new ArrayList<>();
		List<Message> list;
		for(MailBoxInterface mailBox: mailBoxList){
			list=filterBySubjectAndSenderBirthYear(subject, birthYear, mailBox);
			if(list!=null && list.size()>0)
				mListFiltered=Stream.of(mListFiltered, list).flatMap(Collection::stream).collect(Collectors.toList());
		}
		mListFiltered=mListFiltered.stream().distinct().collect(Collectors.toList());
		return mListFiltered;
	}
	/**
	 * Auxiliar method to filter messages by word and sender birthyear
	 * @param subject subject to filter
	 * @param birthYear birthyear to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterBySubjectAndSenderBirthYear(String subject, int birthYear, MailBoxInterface mailBox){
		if(mailBox.getUser().getBirthYear()>birthYear){
			return mailBox.getMailstore().filterPerSubject(subject);
		}
		return null;
	}
	/**
	 * Method to filter messages by receiver birth year
	 * @param birthYear birthyear to filter
	 * @return messagesList Filtered list
	 */
	public List<Message> filterPerReceiverBirthYear(int birthYear){
		List<Message> newList = new ArrayList<>();
		for (MailBoxInterface mailBox : mailBoxList) {
			newList=Stream.of(newList, mailBox.getMailstore().filterByReceiverBirthYear(birthYear)).flatMap(Collection::stream).collect(Collectors.toList());
		}
		return newList;
	}
	/**
	 * Method to get nWords of user by name
	 * @param name name to filter
	 * @return messagesList Filtered list
	 */
	public int getNWordsOfUserByName(String name){
		int count=0;
		for(Message m : filterByName(name)){
			count+=m.getNumberWords();
		}
		return count;
	}
	/**
	 * Method to deserialize mailservice
	 */
	public void deSerialize() {
		FileInputStream fileInputStream;
		ObjectInputStream objectInputStream;

		try {
			fileInputStream = new FileInputStream("MailService.ser");
			objectInputStream = new ObjectInputStream(fileInputStream);
			//Add stored messages to the list
			mailBoxList=Stream.of(mailBoxList, (List<MailBox>) objectInputStream.readObject()).flatMap(Collection::stream).collect(Collectors.toList());
			objectInputStream.close();
		} catch(FileNotFoundException ignored){

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method to serialize mailservice
	 */
	public void serialize() {
		FileOutputStream fileInput;
		ObjectOutputStream objectOutputStream;
		try {
			fileInput = new FileOutputStream("MailService.ser");
			objectOutputStream = new ObjectOutputStream(fileInput);
			objectOutputStream.writeObject(mailBoxList);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method toString
	 */
	public String toString(){
		List<Message> mList=getMessagesList();
		LinkedHashSet<Message> hashSet = new LinkedHashSet<>(mList);
		mList = new ArrayList<>(hashSet);

		StringBuilder toString = new StringBuilder();
		for(Message m : mList){
			toString.append("\n").append(m.toString());
		}
		return toString.toString();
	}
}
