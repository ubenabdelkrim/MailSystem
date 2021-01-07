package part2.encodingMessages;

import part1.mailServiceElements.Message;
import part1.mailStorePack.MailStore;
import java.util.Iterator;
import java.util.List;

/**
 * MailStore Decorator implementing MailStore Interface
 */
public class MailStoreDecorator implements MailStoreInterface {
    /**
     * MailStore wrapper
     */
    protected MailStoreInterface wrapper;

    /**
     * MailStoreDecorator Constructor
     * @param mailStore mailStore to wrapp
     */
    public MailStoreDecorator(MailStoreInterface mailStore){
        this.wrapper=mailStore;
    }

    @Override
    public void sendMail(Message message) {
        wrapper.sendMail(message);
    }

    @Override
    public void updateMail(String username) {
        wrapper.updateMail(username);
    }

    @Override
    public List<Message> filterPerSender(String username) {
       return wrapper.filterPerSender(username);
    }

    @Override
    public List<Message> sortPerDate() {
        return wrapper.sortPerDate();
    }

    @Override
    public Iterator<Message> getIterator() {
        return wrapper.getIterator();
    }

    @Override
    public List<Message> sortPerSender() {
        return wrapper.sortPerSender();
    }

    @Override
    public List<Message> sortPerReceiver() {
        return wrapper.sortPerReceiver();
    }

    @Override
    public List<Message> getMessagesList() {
        return wrapper.getMessagesList();
    }

    @Override
    public void setMessagesList(List<Message> messagesList) {
        wrapper.setMessagesList(messagesList);
    }

    @Override
    public List<Message> filterPerDate(String date) {
        return wrapper.filterPerDate(date);
    }

    @Override
    public List<Message> filterPerSenderName(String name) {
        return wrapper.filterPerSender(name);
    }

    @Override
    public List<Message> filterPerWord(String word) {
        return wrapper.filterPerWord(word);
    }

    @Override
    public List<Message> filterPerLessThanNWords(int nWord) {
        return wrapper.filterPerLessThanNWords(nWord);
    }

    @Override
    public List<Message> filterPerSubject(String subject) {
        return wrapper.filterPerSubject(subject);
    }

    @Override
    public List<Message> filterPerReceiver(String username) {
        return wrapper.filterPerReceiver(username);
    }

    @Override
    public int getNumberWords() {
        return wrapper.getNumberWords();
    }

    @Override
    public List<Message> filterByReceiverBirthYear(int birthYear) {
        return wrapper.filterByReceiverBirthYear(birthYear);
    }

    public MailStoreInterface getWrapper() {
        return wrapper;
    }

    @Override
    public void listByDate() {
        wrapper.listByDate();
    }

    @Override
    public void listBySenderUsername() {
        wrapper.listBySenderUsername();
    }

    @Override
    public void listByReceiverUsername() {
        wrapper.listByReceiverUsername();
    }

    public void setWrapper(MailStore wrapper) {
        this.wrapper = wrapper;
    }
}
