package biblioj_jee


class Panier {
	
	static hasMany = [livres : LivrePanier]
	
    static constraints = {
    }
		
	void addToPanier(Livre livre, int quantite){
		LivrePanier courant = livres.find {courant ->
				if (courant.livre.id == livre.id) {
					return true
				}
				return false
			}
		
		if (!courant) {
			this.addToLivres(new LivrePanier(livre:livre, quantite:quantite))
		}
		else {
			if (courant.quantite+quantite>livre.nombreExemplairesDisponibles) {
				courant.quantite=livre.nombreExemplairesDisponibles
			}
			else {
				courant.quantite+=quantite
			}
			courant.save(flush:true)
		}
	}
	
	void removeFromPanier(Livre livre, int quantite){
		LivrePanier courant = livres.find {courant ->
				if (courant.livre.id == livre.id) {
					return true
				}
				return false
			}
	
		if (!courant) {
			return
		}
		else if(courant.quantite<=quantite){
			this.removeFromLivres(courant);
		}
		else{
			courant.quantite-=quantite
			courant.save(flush:true)
		}
	}
}
