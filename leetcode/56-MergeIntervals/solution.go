import "sort"

type Intervals []Interval

func (it Intervals) Len() int {
	return len(it)
}

func (it Intervals) Less(i, j int) bool {
	return it[i].Start < it[j].Start || (it[i].Start == it[j].Start && it[i].End < it[j].End)
}

func (it Intervals) Swap(i, j int) {
	it[i], it[j] = it[j], it[i]
}

/**
 * Definition for an interval.
 * type Interval struct {
 *	   Start int
 *	   End   int
 * }
 */
func merge(intervals []Interval) []Interval {
	sort.Sort(Intervals(intervals))
	merged := make([]Interval, 0, len(intervals))
	for _, i := range intervals {
		if len(merged) == 0 {
			merged = append(merged, i)
		} else {
			last := merged[len(merged)-1]
			if last.Start <= i.Start && i.Start <= last.End {
				if last.End < i.End {
					merged[len(merged)-1].End = i.End
				}
			} else {
				merged = append(merged, i)
			}
		}
	}
	return merged
}
