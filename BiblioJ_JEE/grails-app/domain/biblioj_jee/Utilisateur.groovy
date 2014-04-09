package biblioj_jee

class Utilisateur {
	String nom, prenom, email
	
    static constraints = {
		nom blank:false
		prenom blank:false
		email blank:false, email : true
    }
}
