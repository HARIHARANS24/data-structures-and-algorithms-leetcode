class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count = 0;
    }

    public int[] sumPrefixScores(String[] words) {
        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
                node.count++;
            }
        }

        int[] result = new int[words.length];

        for (int i = 0; i < words.length; i++) {
            TrieNode node = root;
            int sum = 0;
            for (char c : words[i].toCharArray()) {
                node = node.children[c - 'a'];
                sum += node.count;
            }
            result[i] = sum;
        }

        return result;
    }
}