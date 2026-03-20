public class PlaceNameBST {

    // Node class (inner class)
    private class BSTNode {
        PlaceNameEntry data;
        BSTNode left;
        BSTNode right;

        public BSTNode(PlaceNameEntry data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private BSTNode root;
    private int comparisons;

    public PlaceNameBST() {     //constructor
        root = null;
        comparisons = 0;
    }

     public void insert(PlaceNameEntry entry) {
        root = insertRec(root, entry);
    }

    private BSTNode insertRec(BSTNode node, PlaceNameEntry entry) {
        if (node == null) {
            return new BSTNode(entry);
        }

        int cmp = entry.getPlaceName().compareTo(node.data.getPlaceName());

        if (cmp < 0) {
            node.left = insertRec(node.left, entry);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, entry);
        }
        // if cmp == 0 → duplicate → do nothing (skip)

        return node;
    }

 // search method
    public PlaceNameEntry search(String name) {
        comparisons = 0; // reset counter
        return searchRec(root, name);
    }

    private PlaceNameEntry searchRec(BSTNode node, String name) {
        if (node == null) {
            return null;
        }

        comparisons++;
        int cmp = name.compareTo(node.data.getPlaceName());

        if (cmp == 0) {
            return node.data;
        } else if (cmp < 0) {
            return searchRec(node.left, name);
        } else {
            return searchRec(node.right, name);
        }
    }

       public int getComparisons() {  //comparisons
        return comparisons;
    }


    public void clear() {  //tree reset
        root = null;
    }
}