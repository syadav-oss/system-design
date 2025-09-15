package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Interview {

//    int num = 124531911 - 24539, 53911
//    int k = 4

    public static void main(String[] args) {
////        539
//        int num = 111245319;
//        int k = 4;
//        int rev = 0;
//
//        while(num > 0) {
//            rev = rev*10 + num%10;
//            num /= 10;
//        }
//        System.out.println(rev);
//        Stack<Integer> st = new Stack<>();
//        while(rev > 0) {
//            int rem = rev%10;
//            while(!st.isEmpty() && k > 0 && st.peek() < rem) {
//                st.pop();
//                k--;
//            }
//            st.push(rem);
//            rev /= 10;
//        }
//
//        int ans = 0;
//        while(!st.isEmpty()) {
//            ans = ans*10 + st.peek();
//            st.pop();
//        }
//        System.out.println(ans);
//        int revAns = 0;
//        while(ans > 0) {
//            revAns = revAns*10 + ans%10;
//            ans /= 10;
//        }
//        System.out.println(revAns);




//        3, 4, 7, 2, -3, 1, 4, 2


//        int[] prefixSum = new int[n+1];
//        for(int i=1;i<=n;i++) {
//            prefixSum[i] = prefixSum[i-1] + arr[i-1];
//        }
//
//        Map<Integer, Integer> freq = new HashMap<>();
//
//        freq.put(0,1);
//        int ans = 0;
//        for(int i=1;i<=n;i++) {
//            int reqSum = prefixSum[i] - k;
//            ans += freq.getOrDefault(reqSum, 0);
//            freq.put()
//        }
        int[] arr = {3, 4, 7, 2, -3, 1, 4, 2};
        int k = 7;
        int n = arr.length;
        int ans = 0;
        for(int i=0;i<n;i++) {
            int sum=0;
            for(int j=i;j<n;j++) {
                sum += arr[j];
                if(sum==k){
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }

//    private static String
}
