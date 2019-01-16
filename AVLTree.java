import java.io.PrintWriter;


/* Class AVLTree */
class AVLTree
{
	
	/* Fields */
    protected AVLNode root;
    private int numberOfNodes;

    
    /* Constructor */
    public AVLTree(){
      root=null;
      numberOfNodes=0;
    }
    
    
    /* Checks if the tree is empty */
    public boolean isEmpty(){
        return root==null;
    }
    
    
    /* Make the tree logically empty */
    public void makeEmpty(){
        root=null;
    }
     
    
    /* Finds the height of a given node */
    private int getHeight(AVLNode t) {
    	if(t==null)
    		return-1;
    	return t.height;		
	}
    
    
    /* Calculates the height of a given node */
    private int height(AVLNode t) {
    	 if (t==null)
	        	return -1;
		  int ans=-1;
		  if (t.left!=null)
			ans=t.left.height;
		  if (t.right!=null)
			ans= max(ans,t.right.height);
		return ans+1;
	}
    
    
    /* Compare the heights of two nodes and returns the higher */
    private int max(int HeightOfleftNode, int HeightOfrightNode){
    	if (HeightOfleftNode>HeightOfrightNode)
    		return HeightOfleftNode;
    	else return HeightOfrightNode;
    }
    
    
    /* Function for inserting data */
    public void insert(Comparable data){
        if (root==null){
        	root=new AVLNode(data);
        	numberOfNodes++;
        }
        else insert(data,root);
   	}

    
	/* --------------------------------------------------------------- * 
        Inserting the data iteratively, by comparing which binary 
        number is bigger. then, function updates the total number
        of nodes in the tree and send it for rotate examination.
    * ---------------------------------------------------------------- */
    private AVLNode insert(Comparable data, AVLNode t){
        	boolean insert=false;
        	AVLNode curr=t;
        	while(!insert){
        		if (curr.data.compareTo(data)==0){
        			return null;
        		}
        		if (curr.data.compareTo(data)>0){
        			if (curr.left==null){
        				curr.left=new AVLNode(data);
        				curr.left.father=curr;
        				insert=true;
        			}
        			curr=curr.left;
        		}
        		else {
        			if (curr.right==null){
        				curr.right=new AVLNode(data);
        				curr.right.father=curr;
        				insert=true;
        			}
        			curr=curr.right;
        		}	
        	}
        		numberOfNodes=numberOfNodes+1;
        		fixTree(curr);
        	return null;
        }
    
    
    /* --------------------------------------------------------------------- * 
        Update the heights of the nodes after an insertion has been made
        and examine if it's necessary to rotate the tree  
    *  --------------------------------------------------------------------- */
    private void fixTree (AVLNode curr){
    	int counter=0;
    	while (curr!=null){
    			if(counter>curr.height)
    				curr.height=counter;
    			int leftHeight=getHeight(curr.left);
    			int rightHeight=getHeight(curr.right);
    			if (Math.abs(rightHeight-leftHeight)>1){
    				AVLNode father;
    				if(rightHeight>leftHeight){
    					leftHeight=getHeight(curr.right.left);
            			rightHeight=getHeight(curr.right.right);
    					if(rightHeight>leftHeight)
        					father=rotateWithRightChild(curr);
        				else
        					father=doubleWithRightChild(curr);
        			}
        			else{
        				leftHeight=getHeight(curr.left.left);
            			rightHeight=getHeight(curr.left.right);
        				if (leftHeight<rightHeight){
        					 father=doubleWithLeftChild(curr);
        				}
        				else father=rotateWithLeftChild(curr);
        			}
    				curr=setHeights(father);
    			}
    			else {
    				curr=curr.father;
    				counter++;
    			}
    		}
    }
    
    
    /* function that set the heights of the AVLTree after the rotation */
    private AVLNode setHeights (AVLNode father){
    	while(father!=null){
    		  father.height=height(father);
    		  father=father.father;
    	}
    		  return father;
    }
    
    
    /* Rotate binary tree node with left child */
    private AVLNode rotateWithLeftChild(AVLNode k2){
    	if (k2==root)
    	{
    		AVLNode curr=k2.left.right;
    		root=k2.left;
    		root.left=k2.left.left;
    		root.right=k2;
    		root.father=null;
    		k2.left=curr;
    		if (curr!=null)
    			curr.father=k2;
    		k2.father=root;
    		k2.height=height(k2);
    		return root;
        }
    	else
    	{
    		AVLNode curr=k2.father;
    		AVLNode k3 = k2.left;
    		if (curr.right==k2)
    			curr.right=k3;
    		else
   		 		curr.left=k3;
            k2.left = k3.right;
            k3.right = k2;
            k3.father=curr;
            k2.father=k3;
             if (k2.left!=null)
            	 k2.left.father=k2;
             k2.height=height(k2);
             return(curr);
    	}
    }

    
    /* Rotate binary tree node with right child */
    private AVLNode rotateWithRightChild(AVLNode k1)
    {
    	if (k1==root)
    	{
    		AVLNode curr=k1.right.left;
    		root=k1.right;
    		root.right=k1.right.right;
    		root.left=k1;
    		root.father=null;
    		k1.right=curr;
    		if (curr!=null)
    			curr.father=k1;
    		k1.father=root;
    		k1.height=height(k1);
    		return root;
        }
    	else
    	{
    		 AVLNode curr=k1.father;
    		 AVLNode k3 = k1.right;
    		 
    		 if (curr.right==k1)
     			curr.right=k3;
     		 else
    		 		curr.left=k3;
             k1.right = k3.left;
             k3.left = k1;
             k3.father=curr;
             k1.father=k3;
             if (k1.right!=null)
            	 k1.right.father=k1;
             k1.height=height(k1);
             return(curr);
    	}

    }
    
    
    /* Set the tree for a rotation with right child, by using rotateWithRightChild */
    private AVLNode doubleWithRightChild(AVLNode k3)
    {
    	AVLNode curr=k3.right;
    	k3.right=curr.left;
    	curr.height--;
    	curr.left=k3.right.right;
    	k3.right.right=curr;
    	curr.father=k3.right;
    	if (curr.left!=null){
    		curr.left.father=curr;
    	}
    	k3.right.height++;
    	return rotateWithRightChild(k3);
    }
    
    
    /* Set the tree for a rotation with left child, by using rotateWithRleftChilds */
    private AVLNode doubleWithLeftChild(AVLNode k1)
    {
    	AVLNode curr=k1.left;
    	k1.left=curr.right;
    	curr.height--;
    	curr.right=k1.left.left;
    	k1.left.left=curr;
    	curr.father=k1.left;
    	if (curr.right!=null)
    		curr.right.father=curr;
    	k1.left.height++;
    	return rotateWithLeftChild(k1);  
    }
    
    
    /* Returns the amount of nodes in the tree */
    public int totalNodesInTree(){
    	int numOfNodes=numberOfNodes;
    	  return numOfNodes;
    }
    
    
    /* Calls to the private method of countNodes */
    public int countNodes(){
    	  return countNodes(root);
    }
    
    
    /* Update the number of nodes in the tree */
    private int countNodes(AVLNode r)
    {
    	if (r == null)
            return 0;
        else
        {
        	numberOfNodes = 1;
        	numberOfNodes = numberOfNodes+ countNodes(r.left);
        	numberOfNodes = numberOfNodes+ countNodes(r.right);
            return numberOfNodes;
        }
    }
    
    
    /* Calling the private function of search */
    public boolean search(Comparable val){
        if (root==null)
        	return false;
        else return search (root, val);
    }
    
    
    /* Searching for an element in the tree */
    private boolean search(AVLNode r, Comparable val)
    {
    	int compareResult = r.data.compareTo(val);
		boolean answer = (compareResult == 0);
		if (answer==false && compareResult < 0){
			answer= (r.right != null && search(r.right,val));
		}
		else if (answer==false && compareResult > 0){ 
			answer = (r.left != null && search(r.left,val));
		}
		return answer;
	}
    
    
    public void inorder(PrintWriter out){
        //Complete Your Code Here
    }
    
    
    private void inorder(AVLNode r, PrintWriter out){
        //Complete Your Code Here
    }
  
    
    public Pair <int[],Integer> getPrivateKey(String sIndex) {
    	  return getPrivateKey(root, sIndex);
    }

    
    /* ------------------------------------------------------------ * 
       Finds where the private key is located and returns the 
       array of the secret code and the numbers of comparisons
       between the nodes of the tree.
    * ------------------------------------------------------------- */
    private Pair <int[],Integer> getPrivateKey(AVLNode r, String sIndex) {
    	int counter=0;
    	AVLNode curr = r;
    	boolean found=false;
    	while (!found){
    		int compareResult = (curr.data.compareTo(sIndex));
    		if (curr.data.compareTo(sIndex)==0){
    			found=true;
    		}
    		else if (compareResult < 0 && !found){
    			curr=curr.right;
    		}
    		else if (compareResult > 0 && !found){
    			curr=curr.left;
    		}
    		counter=counter+1;
    	}
    	return new Pair <int[],Integer> (curr.data.riddle,counter);
    }
 
    
}
    
    