package part2.encodingMessages;

import javax.crypto.NoSuchPaddingException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailService;
import part1.mailStorePack.MailStoreOnFile;
import part2.autoMessageFilters.MailBoxWithSpam;
import java.security.NoSuchAlgorithmException;

/**
 * EncodingMessagesDemo to test ReversesStringDecorator and EncryptionDecorator
 * @author Usama Benabdelkrim Zakan
 */
public class EncodingMessagesDemo {
    /**
     * Main method
     * @param args args
     * @throws NoSuchPaddingException NoSuchPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
        //Initialize mail system
        MailService mailService=new MailService();
        //Create at least 3 users
        User user1=new User("Usama", "usi1",2000);
        User user2=new User("Adel", "adl3",1983);
        //Create mailboxes
        mailService.addNewMailBox(new MailBoxWithSpam(user1));
        mailService.addNewMailBox(new MailBoxWithSpam(user2));
        mailService.createMailStore(new ReversesStringDecorator(new EncryptionDecorator(new MailStoreOnFile())));

        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("TAP", "Student marks", user2));
        mailService.searchMailBoxUser(user2.getUsername()).sendMail(new Message("Examen AC", "Hola usi1. Doncs molt be. I a tu?", user1));

        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        mailService.searchMailBoxUser(user2.getUsername()).updateMailStore(user2.getUsername());

        System.out.println(mailService.searchMailBoxUser(user1.getUsername()).getMailstore().getMessagesList());
    }
}
