package solution

import (
	"testing"
	"sort"
	"reflect"
)

func TestSolution(t *testing.T) {
	logSystem := Constructor()
	logSystem.Put(1, "2017:01:01:23:59:59");
	logSystem.Put(2, "2017:01:01:22:59:59");
	logSystem.Put(3, "2016:01:01:00:00:00");
	ret1 := logSystem.Retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
	 sort.Ints(ret1)
	if !reflect.DeepEqual(ret1, []int{1, 2, 3}) {
		t.Errorf("fail1")
	}
	ret2 := logSystem.Retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
	sort.Ints(ret2)
	if !reflect.DeepEqual(ret2, []int{1, 2}) {
		t.Errorf("fail2")
	}
}
