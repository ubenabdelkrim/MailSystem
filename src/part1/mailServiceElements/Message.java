package part1.mailServiceElements;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is the class that contains the different information (sender, receiver, subject, body, date, priority) of a message.
 * @author Usama Benabdelkrim Zakan
 */
public class Message implements Serializable{
    /**
     * Serializable class to write and read the obejct from file
     */
    private static final long serialVersionUID = 1L;

    private String subject;
    private String body;
    private User sender;
    private User receiver;
    private Date date;

    /**
     * Message Constructor
     * @param subject Message subject
     * @param body Message body
     * @param sender Message sender
     * @param receiver Message receiver
     */
    public Message(String subject, String body, User sender, User receiver) {
        this.subject=subject;
        this.body=body;
        this.sender=sender;
        this.receiver=receiver;
        this.date=new Date(System.currentTimeMillis());
    }

    /**
     * Message Constructor withour parameters
     * @param examen_ac
     * @param s
     * @param user2
     */
    public Message(String subject, String body, User receiver) {
        this.subject=subject;
        this.body=body;
        this.receiver=receiver;
        this.date=new Date(System.currentTimeMillis());
    }

    public Message() {

    }

    /**
     * Subject getter string
     * @return subject Message subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Subject setter string
     * @param subject Message subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * Sender getter string
     * @return sender Message sender
     */
    public User getSender() {
        return sender;
    }
    /**
     * Sender setter string
     * @param sender Message sender
     */
    public void setSender(User sender) {
        this.sender = sender;
    }
    /**
     * Body getter string
     * @return body Message body
     */
    public String getBody() {
        return body;
    }
    /**
     * Subject setter string
     * @param body Message body
     */
    public void setBody(String body) {
        this.body = body;
    }
    /**
     * Receiver getter string
     * @return receiver Message receiver
     */
    public User getReceiver() {
        return receiver;
    }
    /**
     * Receiver setter string
     * @param receiver Message receiver
     */
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    /**
     * Date getter string
     * @return date Message date
     */
    public String getDate() {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yy");
        return df.format(date);
    }
    /**
     * Date setter string
     * @param date Message date
     * @throws ParseException ParseException for date
     */
    public void setDate(String date) throws ParseException {
        DateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        this.date=df.parse(date);
    }
    /**
     * toString method
     * @return string Message
     */
    public String toString() {
        return "--------------------------------------------\n"+
                "Sender username: "+sender.getUsername()+"\n"+
                "Receiver username: "+receiver.getUsername()+"\n"+
                "Subject: "+subject+"\n"+
                "Body: "+body+"\n"+
                "Date: "+getDate()+
                "\n--------------------------------------------\n";
    }
    /**
     * Method to write messages on MailStoreOnFile
     * @return string Message
     */
    public String toStringToFile(){
        return subject+","+body+","
                +sender.getName()+","+sender.getUsername()+","+sender.getBirthYear()
                +","+receiver.getName()+","+receiver.getUsername()+","+receiver.getBirthYear()
                +","+getDate()+"\n";
    }
    /**
     * Method to get number of words of the messages
     * @return string Message
     */
    public int getNumberWords(){
        return body.split("\\s+|\n").length;
    }
    /**
     * Method to get number of chars of the messages
     * @return string Message
     */
    public int getNMessagesChars(){
        return this.body.length();
    }
}
