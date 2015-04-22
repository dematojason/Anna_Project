import java.util.ArrayList;


public class Passenger {
	
	/*
	 * This object will model all relevant properties and data regarding an individual
	 * passenger within the project. (That is, the "airline travel system.") Toward this
	 * end, here's a summary of the data this object should track:
	 * 
	 * First name
	 * Last name
	 * A list of alerts
	 * A list of booked Flight references
	 * A list of "standby" Flight references
	 */
	
	private Passenger p;
	String first;
	String last;
	ArrayList<String> alerts = new ArrayList<String>();
	ArrayList<Flight> bookedFlights = new ArrayList<Flight>();
	ArrayList<Flight> standbyFlights = new ArrayList<Flight>();
	
	public Passenger(String name_first, String name_last) {
		/*
		 * This is the only data you need to initially represent the passenger. The other
		 * data elements (the lists) can be represented by empty, default-initialized ArrayLists.
		 */
		
		first = name_first;
		last = name_last;
	}

	public String getFirstName() {
		return first;
	}
	
	public String getLastName() {
		return last;
	}
	
	public ArrayList<String> getAlerts() {
		return alerts;
	}
	
	public ArrayList<Flight> getBookedFlights() {
		return bookedFlights;
	}
	
	public ArrayList<Flight> getStandbyFlights() {
		return standbyFlights;
	}
	
	public boolean bookFlight(Flight f) {
		/*
		 * Adds Flight f to the Passenger's booked flight itinerary.
		 * Adds the Passenger to the Flight's passenger list.
		 * Returns true if the flight was successfully booked.
		 * Returns false if the flight was full and could not be booked.
		      * Any modifications should be cancelled if this occurs.
		 */
		
		if(f.bookedPassengers.size() == f.getCapacity()){
			return false;
		}else{
			bookedFlights.add(f);
			f.addPassenger(p);
			return true;
		}
	}
	
	public boolean addStandbyFlight(Flight f) {
		/*
		 * Adds Flight f to the Passenger's standby flight list.
		 * Adds the Passenger to the Flight's standby list.
		 * Always succeeds.
		 */
		standbyFlights.add(f);
		f.addStandbyPassenger(p);
		return true;
	}
	
	public void addAlert(String s) {
		p.alerts.add(s);
	}
	
	public void clearAlerts() {
		/*
		 * Removes all alerts for the passenger.
		 */
		alerts.clear();
	}
	
	public void cancelFlight(Flight f) {
		/*
		 * Cancels the specified flight, regardless of whether it is on the "booked" itinerary or the "standby" list.
		 * This also removes the Passenger from the Flight's passenger/standby list, whichever is relevant.
		 */		
		if(standbyFlights.contains(f)) {
			bookedFlights.remove(f);
			f.removePassenger(p);
		}else if(standbyFlights.contains(f)) {
			standbyFlights.remove(f);
			f.removeStandbyPassenger(p);
		}else{
			System.out.println("That passenger could not be found on either Passenger list!");
			System.exit(0);
		}
	}
	
	public void cancelAll() {
		/*
		 * Cancels every flight for which the passenger is booked or on standby, using the same rules as for cancelling a single flight.
		 */
		while(bookedFlights.size() > 0) {
			for(Flight f : p.bookedFlights) {
				p.bookedFlights.remove(f);
			}
		}
		while(standbyFlights.size() > 0) {
			for(Flight f : standbyFlights){
				p.standbyFlights.remove(f);
			}
		}
		
	}
	
}
