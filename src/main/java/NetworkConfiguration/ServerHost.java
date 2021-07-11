package NetworkConfiguration;

public enum ServerHost {
    HOST("localhost");

    private String hostName;

    ServerHost(String hostName) {
        this.hostName = hostName;
    }

    public String getHostName() {
        return hostName;
    }
}
