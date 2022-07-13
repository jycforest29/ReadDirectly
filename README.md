# ReadDirectly
# 프로젝트 소개
인원: 1인 개발<br>
개발 기간: 3일<br>
개요: 문장 직독직해 앱<br>
주요 기능 : 선택한 언어로 입력한 문장 직독직해 번역<br>
# 사용한 기술
서버: DRF<br>
클라이언트: 안드로이드<br>
DB: 서버-sqlite3, 클라이언트-room<br>
# 사용한 오픈소스
googletrans open api
# 프로젝트 스샷 및 설명
![spinner](https://user-images.githubusercontent.com/103106183/177677462-795e8258-929d-4775-9e7d-12359699de3f.png)
![translate](https://user-images.githubusercontent.com/103106183/177677487-3d09480f-9968-408a-89af-871a4019090d.png)
![insert](https://user-images.githubusercontent.com/103106183/177677498-1adfbcb5-b162-4511-a85e-9cc116f1b76b.png)
![likeChunk](https://user-images.githubusercontent.com/103106183/177677509-e39c0823-a116-42a9-8145-bc0bbe952780.png)
![delete](https://user-images.githubusercontent.com/103106183/177677528-113afb25-aea1-4b55-9112-fb7ce44a0ab1.png)

# 보완점
현재 회원가입 관련 토큰에서 막힘...-> 해결<br>
+파일 분석-> 추후 업데이트<br>
+문장 전체 번역과 문장의 각 단어 번역시 스레드 문제-> 해결<br>
# 배운내용
안드로이드)
-스피너
-room db 사용법(c, d)
-레트로핏에서의 토큰 관리
-스레드
-프래그먼트 사용 방법
DRF)
-회원가입/로그인/로그아웃시 토큰 관리
