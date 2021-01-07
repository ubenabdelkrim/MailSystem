package part2.encodingMessages;

import part1.mailServiceElements.Message;
import java.util.Iterator;
import java.util.List;

/**
 * MailStore Interface
 */
public interface MailStoreInterface {
    void sendMail(Message message);
    void updateMail(String username);

    List<Message> filterPerSender(String username);

    List<Message> sortPerDate();

    Iterator<Message> getIterator();

    List<Message> sortPerSender();

    List<Message> sortPerReceiver();

    List<Message> getMessagesList();

    void setMessagesList(List<Message> messagesList);

    List<Message> filterPerDate(String date);

    List<Message> filterPerSenderName(String name);

    List<Message> filterPerWord(String word);

    List<Message> filterPerLessThanNWords(int nWord);

    List<Message> filterPerSubject(String subject);

    List<Message> filterPerReceiver(String username);

    int getNumberWords();

    List<Message> filterByReceiverBirthYear(int birthYear);

    MailStoreInterface getWrapper();

    void listByDate();
    void listBySenderUsername();
    void listByReceiverUsername();
}
