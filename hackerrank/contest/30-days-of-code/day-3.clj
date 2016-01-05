(use '[clojure.string :only (split triml)])

(
        let [
          N_t (read-line) 
          N (Integer/parseInt N_t) 
        ]
        (print (if (odd? N)
            "Weird"
            (cond
            (<= 2 N 5) "Not Weird"
            (<= 6 N 20) "Weird"
            :else "Not Weird")))

)
