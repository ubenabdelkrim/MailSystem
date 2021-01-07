package part1.mailServicePack;

import javax.crypto.NoSuchPaddingException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part2.encodingMessages.MailStoreInterface;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * MailBox Interface
 */
public interface MailBoxInterface {
    /**
     * User getter
     * @return user
     */
    User getUser();

    /**
     * Us
     * @param user user
     */
    void setUser(User user);
    /**
     * The MailStore getter
     * @return mailstore The MailStore implemented to store the messages
     */
    MailStoreInterface getMailstore();
    /**
     * The MailStore setter
     * @param mailstore The MailStore implemented to store the messages
     */
    void setMailstore(MailStoreInterface mailstore);
    /**
     * Method to update the MailStore. This, save the messages list that returns the updateMail method of the MailStore Class.
     * @param username The account's username
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     * @throws NoSuchPaddingException NoSuchPaddingException
     */
    void updateMailStore(String username) throws NoSuchAlgorithmException, NoSuchPaddingException;
    /**
     * Method to send a mail to the receiver that contains the message
     * @param message The message to send
     */
    void sendMail(Message message);
    /**
     * Method to get sent messages
     * @return messagesList List of sent messages
     */
    List<Message> getSentMessages();
    /**
     * Spam List getter
     * @return spamList
     */
    List<Message>  getSpamList();
}
