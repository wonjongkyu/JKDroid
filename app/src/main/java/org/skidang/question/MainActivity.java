package org.skidang.question;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 입력상자에 입력된 전화번호 확인
                String data = editText.getText().toString();

                // 전화걸기 화면을 보여줄 인텐트 객체 생성
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(data));
                // 엑티비티 띄우기
                startActivity(intent);
            }
        });
    }


    public void onButton1Clicked(View v){
        // 입력상자에 입력된 파일명 확인
        String filename = editText.getText().toString();

        if(filename.length() > 0){
            openPDF(filename.trim());
        }else {
            Toast.makeText(getApplicationContext(), "PDF 파일명을 입력하세요", Toast.LENGTH_LONG).show();
        }
    }

    // PDF 파일 경기 기능을 정의한 메소드
    public void openPDF(String filename){
        String sdcardFolder = Environment.getExternalStorageDirectory().getAbsolutePath();
        String filepath = sdcardFolder + File.separator + filename;
        File file = new File(filepath);

        if(file.exists()){
            Uri uri = Uri.fromFile(file);   // Uri 객체로 생성

            Intent intent = new Intent(Intent.ACTION_VIEW); // ACTION_VIEW 액션을 가지는 인텐트 객체 생성
            intent.setDataAndType(uri, "application/pdf"); // Uri 객체와 MIME 타입 지정

            try {
                startActivity(intent);      // 엑티비티 띄우기
            } catch (ActivityNotFoundException ex){
                Toast.makeText(this, "PDF 파일을 보기 위한 뷰어 앱이 없습니다.", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "PDF 파일이 없습니다", Toast.LENGTH_SHORT).show();
        }
    }


}
