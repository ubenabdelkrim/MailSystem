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
    User getUser();
    void setUser(User user);
    MailStoreInterface getMailstore();
    void setMailstore(MailStoreInterface mailstore);
    void updateMailStore(String username) throws NoSuchAlgorithmException, NoSuchPaddingException;
    void sendMail(Message message);
    List<Message> getSentMessages();

    List<Message>  getSpamList();
}
