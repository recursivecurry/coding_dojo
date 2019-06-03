/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (n NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (n NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (n *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (n NestedInteger) GetList() []*NestedInteger {}
 */
func depthSumInverse(nestedList []*NestedInteger) int {
  depthList := make([]int, len(nestedList))
  valList := make([]int, 0)
  valDepthList := make([]int, 0)
  maxDepth := 0
  for i := 0; i < len(nestedList); i++ {
    val := nestedList[i]
    if val.IsInteger() {
      valList = append(valList, val.GetInteger())
      valDepthList = append(valDepthList, depthList[i])
      if depthList[i] > maxDepth {
        maxDepth = depthList[i]
      }
    } else {
      for _, nv := range val.GetList() {
        nestedList = append(nestedList, nv)
        depthList = append(depthList, depthList[i]+1)
      }
    }
  }
  sum := 0
  for i := 0; i < len(valList); i++ {
    sum += (maxDepth + 1 - valDepthList[i]) * valList[i]
  }
  return sum
}
