package part3.redisMailStore;

import part1.mailServiceElements.Message;
import part1.mailStorePack.MailStoreOnFile;
import part2.encodingMessages.MailStoreInterface;
import java.util.Iterator;
import java.util.List;

public class RedisMailStoreAdapter implements MailStoreInterface {

    static JedisClient jedisClient;
    static MailStoreInterface mailStoreInterface;

    private static RedisMailStoreAdapter instance=new RedisMailStoreAdapter();

    private RedisMailStoreAdapter() {

    }

    public static RedisMailStoreAdapter getInstance(){
        jedisClient=new JedisClient();
        jedisClient.connect();
        return instance;
    }

    public void setMailStore(MailStoreInterface mailStoreInterface){
        RedisMailStoreAdapter.mailStoreInterface =mailStoreInterface;
    }

    @Override
    public void sendMail(Message message) {
        jedisClient.lpush(message.getSender().getUsername(), message.toStringToFile());
    }

    @Override
    public void updateMail(String username) {
        mailStoreInterface.setMessagesList(MailStoreOnFile.parseData(jedisClient.lrange(username)));
    }

    @Override
    public List<Message> filterPerSender(String username) {
        return mailStoreInterface.filterPerSender(username);
    }

    @Override
    public List<Message> sortPerDate() {
        return mailStoreInterface.sortPerDate();
    }

    @Override
    public Iterator<Message> getIterator() {
        return mailStoreInterface.getIterator();
    }

    @Override
    public List<Message> sortPerSender() {
        return mailStoreInterface.sortPerSender();
    }

    @Override
    public List<Message> sortPerReceiver() {
        return mailStoreInterface.sortPerReceiver();
    }

    @Override
    public List<Message> getMessagesList() {
        return mailStoreInterface.getMessagesList();
    }

    @Override
    public void setMessagesList(List<Message> messagesList) {
        mailStoreInterface.setMessagesList(messagesList);
    }

    @Override
    public List<Message> filterPerDate(String date) {
        return mailStoreInterface.filterPerDate(date);
    }

    @Override
    public List<Message> filterPerSenderName(String name) {
        return mailStoreInterface.filterPerSenderName(name);
    }

    @Override
    public List<Message> filterPerWord(String word) {
        return mailStoreInterface.filterPerWord(word);
    }

    @Override
    public List<Message> filterPerLessThanNWords(int nWord) {
        return mailStoreInterface.filterPerLessThanNWords(nWord);
    }

    @Override
    public List<Message> filterPerSubject(String subject) {
        return mailStoreInterface.filterPerSubject(subject);
    }

    @Override
    public List<Message> filterPerReceiver(String username) {
        return mailStoreInterface.filterPerReceiver(username);
    }

    @Override
    public int getNumberWords() {
        return mailStoreInterface.getNumberWords();
    }

    @Override
    public List<Message> filterByReceiverBirthYear(int birthYear) {
        return mailStoreInterface.filterByReceiverBirthYear(birthYear);
    }

    @Override
    public MailStoreInterface getWrapper() {
        return null;
    }

    @Override
    public void listByDate() {
        mailStoreInterface.listByDate();
    }

    @Override
    public void listBySenderUsername() {
        mailStoreInterface.listBySenderUsername();
    }

    @Override
    public void listByReceiverUsername() {
        mailStoreInterface.listByReceiverUsername();
    }
}
