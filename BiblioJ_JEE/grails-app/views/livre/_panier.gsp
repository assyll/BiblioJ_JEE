
<%@ page import="biblioj_jee.Panier" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'panier.label', default: 'Panier')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-panier" class="content scaffold-show" role="main" style="margin-right:15px ; margin-top:65px ; border:3px outset black ; padding:0 0 5px 0 ; border-radius:10px">
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
									${livrePanier.livre.toString()}
								</td>
								<td>
									<g:select name="nbExemplairesPanier" from="${1..livrePanier.quantite}" value="${livrePanier.quantite}"/>
								</td>
								<td>
									<g:remoteLink action="updatePanier" controller="livre" 
										params="${[id:(livrePanier.livre.id), value:(nbExemplairesPanier), oldValue:(livrePanier.quantite)]}"
										update="panier"
										onComplete="Effect.Pulsate('panier', {pulses: 1, duration: 1.0});">
										Mettre à Jour
									</g:remoteLink>
								</td>
								<td>
									<g:remoteLink action="removeAll" controller="livre"
										params="${[id:(livrePanier.livre.id)]}"
										update="panier"
										onComplete="Effect.Pulsate('panier', {pulses: 1, duration: 1.0});">
										Supprimer
									</g:remoteLink>
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
					<g:actionSubmit style="margin-left:35px" action="viderPanier" value="${message(code: 'default.button.vider.label', default: 'Vider le Panier')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Voulez-vous vraiment vider votre panier?')}');" />
				</g:form>
			</div>
		</div>
	</body>
</html>
