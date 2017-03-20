/**
 * 
 */
package hun.restoffice.client.controller;

/**
 * Controllers implements this interface
 *
 * @author kalmankostenszky
 */
public interface WizardElement {

	/**
	 * called when  next button pressed on wizard view
	 * 
	 * @return true: if forward to next step allowed
	 */
	boolean onNext();

	/**
	 * called when  previous button pressed on wizard view
	 * 
	 * @return true: if backward to previous step allowed
	 */
	boolean onPrevious();

	/**
	 * called when  canceled button pressed on wizard view
	 */
	void onCancel();

	/**
	 * called when send button pressed on wizard view
	 */
	void onSend();

	// void onLoad(LocalDate date);
}
