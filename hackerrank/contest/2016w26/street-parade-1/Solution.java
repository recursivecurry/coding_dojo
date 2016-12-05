import java.io.*;
import java.util.*;

public class Solution {

    enum State {
        FIRST,
        MID,
        LAST,
        END
    }

    private long m;
    private long hMin;
    private long hMax;
    private long[] as;
    private long[] rs;
    private State state;
    private int start;
    private long startAt;
    private int current;
    private long remained;
    private long shift;


    public Solution(long m, long hMin, long hMax, long[] as, long shift) {
        this.m = m;
        this.hMin = hMin;
        this.hMax = hMax;
        this.as = as;
        this.rs = new long[as.length];
        for (int i=0; i<as.length-1; i++) {
            this.rs[i] = this.as[i+1] - this.as[i];
        }
        this.rs[rs.length-1] = hMax;
        this.state = State.FIRST;
        this.start = 0;
        this.startAt = 0;
        this.current = 0;
        this.remained = m;
        this.shift = shift;

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        long[] as = new long[n+2];
        String[] ss = scanner.nextLine().split(" ");
        long m = scanner.nextLong();
        long hMin = scanner.nextLong();
        long hMax = scanner.nextLong();
        as[0] = 0;
        long as0 = Long.valueOf(ss[0]);
        for (int i=0; i<n; i++) {
            as[i+1] = Long.valueOf(ss[i])-as0+hMax;
        }
        as[0] = 0;
        as[n+1] = as[n] + hMax;

        Solution solution = new Solution(m, hMin, hMax, as, hMax-as0);

        System.out.println(solution.solve());
    }

    private long solve() {

        end:
        while (current<as.length) {
            switch (state) {
                case FIRST:
                    first();
                    break;
                case MID:
                    mid();
                    break;
                case LAST:
                    last();
                    break;
                case END:
                    break end;
            }
        }
        return startAt-shift;
    }

    private void mid() {
        // 현재 위치를 포함하기 불가능하면 FIRST로 이동 종료가능성이 있으면 LAST로 이동.
        // 상태이동하기 전에는 현재위치를 포함하며 진행
        long startMax = rs[start] > hMax ? hMax : rs[start];

        if (rs[current] < hMin) {
            // 현재 위치가 조건을 만족하는 것이 불가능함. 새로운 위치에서 시작 필요
            state = State.FIRST;
        } else if (remained-startMax <= rs[current]) {
            state = State.LAST;
        } else {
            // 최대한 일찍 시작해도 지금 셀을 전부 들어야 한다.
            if (rs[current] > hMax) {
                // 지금 셀이 최대크기보다 커서 포함될 수 없다.
                state = State.FIRST;
            } else {
                // 지금셀을 포함한다.
                remained -= rs[current];
                current++;
                state = State.LAST;
            }
        }
    }

    private void last() {
        // 종료조건을 만족하면 END로 이동 만족하지 못 하면 FIRST나 MID로 이동
        long startMax = rs[start] > hMax ? hMax : rs[start];
        long endMax = rs[current] > hMax ? hMax : rs[current];

        if (remained-startMax >= hMin && remained-startMax <= endMax) {
            // 시작점에서 최대한 일찍 시작하였을때 종료?
            startAt = as[start] + rs[start] - startMax;
            state = State.END;
        } else if (remained-hMin >= hMin && (remained-hMin <= startMax || remained-hMin <= endMax)) {
            // 시작점 또는 종료점에서 최소한으로 시작하였을때 종료?
            startAt = as[start] + rs[start] - hMin;
            state = State.END;
        } else {
            // 종료를 못 하면?
            if (rs[current] < hMin) {
                // 현재점이 시작점/중간점의 최소조건을 만족못하면 새로운 시작점을 찾아야됨
                state = State.FIRST;
            } else {
                // 시작점을 1칸 앞으로 이동하여 다시 상태 확인
                start++;
                remained += rs[start];
                state = State.MID;
            }
        }
    }

    private void first() {
        // 시작할 수 있는 장소를 찾고 MID로 이동
        while (rs[current] < hMin) {
            current++;
        }
        start = current;
        remained = m;
        current++;
        state = State.MID;
    }
}
