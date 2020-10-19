package com.dhr.dataStructer;

/**
 * 并查集
 * 1.将两个集合合并
 * 2.快速判断两个集合是否在一个 集合中
 * 不能使用相同的数据
 * @author duanhaoran
 * @since 2020/10/19 1:31 AM
 */
public class MergeFindArray {
    /**
     * p的索引值和属性值相同则为每一个树的根节点
     */
    int[] p = new int[10010];

    /**
     * 查询在哪一个集合中 + 路径压缩
     * @param x
     * @return
     */
    public int find(int x) {
        if(p[x]!=x) p[x] = find(p[x]);
        return p[x];
    }

    /**
     * 将a插入到b的树上
     * @param a
     * @param b
     */
    public void merge(int a, int b) {
        p[find(a)] = find(b);
    }

    /**
     * 是否在同一个集合中
     * @param a
     * @param b
     * @return
     */
    public boolean isInSameArray(int a, int b) {
        return find(a) == find(b);
    }

}
