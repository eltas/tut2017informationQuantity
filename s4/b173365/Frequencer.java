package s4.b173365; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.*;


public class Frequencer implements FrequencerInterface{
// Code to start with: This code is not working, but good start point to work.
byte [] myTarget;
byte [] mySpace;
boolean targetReady = false;
boolean spaceReady = false; 
static int [] suffixArray;
// The variable, "suffixArray" is the sorted array of all suffixes of mySpace.
// Each suffix is expressed by a interger, which is the starting position in mySpace. // The following is the code to print the variable
private void printSuffixArray() {
    if(spaceReady) {
        for(int i=0; i< mySpace.length; i++) {
            int s = suffixArray[i];
            System.out.print("[printSuffixArray]");
            for(int j=s; j<mySpace.length; j++) {
                System.out.write(mySpace[j]);
            }
            System.out.write('\n');

        }
    }
}
    private int suffixCompare(int i, int j) {
        // comparing two suffixes by dictionary order.
        // i and j denoetes suffix_i, and suffix_j
        // if suffix_i > suffix_j, it returns 1
        // if suffix_i < suffix_j, it returns -1
        // if suffix_i = suffix_j, it returns 0;
        // It is not implemented yet,
        // It should be used to create suffix array.
        // Example of dictionary order
        // "i"< "o": compare by code
        // "Hi"< "Ho" < "Ho "; if head is same, compare the next element
        // "Ho"; if the prefix is identical, longer string is big
        int si = suffixArray[i];
        int sj = suffixArray[j];
        int s = 0;
        if(si > s) s = si;
        if(sj > s) s = sj;
        int n = mySpace.length - s;
        for(int k = 0; k < n; k++){
            if(mySpace[si+k] > mySpace[sj+k]) return 1;
            if(mySpace[si+k] < mySpace[sj+k]) return -1;
        }
        if(si < sj) return 1;
        if(si > sj) return -1;
        return 0;
    }
    
    public void setSpace(byte []space) {
        mySpace = space;
        if(mySpace.length>0) spaceReady = true;
        suffixArray = new int[space.length];
        // put all suffixes in suffixArray. Each suffix is expressed by one interger.
        for(int i = 0; i< space.length; i++) {
            suffixArray[i] = i;
        }
//        for(int i = 0; i < mySpace.length; i++){
//            for(int j = i + 1; j < mySpace.length; j++){
//                if(suffixCompare(i, j) == 1){
//                    int tmp = suffixArray[i];
//                    suffixArray[i] = suffixArray[j];
//                    suffixArray[j] = tmp;
//                }
//            }
//        }
//
//        printSuffixArray();
        
        quickSort(suffixArray, 0, suffixArray.length-1);
        printSuffixArray();
    }
    
    public int[] quickSort(int[] arr, int left, int right) {
        if(left<=right) {
            int l = left;
            int r = right;
            while(l <= r) {
                while(suffixCompare(l, (left+right)/2) == -1) l++;
                while(suffixCompare(r, (left+right)/2) == 1) r--;
                
                if(l <= r) {
                    int tmp = arr[l];
                    arr[l] = arr[r];
                    arr[r] = tmp;
                    l++;
                    r--;
                }
            }
            
            if (left < r) quickSort(arr, left, r);
            if (l < right) quickSort(arr, l, right);
        }
        return arr;
    }

private int targetCompare(int i, int j, int end) {
// comparing suffix_i and target_j_end by dictonary order with limitation of length; 
// if the beginning of suffix_i matches target_i_end, and suffix is longer than target it returns 0; 
// if suffix_i > target_i_end it return 1;
// if suffix_i < target_i_end it return -1
// It is not implemented yet.
// It should be used to search the apropriate index of some suffix. // Example of search 
// suffix       target
// "o" > "i"
// "o"< "z"
// "o"= "o"
// "o"< "oo"
// "Ho"> "Hi"
// "Ho"< "Hz"
// "Ho" = "Ho"
//"Ho" < "Ho":"Ho"isnotintheheadofsuffix"Ho"
// "Ho" = "H" : "H" is in the head of suffix "Ho"
    
    int si = suffixArray[i];
    int s = 0;
    if(si > s) s = si;
    int n = mySpace.length - s;
    if(n > end) n = end; // endはTargetの長さ
    for(int k = 0; k < n; k++){
        if(mySpace[si+k] > myTarget[k]) return 1;
        if(mySpace[si+k] < myTarget[k]) return -1;
    }
    if(n < end) return -1;
    return 0;
}
private int subByteStartIndex(int start, int end) { 
// It returns the index of the first suffix which is equal or greater than subBytes;
// not implemented yet;
// For "Ho", it will return 5 for "Hi Ho Hi Ho".
// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
    
//    for(int i = 0; i < suffixArray.length; i++){
//        int temp = targetCompare(i,start,end);
//        if(temp == 0) return i;
//    }
    int pLeft = 0;
    int pRight = suffixArray.length-1;
    do{
        int center = (pLeft + pRight)/2;
        if (targetCompare(center,start,end) == 0 ) {
            while(targetCompare(center-1,start,end) == 0) center--;
            return center;
        } else if (targetCompare(center,start,end) == -1){
            pLeft = center+1;
        }
    }while (pLeft <= pRight);
    return suffixArray.length;
}
private int subByteEndIndex(int start, int end) {
// It returns the next index of the first suffix which is greater than subBytes; // not implemented yet
// For "Ho", it will return 7 for "Hi Ho Hi Ho".
// For "Ho ", it will return 7 for "Hi Ho Hi Ho".
    int pLeft = 0;
    int pRight = suffixArray.length-1;
    do{
        int center = (pLeft + pRight) / 2;
        if (targetCompare(center,start,end) == 0) {
            while(targetCompare(center+1,start,end) == 0) center++;
            return center;
        } else if (targetCompare(center,start,end) == 1){
            pRight = center-1;
        }
    }while (pLeft <= pRight);
//    for(int i = 0; i < suffixArray.length; i++){
//        int temp = targetCompare(i,start,end);
//        if(temp == 1) return i;
//    }
    
    return suffixArray.length;
} 
public int subByteFrequency(int start, int end) {
/* This method could be defined as follows though it is slow.
int spaceLength = mySpace.length;
int count = 0;
for(int offset = 0; offset< spaceLength - (end - start); offset++) { 
boolean abort = false;
for(int i = 0; i< (end - start); i++) { 
if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; } } 
if(abort == false) { count++; } } 
*/
    
    int first = subByteStartIndex(start,end);
    int last1 = subByteEndIndex(start, end);
    
    return last1 - first + 1;
} 
public void setTarget(byte [] target) { 
   myTarget = target;
   if(myTarget.length>0) targetReady = true;
}
public int frequency() {
    if(targetReady == false) return -1;
    if(spaceReady == false) return 0;
    return subByteFrequency(0, myTarget.length);
    //return 0;
} 

public static void main(String[] args) {
    Frequencer frequencerObject;
    try {
        frequencerObject = new Frequencer();
        frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
        
        frequencerObject.setTarget("H".getBytes());
        int result = frequencerObject.frequency();
        System.out.print("Freq = "+ result+" ");
        if(4 == result) {
            System.out.println("OK");
        }
        else {System.out.println("WRONG");
        }
    }
    catch(Exception e) {
        System.out.println("STOP");
    }
}
}

