package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;

/**
 * Subject interface from Observer pattern
 */
public interface Subject {
	/**
	 * Add filter
	 * @param filter filter
	 */
	void addFilterObserver(Filter filter);

	/**
	 * remove filter
	 * @param filter filter
	 */
	void removeFilterObserver(Filter filter);

	/**
	 * method to notify observers
	 * @param messageList
	 */
	void notifyObservers(List<Message> messageList);

	/**
	 * Method to get updated list
	 * @param filter
	 * @return
	 */
	List<Message> getUpdate(Filter filter);
}
