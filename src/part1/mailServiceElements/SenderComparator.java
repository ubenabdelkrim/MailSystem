package part1.mailServiceElements;

import java.util.Comparator;

/**
 * SenderComparator implements Comparator<Message>
 * @author Usama Benabdelkrim Zakan
 */
public class SenderComparator implements Comparator<Message>{

    @Override
    public int compare(Message o1, Message o2) {
        return o1.getSender().getUsername().compareTo(o2.getSender().getUsername());
    }
}
