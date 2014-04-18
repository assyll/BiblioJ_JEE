package biblioj_jee

class Auteur {
	String nom
	String prenom
	
	static hasMany = [livre : Livre]
	static belongsTo=[Livre]
	
    static constraints = {
		nom nullable : false , blank : false
		prenom nullable : true , blank : false
    }
	
	String toString(){
		nom+" "+prenom
	}
}
