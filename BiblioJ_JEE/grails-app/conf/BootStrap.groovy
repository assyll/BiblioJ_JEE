import biblioj_jee.Auteur
import biblioj_jee.Livre
import biblioj_jee.Typedoc;

class BootStrap {
	
	def init = { servletContext ->
		
		Auteur auteur1 = new Auteur(
			nom: 'Stephen',
			prenom: 'King')
		
		Auteur auteur2 = new Auteur(
			nom: 'Crichton',
			prenom: 'Michael')
		
		Typedoc type1 = new Typedoc(
			intitule:'roman')
		type1.save()
		
		Typedoc type2 = new Typedoc(
			intitule:'revue')
		type2.save()
		
		Livre livre1 = new Livre(
			titre: 'Les dents de la mer',
			nombreExemplaires: 2,
			nombreExemplairesDisponibles: 2,
			type: type1)
		livre1.save()
		
		Livre livre2 = new Livre(
			titre: 'Germinal',
			nombreExemplaires: 2,
			nombreExemplairesDisponibles: 2,
			type: type2)
		livre2.save()
		
		Livre livre3 = new Livre(
			titre: 'Les dents de la mer',
			nombreExemplaires: 5,
			nombreExemplairesDisponibles: 5,
			type: type2)
		livre3.save()
		
		auteur1.addToLivre(livre3)
		auteur1.addToLivre(livre2);
		auteur2.addToLivre(livre2)
	}
	
	def destroy = {
	}
}