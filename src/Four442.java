public class Four442 {

	private int R0;
	private int R1;
	private int[] programme;
	private static final int MAX_INT = 0xF;
	
	public static final int EXIT = 0x0;
	public static final int ADD = 0x1;
	public static final int SUB = 0x2;
	public static final int INCR0 = 0x3;
	public static final int INCR1 = 0x4;
	public static final int DECR0 = 0x5;
	public static final int DECR1 = 0x6;
	public static final int SWAP = 0x7;
	public static final int PRINT = 0x8;
	public static final int LOADR0 = 0x9;
	public static final int LOADR1 = 0xA;
	public static final int STORER0 = 0xB;
	public static final int STORER1 = 0xC;
	public static final int JUMP = 0xD;
	public static final int JZ = 0xE;
	public static final int JNZ = 0xF;
	
	public Four442 () {
	    
		R0 = 0;
		R1 = 0;
		programme = new int[MAX_INT + 1];
		
	}
	
	
	public int getR0() {
	    
		return R0;
		
	}
	
	public int getR1() {
		
		return R1;
		
	}
	
	public void add() {
		
		R0 += R1;
		if (R0 > MAX_INT) {
		    
			R0 %= (MAX_INT + 1);
			
		}
	}
	
	public void sub() {
	    
		R0 -= R1;
		if (R0 < 0) {
			
			R0 += MAX_INT;
			
		}
	}
	
	public void incR0 () {
		
		R0++;
		if (R0 > MAX_INT) {
		    
			R0 = 0;
			
		}
	}
	
	public void incR1 () {
	    
		R1++;
		if (R1 > MAX_INT) {
		    
			R1 = 0;
			
		}	
	}
	
	public void decR0 (){
		
		R0--;
		if (R0  < 0) {
			
			R0 = MAX_INT;
			
		}
		
	}
	
	public void decR1 (){
		
		R1--;
		if (R1 < 0) {
			
			R1 = MAX_INT;
			
		}
		
	}
	
	public void swap(){
		
		int temp = R1;
		char bell = 0x07;
		R1 = R0;
		R0 = temp;
		System.out.print(bell);
		
	}
	
	public void loadR0 (int data) {
		
		R0 = data;
		
	}
	
	public void loadR1 (int data) {
		
		R1 = data;
		
	}
	
	
	public void setProgramme (int[] array) {
	    
	    programme = array;
	    
	}
	
	
	public int run () {
	    
	    int i;
	    int data = 0;
	    int temp = 0;
	    int print = 0;
        
        for (i = 0; i < MAX_INT + 1; i++) {
            
            
            if (programme[i] == EXIT) {
                
                i = MAX_INT + 1;
                System.out.println("exit");
                
            } else if (programme[i] == ADD) {
                
                add();
                System.out.println("add");
                
            } else if (programme[i] == SUB) {
                
                sub();
                System.out.println("sub");
                
            } else if (programme[i] == INCR0) {
                
                incR0();
                System.out.println("incR0");
                
            } else if (programme[i] == INCR1) {
                
                incR1();
                System.out.println("incR1");
                
            } else if (programme[i] == DECR0) {
                
                decR0();
                System.out.println("decR0");
                
            } else if (programme[i] == DECR1) {
                
                decR1();
                System.out.println("decR1");
                
            } else if (programme[i] == SWAP) {
                
                // swap
                temp = getR0();
                loadR0(getR1());
                loadR1(temp);
                
                // ring bell
                System.out.println("swap");
                
            } else if (programme[i] == PRINT) {
                
                i++;
                data = programme[i];
                temp = getR0();
                loadR0(temp ^ data);
                print = getR0();
                System.out.println("print " + getR0());
                
            } else if (programme[i] == LOADR0) {
                
                i++;
                data = programme[i];
                temp = programme[data];
                loadR0(temp);
                System.out.println("loadR0");
                
            } else if (programme[i] == LOADR1) {
                
                i++;
                data = programme[i];
                temp = programme[data];
                loadR1(temp);
                System.out.println("loadR1");
                
            } else if (programme[i] == STORER0) {
                
                i++;
                data = programme[i];
                temp = getR0();
                programme[data] = temp;
                System.out.println("storeR0");
                
            } else if (programme[i] == STORER1) {
                
                i++;
                data = programme[i];
                temp = getR1();
                programme[data] = temp;
                System.out.println("storeR1");
                
            } else if (programme[i] == JUMP) {
                
                i++;
                data = programme[i];
                i = data;
                System.out.println("jmp");
                
            } else if (programme[i] == JZ) {
                
                i++;
                data = programme[i];
                if (getR0() == 0) {
                    
                    i = data;
                    System.out.println("jz");
                    
                }
                
            } else if (programme[i] == JNZ) {
                
                i++;
                data = programme[i];
                if (!(getR0() == 0)) {
                    
                    i = data;
                    System.out.println("jnz");
                    
                }
                
            }
	    
        }
        
        return print;
        
	}
	
	// store == get
	
	/*1-byte Instructions

	0 =  HALT
	1 = Add (R0 = R0 + R1 )
	2 = Subtract (R0 = R0 - R1)
	3 = Increment R0 (R0 = R0 + 1)
	4 = Increment R1 (R1 = R1 + 1)
	5 = Decrement R0 (R0 = R0 - 1)
	6 = Decrement R1 (R1 = R1 - 1)
	7 = Swap Registers (R0 <=> R1) and ring bell!||
	
	
	2-byte Instructions
	value of the second byte is called <data>


	8 = R0=(R0 bitwise-xor <data>) and
    	print <data> (The numerical value of <data> is printed)
	9 =  Load value at address <data> into R0
	A =  Load value at address <data> into R1
	B =  Store R0 into address <data>
	C =  Store R1 into address <data>
	
	// interface stuff
	D =  Jump to address <data>
	E =  Jump to address <data> if R0 == 0
	F =  Jump to address <data> if R0 != 0
*/

}
