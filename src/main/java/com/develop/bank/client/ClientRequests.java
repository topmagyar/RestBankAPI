package com.develop.bank.client;

import com.develop.bank.model.Card;
import com.develop.bank.model.User;
import com.develop.bank.model.card.CardAmountType;
import com.develop.bank.model.connection.ConnectionModel;
import com.develop.bank.model.connection.ConnectionResponse;
import com.develop.bank.model.order.DecreaseOrder;
import com.develop.bank.model.order.IncreaseOrder;
import com.develop.bank.model.order.TransferOrder;
import com.develop.bank.util.CryptTool;
import com.develop.bank.util.KeyConnection;
import com.develop.bank.util.KeyGenerator;
import com.develop.bank.util.PasswordCrypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestOperations;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestClientConfig.class)
public class ClientRequests {

    @Autowired
    private RestOperations rest;

    private String secretKey = "61953";

//    @Test
//    public void testRestRequest() throws Exception {
//        ResponseEntity<String> response = rest.getForEntity("https://localhost:8443/bank-api/bla", String.class);
//        System.out.println("response = " + response);
//        System.out.println("response.getBody() = " + response.getBody());
//    }
//
    @Test
    public void setUpConnectionWithServer() throws Exception {
        KeyConnection keyConnection = new KeyConnection();
        String privateClientKey = new KeyGenerator().generateKey(10);
        String g = new KeyGenerator().generateKey(5);
        String p = new KeyGenerator().generateKey(15);
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
        ResponseEntity<ConnectionResponse> response = rest.postForEntity("https://localhost:8443/bank-api/setUpConnection", connectionModel, ConnectionResponse.class);


        String publicServerKey = response.getBody().getPublicServerKey();
        BigInteger secretKey = keyConnection.calculateSecretKeyForClient(
                new BigInteger(connectionModel.getG()),
                new BigInteger(connectionModel.getP()),
                new BigInteger(privateClientKey),
                new BigInteger(publicServerKey)
        );
        this.secretKey = secretKey.toString();
        System.out.println("secret key: " + secretKey);
    }

    @Test
    public void registerUser() throws Exception {
        User user = new User();
        user.setPassword("123456");
        user.setUsername("user1");
        user.setFirstName("User");
        PasswordCrypt passwordCrypt = new PasswordCrypt();
        String key = passwordCrypt.notZeroDeterm(user.getUsername());
        String password = passwordCrypt.encryptMessage(user.getPassword(), key);
        HttpHeaders headers = new HttpHeaders();
        headers.add("password", new CryptTool().encryptMessageByKey(password, "322728646353086"));
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        ResponseEntity<User> response1 = rest.postForEntity("https://localhost:8443/bank-api/register", request, User.class);
        System.out.println("body: " + response1.getBody().getId());
    }

    @Test
    public void loginUser() {
        String password = "123456";
        String username = "topmagyar";
        PasswordCrypt passwordCrypt = new PasswordCrypt();
        String key = passwordCrypt.notZeroDeterm(username);
        password = passwordCrypt.encryptMessage(password, key);
        HttpHeaders headers = new HttpHeaders();
        headers.add("username", username);
        headers.add("password", new CryptTool().encryptMessageByKey(password, "322728646353086"));
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response1 = rest.postForEntity("https://localhost:8443/bank-api/login", request, String.class);
        System.out.println("body: " + response1.getBody());
    }

    @Test
    public void addCard() {
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        CardAmountType cardAmountType = new CardAmountType("UAH");
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<CardAmountType> request = new HttpEntity<>(cardAmountType, headers);
        ResponseEntity<Card> response = rest.postForEntity("https://localhost:8443/bank-api/cards/add", request, Card.class);
        Card c = response.getBody();
        System.out.println("Card number " + c.getCardNumber());
    }

    @Test
    public void getCards() {
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = rest.exchange("https://localhost:8443/bank-api/cards/get", HttpMethod.GET, request, String.class);
        System.out.println("response " + response.getBody());
    }

    @Test
    public void getCardByID() {
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = rest.exchange("https://localhost:8443/bank-api/cards/get/6", HttpMethod.GET, request, String.class);
        System.out.println("response " + response.getBody());
    }

    @Test
    public void increaseCardAmount() {
        IncreaseOrder increaseOrder = new IncreaseOrder();
        increaseOrder.setAmount((long) 228);
        increaseOrder.setCardNumber("7738879483671899");
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<IncreaseOrder> request = new HttpEntity<>(increaseOrder, headers);

        ResponseEntity<Card> response = rest.postForEntity("https://localhost:8443/bank-api/transfers/increase", request, Card.class);
        System.out.println("Response: " + response.getBody());
    }

