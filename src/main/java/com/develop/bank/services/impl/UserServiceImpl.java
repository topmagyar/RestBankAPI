package com.develop.bank.services.impl;

import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.DAO.TokenDAO;
import com.develop.bank.DAO.UserDAO;
import com.develop.bank.model.User;
import com.develop.bank.model.ValidToken;
import com.develop.bank.services.UserService;
import com.develop.bank.util.CryptTool;
import com.develop.bank.util.PasswordCrypt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private ConnectionDAO connectionDAO;

    private PasswordCrypt passwordCrypt = new PasswordCrypt();

    @Transactional
    @Override
    public long save(User user) {
        String secretKey = connectionDAO.getConnectionInfo(user.getUsername()).getInfo();
        String password = user.getPassword();
        String key = passwordCrypt.notZeroDeterm(user.getUsername());
        String originalPassword = new CryptTool().decryptMessageByKey(password, secretKey);
        user.setPassword(originalPassword);
        originalPassword = passwordCrypt.decryptMessage(originalPassword, key);
        String token = "null";
        try {
            token = generateToken(user);
        } catch (Exception e) {
        }
        user.setToken(token);
        if (token != "null") {
            tokenDAO.save(token);
        }

        return userDAO.save(user);
    }

    public List<User> getUsers(String token) {
        User userObject = userDAO.getUser("token", token);
        ValidToken validToken = tokenDAO.get(token);
        if (validToken == null) {
            return null;
        }
        String user = null;
        try {
            user = checkToken(token);
        } catch (Exception e) {

        }
        if (userObject.getUsername().equals(user)) return userDAO.getUsers();
        return null;
    }

    private String checkToken(String jwt) throws Exception {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey("secret".getBytes("UTF-8"))
                .parseClaimsJws(jwt);
        String username = (String) claims.getBody().get("scope");
        return username;
    }

    private String generateToken(User user) throws Exception {
        String jwt = Jwts.builder()
//                .setSubject("users/TzMUocMF4p")
//                .setExpiration(new Date(1300819380))
                .claim("name", user.getFirstName())
                .claim("username", user.getUsername())
                .claim("admin", false)
                .signWith(
                        SignatureAlgorithm.HS256,
                        "secret".getBytes("UTF-8")
                )
                .compact();

        System.out.println("TOKEN for " + user.getUsername() + " - " + jwt);
        return jwt;
    }
}
