
/*
 *  RedBlack Tree implementation
 *  Functions:
 *  1. int add() returns the size the tree   -- O(logn)
 *  2. int rank(key k) gets the rank of the key-- O(logn)
 *  3. RedBlackNode get(rank r) gets the node with rank r-- O(logn)
 *  4. int size() gets the size of the Tree -- O(1)
 *
 *  The trick in the efficient implementation lied in assigning size to nodes on-the-run and during insertion.
 *  Once size as a variable of node is present for all the nodes, then method of rank becomes O(log n). Then we don't need
 *  to do O(n) traverse every time for getting the number of subtree elements.
 *
 * @Author : Nihar Raut
 * @email: nr2639@columbia.edu
 * Would be looking forward to next steps in the process.
 * Enjoyed coding this!!!
 *
 * */

 public class RedBlackTreeExcercise<T extends Comparable<T>> {

        private static final boolean RED = true;
        private static final boolean BLACK = false;

        private RedBlackNode header;     // root of the BST


        private class RedBlackNode {

            private T val;         // Value in the node
            private RedBlackNode left, right;  // links to left and right subtrees
            private boolean color;     // color of the node
            private int size;          // subtree count

            public RedBlackNode(T val, boolean color, int size) {
                this.val = val;
                this.color = color;
                this.size = size;
            }
        }

        // Function to check if the color of the node is red
        private boolean isRed(RedBlackNode x) {
            if (x == null) return false;
            return x.color == RED;
        }


     /******
      * Balancing Functions
      *
      */
        // make a left-leaning link lean to the right
        private RedBlackNode rotateRight(RedBlackNode node) {

            RedBlackNode current = node.left;
            node.left = current.right;
            current.right = node;
            current.color = current.right.color;
            current.right.color = RED;
            current.size = node.size;
            node.size = size(node.left) + size(node.right) + 1;
            return current;
        }

        // make a right-leaning link lean to the left
        private RedBlackNode rotateLeft(RedBlackNode node) {

            RedBlackNode current = node.right;
            node.right = current.left;
            current.left = node;
            current.color = current.left.color;
            current.left.color = RED;
            current.size = node.size;
            node.size = size(node.left) + size(node.right) + 1;
            return current;
        }

        // flip the colors of a node and its two children
        private void swapColors(RedBlackNode node) {
            node.color = !node.color;
            node.left.color = !node.left.color;
            node.right.color = !node.right.color;
        }

        /*
        * Utility Functions
        *
        * */
     //Insertion function. Returns the size of the tree

     public int add(T val) {
         if (val == null) throw new IllegalArgumentException("first argument to put() is null");

         header = add(header, val);
         header.color = BLACK;
         return size(header);
     }

     private RedBlackNode add(RedBlackNode node, T val) {
         if (node == null) return new RedBlackNode(val, RED, 1);

         int cmp = val.compareTo(node.val);
         if (cmp < 0) node.left = add(node.left, val);
         else if (cmp > 0) node.right = add(node.right, val);
         else node.val = val;

         // fix-up any right-leaning links
         if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
         if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
         if (isRed(node.left) && isRed(node.right)) swapColors(node);
         node.size = size(node.left) + size(node.right) + 1;

         return node;
     }

     //Returns the rank of the value being searched
        //Where rank is the index of the element after sorting the elements in the ascending order
        public int rank(T val) {
            if (val == null) throw new IllegalArgumentException("argument to rank() is null");
            return rank(val, header);
        }
        // number of keys less than key in the subtree rooted at x
        private int rank(T val, RedBlackNode node) {
            if (node == null) return 0;
            int cmp = val.compareTo(node.val);
            if (cmp < 0) return rank(val, node.left);
            else if (cmp > 0) return 1 + size(node.left) + rank(val, node.right);
            else return size(node.left);
        }


        //Returns the node indexed by Rank
        public RedBlackNode get(int k) {
            if (k < 0 || k >= size()) return null;

            RedBlackNode x = get(header, k);
            return x;
        }

        public RedBlackNode get(RedBlackNode node, int k) {

            int t = size(node.left);

            if (t > k) {
                return get(node.left, k);
            } else if (t < k) {
                return get(node.right, k - t - 1);
            } else {
                return node;
            }
        }

     //Returns the count of subnodes in the node
     private int size(RedBlackNode node) {
         if (node == null) return 0;
         return node.size;
     }
     //Returns the size of whole tree
     public int size() {
         return size(header);
     }


     /**************************
      *  Some functions to test if the RedBlack tree is balances and follows all the required conditions
      * @return 1 if the test is successfull 0 if test fails
      */


     //Counts the number of blacknodes in the path to right most and left most null node
     //Not a rigorous test.
     public int count_blackNodes() {
         int right_black_node_count = 0;
         int left_black_node_count = 0;
         RedBlackNode node = header;
         while (node != null) {


             if (node.color == BLACK) right_black_node_count++;
             node = node.right;

         }
         node = header;
         while (node != null) {


             if (node.color == BLACK) left_black_node_count++;
             node = node.left;

         }
         // System.out.print("In class, count"+right_black_node_count + " "+left_black_node_count );
         if (right_black_node_count != left_black_node_count)
             return 0;
         else
             return 1;


     }

     //Checks  parent child red nodes
     public int check_parent_child_Red() {
         return check_parent_child_Red(header);
     }

     public int check_parent_child_Red(RedBlackTreeExcercise.RedBlackNode t) {
         if (t == null)
             return 1;
         else {
             if (isRed(t) && (isRed(t.right) || isRed(t.left)))
             {
                 System.out.println("Violation found");
                 System.out.println("Parent "+ t.color + t.val);
                 System.out.println("Parent "+ t.left.color + t.left.val   );
                 System.out.println("Parent "+ t.right.color + t.right.val);

                 return 0;
             }
             check_parent_child_Red(t.left);
             check_parent_child_Red(t.right);
             return 1;
         }

     }

 }

