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
    MailBoxInterface getMailBox();
    void setMailBox(MailBoxInterface mailBox);
    MailServiceInterface getMailService();
    void setMailService(MailService mailService);

    //Methods to log in and sign up
    void sendMail(Message message);
    void createUser(User user);	//Create user as admin
    MailBoxInterface logas(String username);

    //Filter methods
    List<Message> filterPerDate(String date);
    List<Message> filterPerName(String name);
    List<Message> filterPerUsername(String username);
    List<Message> filterPerWord(String word);
    List<Message> filterPerSubject(String subject);
    List<Message> filterPerLessThanNWords(int nWord);
    List<Message> filterPerWordAndNWord(String word, int nWords);

    //Sort methods
    List<Message> sortPerDate();
    List<Message> sortPerSender();
    List<Message> sortPerReceiver();
}
