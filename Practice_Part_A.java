//Problem 1
//Approach: Use two pointers and a dummy node. Move the fast pointer N steps ahead, then move both pointers until fast reaches NULL. Delete the node after slow.
struct Node* removeNthFromEnd(struct Node *head, int n) {
    struct Node dummy;
    dummy.next = head;

    struct Node *slow = &dummy, *fast = &dummy;

    for (int i = 0; i < n; i++)
        fast = fast->next;

    while (fast->next) {
        fast = fast->next;
        slow = slow->next;
    }

    struct Node *temp = slow->next;
    slow->next = temp->next;
    free(temp);

    return dummy.next;
}
//Complexity: Time O(n), Space O(1).

//Problem 2
//For odd-length lists, the palindrome function should skip the middle node before reversing the second half
int isPalindrome(struct Node *head) {
    struct Node *slow = head, *fast = head;

    while (fast && fast->next) {
        slow = slow->next;
        fast = fast->next->next;
    }

    if (fast)
        slow = slow->next;

    struct Node *second = reverse(slow);

    while (second) {
        if (head->data != second->data)
            return 0;

        head = head->next;
        second = second->next;
    }

    return 1;
}
//Complexity: Time O(n), Space O(1)

//Problem 3
//Approach: Add corresponding digits and carry. Create a new node for each result digit.
struct Node* addNumbers(struct Node *a, struct Node *b) {
    struct Node dummy;
    struct Node *tail = &dummy;
    dummy.next = NULL;

    int carry = 0;

    while (a || b || carry) {
        int sum = carry;

        if (a) {
            sum += a->data;
            a = a->next;
        }

        if (b) {
            sum += b->data;
            b = b->next;
        }

        carry = sum / 10;
        tail->next = createNode(sum % 10);
        tail = tail->next;
    }

    return dummy.next;
}
//Complexity: Time O(max(m,n)), Space O(max(m,n)) for the result list

//Problem 4
//Approach: Calculate both list lengths, move the longer list ahead by the difference, then compare node addresses
int length(struct Node *head) {
    int n = 0;

    while (head) {
        n++;
        head = head->next;
    }

    return n;
}

struct Node* findIntersection(struct Node *a, struct Node *b) {
    int m = length(a), n = length(b);

    while (m > n) {
        a = a->next;
        m--;
    }

    while (n > m) {
        b = b->next;
        n--;
    }

    while (a && b) {
        if (a == b)
            return a;

        a = a->next;
        b = b->next;
    }

    return NULL;
}
//Complexity: Time O(m+n), Space O(1).

//Problem 5
//Approach: Combine three techniques:
//1. Find the middle using slow and fast pointers.
//2. Reverse the second half.
//3. Merge both halves alternately
void reorderList(struct Node *head) {
    if (!head || !head->next)
        return;

    struct Node *slow = head, *fast = head;

    while (fast->next && fast->next->next) {
        slow = slow->next;
        fast = fast->next->next;
    }

    struct Node *second = reverse(slow->next);
    slow->next = NULL;

    struct Node *first = head;

    while (second) {
        struct Node *a = first->next;
        struct Node *b = second->next;

        first->next = second;
        second->next = a;

        first = a;
        second = b;
    }
}
//Complexity: Time O(n), Space O(1).
