type Seq struct {
	Index int
	Step  int
}

func numberOfArithmeticSlices2(A []int) int {
	total := 0
  ss := make(map[Seq]int)
	for i:=0; i<len(A); i++ {
		for j:=0; j<i; j++ {
			step := A[i] - A[j]
			cur, _ := ss[Seq{i, step}]
			prev, _ := ss[Seq{j, step}]
			ss[Seq{i, step}] = cur + prev + 1
			total += prev
		}
	}
	return total
}

func numberOfArithmeticSlices(A []int) int {
	total := 0
  ss := make(map[int]map[int]int)
	for i:=0; i<len(A); i++ {
    ss[i] = map[int]int{}
		for j:=0; j<i; j++ {
			step := A[i] - A[j]
			cur, _ := ss[i][step]
			prev, _ := ss[j][step]
			ss[i][step] = cur + prev + 1
			total += prev
		}
	}
	return total
}
