class Solution {
    public String generateString(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int len = n + m - 1;
        char[] res = new char[len];
        for (int i = 0; i < len; i++) res[i] = '?';

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] == '?' || res[i + j] == str2.charAt(j)) {
                        res[i + j] = str2.charAt(j);
                    } else {
                        return "";
                    }
                }
            }
        }

        for (int i = 0; i < len; i++) {
            if (res[i] == '?') res[i] = 'a';
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'F') {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    boolean fixed = false;
                    for (int j = m - 1; j >= 0 && !fixed; j--) {
                        int idx = i + j;
                        char original = res[idx];
                        for (char c = (char)(original + 1); c <= 'z'; c++) {
                            res[idx] = c;
                            boolean ok = true;
                            for (int k = 0; k < n; k++) {
                                if (str1.charAt(k) == 'T') {
                                    for (int t = 0; t < m; t++) {
                                        if (res[k + t] != str2.charAt(t)) {
                                            ok = false;
                                            break;
                                        }
                                    }
                                    if (!ok) break;
                                }
                            }
                            if (ok) {
                                fixed = true;
                                break;
                            }
                        }
                        if (!fixed) res[idx] = original;
                    }
                    if (!fixed) return "";
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (str1.charAt(i) == 'T') {
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) return "";
                }
            } else {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (res[i + j] != str2.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) return "";
            }
        }

        return new String(res);
    }
}