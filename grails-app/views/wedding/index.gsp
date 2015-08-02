
<%@ page import="projectVT.Wedding" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wedding.label', default: 'Wedding')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body style="overflow: auto;">
		<a href="#list-wedding" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wedding" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="email" title="${message(code: 'wedding.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="firstName" title="${message(code: 'wedding.firstName.label', default: 'Full Name')}" />
					
						<g:sortableColumn property="address" title="${message(code: 'wedding.address.label', default: 'Address')}" />
					
						<g:sortableColumn property="message" title="${message(code: 'wedding.message.label', default: 'Message')}" />

						<g:sortableColumn property="commingWedding" title="${message(code: 'wedding.ip.label', default: 'Attend')}" />

                        <g:sortableColumn property="adults" title="${message(code: 'wedding.ip.label', default: 'Adult')}" />

                        <g:sortableColumn property="kids" title="${message(code: 'wedding.ip.label', default: 'Kids')}" />

                        <g:sortableColumn property="kidChair" title="${message(code: 'wedding.ip.label', default: 'Kid Chair')}" />

                        <g:sortableColumn property="vegetarian" title="${message(code: 'wedding.ip.label', default: 'Vegetarian')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${weddingInstanceList}" status="i" var="weddingInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">


						<td>${fieldValue(bean: weddingInstance, field: "email")}</td>
					
						<td>${weddingInstance.firstName ? new String(org.apache.commons.codec.binary.Base64.decodeBase64(weddingInstance.firstName), "UTF-8") : ''}</td>
					
						<td>${weddingInstance.address ? new String(org.apache.commons.codec.binary.Base64.decodeBase64(weddingInstance.address), "UTF-8") : ''}</td>
					
						<td>${weddingInstance.message ? new String(org.apache.commons.codec.binary.Base64.decodeBase64(weddingInstance.message), "UTF-8") : ''}</td>
					
						<td>${weddingInstance.commingWedding == 1 ? "YES" : weddingInstance.commingWedding == 2 ? "NO-Invitation" : "NO"}</td>

                        <td>${weddingInstance.adults}</td>

                        <td>${weddingInstance.kids}</td>

                        <td>${weddingInstance.kidChair}</td>

                        <td>${weddingInstance.vegetarian}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${weddingInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
