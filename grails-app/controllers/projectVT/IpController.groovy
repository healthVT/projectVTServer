package projectVT

class IpController {

    def update(String ip, String agent){
        new IpAddress(ip: ip, agent: agent).save(failOnError: true);
    }
}
