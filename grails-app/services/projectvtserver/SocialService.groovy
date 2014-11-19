package projectvtserver

import grails.transaction.Transactional
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.RESTClient

@Transactional
class SocialService {

    final String tokenInfo = "https://www.googleapis.com/"

    def validateToken(String token){

        def result = [success: false]
        try{
            def http = new HTTPBuilder("https://www.googleapis.com")
            http.request( Method.GET, ContentType.JSON ) { req ->
                uri.path = '/oauth2/v1/tokeninfo'
                uri.query = [ 'access_token': token ]

                response.success = { resp, json ->
                    result = [success: true, socialId: json.user_id]
                }
            }
        }catch(Exception e){
            e.printStackTrace()
        }

    }


}
