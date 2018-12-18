package com.develop.bank.controllers;

import com.develop.bank.model.util.ConnectionModel;
import com.develop.bank.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RestController
public class ConnectionController {

    @Autowired
    ConnectionService connectionService;

    @PostMapping("/setUpConnection")
    public String setUpConnection(@RequestBody ConnectionModel connectionModel) {
        return connectionService.setUpConnection(connectionModel);
    }
}
