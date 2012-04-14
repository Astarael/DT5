
public class Test4442 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Four442 f = new Four442();
		assert (f.getR0() == 0);
		f.incR0();
		f.incR0();
		assert (f.getR0() == 2);
		f.swap();
		assert (f.getR1() == 2);
		assert (f.getR0() == 0);
		f.decR0();
		f.incR1();
		f.decR1();
		f.incR1();
		assert (f.getR0() == 15);
		assert (f.getR1() == 3);
		f.add();
		assert(f.getR0() == 2);
		f.sub();
		f.sub();
		assert(f.getR0() == 12);
		
		f = new Four442();
		f.loadR0 (0xF);
		assert (f.getR0() == 0xF);
		f.loadR1 (0x9);
		assert (f.getR1() == 0x9);
		
		f.loadR0(0);
		f.loadR1(0);
		
		assert (f.run() == 0);
		int programme[] = new int[16];
		
		programme[0] = Four442.INCR0;
		programme[1] = Four442.PRINT;
		f.setProgramme(programme);
		assert (f.run() == 1);
		
		programme[1] = Four442.INCR0;
		programme[2] = Four442.PRINT;
		f.setProgramme(programme);
        assert (f.run() == 2);
        
		programme[2] = Four442.DECR1;
		programme[3] = Four442.PRINT;
		f.setProgramme(programme);
        assert (f.run() == 2);
        
        programme[3] = Four442.ADD;
        programme[4] = Four442.PRINT;
        f.setProgramme(programme);
        assert (f.run() == 1);
        
        programme[4] = Four442.SWAP;
        programme[5] = Four442.PRINT;
        f.setProgramme(programme);
        assert (f.run() == 15);
        
        programme[5] = Four442.SUB;
        programme[6] = Four442.PRINT;
        f.setProgramme(programme);
        assert (f.run() == 14);
        
        programme[6] = Four442.STORER0;
        programme[7] = 0xF;
        programme[8] = Four442.PRINT;
        f.setProgramme(programme);
        assert (f.run() == 14);
        
        programme[4] = Four442.EXIT;
        programme[8] = Four442.JNZ;
        programme[9] = 0x3;
        f.setProgramme(programme);
        assert (f.run() == 15);
		
		System.out.println ("All tests passed, you are awesome!");
		
	}

}
