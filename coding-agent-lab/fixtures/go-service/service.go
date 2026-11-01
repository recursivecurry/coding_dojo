package service

import (
    "encoding/json"
    "net/http"
    "strings"
)

var items = []string{"alpha", "beta", "gamma", "delta", "epsilon"}

func Handler() http.Handler {
    mux := http.NewServeMux()
    mux.HandleFunc("/health", func(w http.ResponseWriter, r *http.Request) {
        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(map[string]string{"status": "ok"})
    })
    mux.HandleFunc("/first", func(w http.ResponseWriter, r *http.Request) {
        var ids []string
        if raw := r.URL.Query().Get("ids"); raw != "" {
            ids = strings.Split(raw, ",")
        }
        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(map[string]string{"first": ids[0]})
    })
    mux.HandleFunc("/items", func(w http.ResponseWriter, r *http.Request) {
        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(items)
    })
    mux.HandleFunc("/lookup", func(w http.ResponseWriter, r *http.Request) {
        if r.URL.Query().Get("id") != "1" {
            http.Error(w, "not found", http.StatusNotFound)
            return
        }
        w.Header().Set("Content-Type", "application/json")
        json.NewEncoder(w).Encode(map[string]string{"name": "alpha"})
    })
    return mux
}
