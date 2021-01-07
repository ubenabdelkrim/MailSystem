package part3.redisMailStore;

import part1.mailServicePack.MailService;
import part1.mailServicePack.MailServiceInterface;

/**
 * MailServiceFactory Class
 * Extends MailServide and implements MailService Interface
 */
public class MailServiceFactory extends MailService implements MailServiceInterface {

    MailStoreFactory mailStoreFactory;
    /**
     * Mail Service Constructor
     * Initialize Mail Box list.
     *
     * @param mailStoreFactory MailStore Factory instance
     */
    public MailServiceFactory(MailStoreFactory mailStoreFactory) {
        super();
        this.mailStoreFactory=mailStoreFactory;
    }

    /**
     * Method to create MailStore
     * Initialize Mail Box list.
     */
    public void createMailStore(){
        super.createMailStore(mailStoreFactory.getMailStore());
    }
}
