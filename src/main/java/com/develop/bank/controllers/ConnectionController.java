package com.develop.bank.controllers;

import com.develop.bank.model.connection.ConnectionModel;
import com.develop.bank.model.connection.ConnectionResponse;
import com.develop.bank.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class ConnectionController {

    @Autowired
    ConnectionService connectionService;

    @PostMapping("/setUpConnection")
    @ResponseBody
    public ConnectionResponse setUpConnection(@RequestBody ConnectionModel connectionModel) {
        ConnectionResponse connectionResponse = new ConnectionResponse();
        connectionResponse.setPublicServerKey(connectionService.setUpConnection(connectionModel));
        return connectionResponse;
    }
}
