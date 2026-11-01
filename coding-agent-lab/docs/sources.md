# 공식 자료와 읽을 부분

기준일: 2026-11-01. 아래 자료는 원문을 복제하지 않고 실습 연결과 읽을 부분을 안내한다.
모든 자료가 2026년 발표된 것은 아니다. 발표 시기보다 개념의 역할과 현재 공식 규격을 구분해서 읽는다.

| 시점 | 자료 | 읽을 부분 / 실습 |
|---|---|---|
| 2024-12 | [Building effective agents](https://www.anthropic.com/research/building-effective-agents) | 워크플로·에이전트 구분, 도구 설계 / Day 1~3 |
| 현재 공개 코드 | [mini-swe-agent](https://github.com/SWE-agent/mini-swe-agent) | agent/environment/model 경계. 100줄은 코어 설명이지 저장소 전체 크기가 아님 / Day 2·7 |
| 현재 공식 API | [도구 정의](https://platform.claude.com/docs/en/agents-and-tools/tool-use/define-tools) | Schema와 도구 설명 / Day 1 |
| 2025-11 | [Effective harnesses for long-running agents](https://www.anthropic.com/research/effective-harnesses-for-long-running-agents) | 작업 진행과 세션 인계 / Day 9 |
| 2026-01 | [Demystifying evals](https://www.anthropic.com/research/demystifying-evals-for-ai-agents) | trial, trace, outcome, grader / Day 6 |
| 2026-03 | [Harness design for long-running apps](https://www.anthropic.com/research/harness-design-long-running-apps) | reset/compaction, 작성·평가 분리 / Day 9·12 |
| 현재 공식 규격 | [Agent Skills](https://agentskills.io/specification) | metadata → instructions → resources 점진 로딩 / Day 10 |
| 2026-07-28로 연결됨 | [MCP latest](https://modelcontextprotocol.io/specification/latest) | 호스트·클라이언트·서버, 도구, 권한 / Day 11 |
| 2025-11 및 현재 API | [Advanced tool use](https://www.anthropic.com/research/advanced-tool-use) | 도구 검색·코드 기반 호출 / Day 13 |
| 현재 통합 가이드 | [Codex as a platform](https://developers.openai.com/blog/codex-as-a-platform) | exec / SDK / app-server 통합 경계 / Day 14 |

MCP 구형 튜토리얼의 연결·세션 가정과 현재 규격이 다를 수 있다. 사용 SDK 버전과 실제 지원 프로토콜을
확인하고 둘 다 실험 기록에 남긴다. 최신 규격 전체의 수동 구현은 목표가 아니다.
모델 순위, 가격, 공개 벤치마크 수치는 이 과정에서 고정하지 않는다.
