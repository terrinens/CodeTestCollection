import java.util.*;
import java.io.*;

class Solution {
    public int solution(String t, String p) {
        int tLen = t.length();
        int pLen = p.length();
        
        String[] caseList = new String[tLen - pLen + 1];
        for (int i = 0; i <= tLen-pLen; i++) {
            int end = pLen + i;
            caseList[i] = t.substring(i, end);
        }
        
        int count = 0;
        long pInt = Long.parseLong(p);
        for (String cased : caseList) {
            long num = Long.parseLong(cased);
            if (num <= pInt) count++;
        }
        
        return count;
    }
}