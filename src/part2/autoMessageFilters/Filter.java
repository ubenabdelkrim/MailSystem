package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;

/**
 * Filter Interface
 * @author Usama Benabdelkrim Zakan
 */
public interface Filter {
	/**
	 * Update method
	 * @param messagesList messages to update
	 * @return updated list
	 */
	List<Message> update(List<Message> messagesList);
}
