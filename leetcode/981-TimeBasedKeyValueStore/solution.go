type ValueWithTime struct {
  Value string
  Time int
}

type TimeMap map[string][]ValueWithTime


/** Initialize your data structure here. */
func Constructor() TimeMap {
  return make(TimeMap)
}


func (this *TimeMap) Set(key string, value string, timestamp int)  {
  values, _ := (*this)[key]
  (*this)[key] = append(values, ValueWithTime{value, timestamp})
}


func (this *TimeMap) Get(key string, timestamp int) string {
  values, _ := (*this)[key]
  if found := sort.Search(len(values), func(i int) bool {
    return values[i].Time > timestamp
  }); found != -1 {
    if found > 0 {
      return values[found-1].Value
    } else {
      return ""
    }
  }
  if len(values) > 0 {
    return values[len(values)-1].Value
  }
  return ""
}


/**
 * Your TimeMap object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Set(key,value,timestamp);
 * param_2 := obj.Get(key,timestamp);
 */
