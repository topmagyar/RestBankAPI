package com.develop.bank.util;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class KeyConnection {

//    public BigInteger privateAliceKey = new BigInteger("31231232");
//    public BigInteger privateBobKey = new BigInteger("83123213232");

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

    public void main() {
        BigInteger g = new BigInteger("23432432432423432132123123123213214324232");
        BigInteger p = new BigInteger("1312314234243243243242342342343321312321323242232329");
//        BigInteger publicClientKey = calculatePublicClientKey(privateAliceKey, g, p);
//        BigInteger publicServerKey = calculatePublicServerKey(g, p, publicClientKey);
//
//
//        BigInteger clientSecretKey = calculateSecretKeyForClient(g, p, publicClientKey, publicServerKey);
//        BigInteger serverSecretKey = calculateSecretKeyForServer(g, p, publicClientKey);
//
//        System.out.println("Alice Secret Key = " + privateAliceKey);
//        System.out.println("Bob Secret Key = " + privateBobKey);
//        System.out.println("Secret Key = " + serverSecretKey);
//
//
//        System.out.println(clientSecretKey.compareTo(serverSecretKey) == 0);
//        System.out.println(binpow(new BigInteger("10000"), new BigInteger("10"), new BigInteger("123921321312312321321321321312731286392163712638712638712638131232131232131232763786123")));
    }
}
