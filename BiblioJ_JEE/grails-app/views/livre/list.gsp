
<%@ page import="biblioj_jee.Livre" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'livre.label', default: 'Livre')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-livre" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-livre" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<fieldset class="form">
				<g:form action="list" method="GET">
					<div class="fieldcontain">
						<label for="queryTitre"> Titre :</label>
						<g:textField name="queryTitre" value="${params.queryTitre}"/>
					</div>
					<div class="fieldcontain">
						<label for="queryType"> Type :</label>
						<g:textField name="queryType" value="${params.queryType}"/>

					</div>
					<div class="fieldcontain">
						<label for="queryAuteur"> Auteur :</label>
						<g:textField name="queryAuteur" value="${params.queryAuteur}"/>
					</div>
					<div style="margin-left:250px ; margin-top:15px">
						<g:form action="list">
							<g:submitButton name="submit" value="Rechercher"/>
						</g:form>
					</div>
				</g:form>
			</fieldset>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="titre" title="${message(code: 'livre.titre.label', default: 'Titre')}" />
					
						<g:sortableColumn property="type.intitule" title="${message(code: 'livre.type.intitule', default: 'Type')}" />
						
						<g:sortableColumn property="nombreExemplairesDisponibles" title="${message(code: 'livre.nombreExemplairesDisponibles.label', default: 'Nombre Exemplaires Disponibles')}" />
					 
					 	<th>Auteurs</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${livreInstanceList}" status="i" var="livreInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${livreInstance.id}">${fieldValue(bean: livreInstance, field: "titre")}</g:link></td>
					
						<td>${fieldValue(bean: livreInstance, field: "type")}</td>
					
						<td>${fieldValue(bean: livreInstance, field: "nombreExemplairesDisponibles")}</td>
						
						<td><ul>
							<g:each in="${livreInstance.auteur}" var="auteurInstance">
								
								<li>
								${auteurInstance.toString()}
								</li>							
							</g:each>
							</ul>
					</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${livreInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
