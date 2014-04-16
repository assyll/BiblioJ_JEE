package biblioj_jee

import org.springframework.dao.DataIntegrityViolationException

class TypedocController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [typedocInstanceList: Typedoc.list(params), typedocInstanceTotal: Typedoc.count()]
    }

    def create() {
        [typedocInstance: new Typedoc(params)]
    }

    def save() {
        def typedocInstance = new Typedoc(params)
        if (!typedocInstance.save(flush: true)) {
            render(view: "create", model: [typedocInstance: typedocInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), typedocInstance.id])
        redirect(action: "show", id: typedocInstance.id)
    }

    def show(Long id) {
        def typedocInstance = Typedoc.get(id)
        if (!typedocInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "list")
            return
        }

        [typedocInstance: typedocInstance]
    }

    def edit(Long id) {
        def typedocInstance = Typedoc.get(id)
        if (!typedocInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "list")
            return
        }

        [typedocInstance: typedocInstance]
    }

    def update(Long id, Long version) {
        def typedocInstance = Typedoc.get(id)
        if (!typedocInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (typedocInstance.version > version) {
                typedocInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'typedoc.label', default: 'Typedoc')] as Object[],
                          "Another user has updated this Typedoc while you were editing")
                render(view: "edit", model: [typedocInstance: typedocInstance])
                return
            }
        }

        typedocInstance.properties = params

        if (!typedocInstance.save(flush: true)) {
            render(view: "edit", model: [typedocInstance: typedocInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), typedocInstance.id])
        redirect(action: "show", id: typedocInstance.id)
    }

    def delete(Long id) {
        def typedocInstance = Typedoc.get(id)
        if (!typedocInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "list")
            return
        }

        try {
            typedocInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'typedoc.label', default: 'Typedoc'), id])
            redirect(action: "show", id: id)
        }
    }
}
