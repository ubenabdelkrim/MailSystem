package part3.redisMailStore;

import java.lang.reflect.InvocationTargetException;
import part1.mailStorePack.MailStoreOnFile;
import part2.encodingMessages.EncryptionDecorator;
import part2.encodingMessages.MailStoreInterface;
import part2.encodingMessages.ReversesStringDecorator;

/**
 * Mail Service Factory
 */
public class MailStoreFactory {
    /**
     * MailStore
     */
    private String mailstore;
    /**
     * Mail Service Factory Constructor
     * @param mailstore MailStore
     */
    public MailStoreFactory(String mailstore){
        this.mailstore=mailstore;
    }
    /**
     * MailStore getter
     * @return String
     */
    public String getMailstore() {
        return mailstore;
    }

    /**
     * MailStore getter
     * @param mailstore MailStore
     */
    public void setMailstore(String mailstore) {
        this.mailstore = mailstore;
    }
    /**
     * MailStore getter
     * @return MailStoreInterface
     */
    public MailStoreInterface getMailStore() {
        try {
            try {
                if(mailstore.equals(EncryptionDecorator.class.getName())){
                    return new ReversesStringDecorator(new EncryptionDecorator(new MailStoreOnFile()));
                }
                return (MailStoreInterface) Class.forName(mailstore).getConstructor().newInstance();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
