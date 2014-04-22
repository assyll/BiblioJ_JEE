package biblioj_jee

import java.util.Set;

class PanierService {
	
    def creerPanier() {
		def panier = new Panier()
		panier.save()
		
		return panier
    }
	
	def getPanier(boolean create=false) {
		if (Panier.count!=0) {
			Panier.first()
		}
		else if (create) {
			creerPanier()
		}
	}
	
	def addToPanier(Livre livre, int quantite = 1) {
		def panier = getPanier(true)
		
		if (!panier) {
			return
		}
		
		panier.addToPanier(livre,quantite)
		panier.save(flush: true)
		
	}
		
	def removeFromPanier(Livre livre, int quantite = 1) {
		def panier = getPanier()
		
		if (!panier) {
			return
		}
		
		panier.removeFromPanier(livre,quantite)
		panier.save(flush: true)
	}
	
	def getLivres() {
		def panier = getPanier()
		
		if (!panier) {
			return
		}
		else {
			return panier.livres
		}
	}
	
	def viderPanier() {
		def panier = getPanier()
		panier.livres = []
		
		panier.save()
	}
	
	def verifierPanier() {
		def panier = getPanier()
		boolean check=true;
		int dispoCourant;
		
		for(livrePanier in panier.livres) {
			dispoCourant=livrePanier.livre.nombreExemplairesDisponibles;
			if(livrePanier.quantite<=dispoCourant) {
				livrePanier.livre.reserver(livrePanier.quantite)
			}
			else {
				if(dispoCourant!=0){
					panier.removeFromPanier(livrePanier.livre,livrePanier.quantite-dispoCourant)
				}
				else {
					panier.removeFromPanier(livrePanier.livre,livrePanier.quantite)
				}
				check=false;
			}
		}
		check
	}
	
	def remplirReservation(Reservation newReservation){
		def panier = getPanier()
		
		for(livrePanier in panier.livres) {
			newReservation.addToLivre(livrePanier.livre)
		}
		
		newReservation.save(flush:true)
	}
}
