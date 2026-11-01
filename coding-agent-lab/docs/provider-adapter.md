# 실제 모델 연결 — Day 1~2 과제

특정 공급자에 종속되지 않는 내부 이벤트 형식을 먼저 고정한다.
`starter/core.py`의 `model(history)`는 아래 둘 중 하나를 반환한다.

```python
{"calls": [{"id": "call_1", "name": "read_file", "arguments": {"path": "service.go"}}]}
{"final": "서비스 구조를 확인했습니다."}
```

## 공식 SDK로 연결하기

1. 공급자를 하나 고른다. 첫 주에는 모델과 모델 설정을 고정한다.
2. 공급자 공식 SDK를 가상환경에 설치하고 버전을 기록한다. 키와 모델 ID는 환경변수로 받는다.
3. `list_files`, `read_file`의 JSON Schema를 공급자의 도구 정의 형식으로 변환한다.
4. 내부 history를 공급자 messages로 변환하고 공식 SDK를 호출한다.
5. 텍스트 응답과 모든 tool call을 파싱한다. 동일 응답의 여러 호출도 보존한다.
6. 모델의 원본 응답과 tool call ID를 어댑터 상태에 보존하고, 도구 결과를 대응되는 ID로 반환한다.
7. 제한된 재시도와 SDK timeout을 설정한다. usage의 입력·출력·캐시 토큰과 finish reason을 기록한다.

예: Anthropic Messages의 사용자 정의 도구는 `name`, `description`, `input_schema`를 가진다.
응답의 `tool_use` 블록은 assistant 메시지에 보존하고, 실행 결과는 다음 user 메시지의
`tool_result` 블록에서 `tool_use_id`로 연결한다. 단순히 도구 결과를 자연어로 붙이지 않는다.

공식 설명: https://platform.claude.com/docs/en/agents-and-tools/tool-use/define-tools
호출 처리: https://platform.claude.com/docs/en/agents-and-tools/tool-use/handle-tool-calls
다른 공급자 선택 시 그 공급자의 공식 문서로 같은 대응을 구현한다. 어댑터 형식은 공급자 API 자체가 아니다.

## 도구 정의 예

```json
{
  "name": "read_file",
  "description": "작업 저장소 안의 UTF-8 파일을 읽는다. 큰 파일은 절단된다.",
  "input_schema": {
    "type": "object",
    "properties": {"path": {"type": "string"}},
    "required": ["path"],
    "additionalProperties": false
  }
}
```

## 완료 검사

실제 모델이 `read_file`을 호출하고, 결과를 본 다음 파일에 근거한 답을 해야 한다.
시뮬레이터 출력은 실제 API 검증을 대신하지 않는다. 키가 없으면 실제 연결 미완료로 기록한다.
API 오류 메시지에 키·Authorization 헤더가 없는지 확인하고, 공개 로그에 원본 소스나 비밀을 올리지 않는다.
내부 history만으로 모든 공급자의 재개가 가능한 것은 아니다. 원본 응답 블록·reasoning 관련 필드 보존 요구는
공급자 문서를 확인하고 Day 9 체크포인트에 반영한다.
