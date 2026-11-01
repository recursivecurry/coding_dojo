# 사전 준비와 첫 실행

## 환경

- macOS 또는 Linux, Python 3.11 이상, Git, Go 1.23 이상.
- Docker Desktop 또는 호환 Docker 엔진. 컨테이너 명령 실행 실습은 Day 5부터 필요하다.
- 도구 호출을 지원하는 모델 API 계정. Day 1 후반부터 실제 호출을 사용한다.
- Go 프로젝트는 외부 의존성이 없다. 가상환경은 선택 사항이다.

```bash
python3 -m venv .venv
source .venv/bin/activate
python3 -m unittest discover -s tests -v
python3 -m starter.cli --repo fixtures/go-service --task "구조를 조사하라"
(cd fixtures/go-service && go test ./...)
docker version
docker pull golang:1.23-bookworm
```

Go 이미지 태그는 실습 기본값이며 최신 Go 버전이라는 주장이 아니다. 재현 실험에서는 실제 image digest도 기록한다.
이미지 다운로드와 SDK 설치는 인터넷이 필요하지만 이후 채점 컨테이너의 네트워크는 끈다.

## 저장소와 작업 영역 분리

에이전트에 전체 학습 저장소를 노출하지 않는다. 특히 `evals/` 채점 코드를 읽을 수 없어야 한다.

```bash
mkdir -p workspaces
cp -R fixtures/go-service workspaces/task-01
python3 -m starter.cli --repo workspaces/task-01 --task "빈 ids 입력을 처리하라"
```

각 평가 과제와 각 반복은 새로운 디렉터리에서 시작한다. `fixtures/` 자체를 정답으로 바꾸지 않는다.
복사한 프로젝트에는 `.git`이 없으므로 변경 기록이 필요하면 그 디렉터리에서 `git init` 후 초기 커밋을 만든다.
호스트 하네스가 모델 API를 호출하고, 명령은 작업 복제본만 연결한 컨테이너에서 실행한다.

## API 키

API 키는 터미널의 환경변수 또는 커밋하지 않는 `.env`에 둔다. 이 예제는 `.env`를 자동 로딩하지 않는다.
컨테이너에 키를 전달하거나 호스트 홈·SSH 키·Docker 소켓을 마운트하지 않는다.
공급자 선택과 연결 계약은 [모델 연결](provider-adapter.md)을 참조한다.

## 설치 실패 시

Day 1 시작 전 Python·Git을 확보한다. Go/Docker 설치가 늦어져도 읽기 전용 시뮬레이터는 실행 가능하다.
Docker가 없다면 Day 5 격리 실습과 외부 채점은 미검증으로 남긴다. 호스트 셸 실행으로 자동 대체하지 않는다.
