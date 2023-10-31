// --== CS400 Fall 2023 File Header Information ==--
// Name: <Momen Elragaby>
// Email: <melragaby@wisc.edu>
// Group: <A04>
// TA: <Aydan Bailey>
// Lecturer: <Gary Dahl>
// Notes to Grader: <>

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.*;


public class IterableMultiKeyRBT <K extends Comparable<K>> extends RedBlackTree<KeyListInterface<K>> implements IterableMultiKeySortedCollectionInterface<K> {

    private K iterationStartPoint;
    private int numKeys;

    @Override
    public boolean insertSingleKey(K key) {

        KeyList<K> newKeyList = new KeyList<>(key);
        newKeyList.addKey(key);

        // Check if the key is already present in the tree
        Node<KeyListInterface<K>> nodeWithDuplicates = findNode(newKeyList);

        if (nodeWithDuplicates != null) {
            //keyList.addKey(key);
            // Key is a duplicate, don't add it again
            numKeys++;
            return false;
        } else {
            // Key is not a duplicate, insert it into the tree
            insert(newKeyList);
            numKeys++;
            return true;
        }

    }

    @Override
    public int numKeys() {
        return numKeys;

    }

    @Override
    public Iterator<K> iterator() {
        Stack<Node<KeyListInterface<K>>> stack = getStartStack();

        Iterator<K> iterator = new Iterator<>() {


            @Override
            public boolean hasNext(){
                return !stack.isEmpty();
            }

            @Override
            public K next(){
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                // Get the next node from the stack
                Node<KeyListInterface<K>> currentNode = stack.pop();
                K nextKey = currentNode.data.iterator().next();

                // Push the right subtree onto the stack
                if (currentNode.down[1] != null) {

                    stack.push(currentNode.down[1]);
                }

                return nextKey;

            }
        };

        return iterator;

    }

    @Override
    public void setIterationStartPoint(Comparable<K> startPoint) {

        iterationStartPoint = (K) startPoint;
    }

    @Override
    public void clear(){
        super.clear();
        numKeys = 0;
    }


    protected Stack<Node<KeyListInterface<K>>> getStartStack(){

        Stack<Node<KeyListInterface<K>>> stack = new Stack<>();

        Node<KeyListInterface<K>> currentNode = root;

        if (iterationStartPoint == null){

            while (currentNode != null){
                stack.push(currentNode);
                currentNode = currentNode.down[0];
            }

        } else {

            while (currentNode != null) {
                int compare = iterationStartPoint.compareTo(currentNode.data.iterator().next());
                if (compare <= 0) {
                    stack.push(currentNode);
                    currentNode = currentNode.down[0];
                } else {
                    currentNode = currentNode.down[1];
                }
            }
        }

        return stack;
    }

    /**
     * Test the insertion of keys and the iterator without setting a start point.
     * Verify that duplicates are not included in the iterator and the keys are in ascending order.
     */
    @Test
    public void testInsertionAndIteratorWithoutStartPoint() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        assertTrue(tree.insertSingleKey(5));

        assertTrue(tree.insertSingleKey(3));
        assertTrue(tree.insertSingleKey(7));
        assertFalse(tree.insertSingleKey(5)); // Testing duplicates


        List<Integer> expectedKeys = Arrays.asList(3, 5, 7); // Duplicates should not appear
        List<Integer> actualKeys = new ArrayList<>();
        for (Integer key : tree) {
            actualKeys.add(key);
        }

        assertEquals(expectedKeys, actualKeys);
    }

    /**
     * Test the insertion of keys and the iterator with a specified start point.
     * Ensure that the iterator starts from the set start point and includes keys greater than or equal to it.
     */
    @Test
    public void testInsertionAndIteratorWithStartPoint() {
        IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
        tree.setIterationStartPoint(5);
        assertTrue(tree.insertSingleKey(5));
        assertTrue(tree.insertSingleKey(3));
        assertTrue(tree.insertSingleKey(7));
        assertFalse(tree.insertSingleKey(5)); // Testing duplicates
        assertTrue(tree.insertSingleKey(8));

        List<Integer> expectedKeys = Arrays.asList(5, 7, 8); // Keys greater than or equal to 5
        List<Integer> actualKeys = new ArrayList<>();
        for (Integer key : tree) {
            actualKeys.add(key);
        }

        assertEquals(expectedKeys, actualKeys);
    }

    /**
     * Test the numKeys method and the clear method.
     * Verify that the numKeys method counts unique keys correctly and that the clear method clears the tree.
     **/
    @Test
    public void testNumKeysAndClear() {
        IterableMultiKeyRBT<String> tree = new IterableMultiKeyRBT<>();
        assertEquals(0, tree.numKeys());

        assertTrue(tree.insertSingleKey("apple"));
        assertTrue(tree.insertSingleKey("banana"));
        assertTrue(tree.insertSingleKey("cherry"));
        assertFalse(tree.insertSingleKey("apple")); // Testing duplicates

        assertEquals(4, tree.numKeys());
        Assertions.assertEquals(3, tree.size());


        tree.clear();
        assertEquals(0, tree.numKeys()); // After clearing, no keys should be left
    }
}
