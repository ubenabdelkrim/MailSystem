package part1.mailStorePack;

import java.io.*;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part2.encodingMessages.MailStoreInterface;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class implements the MailStore Interface to store the messages in a file.
 * @author Usama Benabdelkrim Zakan
 */
public class MailStoreOnFile extends MailStore implements Serializable, MailStoreInterface {
	private static final long serialVersionUID = 1L;
	/**
	 * MailStore In Memory Constructor.
	 * Initialize the list and configure the name in the file that stores the information
	 */
	public MailStoreOnFile() {
		super();
		file=new File("MailStoreOnFile.ser");
	}
	/**
	 * Method to send message
	 * @param message Message to send
	 */
	public void sendMail(Message message) {
		messagesList.add(message);
		File log=new File (file.getName());
		FileWriter printWriter;
		try {
			printWriter = new FileWriter (log, true);
			printWriter.write(message.toStringToFile());
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method to update MailStore of the user.
	 * Invokes the readFile method
	 * @param username Username
	 */
	@Override
	public void updateMail(String username) {
		List<String> fileLines=readFile();
		
		messagesList=parseData(fileLines);
		List<Message> mList1=filterPerSender(username);
		List<Message> mList2=filterPerReceiver(username);
		messagesList = Stream.of(mList1, mList2).flatMap(Collection::stream).collect(Collectors.toList());
	}

	@Override
	public MailStoreInterface getWrapper() {
		return null;
	}

	/**
	 * Method to readFile and to be invoked by updateMail method
	 * @return messagesList Messages List updated
	 */
	public List<String> readFile(){
		List<String> messagesList=new ArrayList<>();
		try {
			Scanner reader = new Scanner(file);
			while(reader.hasNextLine()) {
				messagesList.add(reader.nextLine());
			}
			return messagesList;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Method to parse the lines of the file which contains the messages of the mail service
	 * @param messageList Messages list to parse
	 * @return messageList Message list parsered
	 */
	@SuppressWarnings("resource")
	public static List<Message> parseData(List<String> messageList) {
		Message message;
		List<Message> parseredMessages=new ArrayList<>();
		
		for(String line : messageList) {
			message=parseMessage(line);
			//add new message
			parseredMessages.add(message);
		}
		return parseredMessages;
	}

	public static Message parseMessage(String string){
		Message message=new Message();
		Scanner lineScanner=new Scanner(string);
		lineScanner.useDelimiter(",");
		//parseData
		message.setSubject(lineScanner.next());
		message.setBody(lineScanner.next());
		message.setSender(new User(lineScanner.next(), lineScanner.next(), Integer.parseInt(lineScanner.next())));
		message.setReceiver(new User(lineScanner.next(), lineScanner.next(), Integer.parseInt(lineScanner.next())));

		try {
			message.setDate(lineScanner.next());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return message;
	}
}