    @Test
    public void decreaseCardAmount() {
        DecreaseOrder decreaseOrder = new DecreaseOrder();
        decreaseOrder.setAmount((long) 228);
        decreaseOrder.setCardNumber("7738879483671899");
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<DecreaseOrder> request = new HttpEntity<>(decreaseOrder, headers);

        ResponseEntity<Card> response = rest.postForEntity("https://localhost:8443/bank-api/transfers/decrease", request, Card.class);
        System.out.println("Response: card amount: " + response.getBody().getAmount());
    }

    @Test
    public void transferCardAmount() {
        TransferOrder transferOrder = new TransferOrder();
        transferOrder.setAmount((long) 100);
        transferOrder.setCardFrom("7738879483671899");
        transferOrder.setCardTo("7085553151801804");
        String username = "topmagyar";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiWWVob3IiLCJ1c2VybmFtZSI6InRvcG1hZ3lhciIsImFkbWluIjpmYWxzZX0.dZZqeQY0CxgVEeJdyuKvtUkFWJy5lJgzIs7d5kUVN7M";
        HttpHeaders headers = new HttpHeaders();
        headers.add("token", new CryptTool().encryptMessageByKey(token, "322728646353086"));
        headers.add("username", username);
        HttpEntity<TransferOrder> request = new HttpEntity<>(transferOrder, headers);

        ResponseEntity<TransferOrder> response = rest.postForEntity("https://localhost:8443/bank-api/transfers/add", request, TransferOrder.class);
        System.out.println("Response: " + response.getBody().getAmount());
    }

//    @Test
//    public void registerUser() {
//        String password = "123456";
//        PasswordCrypt passwordCrypt = new PasswordCrypt();
//        String key = passwordCrypt.notZeroDeterm("topmagyar");
//        password = passwordCrypt.encryptMessage(password, key);
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("password", new CryptTool().encryptMessageByKey(password, "268203753"));
//        User user = new User();
//        user.setUsername("topmagyar");
//        user.setFirstName("Yehor");
//        HttpEntity<User> request = new HttpEntity<>(user, headers);
//        ResponseEntity<String> response1 = rest.postForEntity("https://localhost:8443/bank-api/register", request, String.class);
//        System.out.println("body: " + response1.getBody());
//    }

//    @Test
//    public void registerUser() {
//        String password = "abcabc";
//        PasswordCrypt passwordCrypt = new PasswordCrypt();
//        String key = passwordCrypt.notZeroDeterm("passwordCodeKey");
//        password = passwordCrypt.encryptMessage(password, key);
//        HttpHeaders headers = new HttpHeaders();
//        CryptTool cryptTool = new CryptTool();
//        String ecnryptedPassword = cryptTool.encryptMessageByKey(password, secretKey);
//        headers.add("password", new CryptTool().encryptMessageByKey(password, secretKey));
//        User user = new User();
//        user.setUsername("topmagyar");
//        user.setFirstName("Yehor");
//        HttpEntity<User> request = new HttpEntity<>(user, headers);
//        ResponseEntity<String> response1 = rest.postForEntity("https://localhost:8443/bank-api/register", request, String.class);
//        System.out.println("body: " + response1.getBody());
//    }


//    @Test
//    public void setUpConnectionWithServer() throws Exception {
//        KeyConnection keyConnection = new KeyConnection();
//        String privateClientKey = "12345";
//        String g = "321";
//        String p = "123456";
//        BigInteger publicClientKey = keyConnection.calculatePublicClientKey(
//                new BigInteger(privateClientKey),
//                new BigInteger(g),
//                new BigInteger(p)
//        );
//        ConnectionModel connectionModel = new ConnectionModel();
//        connectionModel.setG(g);
//        connectionModel.setP(p);
//        connectionModel.setUsername("user1");
//        connectionModel.setClientPublicKey(publicClientKey.toString());
//        ResponseEntity<ConnectionResponse> response = rest.postForEntity("https://localhost:8443/bank-api/setUpConnection", connectionModel, ConnectionResponse.class);
//
//        String publicServerKey = response.getBody().getPublicServerKey();
//        BigInteger secretKey = keyConnection.calculateSecretKeyForClient(
//                new BigInteger(connectionModel.getG()),
//                new BigInteger(connectionModel.getP()),
//                new BigInteger(privateClientKey),
//                new BigInteger(publicServerKey)
//        );
//        this.secretKey = secretKey.toString();
//        int q = 12321;
//    }

}
