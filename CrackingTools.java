/* This class packing a few functions that is used in order to crack the private keys and secret codes */
public class CrackingTools {
	
	
	
	/* --------------------------------------------------------------------------- * 
        Return a XOR expression from an integer array , by converting the numbers
        to binary representation, and simultaneously counting the amount of times
        the number '1' is appearing , in order to determine XOR result 
    * ---------------------------------------------------------------------------- */
	 public static String toBinaryAndXor(int [] array){
		 String toXOR = "";
		 int counterOnes=0;
	    	for (int i=0;i<array.length;i++)
	    	{
	    		int num=array[i];
	    		while (num>1)
	    		{
	    			if (num%2!=0)
	    				counterOnes=counterOnes+1;
	    		num=num/2;
	    		}
	    		counterOnes=counterOnes+1;
	    		if (counterOnes%2!=0)
	    	    	toXOR=toXOR+'1';
	    	    	else toXOR=toXOR+'0';
	    	    	counterOnes=0;
	    	}
	    	
	    	return toXOR;
	    }
	   
	    /* Copying an integer array */
	    public static int[] copyArray (int [] array){
	    	 int[] copiedArray = new int[array.length];
	         for (int i = 0; i < array.length; i++){
	        	 copiedArray[i] = array[i];
	         }
	         return copiedArray;
	    }
	     
	    /* Sorting an array that represents a private key or a secret code in linear time */
	    public static int[] sortArray (int [] array){
	    	int [] sortedArray = new int [array.length];
	    	for (int i=0; i<array.length; i=i+1){
	    		int index = (int)(array[i]/(Math.pow(array.length,3)));
	    		sortedArray[index]=array[i];
	    	}
	    	return sortedArray;
	    }
	    	    
}
