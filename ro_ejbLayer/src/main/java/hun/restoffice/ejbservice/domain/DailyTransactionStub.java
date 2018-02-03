/**
 * 
 */
package hun.restoffice.ejbservice.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class DailyTransactionStub implements Serializable {

	private static final long serialVersionUID = 1L;

	private final BigDecimal cash;
	private final BigDecimal card;
	private final BigDecimal pos;
	private final int rowId;


	/**
	 * @param cash
	 * @param card
	 * @param pos
	 * @param rowId
	 */
	public DailyTransactionStub(BigDecimal cash, BigDecimal card, BigDecimal pos, int rowId) {
		super();
		this.cash = cash;
		this.card = card;
		this.pos = pos;
		this.rowId = rowId;
	}



	/**
	 * @return the cash
	 */
	public BigDecimal getCash() {
		return cash;
	}

	/**
	 * @return the sum
	 */
	public BigDecimal getCard() {
		return card;
	}

	/**
	 * @return the pos
	 */
	public BigDecimal getPos() {
		return pos;
	}

	/**
	 * @return the rowId
	 */
	public int getRowId() {
		return rowId;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("DailyTransactionStub [cash=%s, card=%s, pos=%s, rowId=%s]", cash, card, pos, rowId);
	}

	
}
