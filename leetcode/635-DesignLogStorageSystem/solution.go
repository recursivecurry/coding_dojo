package solution

import (
	"sort"
)

type Row struct {
	id int
	timestamp string
}

type LogSystem struct {
	logs []Row
}


func Constructor() LogSystem {
	return LogSystem {
		logs: []Row{},
	}
}


func (this *LogSystem) Put(id int, timestamp string)  {
	found := sort.Search(len(this.logs), func(i int) bool {
		return timestamp < this.logs[i].timestamp
	})
	this.logs = append(this.logs[:found], append([]Row{Row{id: id, timestamp: timestamp}}, this.logs[found:]...)...)
}

func (this *LogSystem) Retrieve(s string, e string, gra string) []int {
	filtered := []int{}
	graS := granularity(s, gra)
	graE := granularity(e, gra)
	end := sort.Search(len(this.logs), func(i int) bool {
		return graE < granularity(this.logs[i].timestamp, gra)
	})
	begin := sort.Search(end, func(i int) bool {
		return graS <= granularity(this.logs[i].timestamp, gra)
	})
	for i := begin; i < end; i++ {
		filtered = append(filtered, this.logs[i].id)
	}
	return filtered
}

func granularity(t, gra string) string {
	switch gra {
	case "Year":
		return t[:4]
	case "Month":
		return t[:7]
	case "Day":
		return t[:10]
	case "Hour":
		return t[:13]
	case "Minute":
		return t[:16]
	default:
		return t
	}
}
