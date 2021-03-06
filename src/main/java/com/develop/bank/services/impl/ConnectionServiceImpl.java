package com.develop.bank.services.impl;

import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.model.ConnectionInfo;
import com.develop.bank.model.connection.ConnectionModel;
import com.develop.bank.services.ConnectionService;
import com.develop.bank.util.KeyConnection;
import com.develop.bank.util.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = false)
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private ConnectionDAO connectionDAO;

    @Override
    public String setUpConnection(ConnectionModel connection) {
        String privateServerKey = new KeyGenerator().generateKey(10);
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

        ConnectionInfo cnctInf = connectionDAO.getConnectionInfo(connection.getUsername());
        if (cnctInf != null) {
            cnctInf.setInfo(secretKey.toString());
            connectionDAO.update(cnctInf);
        } else {
            connectionDAO.save(connection.getUsername(), secretKey.toString());
        }

        return publicServerKey.toString();
    }

    @Override
    public String getSecretKey(String username) {
        return connectionDAO.getConnectionInfo(username).getInfo();
    }
}
