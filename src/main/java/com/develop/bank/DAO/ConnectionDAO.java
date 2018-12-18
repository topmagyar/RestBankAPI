package com.develop.bank.DAO;

import com.develop.bank.model.ConnectionInfo;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface ConnectionDAO {

    void save(String username, String secretKey);
    ConnectionInfo getConnectionInfo(String username);
    void remove(String username);
}
