/**
 * 
 */
package hun.restoffice.weblayer.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.facade.FinanceFacadeLocal;
import hun.restoffice.remoteClient.exception.FacadeException;

/**
 * 
 *
 * @author kalmankostenszky
 */
@WebServlet("/ExpenseDelete")
public class ExpenseDeleteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ExpenseDeleteServlet.class);

	@EJB
	private FinanceFacadeLocal eFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("ExpenseDelete#doGet() invoked");
		String docId = request.getParameter("docId");

		if (docId != null && !"".equalsIgnoreCase(docId.trim()))
		try {
			this.eFacade.deleteExpense(docId);
		} catch (FacadeException e) {
			LOG.error(e);
		}
		
		response.sendRedirect("ExpenseList");
	}

}
