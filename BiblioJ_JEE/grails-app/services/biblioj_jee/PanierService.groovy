package biblioj_jee

import java.util.Set;

class PanierService {

	boolean transactional = true
	
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
	
	def getQt() {
		def panier = getPanier()
		
		if (!panier) {
			return
		}
		else {
			return panier.quantites
		}
	}
	
	def viderPanier() {
		def panier = getPanier()
		panier.livres = []
		
		panier.save()
	}
}
