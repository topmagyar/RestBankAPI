package com.develop.bank.client;

import com.develop.bank.model.User;
import com.develop.bank.model.util.ConnectionModel;
import com.develop.bank.util.CryptTool;
import com.develop.bank.util.KeyConnection;
import com.develop.bank.util.PasswordCrypt;
import org.codehaus.jackson.node.BigIntegerNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
public class ClientRequests {

    @Autowired
    private RestOperations rest;

    private String secretKey = "";

    @Test
    public void testRestRequest() throws Exception {
        ResponseEntity<String> response = rest.getForEntity("https://localhost:8443/bank-api/bla", String.class);
        System.out.println("response = " + response);
        System.out.println("response.getBody() = " + response.getBody());
    }

    @Test
    public void setUpConnectionWithServer() throws Exception {
        KeyConnection keyConnection = new KeyConnection();
        String privateClientKey = "12";
        String g = "12";
        String p = "1000000007";
        BigInteger publicClientKey = keyConnection.calculatePublicClientKey(
                new BigInteger(privateClientKey),
                new BigInteger(g),
                new BigInteger(p)
        );
        ConnectionModel connectionModel = new ConnectionModel();
        connectionModel.setG(g);
        connectionModel.setP(p);
        connectionModel.setUsername("topmagyar");
        connectionModel.setClientPublicKey(publicClientKey.toString());
        ResponseEntity<String> response = rest.postForEntity("https://localhost:8443/bank-api/setUpConnection", connectionModel, String.class);
//        System.out.println("response.getBody() = " + response.getBody());


        String publicServerKey = response.getBody();
        BigInteger secretKey = keyConnection.calculateSecretKeyForClient(
                new BigInteger(connectionModel.getG()),
                new BigInteger(connectionModel.getP()),
                new BigInteger(privateClientKey),
                new BigInteger(publicServerKey)
        );
        this.secretKey = secretKey.toString();
    }

    @Test
    public void loginUser() {
        String password = "123456";
        PasswordCrypt passwordCrypt = new PasswordCrypt();
        String key = passwordCrypt.notZeroDeterm("topmagyar");
        password = passwordCrypt.encryptMessage(password, key);
        HttpHeaders headers = new HttpHeaders();
        headers.add("username", "topmagyar");
        headers.add("password", new CryptTool().encryptMessageByKey(password, "268203753"));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response1 = rest.postForEntity("https://localhost:8443/bank-api/login", request, String.class);
        System.out.println("body: " + response1.getBody());
    }

    @Test
    public void registerUser() {
        String password = "123456";
        PasswordCrypt passwordCrypt = new PasswordCrypt();
        String key = passwordCrypt.notZeroDeterm("topmagyar");
        password = passwordCrypt.encryptMessage(password, key);
        HttpHeaders headers = new HttpHeaders();
        headers.add("password", new CryptTool().encryptMessageByKey(password, "268203753"));
        User user = new User();
        user.setUsername("topmagyar");
        user.setFirstName("Yehor");
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<String> response1 = rest.postForEntity("https://localhost:8443/bank-api/register", request, String.class);
        System.out.println("body: " + response1.getBody());
    }

}
