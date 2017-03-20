/**
 * 
 */
package hun.restoffice.weblayer.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.facade.FinanceFacadeLocal;
import hun.restoffice.remoteClient.domain.IncomeStub;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 *  
 *
 * @author kalmankostenszky
 */
@WebServlet("/IncomeList")
public class IncomeListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(IncomeListServlet.class);

	@EJB
	private FinanceFacadeLocal fFacade;
	
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("IncomeList#doGet() invoked");
		List<IncomeStub> incomes = getIncomes();

		request.setAttribute("incomes", incomes);

		this.forward(request, response);
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("IncomeList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @return
	 */
	private List<IncomeStub> getIncomes() {
		LOG.info("IncomeList#getIncomes invoked");
		List<IncomeStub> stubs = new ArrayList<>();
		try {
			stubs = this.fFacade.getAllIncomes();
			Collections.sort(stubs, new Comparator<IncomeStub>() {

				@Override
				public int compare(IncomeStub o1, IncomeStub o2) {
					int rtrn = o1.getRegistered().getTime().compareTo(o2.getRegistered().getTime());
					return rtrn;
				}
			});

		} catch (FacadeException e) {
			LOG.error(e);
		}
		return stubs;
	}


}
