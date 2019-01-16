/* --------------------------------------------------------------------------- * 
	This class defines a Comparable object that represents a node in AVL Tree,
    which includes private key and a suitable array with a riddle that
    contains a secret code for communication
* ---------------------------------------------------------------------------- */

public class Comparable {

	/*Fields*/
	public String serialNumber;
	public int [] riddle;

	/* Constructor */
	public Comparable (String serialNumber, int[] riddle){
		this.serialNumber=serialNumber;
		 this.riddle = new int[riddle.length];
         for (int i = 0; i < riddle.length; i++){
        	 this.riddle[i] = riddle[i];
         } 
	}
	
	/* Compare Strings that represents the serialNumber */
	public int compareTo(Object other){
		String second = null;
		if (other instanceof Comparable){
			second = ((Comparable) other).serialNumber;
		}
		else if (other instanceof String){
			second=(String)other;
		}
		int ans=0;
		if (this.serialNumber.length()>second.length()){
			ans=1;
		}
		else if (this.serialNumber.length()<second.length()){
			ans=-1;
		}
		else {
			for (int i=0; i<this.serialNumber.length() && ans==0;i++){
				if (this.serialNumber.charAt(i)=='1' && second.charAt(i)=='0')
					ans=1;
				if (this.serialNumber.charAt(i)=='0' && second.charAt(i)=='1')
					ans=-1;
			}
		}
		return ans;
	}
	
}
