package biblioj_jee

class Livre {
	String titre
	Integer nombreExemplaires
	Integer nombreExemplairesDisponibles
	Typedoc type
	
	static hasMany = [auteur : Auteur, reservation : Reservation]
	static belongsTo = [Auteur]
	
    static constraints = {
		titre nullable : false , blank : false
		type  nullable : true
		nombreExemplaires nullable : false
		nombreExemplairesDisponibles nullable : false
    }
	
	String toString() {
		titre
	}
	
	void reserver(Integer nbExemplairesDispo) {
		nombreExemplairesDisponibles-=nbExemplairesDispo
	}
}
