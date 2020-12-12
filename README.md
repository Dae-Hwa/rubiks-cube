# 루빅스 큐브 구현

## 3단계: 루빅스 큐브 구현하기

### 구현 목록

- [x] [큐브 맞추는 방법](https://cube3x3.com/%ED%81%90%EB%B8%8C%EB%A5%BC-%EB%A7%9E%EC%B6%94%EB%8A%94-%EB%B0%A9/#notation) 을 참고해서 루빅스 큐브를 구현한다.
  - [x] 기준면 회전
  - [x] 기준면 측면 회전
  > 회전 기준은 [참고](https://rubiks-cube-solver.com/ko/) 를 따르도록 한다.
- [x] 큐브는 W, B, G, Y, O, R의 6가지 색깔을 가지고 있다.
  - 각 면을 기준면으로 정하고 기준면과 기준면의 상하좌우를 각각 연결한다.
- [X] 입력: 각 조작법을 한 줄로 입력받는다.
  - [X] 기본적인 방식은 2단계와 같다.
  - [X] 숫자가 포함될 경우 직전의 명령어를 입력받은 수만큼 반복한다. 
- [X] 출력: 큐브의 6면을 펼친 상태로 출력한다.
  > 출력은 [참고](https://rubiks-cube-solver.com/ko/) 의 큐브를 펼친 모습과 동일하게 한다.
- [X] Q를 입력받으면 프로그램을 종료하고, 조작 받은 명령의 갯수를 출력시킨다.

### 추가 구현 기능

- [ ] 프로그램 종료 시 경과 시간 출력
- [ ] 큐브의 무작위 섞기 기능
- [ ] 모든 면을 맞추면 축하 메시지와 함께 프로그램을 자동 종료

## 2단계: 평면 큐브 구현하기

### 구현 목록

- [x] 큐브 밀기
  - 큐브를 펼쳐놓은 형태로 생각한다. 
    ```text
    [blocks[0]][blocks[1]][blocks[2]]
    [blocks[7]][mainBlock][blocks[3]]
    [blocks[6]][blocks[5]][blocks[4]]
    ```
  - 명령어에 따라 큐브를 움직인다.
    - 큐브의 꼭지점 부분을 시작점으로 삼는다.
    - 꼭지점 부터 3칸을 끊어 밀어낸다.
        > e.g. 명령어 U의 경우 0번에서 시작하여 2번 인덱스까지 선택 한 뒤 왼쪽으로 밀어낸다.
  > 명령어 내용은 입력 참조
  
- [X] 입력
  - U  가장 윗줄을 왼쪽으로 한 칸 밀기 RRW -> RWR
  - U' 가장 윗줄을 오른쪽으로 한 칸 밀기 RRW -> WRR
  - R  가장 오른쪽 줄을 위로 한 칸 밀기 WWB -> WBW
  - R' 가장 오른쪽 줄을 아래로 한 칸 밀기 WWB -> BWW
  - L  가장 왼쪽 줄을 아래로 한 칸 밀기 RGG -> GRG (L의 경우 R과 방향이 반대임을 주의한다.)
  - L' 가장 왼쪽 줄을 위로 한 칸 밀기 RGG -> GGR
  - B  가장 아랫줄을 오른쪽으로 한 칸 밀기 GBB -> BGB (B의 경우도 U와 방향이 반대임을 주의한다.)
  - B' 가장 아랫줄을 왼쪽으로 한 칸 밀기 GBB -> BBG
  - Q  Bye~를 출력하고 프로그램을 종료한다.

- [X] 초기상태 출력
- [X] 프롬프트 표시
- [X] 한 번에 여러 문자를 입력받은 경우 순서대로 처리해서 매 과정을 화면에 출력

## 1단계: 단어 밀어내기 구현하기

### 구현 목록
- [x] 입력: 사용자로부터 단어 하나, 정수 숫자 하나( -100 <= N < 100) , L 또는 R을 입력받는다. L 또는 R은 대소문자 모두 입력 가능하다.
  - 입력 내용은 WordPusher에서 판단 
- [x] 주어진 단어를 L이면 주어진 숫자 갯수만큼 왼쪽으로, R이면 오른쪽으로 밀어낸다.
  - 주어진 단어를 deque에 삽입하여 L이면 앞쪽(index 0에 가까운), R이면 뒷쪽(마지막 index)의 단어를 뽑아낸다.
  - L이나 R이면서 주어진 숫자가 음수일 경우, L과 R을 반대로 변경하고 주어진 숫자는 절대값으로 사용
- [x] 밀려나간 단어는 반대쪽으로 채워진다. 
  - L이면 deque의 마지막, R이면 deque의 첫번째에 삽입한다.