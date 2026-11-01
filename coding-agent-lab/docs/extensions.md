# 둘째 주 구현 노트

이 문서는 완성된 확장 코드가 아니라 구현 계약과 최소 실험이다.

## 컨테이너 실행기 — Day 4~5

`evals/grade.py`의 Docker 명령 구성을 읽되 채점기와 에이전트 실행기는 분리한다.
작업용 컨테이너는 복제본만 `/work`에 연결하고 해당 경로만 쓰기 가능하게 한다.
`--network none`, `--cap-drop ALL`, `--security-opt no-new-privileges`, `--pids-limit`,
`--memory`, `--cpus`, `--read-only`, `/tmp` tmpfs, 비루트 사용자, 고유 컨테이너 이름을 사용한다.
호스트 timeout 발생 시 `docker rm -f <이름>`으로 실행 컨테이너까지 정리한다.
stdout/stderr는 파일·스트림에 제한을 두고 수집하며, 프로세스 출력 전체를 메모리에 모으지 않는다.

## Skill 로딩 — Day 10

로컬 학습 예시 파일의 형식:

```markdown
---
name: go-test-cycle
description: Go 소스 변경 후 실패를 재현하고 테스트와 vet로 확인할 때 사용한다.
---
먼저 실패를 재현하고, 최소 수정 후 go test ./...와 go vet ./...를 실행한다.
테스트를 실행하지 못했다면 그 사실을 보고한다.
```

첫 구현은 신뢰할 수 있는 로컬 파일 1개로 제한한다. 전체 YAML 파서를 직접 만들지 않는다.
메타데이터와 본문 로딩을 분리하고 본문 크기를 제한한다. 학습자가 만든 파일은 Git에 커밋한다.
이는 앱에 Skill을 설치하는 작업이 아니라 자체 하네스의 파일 로딩 실습이다.

## MCP — Day 11

공식 SDK 저장소: https://github.com/modelcontextprotocol/python-sdk
공식 SDK 예제에서 `project_info` 읽기 도구 하나를 노출하고, 지원 transport 하나만 사용한다.
클라이언트가 도구 목록을 받아 JSON Schema와 호출 결과를 내부 형식으로 변환하게 만든다.
서버별 접두어로 도구 이름을 구분하고, 허용 목록·timeout·오류 반환은 클라이언트에 둔다.
SDK 예제와 최신 사양의 버전 차이를 기록한다. OAuth·원격 배포·모든 transport 지원은 이번 범위 밖이다.

## 리뷰어 — Day 12

입력: 요구사항, 변경 diff, 관련 코드, 실행한 검사와 결과.
출력: `findings: [{path, reason, reproduction, severity}]`와 `unverified` 목록.
권한: 읽기와 검사만 허용. 작성자의 자체 평가는 기본 입력에서 뺀다.
반복: 리뷰 1회→수정 1회로 제한한다. 추가 비용을 trace에 별도 집계한다.

## 고급 도구 사용 — Day 13

A. 도구 검색: 모의 도구 30개의 name/description만 인덱싱한다. `search_tools(query)`가 후보 3개의
Schema를 제공한다. 미등록 도구 실행은 거절한다. lexical 검색만으로 첫 실험이 가능하다.

B. 코드 기반 호출: 제한된 컨테이너에서 여러 파일의 통계를 집계하고 합계만 반환한다.
개별 결과를 모델에 전부 전달하는 기준 버전과 비교한다. 호스트에서 임의 Python을 eval/exec하지 않는다.
이 자체 구현은 공식 Programmatic Tool Calling API와 동일한 제품 기능이 아니다. 개념 실험으로 기록한다.
공식 기능까지 연결하려면 지원 모델·SDK·기능 상태를 현재 문서에서 확인한다.
