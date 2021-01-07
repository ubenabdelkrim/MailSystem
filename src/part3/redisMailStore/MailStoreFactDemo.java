package part3.redisMailStore;

import javax.crypto.NoSuchPaddingException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailStorePack.MailStoreInMemory;
import part1.mailStorePack.MailStoreOnFile;
import part2.autoMessageFilters.MailBoxWithSpam;
import part2.encodingMessages.EncryptionDecorator;
import java.security.NoSuchAlgorithmException;

public class MailStoreFactDemo {
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
        MailServiceFactory mailService;
        User user1=new User("Usama", "usi", 2000);
        User user2=new User("Adel", "adl", 1983);

        //WrappedMailStore
        mailService=new MailServiceFactory(new MailStoreFactory(EncryptionDecorator.class.getName()));
        mailService.addNewMailBox(new MailBoxWithSpam(user1));
        mailService.addNewMailBox(new MailBoxWithSpam(user2));
        mailService.createMailStore();

        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("TAP", "Hello World!", user2));
        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        System.out.print(mailService.searchMailBoxUser(user1.getUsername()).getMailstore().getMessagesList());
        //MailStoreInMemory
        mailService=new MailServiceFactory(new MailStoreFactory(MailStoreInMemory.class.getName()));
        mailService.createMailStore();
        mailService.addNewMailBox(new MailBoxWithSpam(user1));
        mailService.addNewMailBox(new MailBoxWithSpam(user2));
        mailService.createMailStore();

        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("AC", "Hola Mundo!", user2));
        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        System.out.print(mailService.searchMailBoxUser(user1.getUsername()).getMailstore().getMessagesList());
        //MailStoreOnFile
        mailService=new MailServiceFactory(new MailStoreFactory(MailStoreOnFile.class.getName()));
        mailService.createMailStore();
        mailService.addNewMailBox(new MailBoxWithSpam(user1));
        mailService.addNewMailBox(new MailBoxWithSpam(user2));
        mailService.createMailStore();

        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("IPO", "Hola Mon!", user2));
        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        System.out.print(mailService.searchMailBoxUser(user1.getUsername()).getMailstore().getMessagesList());
    }
}
