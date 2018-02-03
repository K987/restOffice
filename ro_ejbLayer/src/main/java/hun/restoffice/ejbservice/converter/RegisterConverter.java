/**
 * 
 */
package hun.restoffice.ejbservice.converter;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import hun.restoffice.ejbservice.domain.RegisterCloseStub;
import hun.restoffice.ejbservice.domain.RegisterStub;
import hun.restoffice.persistence.entity.dailyTransaction.Register;
import hun.restoffice.persistence.entity.dailyTransaction.RegisterClose;

/**
 * 
 *
 * @author kalmankostenszky
 */
@Stateless
public class RegisterConverter implements RegisterConverterLocal {

	/*
	 * (non-Javadoc)
	 * 
	 * @see hun.restoffice.ejbservice.converter.RegisterConverterLocal#to(java.util.List)
	 */
	@Override
	public List<RegisterCloseStub> to(List<RegisterClose> registers) {
		List<RegisterCloseStub> rtrn = new ArrayList<>();
		for (RegisterClose registerClose : registers) {
			rtrn.add(to(registerClose));
		}
		return rtrn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * hun.restoffice.ejbservice.converter.RegisterConverterLocal#to(hun.restoffice.remoteClient.domain.RegisterStub)
	 */
	@Override
	public RegisterCloseStub to(RegisterClose registerClose) {
//		RegisterCloseStub rtrn = new RegisterCloseStub(register.getRegisterCloseAmt(), register.getRegisterCloseDate(), register.getRegister().getRegisterId(),
//				register.getId().getRegisterCloseNo(), register.getRegister().getRegisterType().ordinal());
//		return rtrn;
		RegisterStub regStub = this.to(registerClose.getRegister());
		return new RegisterCloseStub(regStub, registerClose.getRegisterCloseAmt(), registerClose.getRegisterCloseDate(), registerClose.getId().getRegisterCloseNo());
	}

	/* (non-Javadoc)
	 * @see hun.restoffice.ejbservice.converter.RegisterConverterLocal#to(hun.restoffice.persistence.entity.dailyTransaction.Register)
	 */
	@Override
	public RegisterStub to(Register register) {
		return new RegisterStub(register.getRegisterId(), register.getRegisterType().ordinal());
	}

}
