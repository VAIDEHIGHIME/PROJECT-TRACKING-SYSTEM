/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoneOfYourBusiness;

/**
 *
 * @author h p
 */
public class Solution{
    private static String getLambdaString(int drawnNo, int magicNo)
    {
        String lambdaStr="";
        Boolean stopFlag=false;
        Boolean[] isPrime_DP=new Boolean[magicNo+1];
        for(int i=2;i<magicNo+1;i++)
        {
            isPrime_DP[i]=true;
        }
        for(int i=2;i<=magicNo && stopFlag==false;i++)
        {
            if(isPrime_DP[i]==true)
            {
                lambdaStr+=Integer.toString(i);
                if(lambdaStr.length()>drawnNo+5)
                {
                    stopFlag=true;
                }
                for(int j=i;j<=magicNo && stopFlag==false;j+=i)
                {
                    isPrime_DP[j]=false;
                }
            }
        }
        return lambdaStr;
    }
    private static String getExcitingID(int drawnNo)
    {
        int magicNo=20231;
        String lambdaStr= getLambdaString(drawnNo,magicNo);
        String excitingID=lambdaStr.substring(drawnNo,drawnNo+5);
        return excitingID;
        
    }
    public static String solution(int i) 
    {
        // Your code here
        return getExcitingID(i);
    }
    public static void main(String[] args) {
        int drawnNo=5;
        System.out.println("Exiting ID for"+drawnNo+"th drawn no is "+Solution.getExcitingID(drawnNo));
        
    }
}
