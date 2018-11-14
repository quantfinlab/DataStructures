import org.junit.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.Collections;
import java.util.*;


 public class TestJunit {

     /*
     * The setUp function creates two instances of RedBlack Tree.
     * First one would be filled up with random integers and the second one would be our worstcase scenario of
     * elements in increasing order.
     * Size of tree would also be random.
     *
     * */
    private static RedBlackTreeExcercise st;
    private static ArrayList<Integer> inserted_elements;
    private  static ArrayList<Integer> inserted_elements2;
    private static RedBlackTreeExcercise st_worstCase;

    @BeforeClass
    public static void setUp() {
        st = new RedBlackTreeExcercise();
        inserted_elements = new ArrayList<Integer>();
        st_worstCase = new RedBlackTreeExcercise();
        inserted_elements2 = new ArrayList<Integer>();

        Random rd = new Random();
        int rand_size = rd.nextInt(1000);
        boolean rand_sign ;
        int x;
        int y = 0;
        int k=0;
        for (int i = 1; i<= rand_size; i++) {
            rand_sign = rd.nextBoolean();
            x = rd.nextInt(100) + 1;
            y=x;
            if(rand_sign)
                y=x*-1;
            if(!inserted_elements.contains(y)) {
                st.add(y);
                inserted_elements.add(y);
            }
            k=k+x;
            st_worstCase.add(k);
            inserted_elements2.add(k);
        }


        System.out.println("Size of tree " + st.size());
        System.out.println("Inserted elements in normal case: " + inserted_elements.toString());
        System.out.println("Inserted elements in worst case: " + inserted_elements2.toString());

    }
     /**
      * Below test counts the number of black nodes from extreme right and extreme left null nodes
      * Ideally, it should count the number of black nodes from all nullNodes, but because of lack of time I
      * have implemented it only of extreme left and extreme right null nodes.
      */

        @Test
        public void check_blackNodeCount(){
            int blacknode_count = st.count_blackNodes();
            int worst_blackCount = st_worstCase.count_blackNodes();
            //System.out.println("Printing Black Node count equality");
            //System.out.println(blacknode_count +" "+ blacknode_count);
             Assert.assertEquals(blacknode_count,1);
             Assert.assertEquals(worst_blackCount,1);

        }

     /**
      * Below does a traversal of all the nodes and checks if there are any child & parent both of Red color.
      */

        @Test
        public void check_parent_child_Red_Violation(){
            if(st!=null) {
                Assert.assertEquals(st.check_parent_child_Red(), 1);
                Assert.assertEquals(st_worstCase.check_parent_child_Red(), 1);
            }
        }

     /**
      * Checks the rank of the minimum and maximum element inserted in the structure.
      * Minimum element should have the rank 0 and maximum should have rank size-1
      */
       @Test
       public void check_rank(){
            int min_element ; int max_element;

           min_element = Collections.min(inserted_elements);
           max_element = Collections.max(inserted_elements);

            max_element = Collections.max(inserted_elements);
            //System.out.println("Min Element " +min_element);
            //System.out.println("Max Element " +max_element);
            Assert.assertEquals(st.rank(min_element),0);
            Assert.assertEquals(st.rank(max_element),inserted_elements.size()-1);

           min_element = Collections.min(inserted_elements2);
           max_element = Collections.max(inserted_elements2);
           //System.out.println("Min Element " +min_element);
           //System.out.println("Max Element " +max_element);
           Assert.assertEquals(st_worstCase.rank(min_element),0);
           Assert.assertEquals(st_worstCase.rank(max_element),inserted_elements2.size()-1);


       }




    }









