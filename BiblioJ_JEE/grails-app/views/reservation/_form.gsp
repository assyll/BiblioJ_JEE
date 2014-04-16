<%@ page import="biblioj_jee.Reservation" %>



<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="reservation.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="code" type="number" value="${reservationInstance.code}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'livre', 'error')} ">
	<label for="livre">
		<g:message code="reservation.livre.label" default="Livre" />
		
	</label>
	<g:select name="livre" from="${biblioj_jee.Livre.list()}" multiple="multiple" optionKey="id" size="5" value="${reservationInstance?.livre*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: reservationInstance, field: 'reservation', 'error')} required">
	<label for="reservation">
		<g:message code="reservation.reservation.label" default="Reservation" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="reservation" precision="day"  value="${reservationInstance?.reservation}"  />
</div>

