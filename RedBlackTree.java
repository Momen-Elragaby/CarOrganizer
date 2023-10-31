// --== CS400 Fall 2023 File Header Information ==--
// Name: <Momen Elragaby>
// Email: <melragaby@wisc.edu>
// Group: <A04>
// TA: <Aydan Bailey>
// Lecturer: <Gary Dahl>
// Notes to Grader: <>

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RedBlackTree <T extends Comparable<T>> extends BinarySearchTree<T> {

    protected static class RBTNode<T> extends Node<T> {
        public int blackHeight = 0;
        public RBTNode(T data) {
            super(data);
        }
        public RBTNode<T> getUp() {
            return (RBTNode<T>)this.up;
        }
        public RBTNode<T> getDownLeft() {
            return (RBTNode<T>)this.down[0];
        }
        public RBTNode<T> getDownRight() {
            return (RBTNode<T>)this.down[1];
        }
    }
    /**
     * Enforces the Red-Black Tree properties after inserting a new node
     * This method ensures that the Red-Black Tree properties, such as black height
     * and node color, are maintained after inserting a new node.
     * @param newNode the newly inserted Red-Black Tree Node
     */
    protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {
        if (newNode == root || newNode.getUp().blackHeight == 1) {
            return;
        }
        if(newNode.getUp()== null) {
            return;
        }

        RBTNode<T> parent =newNode.getUp();
        RBTNode<T> grandparent = parent.getUp();
        RBTNode<T> uncle = null;
        if (parent.isRightChild()) {
            uncle = grandparent.getDownLeft();
        } else {
            uncle = grandparent.getDownRight();
        }

        if(uncle == null || uncle.blackHeight==1) {
            if(newNode.isRightChild() == parent.isRightChild()) {
                this.rotate(parent, grandparent);
                grandparent.blackHeight=0;
                parent.blackHeight = 0;

            }else {
                this.rotate(newNode, parent);
                this.rotate(newNode, grandparent);
                grandparent.blackHeight = 0;
                newNode.blackHeight =1;
            }

        }else if(uncle.blackHeight ==0) {
            parent.blackHeight =1;
            uncle.blackHeight=1;
            grandparent.blackHeight=0;
            enforceRBTreePropertiesAfterInsert(grandparent);
        }

    }


    @Override
    /**
     * Inserts a new node with the given data into the Red-Black Tree. If the insertion
     * is successful, enforces the Red-Black Tree properties after the insertion.
     *
     * @param data the data to be inserted into the Red-Black Tree
     * @return true if the data is successfully inserted, false otherwise
     * @throws NullPointerException if the provided data is null
     */
    public boolean insert(T data) throws NullPointerException {
        RBTNode<T> newNode = new RBTNode<>(data);
        boolean inserted = this.insertHelper(newNode);

        if(inserted) {
            enforceRBTreePropertiesAfterInsert(newNode);
        }
        RBTNode<T> root = (RBTNode<T>) this.root;
        root.blackHeight = 1;

        return inserted;
    }


    @Test
    public void testa() {
        Integer[] data = new Integer[] {5,2,7};
        RedBlackTree<Integer> newTree =  new RedBlackTree<>();
        for(Integer value: data) newTree.insert(value);

        newTree.insert(6);

        RBTNode<Integer> node = (RBTNode<Integer>) newTree.findNode(6);
        assertEquals(node.blackHeight,0);
        node =(RBTNode<Integer>) newTree.findNode(7);
        assertEquals(node.blackHeight,1);
        node =(RBTNode<Integer>) newTree.findNode(2);
        assertEquals(node.blackHeight,1);
        node =(RBTNode<Integer>) newTree.findNode(5);
        assertEquals(node.blackHeight,1);
    }

    @Test
    public void testb() {

        RedBlackTree<Integer> newTree = new RedBlackTree<>();
        newTree.insert(10); // black node
        newTree.insert(5); //red node

        RBTNode<Integer> node = (RBTNode<Integer>) newTree.findNode(5);
        assertEquals(node.blackHeight,0);

    }
    @Test
    public void testc() {
        RedBlackTree<Integer> newTree = new RedBlackTree<>();
        newTree.insert(10); // Black Node
        newTree.insert(5);//Red Node
        newTree.insert(3);//Red node
        RBTNode<Integer> node = (RBTNode<Integer>) newTree.findNode(3);
        assertEquals(node.blackHeight,0);




    }


}
