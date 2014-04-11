package biblioj_jee

class Utilisateur {
	String nom
	String prenom
	String email
	
    static constraints = {
		nom blank : false
		prenom blank : false
		email blank : false, email : true
    }
}
