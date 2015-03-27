package projectVT

class IpController {

    def update(String ip, String agent, String path){
        new IpAddress(ip: ip, agent: agent, path: path).save(failOnError: true);
    }
}
