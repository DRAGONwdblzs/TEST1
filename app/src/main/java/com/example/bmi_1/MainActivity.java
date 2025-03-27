package com.example.bmi_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText etHeight;
    private EditText etWeight;
    private RadioGroup rgGender;
    private Button btnCalculate;
    private TextView tvResult;
    private TextView tvHealthAdvice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化组件
        etHeight = findViewById(R.id.et_height);
        etWeight = findViewById(R.id.et_weight);
        rgGender = findViewById(R.id.rg_gender);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);
        tvHealthAdvice = findViewById(R.id.tv_health_advice);

        // 设置按钮点击事件
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        try {
            double height = Double.parseDouble(etHeight.getText().toString()) ;
            double weight = Double.parseDouble(etWeight.getText().toString());
            int genderId = rgGender.getCheckedRadioButtonId();
            boolean isMale = genderId == R.id.rb_male;

            double bmi = weight / (height * height);

            tvResult.setText("您的BMI值为：" + String.format("%.2f", bmi));

            String healthAdvice = getHealthAdvice(bmi, isMale);
            tvHealthAdvice.setText(healthAdvice);
        } catch (NumberFormatException e) {
            tvResult.setText("请输入有效的身高和体重！");
            tvHealthAdvice.setText("");
        }
    }

    private String getHealthAdvice(double bmi, boolean isMale) {
        if (bmi < 18.5) {
            return "您的体重过轻，建议增加营养摄入。";
        } else if (bmi < 24) {
            return "您的体重正常，继续保持健康的生活方式。";
        } else if (bmi < 28) {
            return "您的体重超重，建议适当控制饮食并增加运动。";
        } else {
            return "您的体重肥胖，建议咨询医生并进行健康干预。";
        }
    }
}