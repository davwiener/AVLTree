/* Class AVLNode */
class AVLNode
{
    AVLNode left, right, father;
    Comparable data;
    int height;

    /* Constructor */
    public AVLNode(){
        left = null;
        right = null;
        father=null;
        data = null;
        height = 0;
    }
    
    /* Constructor */
    public AVLNode(Comparable n){
        left = null;
        right = null;
        father=null;
        data = n;
        height = 0;
    }

}
 