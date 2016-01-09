; 4Clojure
; https://www.4clojure.com/

; 1
(= true true)

; 2
(= (- 10 (* 2 3)) 4)

; 3
(= "HELLO WORLD" (.toUpperCase "hello world"))

; 4
(= (list :a :b :c) '(:a :b :c))

; 5
(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

; 6
(= [:a :b :c] (list :a :b :c) (vec '(:a :b :c)) (vector :a :b :c))

; 7
(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

; 8
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

; 9
(= #{1 2 3 4} (conj #{1 4 3} 2))

; 10
(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

; 11
(= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3]))

; 12
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))

; 13
(= [20 30 40] (rest [10 20 30 40]))

; 14
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

; 15
(= (* 2 2) 4)
(= (* 2 3) 6)
(= (* 2 11) 22)
(= (* 2 7) 14)

; 16
(= (#(str "Hello, " % "!") "Dave") "Hello, Dave!")
(= (#(str "Hello, " % "!") "Jenn") "Hello, Jenn!")
(= (#(str "Hello, " % "!") "Rhea") "Hello, Rhea!")

; 17
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

; 18
(= '(6 7) (filter #(> % 5) '(3 4 5 6 7)))

; 19
(def nineteen
  (fn lst [xs]
    (if (= 1 (count xs))
      (first xs)
      (lst (rest xs)))))

(= (nineteen [1 2 3 4 5]) 5)
(= (nineteen '(5 4 3)) 3)
(= (nineteen ["b" "c" "d"]) "d")

; 20
(def twenty
  (comp last drop-last))

(= (twenty (list 1 2 3 4 5)) 4)
(= (twenty ["a" "b" "c"]) "b")
(= (twenty [[1 2] [3 4]]) [1 2])

; 21
(def twenty-one
  #(first (drop %2 %1)))

(= (twenty-one '(4 5 6 7) 2) 6)
(= (twenty-one [:a :b :c] 0) :a)
(= (twenty-one [1 2 3 4] 1) 2)
(= (twenty-one '([1 2] [3 4] [5 6]) 2) [5 6])

; 22
(= (reduce (fn [acc _] (inc acc)) 0 '(1 2 3 3 1)) 5)
(= (reduce (fn [acc _] (inc acc)) 0 "Hello World") 11)
(= (reduce (fn [acc _] (inc acc)) 0 [[1 2] [3 4] [5 6]]) 3)
(= (reduce (fn [acc _] (inc acc)) 0 '(13)) 1)
(= (reduce (fn [acc _] (inc acc)) 0 '(:a :b :c)) 3)

; 23
(def twenty-three
  (fn rev [xs]
    (if (seq xs)
      (concat (rev (rest xs)) (list (first xs)))
      '())))

(= (twenty-three [1 2 3 4 5]) [5 4 3 2 1])
(= (twenty-three (sorted-set 5 7 2 7)) '(7 5 2))
(= (twenty-three [[1 2] [3 4] [5 6]]) [[5 6] [3 4] [1 2]])

; 24
(= (apply + [1 2 3]) 6)
(= (apply + (list 0 -2 5 5)) 8)
(= (apply + #{4 2 1}) 7)
(= (apply + '(0 0 -1)) -1)
(= (apply + '(1 10 3)) 14)

; 25
(= (filter odd? #{1 2 3 4 5}) '(1 3 5))
(= (filter odd? [4 2 1 6]) '(1))
(= (filter odd? [2 2 4 6]) '())
(= (filter odd? [1 1 1 3]) '(1 1 1 3))

; 26
(def twenty-six
  (fn fibo [n]
    (case n
      1 '(1)
      2 '(1 1)
      (concat (fibo (dec n)) (list (apply + (drop (- n 3) (fibo (dec n)))))))))

(= (twenty-six 3) '(1 1 2))
(= (twenty-six 6) '(1 1 2 3 5 8))
(= (twenty-six 8) '(1 1 2 3 5 8 13 21))

; 27
(def twenty-seven
  #(= (seq %) (reverse (seq %))))

(false? (twenty-seven '(1 2 3 4 5)))
(true? (twenty-seven "racecar"))
(true? (twenty-seven [:foo :bar :foo]))
(true? (twenty-seven '(1 1 3 3 1 1)))
(false? (twenty-seven '(:a :b :c)))

; 28
(def twenty-eight
  (fn fl [[h & r]]
    (if h
      (if (sequential? h)
        (concat (fl h) (fl r))
        (cons h (fl r))))))

(= (twenty-eight '((1 2) 3 [4 [5 6]])) '(1 2 3 4 5 6))
(= (twenty-eight ["a" ["b"] "c"]) '("a" "b" "c"))
(= (twenty-eight '((((:a))))) '(:a))

; 29
(def twenty-nine
  (fn [s]
    (apply str (filter #(Character/isUpperCase %) s)
           )))

(= (twenty-nine "HeLlO, WoRlD!") "HLOWRD")
(empty? (twenty-nine "nothing"))
(= (twenty-nine "$#A(*&987Zf") "AZ")

; 30
(def thirty
  #(map first (partition-by identity %)))

(= (apply str (thirty "Leeeeeerrroyyy")) "Leroy")
(= (thirty [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
(= (thirty [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2]))

; 31
(def thirty-one
  #(partition-by identity %))

(= (thirty-one [1 1 2 1 1 1 3 3]) '((1 1) (2) (1 1 1) (3 3)))
(= (thirty-one [:a :a :b :b :c]) '((:a :a) (:b :b) (:c)))
(= (thirty-one [[1 2] [1 2] [3 4]]) '(([1 2] [1 2]) ([3 4])))

; 32
(= (mapcat #(list %1 %1) [1 2 3]) '(1 1 2 2 3 3))
(= (mapcat #(list %1 %1) [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
(= (mapcat #(list %1 %1) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
(= (mapcat #(list %1 %1) [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))

; 33
(def thirty-three
  #(apply mapcat list (repeat %2 %1)))

(= (thirty-three [1 2 3] 2) '(1 1 2 2 3 3))
(= (thirty-three [:a :b] 4) '(:a :a :a :a :b :b :b :b))
(= (thirty-three [4 5 6] 1) '(4 5 6))
(= (thirty-three [[1 2] [3 4]] 2) '([1 2] [1 2] [3 4] [3 4]))
(= (thirty-three [44 33] 2) [44 44 33 33])

; 34
(def thirty-four
  (fn [s e] (take (- e s) (iterate inc s))))

(= (thirty-four 1 4) '(1 2 3))
(= (thirty-four -2 2) '(-2 -1 0 1))
(= (thirty-four 5 8) '(5 6 7))

; 35
(def thirty-five 7)

(= thirty-five (let [x 5] (+ 2 x)))
(= thirty-five (let [x 3, y 10] (- y x)))
(= thirty-five (let [x 21] (let [y 3] (/ x y))))

; 36
(= 10 (let [z 1 y 3 x 7] (+ x y)))
(= 4 (let [z 1 y 3 x 7] (+ y z)))
(= 1 (let [z 1 y 3 x 7] z))

; 37
(def thirty-seven "ABC")

(= thirty-seven (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

; 38
(def thirty-eight
  (fn [& l] (reduce #(if (< %1 %2) %2 %1) l)))

(= (thirty-eight 1 8 3 4) 8)
(= (thirty-eight 30 20) 30)
(= (thirty-eight 45 67 11) 67)

; 39
(= (mapcat list [1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c))
(= (mapcat list [1 2] [3 4 5 6]) '(1 3 2 4))
(= (mapcat list [1 2 3 4] [5]) [1 5])
(= (mapcat list [30 20] [25 15]) [30 25 20 15])

; 40
(def fourty
  #(rest (interleave (repeat %1) %2)))

(= (fourty 0 [1 2 3]) [1 0 2 0 3])
(= (apply str (fourty ", " ["one" "two" "three"])) "one, two, three")
(= (fourty :z [:a :b :c :d]) [:a :z :b :z :c :z :d])

; 41
(def fourty-one
  (fn
    [items n]
    (let
      [idxlist (map list items (map inc (range)))]
      (map first (filter (fn [item] (not= 0 (mod (second item) n))) idxlist)))))

(= (fourty-one [1 2 3 4 5 6 7 8] 3) [1 2 4 5 7 8])
(= (fourty-one [:a :b :c :d :e :f] 2) [:a :c :e])
(= (fourty-one [1 2 3 4 5 6] 4) [1 2 3 5 6])

; 42
(def fourty-two
  (fn [n] (apply * (range 1 (inc n)))))

(= (fourty-two 1) 1)
(= (fourty-two 3) 6)
(= (fourty-two 5) 120)
(= (fourty-two 8) 40320)

; 43
(def fourty-three
  (fn [ls n]
    (let [lss (repeat n ls)
          pos (range n)
          lss2 (map list lss pos (repeat n))
          disp (fn [xs p n] (keep-indexed (fn [i v] (if (= p (mod i n)) v nil)) xs))]
      (map #(apply disp %) lss2))))

(= (fourty-three [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6)))
(= (fourty-three (range 9) 3) '((0 3 6) (1 4 7) (2 5 8)))
(= (fourty-three (range 10) 5) '((0 5) (1 6) (2 7) (3 8) (4 9)))

; 44
(def fourty-four
  (fn
    [n ls]
    (let [len (count ls)]
      (take len (drop (mod n len) (cycle ls))))))

(= (fourty-four 2 [1 2 3 4 5]) '(3 4 5 1 2))
(= (fourty-four -2 [1 2 3 4 5]) '(4 5 1 2 3))
(= (fourty-four 6 [1 2 3 4 5]) '(2 3 4 5 1))
(= (fourty-four 1 '(:a :b :c)) '(:b :c :a))
(= (fourty-four -4 '(:a :b :c)) '(:c :a :b))

; 45
(def fourty-five
  '(1 4 7 10 13))
(= fourty-five (take 5 (iterate #(+ 3 %) 1)))

; 46
(def fourty-six
  (fn [f] #(f %2 %1)))

(= 3 ((fourty-six nth) 2 [1 2 3 4 5]))
(= true ((fourty-six >) 7 8))
(= 4 ((fourty-six quot) 2 8))
(= [1 2 3] ((fourty-six take) [1 2 3 4 5] 3))

; 47
(def fourty-seven 4)

(contains? #{4 5 6} fourty-seven)
(contains? [1 1 1 1 1] fourty-seven)
(contains? {4 :a 2 :b} fourty-seven)
(not (contains? [1 2 4] fourty-seven))

; 48
(def fourty-eight 6)

(= fourty-eight (some #{2 7 6} [5 6 7 8]))
(= fourty-eight (some #(when (even? %) %) [5 6 7 8]))

; 49
(def fourty-nine
  (juxt take drop))

(= (fourty-nine 3 [1 2 3 4 5 6]) [[1 2 3] [4 5 6]])
(= (fourty-nine 1 [:a :b :c :d]) [[:a] [:b :c :d]])
(= (fourty-nine 2 [[1 2] [3 4] [5 6]]) [[[1 2] [3 4]] [[5 6]]])

; 50
(def fifty
  #(vals (group-by type %)))

(= (set (fifty [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
(= (set (fifty [:a "foo" "bar" :b])) #{[:a :b] ["foo" "bar"]})
(= (set (fifty [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]})

; 51
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))

; 52
(= [2 4] (let [[a b c d e] [0 1 2 3 4]] [c e]))

; 53
(def fifty-three
  (fn [xs]
    (let [f1 (fn f1 [xs]
               (if (= xs '())
                 '()
                 (cons (list (first xs)) (map (partial cons (first xs)) (f1 (rest xs))))))
          f2 (fn f2 [xs]
               (if (= xs '())
                 '()
                 (concat (f1 xs) (f2 (rest xs)))))
          f3 (fn f3 [xs]
               (filter #(and (apply < %1) (< 1 (count %1))) (f2 xs)))
          f4 (fn f4 [xs]
               (first (sort #(compare (count %2) (count %1)) (f3 xs))))
          ans (f4 xs)]
      (if (nil? ans) '() ans))))

(= (fifty-three [1 0 1 2 3 0 4 5]) [0 1 2 3])
(= (fifty-three [5 6 1 3 2 7]) [5 6])
(= (fifty-three [2 3 3 4 5]) [3 4 5])
(= (fifty-three [7 6 5 4]) [])
(= (fifty-three [7 6 5 4]) [])

; 54
(def fifty-four
  (fn foo [n xs]
    (if (< (count xs) n)
      '()
      (cons (take n xs) (foo n (drop n xs))))))

(= (fifty-four 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8)))
(= (fifty-four 2 (range 8)) '((0 1) (2 3) (4 5) (6 7)))
(= (fifty-four 3 (range 8)) '((0 1 2) (3 4 5)))

; 55
(def fifty-five
  (fn foo [xss]
    (if (empty? xss)
      {}
      (let [[x xs] ((juxt first rest) xss)]
        (conj (foo (filter #(not= x %) xs)) [x (count (filter #(= x %) xss))])))))
