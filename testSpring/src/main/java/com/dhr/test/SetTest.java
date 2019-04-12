package com.dhr.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author haoran.duan
 * @since 13 一月 2019
 */
public class SetTest {
    public static void main(String[] args) {
        SetTest setTest=new SetTest();
        int i = setTest.the_longest_common_prefix(Lists.newArrayList("abcba","acc","abwsf"), "abse");
        System.out.println(i);
        System.out.println(111111110.000>Double.MAX_VALUE);
        setTest.wordBreak("lintcode",Sets.newHashSet("de", "ding", "co", "code", "lint"));
        setTest.testDoubleBeanSet();
    }

    public void testDoubleBeanSet(){
        Set<VOBean> set=Sets.newHashSet();
        VOBean vo=new VOBean();
        vo.setX("a");
        set.add(vo);

        vo.setX("b");

        for(Iterator<VOBean> ite=set.iterator();ite.hasNext();){
            VOBean next = ite.next();
            System.out.println(next.getX());
        }
    }
    class VOBean{
        private String x;

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }
    }
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // write your code here
        Set<String> a=wordSub(s,wordDict,new HashSet<String>(),0);
        System.out.println(a);
        return Lists.newArrayList(a);
    }

    public Set<String> wordSub(String s, Set<String> wordDict,Set<String> x,int k) {
        // write your code here

        for(int i=k;i<=s.length();i++){
            if(wordDict.contains(s.substring(k,i))&&!x.contains(s.substring(k,i))){
                x.add(s.substring(k,i));
            }
        }
        k++;
        if(k!=s.length()){
            wordSub(s,wordDict,x,k);
        }
        return x;

    }
    public int the_longest_common_prefix(List<String> dic, String target) {
        // write your code here
        char[] chars1=target.toCharArray();
        int count=0;
        for(String str:dic){
            char[] chars = str.toCharArray();
            int c=0;
            int len=target.length()>str.length()?str.length():target.length();
            for(int i=0;i<len;i++){
                if(chars[i]==chars1[i]&&c==i){
                    c++;
                }
            }
            count=c>count?c:count;
        }
        return count;
    }
}
