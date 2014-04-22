package biblioj_jee

class Reservation {
	long code
	Date reservation
	
	static hasMany = [livre : Livre]
	static belongsTo = [Livre]
	
    static constraints = {
		code nullable : false 
    }
	
	void setCode(){
		code=this.id
	}
	
}
