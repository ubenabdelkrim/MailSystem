package part1.cliPackage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailBoxInterface;
import part1.mailServicePack.MailService;
import part1.mailServicePack.MailServiceInterface;

import javax.crypto.NoSuchPaddingException;

/**
 * This class consists exclusively in the Command Line Interface.
 *
 * @author Usama Benabdelkrim Zakan
 */
public class CLI implements CLInterface{
    /**
     * User mailbox that login.
     */
    MailBoxInterface mailBox;
    /**
     * MailService
     */
    MailServiceInterface mailService;
    /**
     * CLI Constructor
     * @param mailStoreInterface Mailstore to initialize the Mail system
     */
    public CLI(MailServiceInterface mailStoreInterface) {
        mailService=mailStoreInterface;
    }
    /**
     * MailBox getter
     * @return MailBoxInterface
     */
    public MailBoxInterface getMailBox() {
        return mailBox;
    }
    /**
     * MailBox setter
     * @param mailBox User mailbox
     */
    public void setMailBox(MailBoxInterface mailBox) {
        this.mailBox = mailBox;
    }
    /**
     * MailService getter
     * @return MailServiceInterface
     */
    public MailServiceInterface getMailService() {
        return mailService;
    }
    /**
     * MailService setter
     * @param mailService MailService
     */
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
    /**
     * Method to create the user object
     * @param user User to create
     */
    public void createUser(User user) {
        try {
            mailService.createUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to send a mail
     * @param message message to send
     */
    public void sendMail(Message message) {
        mailBox.sendMail(message);
    }
    /**
     * Method to login with username
     * @param username to identificate the user
     * @return MailBoxInterface
     */
    @Override
    public MailBoxInterface logas(String username) {
        try {
            mailService.deSerialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mailBox=mailService.logIn(username);
        return mailBox;
    }
    /**
     * Method to filter By Date
     * @param date to filter
     * @return filtered list
     */
    public List<Message> filterPerDate(String date){
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerDate(date);
    }
    /**
     * Method to filter By Name
     * @param name to filter
     * @return filtered list
     */
    public List<Message> filterPerName(String name) {
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerSenderName(name);
    }
    /**
     * Method to filter By Username
     * @param username to filter
     * @return filtered list
     */
    public List<Message> filterPerUsername(String username) {
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerSender(username);
    }
    /**
     * Method to filter By Word
     * @param word to filter
     * @return filtered list
     */
    public List<Message> filterPerWord(String word) {
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerWord(word);
    }
    /**
     * Method to filter messages with less than n words indicated with the parameter
     * @param nWord to filter
     * @return filtered list
     */
    public List<Message> filterPerLessThanNWords(int nWord) {
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerLessThanNWords(nWord);
    }
    /**
     * Method to filter messages by word and n words
     * @param word to filter
     * @param nWords to filter
     * @return filtered list
     */
    public List<Message> filterPerWordAndNWord(String word, int nWords) {
        return mailService.filterByWordAndNWord(word, nWords, mailBox);
    }
    /**
     * Method to filter messages by subject
     * @param subject to filter
     * @return filtered list
     */
    public List<Message> filterPerSubject(String subject) {
        try {
            mailBox.updateMailStore(mailBox.getUser().getUsername());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return mailBox.getMailstore().filterPerSubject(subject);
    }
    /**
     * Method to sort messages by date
     * @return sorted list messages
     */
    public List<Message> sortPerDate(){
        mailBox.getMailstore().sortPerDate();
        return mailBox.getMailstore().getMessagesList();
    }
    /**
     * Method to sort messages by sender
     * @return sorted list messages
     */
    public List<Message> sortPerSender(){
        return mailBox.getMailstore().sortPerSender();
    }
    /**
     * Method to sort messages by receiver
     * @return sorted list messages
     */
    public List<Message> sortPerReceiver(){
        return mailBox.getMailstore().sortPerReceiver();
    }
}
