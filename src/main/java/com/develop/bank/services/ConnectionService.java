package com.develop.bank.services;

import com.develop.bank.model.util.ConnectionModel;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */


public interface ConnectionService {

    String setUpConnection(ConnectionModel connection);
}