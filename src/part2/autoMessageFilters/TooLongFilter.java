package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class to filter too long messages with observer pattern
 */
public class TooLongFilter implements Filter{
    @Override
    public List<Message> update(List<Message> messagesList) {
        Predicate<Message> predicate= t -> t.getNMessagesChars()>20;
        return messagesList.stream().filter(predicate).collect(Collectors.toList());
    }
}