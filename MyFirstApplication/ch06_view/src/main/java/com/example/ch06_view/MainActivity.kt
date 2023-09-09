package com.example.ch06_view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

// 코끼리 build.gradle의 binding부터 하기
// MAinActivity.kt의 onCreate 설정 첫번째 3줄부터 하기


// MyFirstApplication
// app: 버튼, 텍스트뷰 등등 간단 소개
// ch06: 버튼, 텍스트뷰 등등 간단 소개

// My07Application
// app: android: 조잡한 버튼 예제
// ch07: 전화번호 예제, Grid, Relative

// My08Application
// app: android:layout_constraintBottom_to, android:layout_align, android:layout_alignParent
// ch08: 타이머 예제, MainActivity.kt, 버튼 및 setOn 메소드
// ch09: 가로모드, 외국어 언어 종류,
// ch10: 다이얼로그, 토스트, alret ...

// My11Application
//액션바/툴바
//- 화면 위쪽 타이트 문자열이 출력되는 영역
//- res-values-themes에서 설정돼있음
//- res에 menu 디렉토리 생성 & menu_actionbar.xml 파일 생성
//- MainActivity.kt의 onCreateOptionsMenu(), onOptionsItemSelected() 만들기 & onCreate()에서 설정
//
//
//프레그먼트
//- app에서 new를 통해 프레그먼트 만들기 -> kt, xml 파일 두개 만들어짐
//- kt파일의 onCreateView() 메서드에서 주로 일 함
//
//
//뷰페이저
//- 스와이프 이벤트로 화면을 전환할 때 사용
//- activity_main.xml에서 뷰페이저 추가
//- MainActivity.kt의 onCreate()에서 설정 & MyFragmentAdapter() 메소드 생성
//
//
//드로어 레이아웃
//- 액티비티 화면에 보이지 않던 내용이 왼쪽이나 오른쪽에서 손가락의 움직임에 따라 밀려 나오는 기능
//- activity_main.xml에서 설정 (감싸기)
//- MainActivity.kt에서 설정
//
//
//네비게이션 뷰
//- 드로어와 함께 실습했음
//- 위쪽은 아이콘과 문자열, 아래쪽은 메뉴 구성
//- res-layout에 navigation_header.xml 설정하고 activity_main에서 연결
//- menu에서 menu_navigation.xml 설정하고 activity_main에서 연결
//- MainActivity.kt에서 설정
//
//
//리사이클러뷰
//- OneFragment.kt 설정
//- MyAdapter.kt 설정 (layoutManger, adapter 필수 설정)
//- fragment_one.xml 설정
//- item_recyclerview.xml 설정
//
//플로팅 액션 버튼
//- fragment_one.xml의 최상위를 coordinatorlayout으로 수정 & 버튼 넣기
//
//탭
//- activity_main.xml에서 탭 레이아웃 설정
//- MainActivity.kt에서 onCreate()에서 설정
//
//앱바
//- activity_main.xml에서 설정


// My12Application
