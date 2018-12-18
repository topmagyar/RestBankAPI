package com.develop.bank.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public class PasswordCrypt {

    private static List<List<Long>> coderMatrix = new ArrayList<>();
    private static Map<String, Long> coderMap = new HashMap<>();

    private static String updateCodeKey(String codeKey) {
        int l = codeKey.length();
        int x = 1;
        while (x * x < l) {
            x++;
        }
        int diff = x*x - l;
        StringBuilder stringBuilder = new StringBuilder(codeKey);
        while (diff > 0) {
            stringBuilder.append(" ");
            diff--;
        }
        return stringBuilder.toString();
    }

    private static List<List<Long>> findCoderMatrix(String codeKey) {
        List<List<Long>> matrix = new ArrayList<>();
        List<Long> l = new ArrayList<>();
        for (int i = 0; i < codeKey.length(); i++) {
            l.add(coderMap.get(String.valueOf(codeKey.charAt(i))));
        }

        int n = (int) Math.sqrt(l.size());
        int counter = 0;
        List<Long> list = new ArrayList<>();
        for (Long i : l) {
            list.add(i);
            counter = (counter + 1) % n;
            if (counter == 0) {
                matrix.add(list);
                list = new ArrayList<>();
            }
        }

        return matrix;
    }

    private static void createCodeValues() {
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

    private static List<List<Long>> convertToMatrix(String message, int n) {
        List<List<Long>> l = new ArrayList<>();
        List<Long> list = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < message.length(); i++) {
            list.add(coderMap.get(String.valueOf(message.charAt(i))));
            counter = (counter + 1) % n;
            if (counter == 0) {
                l.add(list);
                list = new ArrayList<>();
            }
        }
        if (counter != 0) {
            int diff = n - counter;
            while (diff > 0) {
                list.add((long) 67);
                diff--;
            }
            l.add(list);
        }
        return l;
    }

    private static List<List<Long>> encryptMatrixMsg(List<List<Long>> matrixMsg, List<List<Long>> coderMatrix) {
        List<List<Long>> result = new ArrayList<>();
        int n = coderMatrix.size();
        for (List<Long> msg : matrixMsg) {
            List<Long> r = new ArrayList<>();
            for (int i = 0; i < n; i++) {

                long res = 0;

                for (int j = 0; j < n; j++) {
                    res += (msg.get(j) * coderMatrix.get(j).get(i)) % coderMap.size();
                    res %= coderMap.size();
                }

                r.add(res);
            }
            result.add(r);
        }
        return result;
    }

    private static String getCharById(long id) {
        for (String key : coderMap.keySet()) {
            if (coderMap.get(key) == id) {
                return key;
            }
        }
        return null;
    }

    private static List<List<Long>> findSubMatrix(List<List<Long>> matrix, int row, int column) {
        List<List<Long>> subMatrix = new ArrayList<>();
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            if (row != i) {
                List<Long> lineList = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if (j != column) {
                        lineList.add(matrix.get(i).get(j));
                    }
                }
                subMatrix.add(lineList);
            }
        }
        return subMatrix;
    }

    private static Long findMatrixDeterm(List<List<Long>> matrix) {
        if (matrix.size() == 1) {
            return matrix.get(0).get(0);
        }
        if (matrix.size() == 2) {
            return matrix.get(0).get(0)*matrix.get(1).get(1) - matrix.get(1).get(0)*matrix.get(0).get(1);
        } else {
            long determ = 0;
            int n = matrix.size();
            for (int j = 0; j < n; j++) {
                List<List<Long>> subMatrix = findSubMatrix(matrix, 0, j);
                if ((j+2) % 2 == 0) {
                    determ += matrix.get(0).get(j) * findMatrixDeterm(subMatrix);
                } else {
                    determ -= matrix.get(0).get(j) * findMatrixDeterm(subMatrix);
                }
            }
            return determ;
        }
    }

    private static long gcd(long a, long b, G g) {
        if (a == 0) {
            g.setX(0);
            g.setY(1);
//            return Math.abs(b);
            return b;
        }
        G g1 = new G();
        long d = gcd(b%a, a, g1);
        g.setX(g1.getY() - (b / a) * g1.getX());
        g.setY(g1.getX());
        return d;

    }

    static class G {
        long x;
        long y;

        public G() {

        }

        public G(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setX(long x) {
            this.x = x;
        }

        public void setY(long y) {
            this.y = y;
        }

        public long getX() {
            return x;
        }

        public long getY() {
            return y;
        }
    }

    private static long findRevertDetermValue(long determ, long x) {
        if (determ < 0 && x >= 0) {
            return x;
        } else if (determ >= 0 && x < 0) {
            return coderMap.size() + x;
        } else if (determ >= 0 && x >= 0) {
            return x;
        } else {
            return Math.abs(x);
        }
    }

    private static List<List<Long>> revertMatrix(List<List<Long>> matrix) {
        List<List<Long>> revertMatrix = new ArrayList<>();
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            List<Long> l = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                List<List<Long>> subMatrix = findSubMatrix(matrix, i, j);
                long determ = findMatrixDeterm(subMatrix);
                if ((i+j) % 2 == 0) {
                    l.add(determ);
                } else {
                    l.add(-determ);
                }
            }
            revertMatrix.add(l);
        }
        return revertMatrix;
    }

    private static List<List<Long>> modMatrix(List<List<Long>> matrix, long mod) {
        List<List<Long>> modMatrix = new ArrayList<>();
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            List<Long> l = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) < 0) {
                    l.add(-Math.abs(matrix.get(i).get(j) % mod));
                } else {
                    l.add(matrix.get(i).get(j) % mod);
                }
            }
            modMatrix.add(l);
        }
        return modMatrix;
    }

    private static List<List<Long>> multiplyMatrix(List<List<Long>> matrix, long value) {
        List<List<Long>> multiplyMatrix = new ArrayList<>();
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            List<Long> l = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                l.add(matrix.get(i).get(j) * value);
            }
            multiplyMatrix.add(l);
        }
        return multiplyMatrix;
    }

    private static List<List<Long>> transportMatrix(List<List<Long>> matrix) {
        List<List<Long>> transportMatrix = new ArrayList<>();
        int n = matrix.size();
        for (int j = 0; j < n; j++) {
            List<Long> l = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                l.add(matrix.get(i).get(j));
            }
            transportMatrix.add(l);
        }
        return transportMatrix;
    }

    private static List<List<Long>> removeNegativeElements(List<List<Long>> matrix, long value) {
        List<List<Long>> m = new ArrayList<>();
        int n = matrix.size();
        for (int i = 0; i < n; i++) {
            List<Long> l = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) < 0) {
                    l.add(matrix.get(i).get(j) + value);
                } else {
                    l.add(matrix.get(i).get(j));
                }
            }
            m.add(l);
        }
        return m;
    }

    private static G brut(long a, long b, long d) {
        G g = new G();
        for (long x = -1000; x < 1000; x++) {
            for (long y = -1000; y < 1000; y++) {
                if (a*x+b*y == d) {
                    g.setX(x);
                    g.setY(y);
                    return g;
                }
            }
        }
        return null;
    }

    private static void qq(List<List<Long>> m) {
        for (List<Long> l : m) {
            System.out.println(l);
        }
        System.out.println("");
    }

    private static String decryptMessage(String cryptMessage, String codeKey) {
        String decryptedMessage = "";
        codeKey = updateCodeKey(codeKey);
        coderMatrix = findCoderMatrix(codeKey);
//        System.out.println("Coder matrix:");
//        qq(coderMatrix);

        List<List<Long>> matrixCryptMsg = convertToMatrix(cryptMessage, (int) Math.sqrt(codeKey.length()));
//        qq(matrixCryptMsg);

        long determ = findMatrixDeterm(coderMatrix);
//        System.out.println("determ: " + determ);
        if (determ == 0) {
            System.out.println("ERROR");
        } else {
            G g = new G();
            long d = gcd(Math.abs(determ), coderMap.size(), g);
            long x, y;
            if (determ < 0) {
//                x = -Math.abs(g.getX());
                x = -g.getX();
//                y = -Math.abs(g.getY());
                y = -g.getY();
            } else {
                x = g.getX();
                y = g.getY();
            }
//            g = brut(determ, coderMap.size(), d);
//            System.out.println("determ: " + determ);
//            System.out.println("d: " + d);
//            long x = g.getX();
//            System.out.println("x: " + x);
//            long y = g.getY();
//            System.out.println("y: " + y);


//            long revertD = findRevertDetermValue(determ, x);
//            x = 10;
            long revertD = (x % coderMap.size() + coderMap.size()) % coderMap.size();
//            System.out.println("revert d: " + revertD);
            List<List<Long>> revertCoderMatrix = revertMatrix(coderMatrix);
//            System.out.println("Revert matrix:");
//            qq(revertCoderMatrix);

            revertCoderMatrix = modMatrix(revertCoderMatrix, coderMap.size());
//            System.out.println("Mode matrix:");
//            qq(revertCoderMatrix);
            revertCoderMatrix = multiplyMatrix(revertCoderMatrix, revertD);
//            qq(revertCoderMatrix);
            revertCoderMatrix = modMatrix(revertCoderMatrix, coderMap.size());
//            qq(revertCoderMatrix);
            revertCoderMatrix = transportMatrix(revertCoderMatrix);
//            qq(revertCoderMatrix);
            revertCoderMatrix = removeNegativeElements(revertCoderMatrix, coderMap.size());
//            qq(revertCoderMatrix);

            List<List<Long>> matrixMsg = encryptMatrixMsg(matrixCryptMsg, revertCoderMatrix);
//            qq(matrixMsg);
            StringBuilder builder = new StringBuilder(decryptedMessage);
            for (List<Long> list : matrixMsg) {
                for (Long q : list) {
                    builder.append(getCharById(q));
                }
            }
            decryptedMessage = builder.toString();
            decryptedMessage = decryptedMessage.trim();
//            System.out.println("|" + decryptedMessage + "|");
        }

        return decryptedMessage;
    }

    private static String encryptMessage(String message, String codeKey) {
        createCodeValues();
        codeKey = updateCodeKey(codeKey);
        coderMatrix = findCoderMatrix(codeKey);

        List<List<Long>> matrixMsg = convertToMatrix(message, (int) Math.sqrt(codeKey.length()));
        List<List<Long>> cryptedMatrixMsg = encryptMatrixMsg(matrixMsg, coderMatrix);

        String cryptMessage = "";
        StringBuilder builder = new StringBuilder(cryptMessage);
        for (List<Long> list : cryptedMatrixMsg) {
            for (Long q : list) {
                builder.append(getCharById(q));
            }
        }
        cryptMessage = builder.toString();
        return cryptMessage;
    }

    public static void main(String[] args) {
        String message = "06090512Be";
        System.out.println("Original message " + message);

        String codeKey = "topmagsdasdsadsadasdasdasdassaddasdsyar";

//        createCodeValues();
//        System.out.println(coderMap);
//        codeKey = updateCodeKey(codeKey);
//        coderMatrix = findCoderMatrix(codeKey);
//        System.out.println(findMatrixDeterm(coderMatrix));
//
//        System.out.println("Coder matrix:");
//        qq(coderMatrix);
//
//        List<List<Long>> matrixMsg = convertToMatrix(message, (int) Math.sqrt(codeKey.length()));
//
//        List<List<Long>> cryptedMatrixMsg = encryptMatrixMsg(matrixMsg, coderMatrix);
//
//        System.out.println("cryptedMatrixMsg:");
//        qq(matrixMsg);
//        String cryptMessage = "";
//        StringBuilder builder = new StringBuilder(cryptMessage);
//        for (List<Long> list : cryptedMatrixMsg) {
//            for (Long q : list) {
//                builder.append(getCharById(q));
//            }
//        }
//        cryptMessage = builder.toString();
        String encryptMessage = encryptMessage(message, codeKey);
        System.out.println("Encrypt message |" + encryptMessage + "|");


//        List<List<Long>> test = new ArrayList();
//        List<Long> l = new ArrayList<>();
//        l.add((long)0);
//        l.add((long)12);
//        l.add((long)29);
//        test.add(l);
//        l = new ArrayList<>();
//        l.add((long)16);
//        l.add((long)9);
//        l.add((long)14);
//        test.add(l);
//        l = new ArrayList<>();
//        l.add((long)9);
//        l.add((long)8);
//        l.add((long)13);
//        test.add(l);

//        System.out.println(findMatrixDeterm(test));
//        List<List<Long>> m = revertMatrix(test);
//        m = modMatrix(m, 37);
//        m = multiplyMatrix(m, 33);
//        m = modMatrix(m, 37);
//        m = transportMatrix(m);
//        m = removeNegativeElements(m, 37);
//
//        List<List<Long>> ms = new ArrayList<>();
//        List<Long> ll = new ArrayList<>();
//        ll.add((long)0);
//        ll.add((long)31);
//        ll.add((long)14);
//        ms.add(ll);
//        ll = new ArrayList<>();
//        ll.add((long)24);
//        ll.add((long)22);
//        ll.add((long)32);
//        ms.add(ll);
//
//        List<List<Long>> cr = encryptMatrixMsg(ms, m);

        String decryptMessage = decryptMessage(encryptMessage, codeKey);
        System.out.println("Decrypt message: |" + decryptMessage + "|");

    }
}
