/**
 * PeriodicCustomer class represents a customer and customer behaviors and implements a Runnable interface
 * for creating and running threads. As a result of the implementation, the overridden run() method lets customers
 * randomly attempt to book and cancel a reservation for themselves.  
 * 
 * @author Jina Pak, Jihoon Choi
 * @since  11/27
 *
 */
public class PeriodicCustomer implements Runnable{
	
	private String name;
	private Hotel hotel;
	
	/**
	 * A class constructor taking a string as a name of a customer and an object of Hotel.
	 * Both parameters shouldn't be null.
	 * @param name : a string that represents a customer's name
	 * @param hotel : an object containing behaviors such as canceling and booking a reservation etc.  
	 */
	public PeriodicCustomer(String name, Hotel hotel) {
		if(name == null || hotel == null) {
			throw new NullPointerException("Parameter shouldn't be null");
		}
		this.name = name;
		this.hotel = hotel;
	}
	
	/**
	 * This run method is supposed to run itself infinitely since the condition of while loop inside of the parenthesis
	 * is true. A random number decides if a customer tries booking or canceling a reservation and conditions
	 * under each behavior filter out and lead to make a conclusion. Outside of the while loop, try-catch handles a possible
	 * exception from sleep()
	 */
	@Override
	public void run() {
		try {
			while(true) {
				if((int)(Math.random()*2) == 0) {
					int firstDay = (int)(Math.random()*31+1);
					int lastDay = firstDay + (int)(Math.random()*(31-firstDay+1));
					if(hotel.requestReservation(this.name, firstDay, lastDay)) {
						System.out.println("Reservation made: " + this.name + " from " + firstDay + " through " + lastDay);
					}
					else {
						System.out.println("Reservation unsuccessful: " + this.name + " from " + firstDay + " through " + lastDay);
					}
					
				}
				else {
					if(hotel.cancelReservation(this.name)) {
						System.out.println("Reservation successfully canceled for " + this.name);
					}
					else {
						System.out.println("Reservation not canceled for " + this.name + ", no current reservation.");
					}
				}
				Thread.sleep(1000);
			}
		}
		catch(InterruptedException e) {
			System.out.println("Periodic Test Customer " + this.name + " Shutting Down");
		}
	}
}