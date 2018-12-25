package com.develop.bank.util;

import java.util.Random;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class KeyGenerator {

    public String generateKey(long length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(String.valueOf(new Random().nextInt(9) + 1));
            } else {
                builder.append(String.valueOf(new Random().nextInt(10)));
            }
        }
        return builder.toString();
    }
}
