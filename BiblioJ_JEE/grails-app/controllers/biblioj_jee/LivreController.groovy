package biblioj_jee

import org.springframework.dao.DataIntegrityViolationException

class LivreController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def index() {
		redirect(action: "list", params: params)
	}

	def list(Integer max) {
		params.max = 5
		def bookList = Livre.createCriteria().list(params) {
			and{
				if ( params.queryTitre ) {
					ilike("titre", "%${params.queryTitre}%")
				}

				if ( params.queryType) {
					type {
						ilike("intitule","%${params.queryType}%")
					}
				}

				if (params.queryAuteur  ) {
					auteur {
						ilike("nom","%${params.queryAuteur}%")
					}
				}
			}
		}
		println bookList

		[livreInstanceList: bookList, livreInstanceTotal: bookList.totalCount]
	}

	def create() {
		[livreInstance: new Livre(params)]
	}

	def save() {
		def livreInstance = new Livre(params)
		if (!livreInstance.save(flush: true)) {
			render(view: "create", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'livre.label', default: 'Livre'),
			livreInstance.id
		])
		redirect(action: "show", id: livreInstance.id)
	}

	def show(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def edit(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		[livreInstance: livreInstance]
	}

	def update(Long id, Long version) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (livreInstance.version > version) {
				livreInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'livre.label', default: 'Livre')] as Object[],
						"Another user has updated this Livre while you were editing")
				render(view: "edit", model: [livreInstance: livreInstance])
				return
			}
		}

		livreInstance.properties = params

		if (!livreInstance.save(flush: true)) {
			render(view: "edit", model: [livreInstance: livreInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'livre.label', default: 'Livre'),
			livreInstance.id
		])
		redirect(action: "show", id: livreInstance.id)
	}

	def delete(Long id) {
		def livreInstance = Livre.get(id)
		if (!livreInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			livreInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'livre.label', default: 'Livre'),
				id
			])
			redirect(action: "show", id: id)
		}
	}
}
