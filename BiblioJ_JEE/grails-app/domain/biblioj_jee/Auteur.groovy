package biblioj_jee

class Auteur {
	String nom,prenom
	static hasmany = [livre : Livre]
    static constraints = {
		nom nullable : true , blank : false
		prenom nullable : true , blank : false
    }
}
