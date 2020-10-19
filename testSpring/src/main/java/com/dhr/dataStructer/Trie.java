package com.dhr.dataStructer;

/**
 * Trie树
 * 字符串统计
 * 构建需要创建一个完整的线段树
 * 用例 构建一个字符串or数字树
 *
 * @author duanhaoran
 * @since 2020/10/19 1:39 AM
 */
public class Trie {

    private Integer[][] son = new Integer[10010][26];

    private int[] cnt = new int[10010];

    private int idx;

    public void insert(String str) {
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            int a = str.charAt(i) - 'a';
            if (son[p][a] == null) {
                son[p][a] = ++idx;
            }
            p = son[p][a];
        }
        cnt[p]++;
    }

    public Integer query(String string) {
        int p = 0;
        for (int i = 0; i < string.length(); i++) {
            int a = string.charAt(i) - 'a';
            if (son[p][a] != null) {
                p = son[p][a];
            }else {
                return 0;
            }
        }
        return cnt[p];
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("aabce");
        trie.insert("adwqce");
        trie.insert("aabcaawe");
        trie.insert("wqvca");
        trie.insert("abcea");
        trie.insert("wjdiadiw");
        trie.insert("dwnawad");
        System.out.println(trie.query("awdw"));
    }


}
