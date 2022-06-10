abstract class SmartObject {

    private String alias;
    private String macId;
    private String IP;
    private boolean connectionStatus;

    public SmartObject() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMacId() {
        return macId;
    }

    public void setMacId(String macId) {
        this.macId = macId;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public boolean isConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public boolean connect(String IP) {
        this.setIP(IP);
        this.setConnectionStatus(true);
        System.out.println(this.alias + " connection established");
        return true;
    }

    public boolean disconnect() {
        this.setConnectionStatus(false);
        return true;

    }

    public void SmartObjectToString() {

        System.out.println("    MacId: " + this.macId);
        System.out.println("    IP: " + this.IP);
    }

    public boolean controlConnection() {return false;
    }

    public boolean testObject() {
        return false;

    }

    public boolean shutDownObject() {
        return false;
    }
}
