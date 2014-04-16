<%@ page import="biblioj_jee.Typedoc" %>



<div class="fieldcontain ${hasErrors(bean: typedocInstance, field: 'intitule', 'error')} required">
	<label for="intitule">
		<g:message code="typedoc.intitule.label" default="Intitule" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="intitule" required="" value="${typedocInstance?.intitule}"/>
</div>

