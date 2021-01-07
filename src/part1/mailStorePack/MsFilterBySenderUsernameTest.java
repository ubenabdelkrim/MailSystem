package part1.mailStorePack;

import junit.framework.*;

import org.junit.Test;
import part1.mailServiceElements.Message;
import part1.mailServiceElements.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test filterBySenderUsername test
 */
public class MsFilterBySenderUsernameTest extends TestCase{
    MailStoreOnFile mailStoreOnFile=new MailStoreOnFile();
    User user1=new User("Usama", "usi1",2000);
    User user2=new User("Adel", "adl1",2000);
    List<Message> messages=new ArrayList<>();
    Message message=new Message("Examen", "Hola", user1, user2);
    Message message2=new Message("Examen", "Adeu", user2, user1);
    Message message3;

    /**
     * Methdo to test
     */
    @Test
    public void testFilterBySender(){
        mailStoreOnFile.updateMail(user1.getUsername());
        mailStoreOnFile.updateMail(user2.getUsername());
        messages.add(message);
        messages.add(message2);
        message3=mailStoreOnFile.filterPerReceiver(user1.getUsername()).get(0);
        assertEquals(message2.getSender().getUsername(), message3.getSender().getUsername());
        assertEquals(message2.getReceiver().getUsername(), message3.getReceiver().getUsername());
        assertEquals(message2.getBody(), message3.getBody());
        assertEquals(message2.getSubject(), message3.getSubject());
        assertEquals(message2.getDate(), message3.getDate());
    }
}
