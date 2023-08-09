impl Solution {
    pub fn num_islands(grid: Vec<Vec<char>>) -> i32 {
        let height = grid.len();
        let width = grid[0].len();
        let mut u = new_union_find(width * height);
        for y in 0..height {
            for x in 0..width {
                if grid[y][x] == '0' {
                    continue;
                }
                if y > 0 && grid[y-1][x] == '1' {
                    u.merge(y * width + x, (y-1) * width + x);
                }
                if x > 0 && grid[y][x-1] == '1' {
                    u.merge(y * width + x, y * width + x - 1);
                }
            }
        }
        let mut count = 0;
        for y in 0..height {
            for x in 0..width {
                if grid[y][x] == '1' && u.find(y * width + x) == y * width + x {
                    count += 1;
                }
            }
        }
        count
    }
}
struct UnionFind {
    root: Vec<usize>,
    rank: Vec<usize>,
}

fn new_union_find(size: usize) -> UnionFind {
    UnionFind {
        root: (0..size).collect(),
        rank: vec![0; size],
    }
}

impl UnionFind {
    fn find(&mut self, n: usize) -> usize {
        if self.root[n] != n {
            self.root[n] = self.find(self.root[n]);
        }
        self.root[n]
    }

    fn merge(&mut self, a: usize, b: usize) -> usize {
        let a_set = self.find(a);
        let b_set = self.find(b);
        if a_set == b_set {
            return a_set;
        }
        if self.rank[a_set] < self.rank[b_set] {
            self.root[a_set] = b_set;
        } else if self.rank[a_set] > self.rank[b_set] {
            self.root[b_set] = a_set;
        } else {
            self.rank[a_set] += 1;
            self.root[b_set] = a_set;
        }
        return self.root[a_set];
    }
}
