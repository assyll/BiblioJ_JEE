import biblioj_jee.Auteur
import biblioj_jee.Livre
import biblioj_jee.Typedoc;

class BootStrap {
	
	def init = { servletContext ->
		
		Auteur auteur1 = new Auteur(
			nom: 'King',
			prenom: 'Stephen').save()
		
		Auteur auteur2 = new Auteur(
			nom: 'Crichton',
			prenom: 'Michael').save()
		
		Typedoc type1 = new Typedoc(
			intitule:'roman').save()
		
		Typedoc type2 = new Typedoc(
			intitule:'revue').save()
		
		Livre livre1 = new Livre(
			titre: 'Les dents de la mer',
			nombreExemplaires: 2,
			nombreExemplairesDisponibles: 2,
			type: type2).save()
		
		Livre livre2 = new Livre(
			titre: 'Germinal',
			nombreExemplaires: 2,
			nombreExemplairesDisponibles: 2,
			type: type2).save()
		
		Livre livre3 = new Livre(
			titre: 'Les dents de la mer',
			nombreExemplaires: 5,
			nombreExemplairesDisponibles: 5,
			type: type1).save()
		
		livre3.addToAuteur(auteur1)
		livre2.addToAuteur(auteur1)
		livre2.addToAuteur(auteur2)
	}
	
	def destroy = {
	}
}