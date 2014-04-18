package biblioj_jee

class LivrePanier {

	Livre livre;
	Integer quantite;
	
    static constraints = {
    }
	
	static belongsTo = Panier
	
	String toString() {
		livre.toString()+" ("+quantite+")"
	}
	
}
