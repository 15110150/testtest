package server.crm.responses;


public class ServerInfo {
    private String name;
    private String version;
    private String build_no;
    private String ip;
    private String port;
    private String database_host;
    private String database_name;

    public  String getName() {
        return name;
    }

    public  ServerInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public ServerInfo setVersion(String version) {
        this.version = version;
        return this;
    }

    public String getBuild_no() {
        return build_no;
    }

    public ServerInfo setBuild_no(String build_no) {
        this.build_no = build_no;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public ServerInfo setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getPort() {
        return port;
    }

    public ServerInfo setPort(String port) {
        this.port = port;
        return this;
    }

    public String getDatabase_host() {
        return database_host;
    }

    public ServerInfo setDatabase_host(String database_host) {
        this.database_host = database_host;
        return this;
    }

    public String getDatabase_name() {
        return database_name;
    }

    public ServerInfo setDatabase_name(String database_name) {
        this.database_name = database_name;
        return this;
    }

}
