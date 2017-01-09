public class MinStack {
    class State {
        int value;
        int minValue;

        State(int value, int minValue) {
            this.value = value;
            this.minValue = minValue;
        }
    }

    private Deque<State> stack;

    public MinStack() {
        stack = new ArrayDeque<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(new State(x, x));
        } else {
            stack.push(new State(x, Math.min(getMin(), x)));
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek().value;
    }

    public int getMin() {
        return stack.peek().minValue;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
