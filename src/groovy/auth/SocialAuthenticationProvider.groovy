package auth

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.util.Assert
import projectVT.SocialAuth
import projectVT.User


/**
 * Created by Jay on 11/16/2014.
 */
class SocialAuthenticationProvider extends DaoAuthenticationProvider {

    AuthenticationManager authenticationManager
    def socialService

    @Override
    Authentication authenticate(Authentication authentication) {
        def socialAuth = (SocialAuthenticationToken) authentication

        //TODO link to google, if  the token is valide, then find user
        def validated = socialService.validateToken(socialAuth.token);
        println "validated-->"
println validated
        if(validated.success) {
            def user = null
            user = User.findWhere(email: socialAuth.email)

            if(!user){ return null }

            def socialUser = SocialAuth.findByUser(user)
            if(!socialUser){
                SocialAuth.withTransaction { status ->
                    socialUser = new SocialAuth(user: user, socialMedia: socialAuth.socialMedia, socialId: validated.socialId).save(failOnError: true)
                }
            }
                if (user) {
                    def userDetails = userDetailsService.loadUserByUsername(user.email)
                    def token = new SocialAuthenticationToken(userDetails, socialAuth.token)
                    return token
                } else {
                    return null
                }
            }




    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (SocialAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
