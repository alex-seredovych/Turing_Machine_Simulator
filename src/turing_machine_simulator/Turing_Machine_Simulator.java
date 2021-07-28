package turing_machine_simulator;
import java.util.ArrayList;
import java.util.Scanner;
public class Turing_Machine_Simulator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> tuples = new ArrayList<>();
        System.out.println("Enter 5-Tuples. A . by itself to end.");
        String s = input.nextLine();
        while(!(s.equals("."))){
            tuples.add(s);
            s = input.nextLine();
        }
        System.out.println("Enter the initial tape and press enter.");
        StringBuilder tape = new StringBuilder(input.nextLine());//our tape        
        
        System.out.print("Maximum Iterations: ");
        int num=input.nextInt();
        
        //We assume that the first tuple we have to use is 
        int index = 0;//counter for the tape 
        String currentState = tuples.get(0).substring(0,1);
        String readSymbol;//current symbol on the tape
        if (tape.length()==0)//if tape is blank we add a space
            tape.insert(index, " ");
        readSymbol = tape.substring(index,index+1);//reading a symbol from the tape according to the value of the index
        String currentTuple = findTuple(readSymbol, currentState, tuples);//finding the tuple that fits into our reqirements
        System.out.println(tape+"{"+currentState+"}");
        tape.setCharAt(index, currentTuple.charAt(2));//printing a symbol
        //main for loop where each iteration represents a certain operation in the currentState
        for(int i=0;i<num;i++){ 
            if (currentTuple.substring(3,4).equals("R")&&index+1!=tape.length())//change index
                index++;
            else if (currentTuple.substring(3,4).equals("R")&&index+1==tape.length()){
                index++;
                tape.insert(index, " ");
            }
            else if(currentTuple.substring(3,4).equals("L") && index>0)
                index--;
            else if(currentTuple.substring(3,4).equals("L") && index==0)
                tape.insert(index, " ");
            readSymbol = tape.substring(index,index+1);//reading symbol from the tape
            currentState = currentTuple.substring(4,5);//getting next operation/state from currentTuple
            System.out.println(tape+"{"+currentState+"}");
            currentTuple=findTuple(readSymbol, currentState, tuples);//get the next tuple we will be working with
            if (currentTuple.equals("0")){//checking whether the machine has halted or not.
                System.out.println("HALTED");
                break;
            }
            tape.setCharAt(index, currentTuple.charAt(2));//printing a symbol according to the value of the index
            
        }
        if (!(currentTuple.equals("0")))//in case if machine didn't halt
            System.out.println("Max Iterations Reached");
        System.out.println("Final State: "+currentState); 
        
    }
    public static String findTuple(String readSymbol, String currentState, ArrayList<String> tuples){
        String tupoTuple ="";//working variable that will return the next tuple
        for(int i=0;i<tuples.size();i++){
            tupoTuple = tuples.get(i);//get a next tuple, and check if it fits according to our requirements
            if (tupoTuple.substring(0, 2).equals(currentState.concat(readSymbol))){
                return tupoTuple;
            }
        }
        return "0";//HALT condition - the next tuple hasn't been found or final state was found
    }
}
//Prints ones and zeros, no state that halts the program
/*
A 0RB 
B 1RA
.
*/
//Manually writes x to replace all zeros in the tape
/*
10 R2
1  Rr
1xxRr
2xxR2
20xR3
2  Ra
300R4
3xxR3
3  L5
4xxR4
40xR3
4  Rr
5xxL5
500L5
5  R2
.
*/
//
/*
a eRb
b eRc
c 0Rd
d  Re
d00Re
d11Re
deeRe
dxxRe
e 0Lf
f  Lg
f00Lg
f11Lg
feeLg
fxxLg
g11Rh
h1xLi
h0xLi
hexLi
hxxLi
h xLi
i11Lj
i  Lj
i00Lj
ixxLj
j11Lg
j00Lg
j  Lg
jxxLg
g00Rk
k  Ll
k00Ll
k11Ll
keeLl
kxxLl
l00Rm
l11Rm
m  Rl
m00Rl
m11Rl
meeRl
mxxRl
l 1Ln
nx Rl
neeRo
n  Lp
p  Ln
p00Ln
p11Ln
pxxLn
peeLn
o00Rq
o11Rq
oeeRq
oxxRq
q  Ro
q00Ro
q11Ro
qeeRo
qxxRo
o 0Lr
r  Lg
r00Lg
r11Lg
reeLg
rxxLg
.
*/