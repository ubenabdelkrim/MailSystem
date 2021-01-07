package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;
import part1.mailServicePack.MailBox;
import part1.mailServicePack.MailBoxInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * MailBox class with spamlist that implements MailBox Interface and Subject from Observer pattern
 * @author Usama Benabdelkrim Zakan
 */
public class MailBoxWithSpam extends MailBox implements MailBoxInterface, Subject {
    /**
     * Spam List
     */
    List<Message> spamList;
    /**
     * Filter Observers
     */
    List<Filter> filterObservers;
    /**
     * MailBoxWithSpam Constructor
     * @param user mailbox user
     */
    public MailBoxWithSpam(User user) {
        super(user);
        spamList=new ArrayList<>();
        filterObservers =new ArrayList<>();
        filterObservers.add(new TooLongFilter());
        filterObservers.add(new SpamUserFilter());
    }
    /**
     * Spam List getter
     * @return spamList
     */
    public List<Message> getSpamList() {
        return spamList;
    }
    /**
     * SpamList Setter
     * @param spamList SpamList
     */
    public void setSpamList(List<Message> spamList) {
        this.spamList = spamList;
    }
    /**
     * Method to add filter osberver
     * @param filter Filter
     */
    @Override
    public void addFilterObserver(Filter filter) {
        filterObservers.add(filter);
    }
    /**
     * Method to remove filter osberver
     * @param filter Filter
     */
    @Override
    public void removeFilterObserver(Filter filter) {
        filterObservers.remove(filter);
    }
    /**
     * Method to notify all observers
     * @param messageList Messages List
     */
    @Override
    public void notifyObservers(List<Message> messageList) {
        for(Filter f: filterObservers){
            spamList= Stream.of(spamList, f.update(messageList)).flatMap(Collection::stream).collect(Collectors.toList());
            messageList.removeAll(spamList);
        }
    }
    /**
     * Method to update the spamlist with filter
     * @param filter Filter to update
     * @return spamList Updated SpamList
     */
    @Override
    public List<Message> getUpdate(Filter filter) {
        return this.spamList;
    }
    /**
     * Method to update MailStore
     * @param username Username to filter the mailbox
     */
    public void updateMailStore(String username) {
        mailstore.updateMail(username);
        notifyObservers(getMailstore().filterPerSender(username));
    }
}
