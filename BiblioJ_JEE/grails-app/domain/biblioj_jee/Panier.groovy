package biblioj_jee

import java.lang.reflect.Array


class Panier {

	List<Integer> quantites
	
	static hasMany = [livres : Livre]
	
    static constraints = {
    }
	
	Panier(){
		quantites=[-1];
	}
	
	void addToPanier(Livre livre, int quantite){
		int indexCourant=livres.findIndexOf { courant ->
				if (courant.id == livre.id) {
					return true
				}
				return false
			}
		
		if (indexCourant<0) {
			this.addToLivres(livre)
			quantites.add(quantite)
		}
		else {
			quantites.add(indexCourant, quantites.get(indexCourant)+quantite)
		}
	}
	
	void removeFromPanier(Livre livre, int quantite){
		int indexCourant=livres.findIndexOf { courant ->
			if (courant.id == livre.id) {
				return true
			}
			return false
		}
	
		if (indexCourant>0) {
			if(quantites.get(indexCourant)<=quantite){
				quantites.remove(indexCourant);
				this.removeFromLivres(livre)
			}
			else{
				quantites.add(indexCourant, quantites.get(indexCourant)-quantite)
			}
		}
	}
	
}
