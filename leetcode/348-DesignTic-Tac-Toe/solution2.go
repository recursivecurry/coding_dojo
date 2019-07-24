type TicTacToe struct {
  counter [][]int
  size int
}


/** Initialize your data structure here. */
func Constructor(n int) TicTacToe {
  counter := [][]int{make([]int, n), make([]int, n), make([]int, 2)}
  return TicTacToe{
    counter: counter,
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
  if player == 2 {
    player = -1
  }
  this.counter[0][row] += player
  if this.counter[0][row] == this.size {
    return 1
  } else if this.counter[0][row] == -this.size {
    return 2
  }
  this.counter[1][col] += player
  if this.counter[1][col] == this.size {
    return 1
  } else if this.counter[1][col] == -this.size {
    return 2
  }
  if row == col {
    this.counter[2][0] += player
    if this.counter[2][0] == this.size {
      return 1
    } else if this.counter[2][0] == -this.size {
      return 2
    }
  }
  if row == this.size - col - 1 {
    this.counter[2][1] += player
    if this.counter[2][1] == this.size {
      return 1
    } else if this.counter[2][1] == -this.size {
      return 2
    }
  }
  return 0
}

/**
 * Your TicTacToe object will be instantiated and called as such:
 * obj := Constructor(n);
 * param_1 := obj.Move(row,col,player);
 */
