public class Puzzle {

	/* Fields */
    private int[] privateKey;
    private int[] riddle;
    

    /* Constructor */
    public Puzzle(int[] privateKey, int[] riddle){
        this.privateKey = privateKey;
        this.riddle = riddle;
    }

    /* returns a hard copy of a Puzzle */
    public Puzzle(Puzzle o){
        int [] oRiddle = o.getRiddle();
        int [] oPrivateKey = o.getPrivateKey();
        privateKey = new int[oPrivateKey.length];
        riddle = new int[oRiddle.length];
        for (int i = 0; i < oPrivateKey.length; i++){
        	privateKey[i] = oPrivateKey[i];
        }
        for (int i = 0; i < oRiddle.length; i++){
        	riddle[i] = oRiddle[i];
        }
    }

    /* returns the privateKey array */
    public int[] getPrivateKey(){
        return privateKey;
    }

    /* returns the riddle array */
    public int[] getRiddle() {
        return riddle;
    }
    

}
