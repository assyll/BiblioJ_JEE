package biblioj_jee

class Livre {
	String titre
	int nombreExemplaires
	int nombreExemplairesDisponibles
	Typedoc type
	
	static hasMany = [auteur : Auteur, reservation : Reservation]
	static belongsTo = [Reservation]
	
    static constraints = {
		titre nullable : false , blank : false
		type  nullable : true
		nombreExemplaires nullable : false
		nombreExemplairesDisponibles nullable : false, max : nombreExemplaires
    }
}
