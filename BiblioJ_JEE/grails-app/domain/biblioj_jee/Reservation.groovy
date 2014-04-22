package biblioj_jee

class Reservation {
	Date reservation
	long code
	
	static hasMany = [livre : Livre]
	static belongsTo = [Livre]
	
    static constraints = {
		code nullable : false 
    }
	
	void setCode(){
		code=Reservation.count()+1
	}
}
