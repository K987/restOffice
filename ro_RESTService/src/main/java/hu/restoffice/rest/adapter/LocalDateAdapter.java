/**
 * 
 */
package hu.restoffice.rest.adapter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author kalmankostenszky
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public LocalDate unmarshal(String v) throws Exception {

		return LocalDate.parse(v);
	}

	/* (non-Javadoc)
	 * @see javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}
