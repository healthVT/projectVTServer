import auth.SocialAuthenticationFilter
import auth.SocialAuthenticationProvider

// Place your Spring DSL code here
beans = {
    socialAuthenticationFilter(SocialAuthenticationFilter){
        authenticationManager = ref('authenticationManager')
        tokenGenerator = ref('tokenGenerator')
        tokenStorageService = ref('tokenStorageService')
        authenticationSuccessHandler = ref('authenticationSuccessHandler')
        endpointUrl = "/socialMedia"
    }

    socialAuthenticationProvider(SocialAuthenticationProvider){
        authenticationManager = ref('authenticationManager')
        socialService = ref('socialService')
        userDetailsService = ref('userDetailsService')

    }
}
