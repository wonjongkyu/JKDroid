package org.skidang.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 다른 엑티비티를 띄우기 위한 요청코드 정의
    public static final int REQUEST_CODE_MENU = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_MENU){
            Toast.makeText(getApplicationContext(), "onActivityResult 메소드 호출됨. 요청 코드 : " + requestCode +
                    ", 결과 코드 : " + resultCode, Toast.LENGTH_LONG).show();

            if(resultCode == RESULT_OK){
                String name = data.getExtras().getString("name");
                Toast.makeText(getApplicationContext(), "응답으로 전달될 name : " + name,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onButton1Clicked(View v){
        // 또 다른 엑티비티를 띄우기 위한 인텐트 객체 생성
        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
        // 엑티비티 띄우기
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }
}
