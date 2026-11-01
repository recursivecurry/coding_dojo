package service

import (
    "encoding/json"
    "go/ast"
    "go/parser"
    "go/token"
    "net/http/httptest"
    "os"
    "reflect"
    "strings"
    "testing"
)

func response(method, path string) *httptest.ResponseRecorder {
    w := httptest.NewRecorder()
    Handler().ServeHTTP(w, httptest.NewRequest(method, path, nil))
    return w
}
func first(t *testing.T, path, want string) {
    t.Helper()
    w := response("GET", path)
    var body map[string]string
    if w.Code != 200 || json.Unmarshal(w.Body.Bytes(), &body) != nil || body["first"] != want {
        t.Fatalf("%s: status=%d body=%s", path, w.Code, w.Body.String())
    }
}
func TestEmptyIDs(t *testing.T) {
    for _, path := range []string{"/first", "/first?ids="} {
        if w := response("GET", path); w.Code != 400 { t.Fatalf("%s: %d", path, w.Code) }
    }
    first(t, "/first?ids=a,b", "a")
}
func TestPagination(t *testing.T) {
    for _, tc := range []struct{ path string; want []string }{
        {"/items", []string{"alpha", "beta", "gamma", "delta", "epsilon"}},
        {"/items?offset=1&limit=2", []string{"beta", "gamma"}},
        {"/items?offset=4&limit=2", []string{"epsilon"}},
        {"/items?offset=5&limit=2", []string{}},
        {"/items?offset=999999&limit=1", []string{}},
    } {
        w := response("GET", tc.path)
        var got []string
        if w.Code != 200 || json.Unmarshal(w.Body.Bytes(), &got) != nil || !reflect.DeepEqual(got, tc.want) {
            t.Fatalf("%s: %d %s", tc.path, w.Code, w.Body.String())
        }
    }
    for _, q := range []string{"offset=-1", "offset=x", "limit=0", "limit=6", "limit=x", "offset=1.5", "limit=99999999999999999999999999"} {
        if w := response("GET", "/items?"+q); w.Code != 400 { t.Fatalf("%s: %d", q, w.Code) }
    }
}
func TestErrorEnvelope(t *testing.T) {
    w := response("GET", "/lookup?id=missing")
    var got map[string]map[string]string
    if w.Code != 404 || !strings.HasPrefix(w.Header().Get("Content-Type"), "application/json") || json.Unmarshal(w.Body.Bytes(), &got) != nil {
        t.Fatalf("invalid error: %d %s", w.Code, w.Body.String())
    }
    if got["error"]["code"] != "not_found" || got["error"]["message"] != "not found" { t.Fatal(got) }
    good := response("GET", "/lookup?id=1")
    var body map[string]string
    if good.Code != 200 || json.Unmarshal(good.Body.Bytes(), &body) != nil || body["name"] != "alpha" { t.Fatal(good.Body.String()) }
}
func TestHealthRefactor(t *testing.T) {
    src, err := os.ReadFile("health.go")
    if err != nil { t.Fatal(err) }
    file, err := parser.ParseFile(token.NewFileSet(), "health.go", src, 0)
    if err != nil { t.Fatal(err) }
    found := false
    for _, decl := range file.Decls {
        if f, ok := decl.(*ast.FuncDecl); ok && f.Name.Name == "healthHandler" { found = true }
    }
    if !found { t.Fatal("health.go must define healthHandler") }
    routes, err := os.ReadFile("service.go")
    if err != nil || !strings.Contains(string(routes), "healthHandler") { t.Fatal("service.go must register healthHandler") }
    w := response("GET", "/health")
    var body map[string]string
    if w.Code != 200 || json.Unmarshal(w.Body.Bytes(), &body) != nil || body["status"] != "ok" { t.Fatal(w.Body.String()) }
}
func TestNormalizeIDs(t *testing.T) {
    first(t, "/first?ids=%20,%20a%20,,b", "a")
    first(t, "/first?ids=a,b", "a")
    for _, path := range []string{"/first", "/first?ids=,%20,"} {
        if w := response("GET", path); w.Code != 400 { t.Fatalf("%s: %d", path, w.Code) }
    }
}
func TestMethodGuard(t *testing.T) {
    for _, method := range []string{"POST", "PUT", "DELETE"} {
        w := response(method, "/health")
        if w.Code != 405 || w.Header().Get("Allow") != "GET" { t.Fatalf("%s: %d Allow=%s", method, w.Code, w.Header().Get("Allow")) }
    }
    w := response("GET", "/health")
    var body map[string]string
    if w.Code != 200 || json.Unmarshal(w.Body.Bytes(), &body) != nil || body["status"] != "ok" { t.Fatal(w.Body.String()) }
}
