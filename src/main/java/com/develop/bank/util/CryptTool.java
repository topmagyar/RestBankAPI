package com.develop.bank.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class CryptTool {

    private Map<String, Long> coderMap = new HashMap<>();

    private void createCodeValues() {
        for (int i = 0; i < 10; i++) {
            coderMap.put(String.valueOf(i), (long) i);
        }
        long counter = 10;
        for (char q = 'a'; q <= 'z'; q++) {
            coderMap.put(String.valueOf(q), counter);
            counter++;
        }
        for (char q = 'A'; q <= 'Z'; q++) {
            coderMap.put(String.valueOf(q), counter);
            counter++;
        }
        coderMap.put(" ", counter);
        counter++;
        coderMap.put("!", counter);
        counter++;
        coderMap.put("@", counter);
        counter++;
        coderMap.put("#", counter);
        counter++;
        coderMap.put("$", counter);
    }

    private String updateKey(String key, int l) {
        StringBuilder updatedKey = new StringBuilder();
        updatedKey.append(key);
        while (updatedKey.length() < l * 2) {
            updatedKey.append(key);
        }
        return updatedKey.toString();
    }

    private List<Long> convertMessageToList(String message) {
        List<Long> l = new ArrayList<>();
        for (int i = 0; i < message.length(); i++) {
            l.add(coderMap.get(String.valueOf(message.charAt(i))));
        }
        return l;
    }

    private String getCharById(long id) {
        for (String key : coderMap.keySet()) {
            if (coderMap.get(key) == id) {
                return key;
            }
        }
        return null;
    }

    private List<Long> convertKeyToList(String key) {
        List<Long> l = new ArrayList<>();
        for (int i = 0; i < key.length(); i+=2) {
            if (i + 1 < key.length()) {
                long val = Long.valueOf(String.valueOf(key.charAt(i)) + String.valueOf(key.charAt(i + 1)));
                l.add(val);
            }
        }
        return l;
    }

    public String encryptMessageByKey(String message, String key) {
        createCodeValues();
        key = updateKey(key, message.length());
        List<Long> msgList = convertMessageToList(message);
        List<Long> keyList = convertKeyToList(key);
        int counter = 0;
        List<Long> resultList = new ArrayList<>();
        for (Long item : msgList) {
            long val = (item + keyList.get(counter)) % coderMap.size();
            resultList.add(val);
            counter++;
        }
        StringBuilder encryptMessage = new StringBuilder();
        for (Long val : resultList) {
            encryptMessage.append(getCharById(val));
        }
        return encryptMessage.toString();
    }

    public String decryptMessageByKey(String message, String key) {
        createCodeValues();
        key = updateKey(key, message.length());
        List<Long> msgList = convertMessageToList(message);
        List<Long> keyList = convertKeyToList(key);
        int counter = 0;
        List<Long> resultList = new ArrayList<>();
        for (Long item : msgList) {
            long val = (item - keyList.get(counter)) % coderMap.size();
            while (val < 0) {
                val += coderMap.size();
            }
            resultList.add(val);
            counter++;
        }
        StringBuilder decryptMessage = new StringBuilder();
        for (Long val : resultList) {
            decryptMessage.append(getCharById(val));
        }
        return decryptMessage.toString();
    }

}
