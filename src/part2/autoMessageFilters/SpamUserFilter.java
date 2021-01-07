package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class to filter users with username that contains "spam"
 * @author Usama Benabdelkrim Zakan
 */
public class SpamUserFilter implements Filter{
    /**
     * Method to update the filter
     * @param messagesList MessageList to update
     * @return messagesList updated
     */
    @Override
    public List<Message> update(List<Message> messagesList) {
        Predicate<Message> predicate= t -> t.getSender().getUsername().contains("spam");
        return messagesList.stream().filter(predicate).collect(Collectors.toList());
    }
}