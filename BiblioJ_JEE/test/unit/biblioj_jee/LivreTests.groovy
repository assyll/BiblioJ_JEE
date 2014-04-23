package biblioj_jee



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Livre)
class LivreTests {

    void testCreation() {
       Livre livre = new Livre(titre : "Rien ne s'oppose à la nuit : roman", nombreExemplaires : 5, nombreExemplairesDisponibles : 5)
	   assertTrue(livre != null)
	   
    }
}
