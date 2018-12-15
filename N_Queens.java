class Solution {
    public List<List<String>> solveNQueens(int n) {
        boolean[] col = new boolean[n];
        boolean[] dia45 = new boolean[2*n-1];
        boolean[] dia135 = new boolean[2*n-1];
        
        res = new ArrayList<>();
        
        construct(n, 0, col, dia45, dia135, new ArrayList<>());
        
        return res;
    }
    
    private List<List<String>> res;
    
    private void construct(int n, int rowIndex, boolean[] col, boolean[] dia45, boolean[] dia135, List<String> list){
        
        //结束条件
        if(rowIndex == n){
            res.add(new ArrayList<>(list));
            return;
        }
        
        //遍历第rowIndex行的每一列，回溯法求解
        for(int i=0; i<n; ++i){
            
            //判断第rowIndex行，第i列上是否可以放置一个Queen, 不可以则跳过。
            if(col[i] || dia45[rowIndex+i] || dia135[rowIndex-i+n-1]) continue;
            
            /*
                如果该位置可以放置Queen，初始化一个长度为n的数组，在第i个位置放上一个Q，其余地方放置‘.’
                将该行加入ist,并将该位置的同列，同对角线做好标记
            */
            char[] charArray = new char[n];
            Arrays.fill(charArray,'.');
            charArray[i] = 'Q';
            
            String s = new String(charArray);
            list.add(s);
            col[i] = true;
            dia45[rowIndex+i] = true;
            dia135[rowIndex-i+n-1] = true;
            
            construct(n, rowIndex+1, col, dia45, dia135, list);
            
            
            //回溯部分
            list.remove(list.size()-1);
            col[i] = false;
            dia45[rowIndex+i] = false;
            dia135[rowIndex-i+n-1] = false;

        }
    }
}