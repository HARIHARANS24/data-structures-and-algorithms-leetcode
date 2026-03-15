class Trie {
 class Node {
 Node[] child = new Node[26];
 boolean end;
 }
 Node root;
 public Trie() {
 root = new Node();
 }
 public void insert(String word) {
 Node node = root;
 for (char c : word.toCharArray()) {
 int i = c - 'a';
 if (node.child[i] == null) node.child[i] = new Node();
 node = node.child[i];
 }
 node.end = true;
 }
 public boolean search(String word) {
 Node node = root;
 for (char c : word.toCharArray()) {
 int i = c - 'a';
 if (node.child[i] == null) return false;
 node = node.child[i];
 }
 return node.end;
 }
 public boolean startsWith(String prefix) {
 Node node = root;
 for (char c : prefix.toCharArray()) {
 int i = c - 'a';
 if (node.child[i] == null) return false;
 node = node.child[i];
 }
 return true;
 }
}