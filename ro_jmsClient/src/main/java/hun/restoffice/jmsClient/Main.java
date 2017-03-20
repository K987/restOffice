/**
 * 
 */
package hun.restoffice.jmsClient;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import hun.restoffice.jmsClient.queue.QueueMessageFactory;
import hun.restoffice.jmsClient.queue.QueueName;
import hun.restoffice.jmsClient.topic.IncomeListener;
import hun.restoffice.jmsClient.topic.Subsrcriber;
import hun.restoffice.jmsClient.topic.TopicName;
import hun.restoffice.remoteClient.domain.DocTypeStub;
import hun.restoffice.remoteClient.domain.IncomeStub;
import hun.restoffice.remoteClient.domain.PaymentMethodStub;

/**
 * 
 *
 * @author kalmankostenszky
 */
public class Main {

	private static final Logger LOG = Logger.getLogger(Main.class);

	private static boolean run = true;

	static Subsrcriber subsrcriber;
	static IncomeListener listener;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			subsrcriber = new Subsrcriber(TopicName.TOPIC_DAILY_INCOME);
			listener = new IncomeListener();
			subsrcriber.startASync(listener);
		} catch (NamingException e) {
			LOG.error("cant start listening " + e.getLocalizedMessage());
		} catch (JMSException e) {
			LOG.error("topic not found " + e.getLocalizedMessage());
		}

		while (run) {
			System.out.println(" Send new income: press 1 \n Get daily closes: press 2 \n Quit: press 3: ");
			int option = 0;
			try {
				option = Integer.parseInt(System.console().readLine());
			} catch (NumberFormatException e) {
				System.out.println("It is not a number you silly");
			}
			switch (option) {
				case 1:
					sendNewIncome();
					break;
				case 2:
					getDailyCloses();
					break;
				case 3:
					quit();
					break;
				default:
					System.out.println("\nfrom 1 to 3 my friend. Try again \n");
			}

		}
	}

	/**
	 * 
	 */
	private static void quit() {
		run = false;
		System.out.println("bye then...");
		try {
			subsrcriber.stop();
		} catch (JMSException e) {
			LOG.error(e);
		}
	}

	/**
	 * 
	 */
	private static void getDailyCloses() {
		listener.printAll();

	}

	/**
	 * 
	 */
	private static void sendNewIncome() {
		String docId = "";
		while (docId.length() == 0) {
			System.out.println("\nEnter docId: ");
			docId = System.console().readLine();
		}

		String partner = null;
		while (partner == null) {
			System.out.print("\n\n\nEnter partner: Rendezvény Kkt = 1, Szesz Kft = 2, XY tulajdonos = 3: ");
			partner = System.console().readLine();
			switch (partner) {
				case "1":
					partner = "Rendezvény Kft";
					break;
				case "2":
					partner = "Szesz Kft";
					break;
				case "3":
					partner = "XY tulajdonos";
					break;
				default:
					partner = null;
					break;
			}
		}

		System.out.print("\nAdd description: ");
		String description = System.console().readLine();

		DocTypeStub docType = null;
		while (docType == null) {
			System.out.print("Add document type : external = 0, internal = 1");
			int ordinal = -1;
			try {
				ordinal = Integer.parseInt(System.console().readLine());
				docType = DocTypeStub.values()[ordinal];
			} catch (Exception e) {
				System.out.println("that was a bad one, try again");
			}
		}

		PaymentMethodStub payMethod = null;
		while (payMethod == null) {
			System.out.print("\nChoose payment method: cash = 0, transfer = 1, deffered_cash = 2, debt_card = 4: ");
			int ordinal = -1;
			try {
				ordinal = Integer.parseInt(System.console().readLine());
				payMethod = PaymentMethodStub.values()[ordinal];
			} catch (Exception e) {
				System.out.println("that was a bad one, try again");
			}
		}
		String incType = null;
		while (incType == null) {
			System.out.print("\nEnter incomeType : terembérlet = 1, szolgáltatás = 2, visszáru = 3, tulajdonosi befizetés = 4: ");
			incType = System.console().readLine();
			switch (incType) {
				case "1":
					incType = "terembérlet";
					break;
				case "2":
					incType = "szolgáltatás";
					break;
				case "3":
					incType = "visszáru";
					break;
				case "4":
					incType = "tulajdonosi befizetés";
					break;
				default:
					incType = null;
					break;
			}
		}

		BigDecimal grossTotal = null;
		while (grossTotal == null) {
			System.out.print("\nAdd grossTotal: ");
			String total = System.console().readLine();
			try {
				grossTotal = new BigDecimal(total);
			} catch (NumberFormatException e) {
				System.out.println("that was a bad one, try again");
			}
		}
		Calendar registered = Calendar.getInstance();
		try {
			new QueueMessageFactory(QueueName.DESTINATION_INCOME).sendIncomeMessage(
					new IncomeStub(docId, docType, partner, description, grossTotal, payMethod, incType, registered, null, null, null, null));
		} catch (Exception e) {
			LOG.error(e);
		}
	}

}
