package com.develop.bank.services.impl;

import com.develop.bank.model.util.ConnectionModel;
import com.develop.bank.services.ConnectionService;
import com.develop.bank.util.KeyConnection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = true)
public class ConnectionServiceImpl implements ConnectionService {

    @Override
    public String setUpConnection(ConnectionModel connection) {
        String privateServerKey = "31";
        KeyConnection keyConnection = new KeyConnection();
        BigInteger publicServerKey = keyConnection.calculatePublicServerKey(
                new BigInteger(connection.getG()),
                new BigInteger(connection.getP()),
                new BigInteger(privateServerKey));

        BigInteger secretKey = keyConnection.calculateSecretKeyForServer(
                new BigInteger(connection.getG()),
                new BigInteger(connection.getP()),
                new BigInteger(connection.getClientPublicKey()),
                new BigInteger(privateServerKey)
        );

        return publicServerKey.toString();
    }
}
