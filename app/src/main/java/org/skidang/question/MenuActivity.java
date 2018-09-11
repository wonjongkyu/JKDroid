package org.skidang.question;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // 버튼 객체 참조
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // 인텐트 객체 생성하고 name의 값을 부가 데이터로 넣기
                Intent intent = new Intent();
                intent.putExtra("name", "mike");
                // 응답 보내기
                setResult(RESULT_OK, intent);

                // 현재 엑티비티 없애기
                finish();
            }
        });
    }
}
