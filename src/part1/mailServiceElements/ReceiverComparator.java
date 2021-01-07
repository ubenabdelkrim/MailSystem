package part1.mailServiceElements;

import java.util.Comparator;

/**
 * ReceiverComparator implements Comparator<Message>
 * @author Usama Benabdelkrim Zakan
 */
public class ReceiverComparator implements Comparator<Message>{

    @Override
    public int compare(Message o1, Message o2) {
        return o1.getReceiver().getUsername().compareTo(o2.getReceiver().getUsername());
    }

}
