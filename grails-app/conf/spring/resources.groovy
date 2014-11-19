import auth.SocialAuthenticationFilter
import auth.SocialAuthenticationProvider
import com.odobo.grails.plugin.springsecurity.rest.token.rendering.RestAuthenticationTokenJsonRenderer

// Place your Spring DSL code here
beans = {
    socialAuthenticationFilter(SocialAuthenticationFilter){
        authenticationManager = ref('authenticationManager')
        tokenGenerator = ref('tokenGenerator')
        tokenStorageService = ref('tokenStorageService')
        authenticationSuccessHandler = ref('restAuthenticationSuccessHandler')
        endpointUrl = "/socialMedia"
    }

    socialAuthenticationProvider(SocialAuthenticationProvider){
        authenticationManager = ref('authenticationManager')
        socialService = ref('socialService')
        userDetailsService = ref('userDetailsService')

    }
}
