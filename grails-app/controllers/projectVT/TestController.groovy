package projectVT

class TestController {
    def socialService

    def testToken(String token){
        socialService.validateToken(token);
    }
}
