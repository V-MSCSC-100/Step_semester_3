class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Main {
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverse(slow.next);
        Node first = head;
        Node second = secondHalf;
        boolean result = true;

        while (second != null) {
            if (first.data != second.data) {
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        slow.next = reverse(secondHalf);

        return result;
    }

    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)
// Array approach: O(n) time and O(n) extra space
