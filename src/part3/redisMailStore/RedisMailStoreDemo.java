package part3.redisMailStore;

import javax.crypto.NoSuchPaddingException;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailBoxInterface;
import part1.mailServicePack.MailService;
import part1.mailStorePack.MailStoreInMemory;
import part1.mailStorePack.MailStoreOnFile;
import part2.autoMessageFilters.MailBoxWithSpam;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class RedisMailStoreDemo {
    static Scanner sr=new Scanner(System.in);
    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException{
        RedisMailStoreAdapter mailStore=RedisMailStoreAdapter.getInstance();
        mailStore.setMailStore(new MailStoreInMemory());

        //Initialize mail system
        MailService mailService=new MailService();

        //Create at least 3 users
        User user1=new User("Usama", "usi1",2000);
        User user2=new User("Usama", "usi2",2000);
        User user3=new User("Adel", "adl3",1983);
        //Create mailboxes
        MailBoxInterface mBox1=new MailBoxWithSpam(user1);
        MailBoxInterface mBox2=new MailBoxWithSpam(user2);
        MailBoxInterface mBox3=new MailBoxWithSpam(user3);
        //Add mailboxes
        mailService.addNewMailBox(mBox1);
        mailService.addNewMailBox(mBox2);
        mailService.addNewMailBox(mBox3);
        mailService.createMailStore(mailStore);
        //Send mails
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Examen AC", "Hola usi2. com t'ha anat l'examen?",user2));

        mailService.searchMailBoxUser(user2.getUsername()).sendMail(new Message("Examen AC", "Hola usi1. Doncs molt be. I a tu?",user1));
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Examen AC", "Hola usi2. Doncs tambe. Ara estic amb la segona part de la practica de TAP.", user1, user2));


        mailService.searchMailBoxUser(user3.getUsername()).sendMail(new Message("Entrenament", "Hola usi1. vindras a entrenar avui?", user1));
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Entrenament", "Hola adl3. avui no. He de descansar per a la competicio de dema.", user1, user3));

        mailService.searchMailBoxUser(user3.getUsername()).sendMail(new Message("Entrenament", "Hola usi2. vindras a entrenar avui?", user2));
        mailService.searchMailBoxUser(user2.getUsername()).sendMail(new Message("Entrenament", "Hola adl3. Doncs molt si! Aquests dies vindre sempre.", user2, user3));
        //4.      Get one of the mailboxes and update its mail
        System.out.println("Update mail");
        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());
        //5.	  List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities of the mailbox!
        System.out.println("List by Date");
        mailService.searchMailBoxUser(user1.getUsername()).getMailstore().listByDate();
        //6.      Now list the messages by sender username using the mailbox feature.
        System.out.println("List by Sender Username");
        mailService.searchMailBoxUser(user1.getUsername()).getMailstore().listBySenderUsername();
        //7. 	  Filter the messages by key word and sender username
        System.out.println("Filter messages with key word and sender");
        System.out.println("Please, introduce the key word and username of sender in order:");
        System.out.println(mailService.filterByWordAndSender(sr.nextLine(), sr.nextLine()));
        System.out.println("--------------------------------------------------------------------------");
        //8.      Use the mail system object to retrieve all messages and print them.
        System.out.println("Retrieve messages and print them: ");
        mailService.deSerialize();
        System.out.println(mailService.toString());
        System.out.println("--------------------------------------------------------------------------");
        //9.      Filter messages globally by single word subject and born after year
        System.out.println("Filter messages by subject and sender birthYear");
        System.out.println("Please, introduce a subject and sender birth year in order:");
        System.out.println(mailService.filterBySubjectAndSenderBirthYear(sr.nextLine(), sr.nextInt()));
        System.out.println("--------------------------------------------------------------------------");
        //10.    Get the count of messages in the system and print it.
        System.out.println("Number of messages in system: "+mailService.getNMessages());
        System.out.println("--------------------------------------------------------------------------");
        //11.    Get the average number of messages received per user and print it.
        System.out.println("Average of messages receiver per user: "+mailService.getAverageMessagesPerUser());
        System.out.println("--------------------------------------------------------------------------");
        //12.    Group the messages per subject in a Map<String, List<Message>>and print it.
        System.out.println("Filter by subject");
        System.out.println("Please, introduce the subject");
        sr.next();
        System.out.println(mailService.filterPerSubject(sr.nextLine()));
        System.out.println("--------------------------------------------------------------------------");
        //13.    Count the words of all messages sent by users with a certain real name.
        System.out.println("Filter by sender name and count the number of words of all messages");
        System.out.println("Please, introduce the name:");
        System.out.println(mailService.getNWordsOfUserByName(sr.next()));
        System.out.println("--------------------------------------------------------------------------");
        //14.    Use the name that you used on two users. Print the result.
        System.out.println("Filter by sender name and count the number of words of all messages");
        System.out.println("Please, introduce the name:");
        System.out.println(mailService.getNWordsOfUserByName(sr.next()));
        System.out.println("--------------------------------------------------------------------------");
        //15.    Print the messages received by users born before year.
        System.out.println("Filter messages received by users born before year");
        System.out.println("Please, introduce the birth year in order:");
        System.out.println(mailService.filterPerReceiverBirthYear(sr.nextInt()));
        System.out.println("--------------------------------------------------------------------------");


        //16.    Now change the mail store to the file implementation.
        for(MailBoxInterface mailBox : mailService.getMailBoxList()){
            mailBox.setMailstore(new MailStoreOnFile());
        }
        System.out.println("--------------------------------------------------------------------------");
        mailService.searchMailBoxUser(user1.getUsername()).sendMail(new Message("Cotxe", "Hola Adel. El mecànic et va arreglar el cotxe?", user2));
        mailService.searchMailBoxUser(user3.getUsername()).sendMail(new Message("Cotxe", "Hola Usama. Encara no. Diuen que estarà arreglat demà mateix.", user2));
        mailService.searchMailBoxUser(user1.getUsername()).updateMailStore(user1.getUsername());

        //5.	  List the mailbox messages in the console. (Sorted by newer first.) Use the iterable capabilities of the mailbox!
        System.out.println("List by Date");
        mailService.searchMailBoxUser(user1.getUsername()).getMailstore().listByDate();
        sr.nextLine();
        //7. 	  Filter the messages by key word and sender username
        System.out.println("Filter messages with key word and sender");
        System.out.println("Please, introduce the key word and username of sender in order:");
        System.out.println(mailService.filterByWordAndSender(sr.nextLine(), sr.nextLine()));
        System.out.println("--------------------------------------------------------------------------");
    }
}
