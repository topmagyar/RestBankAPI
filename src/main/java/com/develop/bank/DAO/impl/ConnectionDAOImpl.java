package com.develop.bank.DAO.impl;

import com.develop.bank.DAO.ConnectionDAO;
import com.develop.bank.model.ConnectionInfo;
import com.develop.bank.model.connection.ConnectionModel;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

@Repository
public class ConnectionDAOImpl implements ConnectionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(String username, String secretKey) {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        connectionInfo.setInfo(secretKey);
        connectionInfo.setUsername(username);

        sessionFactory.getCurrentSession().save(connectionInfo);
    }

    @Override
    public ConnectionInfo getConnectionInfo(String username) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ConnectionInfo.class)
                .add(Restrictions.eq("username", username));
        Object result = criteria.uniqueResult();
        return (ConnectionInfo) result;
    }

    @Override
    public void remove(String username) {
        ConnectionInfo connectionInfo = getConnectionInfo(username);
        sessionFactory.getCurrentSession().delete(connectionInfo);
    }

    @Override
    public ConnectionInfo update(ConnectionInfo connectionInfo) {
        sessionFactory.getCurrentSession().update(connectionInfo);
        return connectionInfo;
    }

//
//
//    ClassLoader classLoader = getClass().getClassLoader();
//    File file = new File(classLoader.getResource("connections.txt").getFile());
//
//    @Override
//    public void save(String username, String secretKey) {
//        String res = username + "/" + secretKey;
//        if (file != null) {
//            try {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//                bw.append(res);
//                bw.close();
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    @Override
//    public ConnectionInfo getConnectionInfo(String username) {
//        if (file != null) {
//            List<String> list = readFile(file);
//            String key = checkExist(list, username);
//            if (key != null) {
//                ConnectionInfo connectionInfo = new ConnectionInfo();
//                connectionInfo.setUsername(username);
//                connectionInfo.setInfo(key);
//                return connectionInfo;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public void remove(String username) {
//
//        ConnectionInfo connectionInfo = getConnectionInfo(username);
//        sessionFactory.getCurrentSession().delete(connectionInfo);
//    }
//
//    private List<String> readFile(File file) {
//        List<String> list = new ArrayList<>();
//        try (Scanner scanner = new Scanner(file)) {
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                list.add(line);
//            }
//
//            scanner.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    private String checkExist(List<String> list, String username) {
//        for (String s : list) {
//            int id = s.indexOf("/");
//            String usr = s.substring(0, id - 1);
//            if (usr.equals(username)) {
//                return s.substring(id + 1);
//            }
//        }
//        return null;
//    }



}
