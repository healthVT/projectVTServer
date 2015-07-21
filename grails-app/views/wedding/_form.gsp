<%@ page import="projectVT.Wedding" %>



<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="wedding.email.label" default="Email" />
		
	</label>
	<g:textField name="email" value="${weddingInstance?.email}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'firstName', 'error')} ">
	<label for="firstName">
		<g:message code="wedding.firstName.label" default="First Name" />
		
	</label>
	<g:textField name="firstName" value="${weddingInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'lastName', 'error')} ">
	<label for="lastName">
		<g:message code="wedding.lastName.label" default="Last Name" />
		
	</label>
	<g:textField name="lastName" value="${weddingInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'address', 'error')} ">
	<label for="address">
		<g:message code="wedding.address.label" default="Address" />
		
	</label>
	<g:textField name="address" value="${weddingInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'message', 'error')} ">
	<label for="message">
		<g:message code="wedding.message.label" default="Message" />
		
	</label>
	<g:textField name="message" value="${weddingInstance?.message}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'ip', 'error')} ">
	<label for="ip">
		<g:message code="wedding.ip.label" default="Ip" />
		
	</label>
	<g:textField name="ip" value="${weddingInstance?.ip}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'adults', 'error')} required">
	<label for="adults">
		<g:message code="wedding.adults.label" default="Adults" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="adults" type="number" value="${weddingInstance.adults}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'commingWedding', 'error')} required">
	<label for="commingWedding">
		<g:message code="wedding.commingWedding.label" default="Comming Wedding" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="commingWedding" type="number" value="${weddingInstance.commingWedding}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'kids', 'error')} required">
	<label for="kids">
		<g:message code="wedding.kids.label" default="Kids" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="kids" type="number" value="${weddingInstance.kids}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: weddingInstance, field: 'loginTimes', 'error')} required">
	<label for="loginTimes">
		<g:message code="wedding.loginTimes.label" default="Login Times" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="loginTimes" type="number" value="${weddingInstance.loginTimes}" required=""/>

</div>

