import java.io.*;
import java.util.*;

public class Main
{
 
  public static int levelCount(String word){
    int maxLevel=1;
    int curLevel=1;
    /*write your code here and return appropriate value*/
    char[] arr  = word.toCharArray();
    for (int i=0;i<arr.length;i++) {
        if(arr[i]=='{') {
            curLevel++;
            if (curLevel>maxLevel) {
                maxLevel++;
            }
        }
        else if (arr[i]=='}' ){
            curLevel--;
        }
    }
    
      return maxLevel;
  }
  
  public static void main(String[] args)
  {
//    String word="{\"0\":{\"name\":\"John\"},\"1\":{\"name\":\"micheal\"}}";
      String word="{\"0\":{\"name\":\"[John]\"}\",\"1\":{\"name\":\"[micheal]\"}}";
    System.out.println(levelCount(word)); 
  }
}
