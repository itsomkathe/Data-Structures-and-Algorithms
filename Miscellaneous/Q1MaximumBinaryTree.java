package Miscellaneous;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

//Link: https://leetcode.com/problems/maximum-binary-tree/

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode ans = dfs(nums, 0, nums.length-1);
        return ans;
    }
    
    //Solution 1: Not optimized
    TreeNode dfs(int[] nums, int start, int end){
        if(start>end){
            return null;
        }else if(start == end){
            TreeNode self = new TreeNode(nums[start]);
            return self;
        }
        
        int idx = start;
        for(int i=start; i<=end; i++){
            if(nums[idx]<nums[i]){
                idx = i;
            }
        }

        TreeNode left = dfs(nums, start, idx-1);
        TreeNode right = dfs(nums, idx+1, end);
        
        TreeNode self = new TreeNode(nums[idx], left, right);
        return self;
    }
}
