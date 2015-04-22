import java.util.ArrayList;


public class Flight {

	/*
	 * This object will track all data regarding a single takeoff-landing sequence of
	 * an airplane. For summary, here's the data that this object will need to track in
	 * some manner. Time should be tracked as "military time", which can be represented
	 * simply as a single int. (6:15 AM = 0615, 9:20 PM = 2120.)
	 * 
	 * Originating (source) airport code
	 * Destination airport code
	 * Takeoff time
	 * Landing time
	 * Max capacity
	 * A list of booked Passengers
	 * A list of standby Passengers
	 */
	
	private Flight f;
	String src;
	String dest;
	int takeoffTime;
	int landingTime;
	int capacity;
	ArrayList<Passenger> bookedPassengers = new ArrayList<Passenger>();
	ArrayList<Passenger> standbyPassengers = new ArrayList<Passenger>();
	
	public Flight(String source, String destination, int time_takeoff, int time_landing, int cap) {
		/*
		 * Flight clearly requires a lot more up-front data for instantiation
		 * Note: takeoffTime MUST come before landingTime. throw a RunTimeException otherwise.
		 */
		
		src = source;
		dest = destination;
		takeoffTime = time_takeoff;
		landingTime = time_landing;
		capacity = cap;
		
		if(takeoffTime > landingTime || takeoffTime == landingTime) {
			throw new RuntimeException();
		}
		
	}
	
	public String getSourceAirport() {
		return src;
	}
	
	public String getDestinationAirport() {
		return dest;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getTakeoffTime() {
		return takeoffTime;
	}
	
	public int getLandingTime() {
		return landingTime;
	}
	
	public ArrayList<Passenger> getBookedPassengers() {
		return bookedPassengers;
	}
	
	public ArrayList<Passenger> getStandbyPassengers() {
		return standbyPassengers;
	}
	
	public boolean addPassenger(Passenger p) {
		/*
		 * Adds the Passenger to the Flight's passenger list.
		 * Returns true if the Passenger was successfully added.
		 * Returns false if the flight was full and could not be booked.
		 */
		if(bookedPassengers.size() == capacity || bookedPassengers.size() > capacity) {
			return false;
		}else{
			bookedPassengers.add(p);
			//p.bookFlight(f);
			return true;
		}
	}
	
	public boolean addStandbyPassenger(Passenger p) {
		/*
		 * Adds the Passenger to the Flight's standby list.
		 * Always succeeds
		 */
		standbyPassengers.add(p);
		return true;
	}
	
	public void removePassenger(Passenger p) {
		/*
		 * Removes the Passenger from the Flight's passenger list.
		 * Does not generate an alert.
		 */
		bookedPassengers.remove(p);
	}
	
	public void removeStandbyPassenger(Passenger p) {
		/*
		 * Removes the Passenger from the Flight's standby list.
		 * Does not generate an alert.
		 */
		standbyPassengers.remove(p);
	}
	
	public void cancel() {
		/*
		 * Cancels the specified flight.
		 * All Passengers are removed from both internal lists.
		 * All Passengers receive an alert about the cancellation.
		 */
		for(Passenger p : bookedPassengers) {
			p.getAlerts();
		}
		for(Passenger p : standbyPassengers) {
			p.getAlerts();
		}
		bookedPassengers.clear();
		standbyPassengers.clear();
	}
	
	public int promotePassengers() {
		/*
		 * If the "booked" Passenger list is not full, Passengers should be removed
		 * from the "standby" list until the plane is full and added to the "booked" list.
		 * 
		 * If there aren't enough Passengers on the "standby" list, all Passengers are
		 * "promoted."
		 * 
		 * All Passenger data should be updated accordingly.
		 	  * That is, their "standby" list and "itinerary" are also updated.
		 * 
		 * Each Passenger "promoted" by this method call should receive an alert about
		 * their change in booking status.
		 */
		int passengersPromotedCount = 0;
		
		while(bookedPassengers.size() < capacity) { //while the flight is not full...
			
			if(standbyPassengers.get(0) != null) { //make sure the flight's list of standby passengers is not empty
				Passenger passenger = standbyPassengers.get(0); //set passenger equal to the first passenger on the flight's list of standby passengers
				addPassenger(passenger); //add passenger to the flight's list of passengers
				removeStandbyPassenger(passenger); //remove passenger from the flight's list of standby passengers
				passenger.cancelFlight(f); //cancel paseenger's flight
				passenger.addAlert("On your flight " + f + ", you have been promoted from standby to booked!"); //add alert to passenger's alerts telling them they have been promoted
				passengersPromotedCount++;
			}
			
		}
		return passengersPromotedCount;
	}
	
}
