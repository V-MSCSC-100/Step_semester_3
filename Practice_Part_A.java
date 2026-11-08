//Problem 1 – Remove N-th Node From End
//Approach:** Use two pointers and a dummy node. Move the fast pointer N steps ahead, then move both pointers until fast reaches the end. Delete the node after slow.
Node removeNthFromEnd(Node head, int n) {
    Node dummy = new Node(0);
    dummy.next = head;

    Node slow = dummy, fast = dummy;

    for (int i = 0; i < n; i++)
        fast = fast.next;

    while (fast.next != null) {
        fast = fast.next;
        slow = slow.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
}
//Complexity:** Time O(n), Space O(1).

//Problem 2 – Check if a Linked List is a Palindrome
//Approach:** Find the middle using slow and fast pointers. For odd-length lists, skip the middle node before reversing the second half. Compare both halves.
boolean isPalindrome(Node head) {
    Node slow = head, fast = head;

    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    if (fast != null)
        slow = slow.next;

    Node second = reverse(slow);

    while (second != null) {
        if (head.data != second.data)
            return false;

        head = head.next;
        second = second.next;
    }

    return true;
}
//Complexity:** Time O(n), Space O(1).

//Problem 3 – Add Two Numbers Represented by Linked Lists
//Approach:** Add corresponding digits and carry. Create a new node for each result digit.
Node addNumbers(Node a, Node b) {
    Node dummy = new Node(0);
    Node tail = dummy;

    int carry = 0;

    while (a != null || b != null || carry != 0) {
        int sum = carry;

        if (a != null) {
            sum += a.data;
            a = a.next;
        }

        if (b != null) {
            sum += b.data;
            b = b.next;
        }

        carry = sum / 10;
        tail.next = new Node(sum % 10);
        tail = tail.next;
    }

    return dummy.next;
}
//Complexity:** Time O(max(m,n)), Space O(max(m,n)) for the result list.

//Problem 4 – Find Intersection Node of Two Linked Lists
//Approach:** Calculate both list lengths, move the longer list ahead by the difference, then compare node addresses.
int length(Node head) {
    int n = 0;

    while (head != null) {
        n++;
        head = head.next;
    }

    return n;
}

Node findIntersection(Node a, Node b) {
    int m = length(a), n = length(b);

    while (m > n) {
        a = a.next;
        m--;
    }

    while (n > m) {
        b = b.next;
        n--;
    }

    while (a != null && b != null) {
        if (a == b)
            return a;

        a = a.next;
        b = b.next;
    }

    return null;
}
//Complexity:** Time O(m+n), Space O(1).

//Problem 5 – Reorder Linked List Alternately From Ends
//Approach:** Combine three techniques:
//1. Find the middle using slow and fast pointers.
//2. Reverse the second half.
//3. Merge both halves alternately.
void reorderList(Node head) {
    if (head == null || head.next == null)
        return;

    Node slow = head, fast = head;

    while (fast.next != null && fast.next.next != null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    Node second = reverse(slow.next);
    slow.next = null;

    Node first = head;

    while (second != null) {
        Node a = first.next;
        Node b = second.next;

        first.next = second;
        second.next = a;

        first = a;
        second = b;
    }
}
//Complexity:** Time O(n), Space O(1).
