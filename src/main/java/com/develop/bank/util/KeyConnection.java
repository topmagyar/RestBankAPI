package com.develop.bank.util;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class KeyConnection {

    public BigInteger binpow(BigInteger a, BigInteger n, BigInteger mod) {
        if (n.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ONE;
        if (n.mod(new BigInteger("2")).compareTo(new BigInteger("1")) == 0) {
            return binpow(a, n.subtract(BigInteger.ONE), mod).mod(mod).multiply(a).mod(mod);
        } else {
            BigInteger b = binpow(a, n.divide(new BigInteger("2")), mod).mod(mod);
            return b.multiply(b).mod(mod);
        }
    }

    public BigInteger calculatePublicClientKey(BigInteger privateClientKey, BigInteger g, BigInteger p) {
        BigInteger A = binpow(g, privateClientKey, p);
        return A;
    }

    public BigInteger calculatePublicServerKey(BigInteger g, BigInteger p, BigInteger privateServerKey) {
        BigInteger B = binpow(g, privateServerKey, p);
        return B;
    }

    public BigInteger calculateSecretKeyForClient(BigInteger g, BigInteger p, BigInteger privateClientKey, BigInteger publicServerKey) {
        BigInteger K = binpow(publicServerKey, privateClientKey, p);
        return K;
    }

    public BigInteger calculateSecretKeyForServer(BigInteger g, BigInteger p, BigInteger publicClientKey, BigInteger privateServerKey) {
        BigInteger K = binpow(publicClientKey, privateServerKey, p);
        return K;
    }

}
