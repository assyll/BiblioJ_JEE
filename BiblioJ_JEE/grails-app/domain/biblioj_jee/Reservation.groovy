package biblioj_jee

class Reservation {
	long code
	Date reservation
	static hasmany = [livre : Livre]
	
    static constraints = {
		code nullable : false 
    }
}
