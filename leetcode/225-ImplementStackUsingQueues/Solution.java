class MyStack {
    Deque<Integer> q1 = new ArrayDeque<>();
    Deque<Integer> q2 = new ArrayDeque<>();
    // Push element x onto stack.
    public void push(int x) {
        if (q1.isEmpty() && q2.isEmpty()) {
            q1.addLast(x);
        } else if (q1.isEmpty()) {
            q1.addLast(x);
            while (!q2.isEmpty()) {
                q1.addLast(q2.pollFirst());
            }
        } else {
            q2.addLast(x);
            while (!q1.isEmpty()) {
                q2.addLast(q1.pollFirst());
            }
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
        if (q1.isEmpty()) {
            q2.pollFirst();
        } else {
            q1.pollFirst();
        }
    }

    // Get the top element.
    public int top() {
        if (q1.isEmpty()) {
            return q2.peekFirst();
        } else {
            return q1.peekFirst();
        }
    }

    // Return whether the stack is empty.
    public boolean empty() {
        if (q1.isEmpty() && q2.isEmpty()) {
            return true;
        }
        return false;
    }
}
