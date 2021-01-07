package part2.autoMessageFilters;

import javax.crypto.NoSuchPaddingException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailBoxInterface;
import part1.mailServicePack.MailService;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * AutomaticMessagesFilterDemo to test AutomaticMessageFilter
 * @author Usama Benabdelkrim Zakan
 */
public class AutomaticMessagesFiltersDemo {
    /**
     * Main Method
     * @param args args
     * @throws NoSuchPaddingException NoSuchPaddingException
     * @throws NoSuchAlgorithmException NoSuchAlgorithmException
     */
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException {
        //Initialize mail system
        MailService mailService=new MailService();
        mailService.deSerialize();
        //Create at least 3 users
        User user1=new User("Usama", "usi1spam",2000);
        User user2=new User("Usama", "usi2",2000);
        User user3=new User("Adel", "adl3",1983);
        //Add mailboxes
        mailService.addNewMailBox(new MailBoxWithSpam(user1));
        mailService.addNewMailBox(new MailBoxWithSpam(user2));
        mailService.addNewMailBox(new MailBoxWithSpam(user3));
        mailService.createMailStore();
        //Send mails
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Examen AC", "Hola usi2.", user2));

        mailService.searchMailBoxUser(user2.getUsername()).sendMail(new Message("Examen AC", "Hola usi1. ", user1));

        mailService.searchMailBoxUser(user3.getUsername()).sendMail(new Message("Entrenament", "Hola usi1. ", user1));
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Entrenament", "Hola adl3. ", user3));

        mailService.searchMailBoxUser(user3.getUsername()).sendMail(new Message("Entrenament", "Hola usi2. ", user2));
        mailService.searchMailBoxUser(user2.getUsername()).sendMail(new Message("Entrenament", "Hola adl3. Doncs molt si! Aquests dies vindre sempre.????????????????????????????????????????????????????????????????????????", user2, user3));

        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        mailService.searchMailBoxUser(user2.getUsername()).updateMailStore(user2.getUsername());
        mailService.searchMailBoxUser(user3.getUsername()).updateMailStore(user3.getUsername());

        System.out.println(mailService.searchMailBoxUser(user1.getUsername()).getMailstore());
        System.out.println(mailService.searchMailBoxUser(user3.getUsername()).getMailstore());

        System.out.println("Users with messages in spam list");
        List<User> userList=new ArrayList<>();
        userList=Stream.of(userList, getSpamListSenders(mailService.searchMailBoxUser(user1.getUsername()))).flatMap(Collection::stream).collect(Collectors.toList());
        userList=Stream.of(userList, getSpamListSenders(mailService.searchMailBoxUser(user2.getUsername()))).flatMap(Collection::stream).collect(Collectors.toList());
        userList=Stream.of(userList, getSpamListSenders(mailService.searchMailBoxUser(user3.getUsername()))).flatMap(Collection::stream).collect(Collectors.toList());

        userList=userList.stream().distinct().collect(Collectors.toList());
        System.out.print(userList);
    }

    /**
     * Get users with messages in spamlist
     * @param mailBoxP2
     * @return
     */
    public static List<User> getSpamListSenders(MailBoxInterface mailBoxP2){
        return mailBoxP2.getSpamList().stream().map(Message::getSender).collect(Collectors.toList());
    }
}
