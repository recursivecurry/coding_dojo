package service

import (
    "net/http/httptest"
    "testing"
)

func TestBaseline(t *testing.T) {
    for _, path := range []string{"/health", "/first?ids=a,b", "/items", "/lookup?id=1"} {
        w := httptest.NewRecorder()
        Handler().ServeHTTP(w, httptest.NewRequest("GET", path, nil))
        if w.Code != 200 { t.Fatalf("%s: status %d", path, w.Code) }
    }
}
