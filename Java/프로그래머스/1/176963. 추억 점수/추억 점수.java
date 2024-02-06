import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> nameMap = new HashMap<>();
        for(int i = 0; i < name.length; i++) {
            nameMap.put(name[i], yearning[i]);
        }
        
        int[] answer = new int[photo.length];
        for(int i = 0; i < photo.length; i++) {
            String[] inners = photo[i];
            int length = inners.length;
            int scores = 0;
            
            for(int j = 0; j < length; j++) {
                String pName = inners[j];
                scores += nameMap.getOrDefault(pName, 0);
            }
            
            answer[i] = scores;
        }
        
        return answer;
    }
}