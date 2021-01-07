package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;

/**
 * Subject interface from Observer pattern
 */
public interface Subject {
	void addFilterObserver(Filter filter);
	void removeFilterObserver(Filter filter);
	void notifyObservers(List<Message> messageList);
	List<Message> getUpdate(Filter filter);
}
