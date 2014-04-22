
<%@ page import="biblioj_jee.Panier" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'panier.label', default: 'Panier')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-panier" class="content scaffold-show" role="main" style="margin-top:10px ; border:3px outset black ; padding:0 0 5px 0 ; border-radius:10px ; overflow:hidden">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<h1 align=center>Panier</h1>
			<div class="property-list panier">
				<g:if test="${Panier.first()?.livres}">
					<table>
						<g:each in="${Panier.first()?.livres}" var="livrePanier">
							<tr>
								<td>
									<g:link action="show" controller="livre" params="${[id:(livrePanier.livre.id)]}">
										${livrePanier.livre.toString()}
									</g:link>
								</td>
								<td>
									<g:form controller="livre" action="updatePanier" method="post" params="${[id:(livrePanier.livre.id),oldValue:(livrePanier.quantite)]}">
										<g:select name="nbExemplairesPanier" from="${1..livrePanier.quantite}" 
											value="${livrePanier.quantite}"/>
										<g:submitButton name="miseAjours" value="Mettre à Jour" />
										<g:actionSubmit action="removeAll" controller="livre"
											params="${[id:(livrePanier.livre.id)]}" value="Supprimer"/>
									</g:form>
								</td>
							</tr>
						</g:each>
					</table>
				</g:if>
			</div>
			<div align="center">
				<g:form controller="livre">
					<g:hiddenField name="id" value="${livreInstance?.id}" />
					<g:actionSubmit style="margin-left:35px" action="verifierPanier" value="${message(code: 'default.button.reserver.label', default: 'Réserver')}" />
					<g:actionSubmit style="margin-left:35px" action="viderPanier" value="${message(code: 'default.button.vider.label', default: 'Vider le Panier')}" onclick="return confirm('${message(code: 'default.button.panier.confirm.message', default: 'Voulez-vous vraiment vider votre panier?')}');" />
				</g:form>
			</div>
		</div>
	</body>
</html>
