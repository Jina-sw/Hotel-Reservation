import java.util.ArrayList;

/**
 * A class that represents a one room hotel and allows reservations to be made
 * and cancelled for the month of December.
 * 
 * @author Chris Schmidt
 *
 */



//888888ba                                  dP                     dP oo   dP   
//88    `8b                                 88                     88      88   
//88     88 .d8888b.    88d888b. .d8888b. d8888P    .d8888b. .d888b88 dP d8888P 
//88     88 88'  `88    88'  `88 88'  `88   88      88ooood8 88'  `88 88   88   
//88    .8P 88.  .88    88    88 88.  .88   88      88.  ... 88.  .88 88   88   
//8888888P  `88888P'    dP    dP `88888P'   dP      `88888P' `88888P8 dP   dP   
//                                                                              
//                                                                              
//  dP   dP       oo             .8888b oo dP                                   
//  88   88                      88        88                                   
//d8888P 88d888b. dP .d8888b.    88aaa  dP 88 .d8888b.                          
//  88   88'  `88 88 Y8ooooo.    88     88 88 88ooood8                          
//  88   88    88 88       88    88     88 88 88.  ...                          
//  dP   dP    dP dP `88888P'    dP     dP dP `88888P'                          
                                                                              
                                                                              



public class Hotel {
	private final int DAY_COUNT = 31;
	private ArrayList<String> reservations;

	/**Create a hotel object which will track reservations for one month for one room
	 * 
	 */
	public Hotel() {
		reservations = new ArrayList<String>(DAY_COUNT);
		for (int i = 0; i < DAY_COUNT; i++) {
			reservations.add(null);
		}
	}

	/**
	 * Displays the full set of reservation information. Each day is marked with
	 * either the user with the reservation or Available
	 */
	public String reservationInformation() {
		String out = "Hotel Reservation Info\n__________________\n";

		for (int i = 0; i < DAY_COUNT; i++) {
			out += (i + 1) + ": ";
			if (reservations.get(i) == null) {
				out += "Available\n";
			} else {
				out += reservations.get(i) + "\n";
			}
		}
		return out;
	}

	/**
	 * Requests a reservation.  The reservation is not made if the user already has a reservation
	 * if the first and last days are invalid in any way
	 * or if any of the requested days are already taken
	 * @param user: The user requesting a reservation
	 * @param first: The first day of the requested reservation
	 * @param last: The last day of the requested reservation
	 * @return returns true if the reservation was made, false otherwise
	 */
	public synchronized boolean requestReservation(String user, int first, int last) {
		if (user==null){
			throw new NullPointerException("user String must not be null");
		}
		
		
		if (first < 1 || last > DAY_COUNT || last < first) {
			throw new IllegalArgumentException("first and last must be between 1 and 31. first cannot come after last");
		}
		
		if (reservations.indexOf(user) != -1) {
			return false;// "Only one reservation allowed per person";
		}

		return reserveRoom(reservations, user, first, last);

	}

	/**
	 * Cancels the reservation for the user (if one exists)
	 * @param user: the user requesting the cancellation
	 * @return: true if there was a reservation to cancel
	 */
	public synchronized boolean cancelReservation(String user) {
		if (user==null){
			throw new NullPointerException("user String must not be null");
		}
		if (reservations.indexOf(user) == -1) {
			return false;// "You don't have a reservation to cancel.";
		}

		for (int i = 0; i < DAY_COUNT; i++) {
			if (user.equals(reservations.get(i))) {
				reservations.set(i, null);
			}
		}
		return true;// "Reservation Succesfully Canceled";
	}

	private boolean reserveRoom(ArrayList<String> room, String name, int first, int last) {
		for (int i = first - 1; i <= last - 1; i++) {
			if (room.get(i) != null) {
				return false;
			}
		}
		for (int i = first - 1; i <= last - 1; i++) {
			room.set(i, name);
		}
		return true;
	}


}