package part1.mailStorePack;

import java.io.*;
import part1.mailServiceElements.Message;
import part2.encodingMessages.MailStoreInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class implements the MailStore Interface to store the messages in a messages list serialized.
 * @author Usama Benabdelkrim Zakan
 */
public class MailStoreInMemory extends MailStore implements Serializable, MailStoreInterface {
	
	/**
	 * Serializable Class to read and write it
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * MailStore In Memory Constructor.
	 * Initialize the list and configure the name in the file that stores the information
	 */
	public MailStoreInMemory() {
		super();
		file=new File("MailStoreInMemory.ser");
	}
	/**
	 * Method to send message
	 * @param message Message to send
	 */
	public void sendMail(Message message) {
		messagesList.add(message);
		try {
			writeMailStore(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method to update MailStore of the user 
	 * @param username Username
	 */
	public void updateMail(String username) {
		messagesList=readMailStore();
		if(messagesList!=null && messagesList.size()>0){
			List<Message> mList1=filterPerSender(username);
			List<Message> mList2=filterPerReceiver(username);
			messagesList = Stream.of(mList1, mList2).flatMap(Collection::stream).collect(Collectors.toList());
		}
	}

	@Override
	public MailStoreInterface getWrapper() {
		return null;
	}

	/**
	 * Method to read the MailStore from file
	 */
	 
	public List<Message> readMailStore() {
	    FileInputStream fileInputStream;
	    ObjectInputStream objectInputStream;
	    List<Message> mList = null;
		try {
			fileInputStream = new FileInputStream("MailStoreInMemory.ser");
			objectInputStream = new ObjectInputStream(fileInputStream);
			mList = (List<Message>) objectInputStream.readObject();
			objectInputStream.close();
		}catch(FileNotFoundException ignored){

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}
	/**
	 * Method to write the MailStore to file
	 * @throws IOException Exception to serialize file
	 */
	public void writeMailStore(Message message) throws IOException {
		List<Message> mList;
		mList=readMailStore();
		if (mList == null) {
			mList = new ArrayList<>();
		}
		mList.add(message);

		FileOutputStream fileInput=new FileOutputStream("MailStoreInMemory.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileInput);
     
	    objectOutputStream.writeObject(mList);
	    objectOutputStream.flush();
	    objectOutputStream.close();
	}

}
