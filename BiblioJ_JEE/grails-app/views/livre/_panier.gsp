
<%@ page import="biblioj_jee.Panier" %>

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'panier.label', default: 'Panier')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<div id="show-panier" class="content scaffold-show" role="main" style="float:right ; margin-right:15px ; margin-top:65px ; border:3px outset black ; padding:0 0 5px 0 ; border-radius:10px">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<h1 align=center>Panier</h1>
			<div class="property-list panier">
				<g:if test="${Panier.first()?.livres}">
					<g:select name="livres" from="${Panier.first().livres}" multiple="multiple" optionKey="id" value="${Panier.first().livres.toString()}" class="many-to-many"/>
				</g:if>
			</div>
			<g:form controller="livre">
				<g:hiddenField name="id" value="${livreInstance?.id}" />
				<g:actionSubmit style="margin-left:35px" action="viderPanier" value="${message(code: 'default.button.vider.label', default: 'Vider le Panier')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Voulez-vous vraiment vider votre panier?')}');" />
			</g:form>
		</div>
	</body>
</html>
