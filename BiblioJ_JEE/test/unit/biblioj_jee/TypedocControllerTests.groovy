package biblioj_jee



import org.junit.*
import grails.test.mixin.*

@TestFor(TypedocController)
@Mock(Typedoc)
class TypedocControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/typedoc/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.typedocInstanceList.size() == 0
        assert model.typedocInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.typedocInstance != null
    }

    void testSave() {
        controller.save()

        assert model.typedocInstance != null
        assert view == '/typedoc/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/typedoc/show/1'
        assert controller.flash.message != null
        assert Typedoc.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/typedoc/list'

        populateValidParams(params)
        def typedoc = new Typedoc(params)

        assert typedoc.save() != null

        params.id = typedoc.id

        def model = controller.show()

        assert model.typedocInstance == typedoc
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/typedoc/list'

        populateValidParams(params)
        def typedoc = new Typedoc(params)

        assert typedoc.save() != null

        params.id = typedoc.id

        def model = controller.edit()

        assert model.typedocInstance == typedoc
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/typedoc/list'

        response.reset()

        populateValidParams(params)
        def typedoc = new Typedoc(params)

        assert typedoc.save() != null

        // test invalid parameters in update
        params.id = typedoc.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/typedoc/edit"
        assert model.typedocInstance != null

        typedoc.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/typedoc/show/$typedoc.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        typedoc.clearErrors()

        populateValidParams(params)
        params.id = typedoc.id
        params.version = -1
        controller.update()

        assert view == "/typedoc/edit"
        assert model.typedocInstance != null
        assert model.typedocInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/typedoc/list'

        response.reset()

        populateValidParams(params)
        def typedoc = new Typedoc(params)

        assert typedoc.save() != null
        assert Typedoc.count() == 1

        params.id = typedoc.id

        controller.delete()

        assert Typedoc.count() == 0
        assert Typedoc.get(typedoc.id) == null
        assert response.redirectedUrl == '/typedoc/list'
    }
}
