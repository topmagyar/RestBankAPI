package com.develop.bank.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
public class ClientRequests {

    @Autowired
    private RestOperations rest;

    @Test
    public void testRestRequest() throws Exception {
        ResponseEntity<String> response = rest.getForEntity("https://localhost:8443/bank-api/bla", String.class);
        System.out.println("response = " + response);
        System.out.println("response.getBody() = " + response.getBody());
    }

}
