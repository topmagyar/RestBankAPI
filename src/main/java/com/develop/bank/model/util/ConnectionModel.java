package com.develop.bank.model.util;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class ConnectionModel {

    private String clientPublicKey;
    private String g;
    private String p;
    private String serverPublicKey;
    private String username;

    public void setClientPublicKey(String clientPublicKey) {
        this.clientPublicKey = clientPublicKey;
    }

    public void setG(String g) {
        this.g = g;
    }

    public void setP(String p) {
        this.p = p;
    }

    public void setServerPublicKey(String serverPublicKey) {
        this.serverPublicKey = serverPublicKey;
    }

    public String getClientPublicKey() {
        return clientPublicKey;
    }

    public String getG() {
        return g;
    }

    public String getP() {
        return p;
    }

    public String getServerPublicKey() {
        return serverPublicKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
