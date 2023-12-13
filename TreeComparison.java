import java.util.Random;

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    TreeNode root;

    public BinaryTree(int rootData) {
        root = new TreeNode(rootData);
    }

    // Insertion in a binary tree
    public void insert(int data) {
        insert(root, data);
    }

    private void insert(TreeNode node, int data) {
        if (node == null) {
            return;
        }

        if (data < node.data) {
            if (node.left == null) {
                node.left = new TreeNode(data);
            } else {
                insert(node.left, data);
            }
        } else {
            if (node.right == null) {
                node.right = new TreeNode(data);
            } else {
                insert(node.right, data);
            }
        }
    }

    // Search in a binary tree
    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(TreeNode node, int data) {
        if (node == null) {
            return false;
        }

        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }
}

class AVLNode {
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}

class AVLTree {
    private AVLNode root;

    public AVLTree() {
        root = null;
    }

    // Get the root of the AVL tree
    public AVLNode getRoot() {
        return root;
    }

    // Get the height of a node (null-safe)
    private int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Update the height of a node based on its children
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    // Calculate the balance factor for a node
    private int getBalanceFactor(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // Right rotate a subtree rooted with y
    private AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // Left rotate a subtree rooted with x
    private AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // Balance the AVL tree after insertion
    private AVLNode insert(AVLNode node, int data) {
        // Perform the standard BST insertion
        if (node == null) {
            return new AVLNode(data);
        }

        if (data < node.data) {
            node.left = insert(node.left, data);
        } else if (data > node.data) {
            node.right = insert(node.right, data);
        } else {
            // Duplicate data is not allowed
            return node;
        }

        // Update height of this ancestor node
        updateHeight(node);

        // Get the balance factor
        int balance = getBalanceFactor(node);

        // Balance the node

        // Left heavy
        if (balance > 1) {
            if (data < node.left.data) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            if (data > node.right.data) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node;
    }

    // Insert data into the AVL tree
    public void insert(int data) {
        root = insert(root, data);
    }

    // Search for data in the AVL tree
    public boolean search(int data) {
        return search(root, data);
    }

    private boolean search(AVLNode node, int data) {
        if (node == null) {
            return false;
        }

        if (data == node.data) {
            return true;
        } else if (data < node.data) {
            return search(node.left, data);
        } else {
            return search(node.right, data);
        }
    }
}

public class TreeComparison {
    public static void main(String[] args) {
        int n = 1000; // Number of elements in the data sets
        int[] userArray = generateRandomArray(n);

        // Compare Binary Tree
        BinaryTree binaryTree = createBinaryTree(userArray);
        long binaryTreeSearchTime = measureSearchTime(binaryTree, userArray);
        int binaryTreeHeight = getHeight(binaryTree);

        // Compare Binary Search Tree
        BinaryTree binarySearchTree = createBinarySearchTree(userArray);
        long binarySearchTreeSearchTime = measureSearchTime(binarySearchTree, userArray);
        int binarySearchTreeHeight = getHeight(binarySearchTree);

        // Compare AVL Tree
        AVLTree avlTree = createAVLTree(userArray);
        long avlTreeSearchTime = measureSearchTime(avlTree, userArray);
        int avlTreeHeight = getHeight(avlTree);

        // Print the heights
        System.out.println("Height of Binary Tree: " + binaryTreeHeight);
        System.out.println("Height of Binary Search Tree: " + binarySearchTreeHeight);
        System.out.println("Height of AVL Tree: " + avlTreeHeight);

        // Print the search times
        System.out.println("Search Time for Binary Tree: " + binaryTreeSearchTime + " nanoseconds");
        System.out.println("Search Time for Binary Search Tree: " + binarySearchTreeSearchTime + " nanoseconds");
        System.out.println("Search Time for AVL Tree: " + avlTreeSearchTime + " nanoseconds");

        // Compare and print the efficiency
        compareEfficiency(binaryTreeSearchTime, binarySearchTreeSearchTime, avlTreeSearchTime);
    }

    // Generate a random array of integers for testing
    public static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    // Create a Binary Tree and insert data
    public static BinaryTree createBinaryTree(int[] userArray) {
        BinaryTree binaryTree = new BinaryTree(userArray[0]);
        for (int i = 1; i < userArray.length; i++) {
            binaryTree.insert(userArray[i]);
        }
        return binaryTree;
    }

    // Create a Binary Search Tree and insert data
    public static BinaryTree createBinarySearchTree(int[] userArray) {
        BinaryTree binarySearchTree = new BinaryTree(userArray[0]);
        for (int i = 1; i < userArray.length; i++) {
            binarySearchTree.insert(userArray[i]);
        }
        return binarySearchTree;
    }

    // Create an AVL Tree and insert data
    public static AVLTree createAVLTree(int[] userArray) {
        AVLTree avlTree = new AVLTree();
        for (int data : userArray) {
            avlTree.insert(data);
        }
        return avlTree;
    }

    // Measure the time taken to search all elements in the data set
    public static long measureSearchTime(BinaryTree tree, int[] userArray) {
        long startTime = System.nanoTime();
        for (int data : userArray) {
            tree.search(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Measure the time taken to search all elements in the data set
    public static long measureSearchTime(AVLTree tree, int[] userArray) {
        long startTime = System.nanoTime();
        for (int data : userArray) {
            tree.search(data);
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    // Print the height of a tree
    public static int getHeight(BinaryTree tree) {
        return getHeight(tree.root);
    }

    public static int getHeight(AVLTree tree) {
        return getHeight(tree.getRoot());
    }

    private static int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private static int getHeight(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Compare and print the efficiency
 // Compare and print the efficiency
    private static void compareEfficiency(long binaryTreeTime, long binarySearchTreeTime, long avlTreeTime) {
        System.out.println("Efficiency Comparison:");

        if (binaryTreeTime < binarySearchTreeTime && binaryTreeTime < avlTreeTime) {
            System.out.println("Binary Tree is the most efficient due to faster search times.");
        } else if (binarySearchTreeTime < avlTreeTime) {
            System.out.println("Binary Search Tree is the most efficient due to faster search times.");
        } else {
            System.out.println("AVL Tree is the most efficient due to self-balancing properties.");
        }
    }
}


