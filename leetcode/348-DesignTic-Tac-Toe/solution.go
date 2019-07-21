type TicTacToe struct {
  grid [][]byte
  size int
}


/** Initialize your data structure here. */
func Constructor(n int) TicTacToe {
  grid := make([][]byte, n)
  for i := 0; i < n; i++ {
    grid[i] = make([]byte, n)
  }
  return TicTacToe{
    grid: grid,
    size: n,
  }
}


/** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
func (this *TicTacToe) Move(row int, col int, player int) int {
  this.grid[row][col] = byte(player)
  if this.check(row, col, player) {
    return player
  }
  return 0
}

func (this *TicTacToe) check(row, col, player int) bool {
  success := [4]bool{true, true, row == col, row == this.size - col - 1}
  for i := 0; i < this.size; i++ {
    if success[0] && this.grid[row][i] != byte(player) {
      success[0] = false
    }
    if success[1] && this.grid[i][col] != byte(player) {
      success[1] = false
    }
    if success[2] && this.grid[i][i] != byte(player) {
      success[2] = false
    }
    if success[3] && this.grid[i][this.size - i - 1] != byte(player)  {
      success[3] = false
    }
    if !success[0] && !success[1] && !success[2] && !success[3] {
      return false
    }
  }
  return true
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Move(row,col,player);
 */
