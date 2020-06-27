/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NoneOfYourBusiness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author h p
 */
public class FooBarChallengeUtil 
{
    public static void main(String[] args)throws Exception 
    { 
        File file = new File("C:\\Users\\h p\\Desktop\\FooBarChallenge\\Challenge1.txt"); 
        BufferedReader br = new BufferedReader(new FileReader(file)); 
        String st; 
        String prime="";
        int i=0;
        while ((st = br.readLine()) != null)
        {
           prime+=st;
           i++;
           if(prime.length()>10005)
           {
               System.out.println(i);
               System.out.println(st);
               break;
           }
        } 
        System.out.println(prime.length());
        System.out.println(prime.substring(5,5+5));
    } 
}
