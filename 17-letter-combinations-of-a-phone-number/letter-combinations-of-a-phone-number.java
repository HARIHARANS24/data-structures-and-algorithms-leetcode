import java.util.*;
class Solution {
 public List<String> letterCombinations(String digits) {
 List<String> res = new ArrayList<>();
 if (digits.length() == 0) return res;
 String[] map = {"","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
 backtrack(digits, 0, new StringBuilder(), res, map);
 return res;
 }
 private void backtrack(String digits, int i, StringBuilder sb, List<String> res, String[] map) {
 if (i == digits.length()) {
 res.add(sb.toString());
 return;
 }
 String letters = map[digits.charAt(i) - '0'];
 for (char c : letters.toCharArray()) {
 sb.append(c);
 backtrack(digits, i + 1, sb, res, map);
 sb.deleteCharAt(sb.length() - 1);
 }
 }
}