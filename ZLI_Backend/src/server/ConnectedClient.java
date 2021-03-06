package server;

import ocsf.server.ConnectionToClient;

public class ConnectedClient {
    private String ip;
    private String host;
    private String status;
    private ConnectionToClient client;

    public ConnectedClient(ConnectionToClient client) {
        this.ip = client.getInetAddress().getHostAddress();
        this.host = client.getInetAddress().getHostName();
        this.status = "Connected";
        this.client = client;
    }

    
    /** 
     * @return String
     */
    public String getIp() {
        return ip;
    }

    
    /** 
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    
    /** 
     * @return String
     */
    public String getHost() {
        return host;
    }

    
    /** 
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    
    /** 
     * @return String
     */
    public String getStatus() {
        return status;
    }

    
    /** 
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    
    /** 
     * @return ConnectionToClient
     */
    public ConnectionToClient getClient() {
        return client;
    }

    
    /** 
     * @param client
     */
    public void setClient(ConnectionToClient client) {
        this.client = client;
    }

    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConnectedClient other = (ConnectedClient) obj;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        return true;
    }

}
