
<%@ page import="projectVT.Wedding" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wedding.label', default: 'Wedding')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wedding" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wedding" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wedding">
			
				<g:if test="${weddingInstance?.email}">
				<li class="fieldcontain">
					<span id="email-label" class="property-label"><g:message code="wedding.email.label" default="Email" /></span>
					
						<span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${weddingInstance}" field="email"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.firstName}">
				<li class="fieldcontain">
					<span id="firstName-label" class="property-label"><g:message code="wedding.firstName.label" default="First Name" /></span>
					
						<span class="property-value" aria-labelledby="firstName-label"><g:fieldValue bean="${weddingInstance}" field="firstName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.lastName}">
				<li class="fieldcontain">
					<span id="lastName-label" class="property-label"><g:message code="wedding.lastName.label" default="Last Name" /></span>
					
						<span class="property-value" aria-labelledby="lastName-label"><g:fieldValue bean="${weddingInstance}" field="lastName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.address}">
				<li class="fieldcontain">
					<span id="address-label" class="property-label"><g:message code="wedding.address.label" default="Address" /></span>
					
						<span class="property-value" aria-labelledby="address-label"><g:fieldValue bean="${weddingInstance}" field="address"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.message}">
				<li class="fieldcontain">
					<span id="message-label" class="property-label"><g:message code="wedding.message.label" default="Message" /></span>
					
						<span class="property-value" aria-labelledby="message-label"><g:fieldValue bean="${weddingInstance}" field="message"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.ip}">
				<li class="fieldcontain">
					<span id="ip-label" class="property-label"><g:message code="wedding.ip.label" default="Ip" /></span>
					
						<span class="property-value" aria-labelledby="ip-label"><g:fieldValue bean="${weddingInstance}" field="ip"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="wedding.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${weddingInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.adults}">
				<li class="fieldcontain">
					<span id="adults-label" class="property-label"><g:message code="wedding.adults.label" default="Adults" /></span>
					
						<span class="property-value" aria-labelledby="adults-label"><g:fieldValue bean="${weddingInstance}" field="adults"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.commingWedding}">
				<li class="fieldcontain">
					<span id="commingWedding-label" class="property-label"><g:message code="wedding.commingWedding.label" default="Comming Wedding" /></span>
					
						<span class="property-value" aria-labelledby="commingWedding-label"><g:fieldValue bean="${weddingInstance}" field="commingWedding"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.kids}">
				<li class="fieldcontain">
					<span id="kids-label" class="property-label"><g:message code="wedding.kids.label" default="Kids" /></span>
					
						<span class="property-value" aria-labelledby="kids-label"><g:fieldValue bean="${weddingInstance}" field="kids"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${weddingInstance?.loginTimes}">
				<li class="fieldcontain">
					<span id="loginTimes-label" class="property-label"><g:message code="wedding.loginTimes.label" default="Login Times" /></span>
					
						<span class="property-value" aria-labelledby="loginTimes-label"><g:fieldValue bean="${weddingInstance}" field="loginTimes"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:weddingInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${weddingInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
