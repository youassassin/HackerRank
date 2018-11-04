/**
 * @author Geoffrey J Grimaud
 */
import java.util.*;
import java.io.*;

public class Solution {
    
    /* This solution takes in alices scores (ascending order) 
     * with marker i and scores (decending order) with marker j
     * and progressively checks if alice's score is <, ==, and >.
     * if the alice's score is < or == than scores then j does 
     * not move. If alice's score > than scores then j-- loops and
     * the place decreases if the scores change. Only at j == 0
     * does alice's score > scores get assigned to result. As
     * j = -1 does not exsist. 
     */
    public static int[] solve(int[] scores, int[] alice){
        int [] result = new int [alice.length]; //alices placement
        
        //creates an array of the placement that the scores are in
        int scorePlace = 1;
        for(int i = 1; i < scores.length; i++)
            if(scores[i-1] != scores[i])
                scorePlace++;
        
        int i = 0, j = scores.length - 1
        while(i < alice.length && j >= 0){
            if(alice[i] < scores[j]){
                result[i] = scorePlace + 1; i++;
            }
            else if(alice[i] == scores[j]){
                result[i] = scorePlace; i++;
            }
            else{
                while(alice[i] >= scores[j] && j > 0){
                    if(scores[j] != scores[j-1])
                        scorePlace--;
                    j--;
                }
                if(j == 0)
                    if(alice[i] > scores[j]){
                        result[i] = 1;
                        i++;
                    }
            }
        }
        
        return result;
    }
    /* main method to read in and print out answer
     * BufferedReader is faster than Scanner and can prevent some timeouts
     */
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        
        //read in scores
        int [] scores = new int [Integer.parseInt(br.readLine())];
        String [] temp = br.readLine().split(" ");
        for(int i = 0; i < scores.length; i++)
            scores[i] = Integer.parseInt(temp[i]);
        
        //read in alice's scores
        int [] alice = new int [Integer.parseInt(br.readLine())];
        temp = br.readLine().split(" ");
        for(int i = 0; i < alice.length; i++)
            alice[i] = Integer.parseInt(temp[i]);
        
        //solve the problem
        int [] result = solve(scores, alice);
        
        //print out result
        for(int i = 0; i < result.length; i++)
            System.out.println(result[i]);
        
        br.close();  
    }
}


