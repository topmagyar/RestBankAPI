package com.develop.bank.util;

import java.math.BigInteger;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class KeyConnection {

    public static BigInteger privateAliceKey = new BigInteger("31231232");
    public static BigInteger privateBobKey = new BigInteger("83123213232");

    static BigInteger binpow(BigInteger a, BigInteger n, BigInteger mod) {
        if (n.compareTo(BigInteger.ZERO) == 0)
            return BigInteger.ONE;
        if (n.mod(new BigInteger("2")).compareTo(new BigInteger("1")) == 0) {
            return binpow(a, n.subtract(BigInteger.ONE), mod).mod(mod).multiply(a).mod(mod);
        } else {
            BigInteger b = binpow(a, n.divide(new BigInteger("2")), mod).mod(mod);
            return b.multiply(b).mod(mod);
        }
    }

    static BigInteger calculatePublicClientKey(BigInteger a, BigInteger g, BigInteger p) {
        BigInteger A = binpow(g, a, p);
        return A;
    }

    static BigInteger calculatePublicServerKey(BigInteger g, BigInteger p, BigInteger A) {
        BigInteger B = binpow(g, privateBobKey, p);
        return B;
    }

    static BigInteger calculateSecretKeyForClient(BigInteger g, BigInteger p, BigInteger publicAliceKey, BigInteger publicBobKey) {
        BigInteger K = binpow(publicBobKey, privateAliceKey, p);
        return K;
    }

    static BigInteger calculateSecretKeyForServer(BigInteger g, BigInteger p, BigInteger publicAliceKey) {
        BigInteger K = binpow(publicAliceKey, privateBobKey, p);
        return K;
    }

    public static void main(String[] args) {
        BigInteger g = new BigInteger("23432432432423432132123123123213214324232");
        BigInteger p = new BigInteger("1312314234243243243242342342343321312321323242232329");
        BigInteger publicClientKey = calculatePublicClientKey(privateAliceKey, g, p);
        BigInteger publicServerKey = calculatePublicServerKey(g, p, publicClientKey);


        BigInteger clientSecretKey = calculateSecretKeyForClient(g, p, publicClientKey, publicServerKey);
        BigInteger serverSecretKey = calculateSecretKeyForServer(g, p, publicClientKey);

        System.out.println("Alice Secret Key = " + privateAliceKey);
        System.out.println("Bob Secret Key = " + privateBobKey);
        System.out.println("Secret Key = " + serverSecretKey);


        System.out.println(clientSecretKey.compareTo(serverSecretKey) == 0);
//        System.out.println(binpow(new BigInteger("10000"), new BigInteger("10"), new BigInteger("123921321312312321321321321312731286392163712638712638712638131232131232131232763786123")));
    }
}
