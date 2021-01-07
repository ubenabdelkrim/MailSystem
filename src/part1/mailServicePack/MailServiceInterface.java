package part1.mailServicePack;

import java.io.IOException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import java.util.List;

/**
 * MailService Interface
 */
public interface MailServiceInterface {
    void createMailStore();
    MailBox createUser(User user) throws IOException;
    MailBoxInterface logIn(String username);

    void addNewMailBox(MailBoxInterface mailbox);
    void removeMailBox(MailBoxInterface mailBox);

    List<MailBoxInterface> getMailBoxList();
    void setMailBoxList(List<MailBoxInterface> mailBoxList);
    List<Message> getMessagesList();
    List<User> getUsersList();
    int getNMessages();
    double getAverageMessagesPerUser();
    List<Message> groupMessagesPerSubject(String subject);
    int getNumberMessagesPerUser(String name);
    MailBoxInterface searchMailBoxUser(String username);

    String toString();

    void deSerialize() throws IOException;

    List<Message> filterByWordAndSender(String nextLine, String nextLine1);

    List<Message> filterPerSubject(String nextLine);

    int getNWordsOfUserByName(String next);

    List<Message> filterPerReceiverBirthYear(int nextInt);

    List<Message> filterBySubjectAndSenderBirthYear(String nextLine, int nextInt);

    void serialize();

    List<Message> filterByWordAndNWord(String word, int nWords, MailBoxInterface mailBox);
}
