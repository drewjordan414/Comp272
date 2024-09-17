package HW1;

/*
 * *** Drew Jordan / 002 ***
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer value.
     *
     * Methods:
     * - void sortedInsert(val) - Inserts value 'val' into the linked-list in
     * sorted fashion
     * - void removeElementsLT(val) - Removes values in the linked-list that are
     * less
     * than 'val'
     * - void removeElement(val) - Removes all values in the linked list of
     * value 'val'
     * - String toString() - Converts and returns the linked-list as a string
     * delimited by brackets []
     */
    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d) {
                data = d;
                next = null;
            }
        }

        Node head; // head of the Linked-list

        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in the parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         */
        public void sortedInsert(int data) {
            Node new_node = new Node(data);

            // Special case for the head node.
            if (this.head == null || head.data >= new_node.data) {
                new_node.next = head;
                head = new_node;
            } else {
                Node current = this.head;

                // Find where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }
        }

        /*
         * Method removeElementsLT() - This method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         * It calls the method removeElement() for each value found that is less than
         * the parameter value.
         */
        public void removeElementsLT(int ltValue) {
            Node current = this.head;

            // Remove elements less than the ltValue starting from the head
            while (current != null && current.data < ltValue) {
                removeElement(current.data);
                current = this.head;
            }

            // Continue removing elements in the list
            while (current != null && current.next != null) {
                if (current.next.data < ltValue) {
                    removeElement(current.next.data);
                } else {
                    current = current.next;
                }
            }
        }

        /*
         * Method removeElement() - This method removes all nodes that contain a
         * value equal to the provided parameter 'value'.
         */
        public void removeElement(int value) {
            // Special case for the head node
            while (this.head != null && this.head.data == value) {
                this.head = this.head.next;
            }

            Node current = this.head;

            // Traverse and remove matching elements
            while (current != null && current.next != null) {
                if (current.next.data == value) {
                    current.next = current.next.next; // Skip the matching node
                } else {
                    current = current.next; // Move to the next node
                }
            }
        }

        /*
         * Method toString() - this is a helper method for printing/converting
         * a string object from the linked-list.
         */
        public String toString() {
            StringBuilder output = new StringBuilder("[");
            Node currNode = this.head;
            while (currNode != null) {
                output.append(currNode.data).append(" ");
                currNode = currNode.next;
            }
            return output.toString().trim() + "]";
        }
    }

    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * Methods:
     * - boolean isPalindrome(string) - method returns true or false if 'string'
     * is a palindrome
     * - int findLargestK(stack, k) - method accepts a stack and returns the
     * largest index in the stack containing
     * value 'k' (stack index starts at 0)
     */
    static class Stacks {

        /*
         * Method isPalindrome() - This method returns true or false based on whether
         * the input string is a palindrome (ignores case and spaces).
         */
        public static boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>();
            input = input.toLowerCase().replaceAll("[^a-z]", ""); // Remove non-alphabetic chars

            // Push all characters into the stack
            for (char c : input.toCharArray()) {
                stack.push(c);
            }

            // Pop characters and compare with original string
            for (char c : input.toCharArray()) {
                if (c != stack.pop()) {
                    return false; // Not a palindrome
                }
            }
            return true; // Is a palindrome
        }

        /*
         * Method findLargestK() - This method returns the largest index
         * position in the stack for the value specified by the parameter 'k'.
         * The stack remains unchanged after the method execution.
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            int largestIndex = -1;
            int index = 0;
            Stack<Integer> tempStack = new Stack<>();

            // Pop elements from the original stack into the temp stack, checking for the
            // value 'k'
            while (!stack.isEmpty()) {
                int current = stack.pop();
                if (current == k) {
                    largestIndex = index; // Update the largest index when 'k' is found
                }
                tempStack.push(current);
                index++;
            }

            // Restore the original stack from the temp stack
            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }

            return largestIndex;
        }
    }

    /*
     * Algorithm Analysis 1
     *
     * Time complexity: O(N + M) because the loops run independently.
     * Space complexity: O(1) as no extra space is used besides a few variables.
     */
    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i = 0; i < n; i++) {
            a += Math.random();
        }

        for (int j = 0; j < m; j++) {
            b += Math.random();
        }

        return 3; // O(N + M) time, O(1) space
    }

    /*
     * Algorithm Analysis 2
     *
     * The inner loop runs in O(log N) time due to j = j * 2, and the outer loop
     * runs O(N) times.
     * Thus, the overall time complexity is O(N log N).
     */
    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n / 2; i <= n; i++) {
            for (j = 2; j <= n; j = j * 2) {
                k += n / 2;
            }
        }

        return 2; // O(N log N) time
    }
}
