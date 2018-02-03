/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.List;

import javax.ejb.Local;

import hun.restoffice.ejbservice.domain.RegisterCloseStub;
import hun.restoffice.ejbservice.domain.RegisterStub;
import hun.restoffice.persistence.entity.dailyTransaction.Register;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterClose;

/**
 *  
 *
 * @author kalmankostenszky
 */
@Local
public interface RegisterConverterLocal {

	/**
	 * From list of RegisterCloses to list of RegisterCloseStubs
	 * @param registers
	 * @return
	 */
	List<RegisterCloseStub> to(List<RegisterClose> registers);

	/**
	 * From RegisterClose to RegisterCloseStub
	 * @param registerClose
	 * @return
	 */
	RegisterCloseStub to(RegisterClose registerClose);
	
	/**
	 * From Register to RegisterStub
	 * @param register
	 * @return
	 */
	RegisterStub to(Register register);


}
