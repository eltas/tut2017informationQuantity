package s4.b173365; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;


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
        quickSort(suffixArray, 0, suffixArray.length-1);
    }
    
    public int[] quickSort(int[] arr, int left, int right) {
         if(left<right) {
            int i = 1;
            int r = right - 1;
            int p = right;
            while(left <= r) {
                if((suffixCompare(r, p) == 1)) {
                    if((p-i)!=r) {
                        int tmp = arr[p-i];
                        arr[p-i] = arr[r];
                        arr[r] = tmp;
                    }
                    i++;
                }
                r--;
            }
            int tmp = arr[p];
            arr[p] = arr[p-i+1];
            arr[p-i+1] = tmp;
            if(left < (p-i)) quickSort(arr, left, p-i);
            if((p-i+2) < right) quickSort(arr, p-i+2, right);
        }
        return arr;
    }

private int targetCompare(int i, int start, int end) {
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
    if (i < 0) {
        return -1;
    }
	else if (i > mySpace.length - 1) { 
        return 1;
    }    

    int si = suffixArray[i];

    int n = mySpace.length - si;
    if(n > (end - start) ) n = end - start; // endはTargetの長さ
    for(int k = 0; k < n; k++){
        if(mySpace[si+k] > myTarget[start+k]) return 1;
        if(mySpace[si+k] < myTarget[start+k]) return -1;
    }
    if(n < end - start) return -1;
    return 0;
}
private int subByteStartIndex(int start, int end) { 
// It returns the index of the first suffix which is equal or greater than subBytes;
// not implemented yet;
// For "Ho", it will return 5 for "Hi Ho Hi Ho".
// For "Ho ", it will return 6 for "Hi Ho Hi Ho".
    int pLeft = 0;
    int pRight = suffixArray.length-1;
    do{
        int center = (pLeft + pRight)/2;
        if (targetCompare(center,start,end) == 0 ) {
            while(targetCompare(center-1,start,end) == 0 ) center--;
            return center;
        } else if (targetCompare(center,start,end) == -1){
            pLeft = center+1;
        } else {
            pRight = center-1;
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
            return center+1;
        } else if (targetCompare(center,start,end) == 1){
            pRight = center-1;
        } else {
            pLeft = center+1;
        }
    }while (pLeft <= pRight);
    
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
    
    return last1 - first;
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

