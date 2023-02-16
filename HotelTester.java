/**
 * HotelTester class is to test if Hotel and PeriodicCustomer classes work well together.
 * Using three threads, customers created have a different name from one another and randomly behave
 * canceling and booking. The result of behaviors gets decided under conditions set in Hotel.
 * After running for a while, all three threads get interrupted and joined so that the method halts 
 * the job and prints the reservation information.
 *   
 * @author Jina Pak, Jihoon Choi
 * @since  11/27
 *
 */
public class HotelTester {

	public static void main(String[] args) throws InterruptedException{
		Hotel hotel = new Hotel();
		
		Thread customer1 = new Thread(new PeriodicCustomer("Jihoon", hotel));
		Thread customer2 = new Thread(new PeriodicCustomer("Jina", hotel));
		Thread customer3 = new Thread(new PeriodicCustomer("asdf", hotel));

		customer1.start();
		customer2.start();
		customer3.start();
		
		Thread.sleep(5000);
		
		customer1.interrupt();
		customer2.interrupt();
		customer3.interrupt();
		
		customer1.join();
		customer2.join();
		customer3.join();
		
		System.out.println();
		System.out.print(hotel.reservationInformation());
	}

}
