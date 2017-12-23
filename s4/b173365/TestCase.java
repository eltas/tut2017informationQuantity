package s4.b173365; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID. 
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity, 
}                        
*/


public class TestCase {
    public static void main(String[] args) {
	try {
	    // FrequencerInterface  myObject;
	    int freq;
	    // System.out.println("checking s4.b173365.Frequencer");
	    // myObject = new s4.b173365.Frequencer();
	    // myObject.setSpace("Hi Ho Hi Ho".getBytes());
	    // myObject.setTarget("H".getBytes());
        // freq = myObject.frequency();
	    // System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
	    // if(4 == freq) {
        //     System.out.println("OK");
        // }
        // else {
        //     System.out.println("WRONG");
        // }
        
        // // テストケース加筆部分
        // myObject.setTarget("i".getBytes());
        // freq = myObject.frequency();
        // System.out.print("\"i\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
        // if(2 == freq) {
        //     System.out.println("OK");
        // }
        // else {
        //     System.out.println("WRONG");
        // }
        // myObject.setSpace("Alice In Wonderland".getBytes());
        // myObject.setTarget("Alice".getBytes());
        // freq = myObject.frequency();
        // System.out.print("\"Alice\" in \"Alice In Wonderland\" appears "+freq+" times. ");
        // if(1 == freq) {
        //     System.out.println("OK");
        // }
        // else {
        //     System.out.println("WRONG");
        // }


        // 加筆ここまで

        // week2　加筆ここから
        FrequencerInterface  myObject2;
        System.out.println("week2 s4.b173365.Frequencer TARGET test");
	    myObject2 = new s4.b173365.Frequencer();
	    myObject2.setSpace("Hi Ho Hi Ho".getBytes());
        freq = myObject2.frequency();
	    System.out.print("TARGET not set: " + freq + " ");
	    if(-1 == freq) {
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        myObject2.setTarget("".getBytes());
        freq = myObject2.frequency();
        System.out.print("TARGET length 0: " + freq + " ");
	    if(-1 == freq) {
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }

        FrequencerInterface  myObject3;
        System.out.println("week2 s4.b173365.Frequencer SPACE test");
        myObject3 = new s4.b173365.Frequencer();
        myObject3.setTarget("H".getBytes());
        freq = myObject3.frequency();
	    System.out.print("SPACE not set: " + freq + " ");
	    if(0 == freq) {
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        myObject3.setSpace("".getBytes());
        freq = myObject3.frequency();
        System.out.print("SPACE length 0: " + freq + " ");
	    if(0 == freq) {
            System.out.println("OK");
        }
        else {
            System.out.println("WRONG");
        }
        //week2 ここまで



    }
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}
        
	try {

	    InformationEstimatorInterface myObject;
        double value;
        // week2 ここから

        InformationEstimatorInterface myObject2;
        System.out.println("week2 s4.b173365.InformationEstimator TARGET test");
        myObject2 = new s4.b173365.InformationEstimator();
        myObject2.setSpace("3210321001230123".getBytes());
        value = myObject2.estimation();
        System.out.print("TARGET not set: " + value + " ");
        if(0.0 == value) {
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
        myObject2.setTarget("".getBytes());
        value = myObject2.estimation();
        System.out.print("TARGET length 0: " + value + " ");
        if(0.0 == value) {
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }

        System.out.println("week2 s4.b173365.InformationEstimator SPACE test");
        myObject = new s4.b173365.InformationEstimator();
        myObject.setTarget("0".getBytes());
        value = myObject.estimation();
        System.out.print("SPACE not set: " + value + " ");
        if(Double.MAX_VALUE == value) {
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
        myObject.setSpace("".getBytes());
        value = myObject.estimation();
        System.out.print("SPACE length 0: " + value + " ");
        if(Double.MAX_VALUE == value) {
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
        // week2 ここまで

        // week2 ここまで
	    // System.out.println("checking s4.b173365.InformationEstimator");
	    // myObject = new s4.b173365.InformationEstimator();
	    // myObject.setSpace("3210321001230123".getBytes());
	    // myObject.setTarget("0".getBytes());
	    // value = myObject.estimation();
	    // System.out.println(">0 "+value);
	    // myObject.setTarget("01".getBytes());
	    // value = myObject.estimation();
	    // System.out.println(">01 "+value);
	    // myObject.setTarget("0123".getBytes());
	    // value = myObject.estimation();
	    // System.out.println(">0123 "+value);
	    // myObject.setTarget("00".getBytes());
	    // value = myObject.estimation();
	    // System.out.println(">00 "+value);
	}
	catch(Exception e) {
	    System.out.println("Exception occurred: STOP");
	}

    }
}
