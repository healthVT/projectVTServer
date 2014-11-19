package auth

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails

/**
 * Created by Jay on 11/16/2014.
 */
class SocialAuthenticationToken extends UsernamePasswordAuthenticationToken {
    String token
    String email
    String socialMedia

    public SocialAuthenticationToken(String token, String email, String socialMedia) {
        super(null, null)
        this.token = token
        this.email = email
        this.socialMedia = socialMedia
    }

    public SocialAuthenticationToken(UserDetails principal, String tokenResponse) {
        super(principal, tokenResponse, principal.getAuthorities())
    }
}
