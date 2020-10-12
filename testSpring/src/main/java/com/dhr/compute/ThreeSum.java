package com.dhr.compute;

import com.fasterxml.jackson.core.type.TypeReference;
import com.guman.common.json.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author duanhaoran
 * @since 2020/6/9 1:37 PM
 */
public class ThreeSum {

    public static void main(String[] args) {
        List<List<Integer>> lists = new ThreeSum().threeSum(new int[]{-4,-2,-2,-2,0,1,2,2,2,3,3,4,4,6,6});
        System.out.println(JSON.writeValueAsString(lists));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length -1;
        int l = left+1;
        List<List<Integer>> list = new ArrayList<>();
        while(left < right-1){
            if(nums[left] + nums[right] + nums[l] > 0){
                right--;
                l = left +1;
            }else if(nums[left] + nums[right] + nums[l] < 0){
                l++;
                if(l >= right){
                    left++;
                    l = left + 1;
                }
            }else{
                List<Integer> res= new ArrayList<>();
                res.add(nums[left]);
                res.add(nums[l]);
                res.add(nums[right]);
                if(list.size() == 0){
                    list.add(res);
                }else {
                    List<Integer> last = list.get(list.size() - 1);
                    if (last.get(0) != nums[left]
                            || last.get(1) != nums[l]
                            || last.get(2) != nums[right]) {
                        list.add(res);
                    }
                }
                l++;
                if(l >= right){
                    left++;
                    l = left + 1;
                }
            }
        }
        return list;
    }
}
