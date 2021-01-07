package part1.cliPackage;

import part1.mailServicePack.MailBoxInterface;
import java.util.List;

import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailService;
import part1.mailServicePack.MailServiceInterface;

/**
 * CLI interface
 * @author Usama Benabdelkrim Zakan
 */
public interface CLInterface {
    /**
     * MailBox getter
     * @return MailBoxInterface
     */
    MailBoxInterface getMailBox();
    /**
     * MailBox setter
     * @param mailBox User mailbox
     */
    void setMailBox(MailBoxInterface mailBox);
    /**
     * MailService getter
     * @return MailServiceInterface
     */
    MailServiceInterface getMailService();
    /**
     * MailService setter
     * @param mailService MailService
     */
    void setMailService(MailService mailService);

    //Methods to log in and sign up
    /**
     * Method to send a mail
     * @param message message to send
     */
    void sendMail(Message message);
    /**
     * Method to create the user object
     * @param user User to create
     */
    void createUser(User user);	//Create user as admin
    /**
     * Method to login with username
     * @param username to identificate the user
     * @return MailBoxInterface
     */
    MailBoxInterface logas(String username);

    //Filter methods
    /**
     * Method to filter By Date
     * @param date to filter
     * @return filtered list
     */
    List<Message> filterPerDate(String date);
    /**
     * Method to filter By Name
     * @param name to filter
     * @return filtered list
     */
    List<Message> filterPerName(String name);
    /**
     * Method to filter By Username
     * @param username to filter
     * @return filtered list
     */
    List<Message> filterPerUsername(String username);
    /**
     * Method to filter By Word
     * @param word to filter
     * @return filtered list
     */
    List<Message> filterPerWord(String word);
    /**
     * Method to filter messages by subject
     * @param subject to filter
     * @return filtered list
     */
    List<Message> filterPerSubject(String subject);
    /**
     * Method to filter messages with less than n words indicated with the parameter
     * @param nWord to filter
     * @return filtered list
     */
    List<Message> filterPerLessThanNWords(int nWord);
    /**
     * Method to filter messages by word and n words
     * @param word to filter
     * @param nWords to filter
     * @return filtered list
     */
    List<Message> filterPerWordAndNWord(String word, int nWords);

    //Sort methods
    /**
     * Method to sort messages by date
     * @return sorted list messages
     */
    List<Message> sortPerDate();
    /**
     * Method to sort messages by sender
     * @return sorted list messages
     */
    List<Message> sortPerSender();
    /**
     * Method to sort messages by receiver
     * @return sorted list messages
     */
    List<Message> sortPerReceiver();
}
