package biblioj_jee

class Reservation {
	long code
	Date reservation
	
	static hasMany = [livre : Livre]
	
    static constraints = {
		code nullable : false 
    }
}
