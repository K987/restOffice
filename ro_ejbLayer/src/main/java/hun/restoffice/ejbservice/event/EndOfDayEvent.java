/**
 * 
 */
package hun.restoffice.ejbservice.event;

import java.io.Serializable;
import java.util.List;

import hun.restoffice.ejbservice.domain.IncomeStub;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class EndOfDayEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1071882796382762257L;

	public List<IncomeStub> incomes;;

	public EndOfDayEvent() {

	}

	public EndOfDayEvent(List<IncomeStub> incomes) {
		this.incomes = incomes;
	}

	
}
