package part2.autoMessageFilters;

import part1.mailServiceElements.Message;
import java.util.List;

/**
 * Filter Interface
 * @author Usama Benabdelkrim Zakan
 */
public interface Filter {
	List<Message> update(List<Message> messagesList);
}
