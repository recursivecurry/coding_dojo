import "sort"

type Schedule struct {
    intervals []Interval
}

func (s *Schedule) Len() int {
    return len(s.intervals)
}

func (s *Schedule) Less(i, j int) bool {
    if s.intervals[i].Start < s.intervals[j].Start {
        return true
    }
    return false
}

func (s *Schedule) Swap(i, j int) {
    s.intervals[i], s.intervals[j] = s.intervals[j], s.intervals[i]
}

func (s *Schedule) Check() bool {
    for i:=0; i<len(s.intervals)-1; i++ {
        if s.intervals[i].End > s.intervals[i+1].Start {
            return false
        }
    }
    return true
}

func canAttendMeetings(intervals []Interval) bool {
    if len(intervals) <= 1 {
        return true
    }
    s := Schedule{intervals}
    sort.Sort(&s)
    return s.Check()
}
