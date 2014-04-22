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
		LivrePanier livreCourant;
		
		for(int i=0;i<panier.livres.size();i++) {
			livreCourant=panier.livres[i]
			dispoCourant=livreCourant.nombreExemplairesDisponibles;
			if(livreCourant.quantite<=dispoCourant) {
				livreCourant.livre.reserver(livreCourant.quantite)
			}
			else {
				if(dispoCourant!=0){
					livreCourant.livre.reserver(dispoCourant)
				}
				check=false;
			}
		}
		
		check
	}
	
	def remplirReservation(Reservation newReservation){
		def panier = getPanier()
		
		for(int i=0;i<panier.livres.size();i++) {
			for(int j=0;j<panier.livres[i].quantite;j++) {
				newReservation.addToLivres(panier.livres[i].livre)
			}
		}
		
		newReservation.save()
	}
}
