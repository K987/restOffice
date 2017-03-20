/**
 * 
 */
package hun.restoffice.weblayer.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import hun.restoffice.ejbservice.domain.ExpenseStub;
import hun.restoffice.ejbservice.domain.PartnerStub;
import hun.restoffice.ejbservice.facade.FinanceFacadeLocal;
import hun.restoffice.ejbservice.facade.PartnerFacadeLocal;
import hun.restoffice.remoteClient.exception.FacadeException;
import hun.restoffice.weblayer.util.FinanceHelperLocal;

/**
 * 
 *
 * @author kalmankostenszky
 */
@WebServlet("/ExpenseEdit")
public class ExpenseEditServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(ExpenseEditServlet.class);

	@EJB
	private FinanceFacadeLocal fFacade;

	@EJB
	private FinanceHelperLocal fHelper;

	@EJB
	private PartnerFacadeLocal pFacade;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("ExpenseEditServlet#doGet invoked");
		String docId = request.getParameter("docId");
		LOG.info(docId);

		if (docId == null || "".equals(docId.trim()))
			response.sendRedirect("ExpenseList");

		// public ExpenseStub(String docId, String docType, PartnerStub name, int payMethod, BigDecimal grossTotal,
		// String description, Date registered, Date expiry, Date payed, Date startDate, Date endDate, String
		// costCenter, String costType) {

		ExpenseStub expense = new ExpenseStub("-", 0, null, 0, new BigDecimal("0"), "-", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),
				null, null, null, "-", "-");
		if (!"-1".equals(docId.trim())) {
			try {
				expense = this.fFacade.getExpenseById(docId);
			} catch (FacadeException e) {
				LOG.error(e);
				response.sendRedirect("ExpenseList");
			}
		}

		request.setAttribute("expense", expense);
		request.setAttribute("isNew", docId);
		try {
			request.setAttribute("partners", this.fHelper.getPartners());
			request.setAttribute("costCenters", this.fHelper.getCostCenters());
			request.setAttribute("expenseTypes", this.fHelper.getExpenseTypes());
		} catch (FacadeException e) {
			response.sendRedirect("ExpnseList");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("ExpenseEdit.jsp");
		dispatcher.forward(request, response);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOG.info("ExpenseEditServlet#doPost invoked");
		
		request.setCharacterEncoding("UTF-8");
		
		String docId = request.getParameter("docId");
		int docType = Integer.parseInt(request.getParameter("docType"));
		PartnerStub partner = null;
		try {
			partner = this.pFacade.getPartnerById(Integer.parseInt(request.getParameter("partner")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FacadeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal grossTotal = new BigDecimal(request.getParameter("grossTotal"));
		String description = request.getParameter("description");
		Date issue = formatCalendar(request.getParameter("issueDate"));
		int payMethod = Integer.parseInt(request.getParameter("payMethod"));
		Date expiry = formatCalendar(request.getParameter("expiryDate"));
		Date payed = formatCalendar(request.getParameter("payedDate"));
		String costCenter = request.getParameter("costCenter");
		String costType = request.getParameter("costType");
		Date accPerStart = formatCalendar(request.getParameter("accStartDate"));
		Date accPerEnd = formatCalendar(request.getParameter("accEndDate"));
		String isNew = request.getParameter("isNew");

		ExpenseStub stub = new ExpenseStub(docId, docType, partner, payMethod, grossTotal, description, issue, expiry, payed, accPerStart, accPerEnd,
				costCenter, costType);
		LOG.info("Input is: " + stub);
		try {
			if ("-1".equals(isNew.trim()))
				this.fFacade.addExpense(stub);
			else
				this.fFacade.updateExpense(stub);
		} catch (FacadeException e) {
			LOG.error(e);
		}
		
		response.sendRedirect("ExpenseList");
	}

	/**
	 * @param parameter
	 * @return
	 */
	private Date formatCalendar(String parameter) {
		if (parameter == null && "".equals(parameter))
			return null;
		Calendar rtrn = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.");
		try {
			rtrn.setTime(df.parse(parameter.trim()));
		} catch (ParseException e) {
			LOG.error(e);
			return null;
		}
		return rtrn.getTime();
	}

}
