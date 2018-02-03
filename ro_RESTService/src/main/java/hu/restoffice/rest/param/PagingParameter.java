/**
 * 
 */
package hu.restoffice.rest.param;

import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;


/**
 * @author kalmankostenszky
 *
 */
public class PagingParameter {
	
	private static final Logger log = Logger.getLogger(PagingParameter.class);

	@QueryParam("offset")
	String offset;
	
	@QueryParam("size")
	String size;

	/**
	 * @return the offset
	 */
	public String getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(String offset) {
		this.offset = offset;
		log.info("setOffset invoked");
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
		log.info("setSize invoked");
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagingParameter [offset=" + offset + ", size=" + size + "]";
	}
	
	
	
	
	
}
