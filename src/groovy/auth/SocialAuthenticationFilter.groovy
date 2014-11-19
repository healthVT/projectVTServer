package auth

import com.odobo.grails.plugin.springsecurity.rest.RestAuthenticationSuccessHandler
import com.odobo.grails.plugin.springsecurity.rest.RestAuthenticationToken
import grails.converters.JSON
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.web.filter.GenericFilterBean
import projectVT.User

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by Jay on 11/16/2014.
 */
class SocialAuthenticationFilter extends GenericFilterBean {

    String endpointUrl
    def tokenGenerator
    def tokenStorageService
    RestAuthenticationSuccessHandler authenticationSuccessHandler
    AuthenticationManager authenticationManager

    @Override
    void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = request as HttpServletRequest
        HttpServletResponse httpServletResponse = response as HttpServletResponse

        def actualUri = httpServletRequest.requestURI - httpServletRequest.contextPath

        logger.debug "Actual URI is ${actualUri}; endpoint URL is ${endpointUrl}"

        //Only apply filter to the configured URL
        if (actualUri == endpointUrl) {
            log.debug "Applying authentication filter to this request"

            //Only POST is supported
            if (httpServletRequest.method != 'POST') {
                log.debug "${httpServletRequest.method} HTTP method is not supported. Setting status to ${HttpServletResponse.SC_METHOD_NOT_ALLOWED}"
                httpServletResponse.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED)
                return
            }
            String accessToken = request.getParameter("socialToken")
            String email = request.getParameter("email")
            String socialMedia = request.getParameter("socialMedia")
            println request
            println accessToken
            println email
            println socialMedia

            def user = User.findWhere(email: email)

            if(user){
                SocialAuthenticationToken authenticationRequest = new SocialAuthenticationToken(accessToken, email, socialMedia)
                Authentication auth = authenticationManager.authenticate(authenticationRequest)

                if(auth.isAuthenticated()){
                    def tokenValue = tokenGenerator.generateToken()
                    println tokenValue + " <-- token"
                    tokenStorageService.storeToken(tokenValue, auth.principal)
                    RestAuthenticationToken restAuthenticationToken = new RestAuthenticationToken(auth.principal, ("N/A"), auth.authorities, tokenValue)
                    authenticationSuccessHandler.onAuthenticationSuccess(httpServletRequest, httpServletResponse, restAuthenticationToken)

                }
            }else{
                response.contentType = 'application/json'
                response.characterEncoding = 'UTF-8'
                def result = [success: true, username: null] as JSON
                response << result.toString()
                return
            }
        }


    }
}
