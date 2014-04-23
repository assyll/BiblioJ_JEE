package biblioj_jee



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Auteur)
class AuteurTests {

    void testCreation() {
       Auteur auteur = new Auteur(nom : "Vegan", prenom : "Delphine de")
	   assertTrue(auteur != null)	   
    }
}
