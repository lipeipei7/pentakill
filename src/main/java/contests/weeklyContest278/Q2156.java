package contests.weeklyContest278;

/*
* https://leetcode.com/contest/weekly-contest-278/problems/find-substring-with-given-hash-value/
* The hash of a 0-indexed string s of length k, given integers p and m, is computed using the following function:
*
* hash(s, p, m) = (val(s[0]) * p0 + val(s[1]) * p1 + ... + val(s[k-1]) * pk-1) mod m.
* Where val(s[i]) represents the index of s[i] in the alphabet from val('a') = 1 to val('z') = 26.
*
* You are given a string s and the integers power, modulo, k, and hashValue.
* Return sub, the first substring of s of length k such that hash(sub, power, modulo) == hashValue.
*
* The test cases will be generated such that an answer always exists.
* A substring is a contiguous non-empty sequence of characters within a string.
*/
public class Q2156 {
    public String subStrHash(String s, int p, int m, int k, int hashValue) {
        long cur = 0, pk = 1;
        int res = 0, n = s.length();

        for (int i = n - 1; i >= 0; i--) {
            cur = ((cur * p) % m + (s.charAt(i) - 'a' + 1)) % m;
            if (i + k >= n)
                pk = pk * p % m;
            else
                cur = (cur - ((s.charAt(i + k) - 'a' + 1) * pk % m) + m) % m;
            if (cur == hashValue)
                res = i;
        }

        return s.substring(res, res + k);
    }

    public String subStrHash2(String s, int power, int modulo, int k, int hashValue) {
        long hash = 0;
        int i = s.length() - 1;
        long powerK = 1;
        int candidate = -1;

        while (i >= 0) {
            int v = (s.charAt(i) - 'a') + 1;
            hash = ((hash * power) % modulo + v) % modulo;
            if (i >= s.length() - k) {
                powerK = (powerK * power) % modulo;
            }
            if (i < s.length() - k) {
                // add modulo to avoid negative
                hash = (hash - (((s.charAt(i + k) - 'a') + 1) * powerK) % modulo + modulo) % modulo;
            }
            //we need to keep tracking the target to find the first candidate since we are looping backward
            if (hash == hashValue) {
                candidate = i;
            }
            i--;
        }
        return s.substring(candidate, candidate + k);
    }


    public static void main(String[] args) {
        Q2156 solution = new Q2156();
        //Input: s = "leetcode", power = 7, modulo = 20, k = 2, hashValue = 0; Output: "ee"
        System.out.println(solution.subStrHash2("leetcode", 7, 20, 2, 0));
        //Input: s = "fbxzaad", power = 31, modulo = 100, k = 3, hashValue = 32; Output: "fbx"
        System.out.println(solution.subStrHash2("fbxzaad", 31, 100, 3, 32));
    }
}
