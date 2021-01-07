package part2.encodingMessages;

/**
 * Class to reverses a String
 * Extends ProcessingStrategy
 */
public class ReversesStringDecorator extends ProcessingStrategy{
	/**
	 * ReversesStringDecorator Constructor
	 * @param mailStore mailStore to process
	 */
	public ReversesStringDecorator(MailStoreInterface mailStore){
		super(mailStore);
	}

	/**
	 * Method to reverse the body
	 * @param string body to reverse
	 * @return string reversed
	 */
	public String code(String string) {
		return new StringBuilder(string).reverse().toString();
	}

	/**
	 * Method to reverse the body
	 * @param string body to reverse
	 * @return string reversed
	 */
	public String decode(String string){
		return code(string);
	}

}
