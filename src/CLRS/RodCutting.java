package CLRS;

public class RodCutting {
    public static void main(String[] args){

        int n = 7;
        //zeroth element is added as 0 by default
        int[] arr = new int[]{0,1,5,8,9,10,17,17,20,24,30};
        if(n==1)
        {
            System.out.println(arr[1]);
            return;
        }
        solve(arr,n);

    }

    private static void solve(int[] arr, int n){
        int ans[] = new int[arr.length];
        ans[0]=0;
        ans[1]=arr[1];
        for(int i=1;i<=n;i++){
            int max=Integer.MIN_VALUE;
            for(int j=1;j<=(i/2)+1;j++){//avoiding repeated calc
                int curr = ans[j] + ans[i-j];
                if(curr>max){
                    max=curr;
                }
            }
            ans[i]=Math.max(arr[i],max);
        }
        System.out.println(ans[n]);
    }
}
