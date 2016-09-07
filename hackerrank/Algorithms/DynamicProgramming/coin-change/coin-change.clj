(use '[clojure.set :only (union)])
(use '[clojure.string :only (split)])

(defn solve [kind current amount]
  (if (= amount 0)
    #{current}
    (reduce
      (fn [a v]
        (clojure.set/union a (if (<= v amount)
               (solve
                 kind
                 (assoc current (long v) (inc (get current (long v) 0)))
                 (- amount v))
               #{})))
      #{}
      kind))
)

(def solve-mem (memoize solve))

(def nm (map read-string (clojure.string/split (read-line) #"\s+")))
(def ks (map read-string (clojure.string/split (read-line) #"\s+")))
(println (count (solve-mem ks, {}, (first nm))))
