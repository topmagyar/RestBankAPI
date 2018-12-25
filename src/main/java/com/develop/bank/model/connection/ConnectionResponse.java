package com.develop.bank.model.connection;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class ConnectionResponse {

    private String publicServerKey;

    public void setPublicServerKey(String publicServerKey) {
        this.publicServerKey = publicServerKey;
    }

    public String getPublicServerKey() {
        return publicServerKey;
    }
}
