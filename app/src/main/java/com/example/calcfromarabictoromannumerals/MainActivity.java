package com.example.calcfromarabictoromannumerals;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private TextView tvValue1, tvOperation, tvValue2, resultField;
    String operation = "";
    Double value1, value2, result;
    String val1, val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialization();
    }

    public void onNumberClick(View v) {
        Button button = (Button) v;
        if (TextUtils.isEmpty(tvOperation.getText().toString())) {
            tvValue1.append(button.getText().toString());
        } else {
            tvValue2.append(button.getText().toString());
        }
    }

    public void onOperationClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.emptyValue, Toast.LENGTH_SHORT).show();
                } else {
                    operation = "+";
                    tvOperation.setText(R.string.plus);
                }
                break;
            case R.id.btnDiv:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.emptyValue, Toast.LENGTH_SHORT).show();
                } else {
                    operation = "/";
                    tvOperation.setText(R.string.div);
                }
                break;
            case R.id.btnSub:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.emptyValue, Toast.LENGTH_SHORT).show();
                } else {
                    operation = "-";
                    tvOperation.setText(R.string.minus);
                }
                break;
            case R.id.btnMult:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.emptyValue, Toast.LENGTH_SHORT).show();
                } else {
                    operation = "*";
                    tvOperation.setText(R.string.mult);
                }
                break;
            case R.id.btnEquall:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.emptyValues, Toast.LENGTH_SHORT).show();
                } else
                    if (TextUtils.isEmpty(tvValue2.getText().toString())) {
                        Toast.makeText(MainActivity.this, R.string.emptyValues, Toast.LENGTH_SHORT).show();
                } else {
                        try {
                            value1 = Double.parseDouble(tvValue1.getText().toString());
                            value2 = Double.parseDouble(tvValue2.getText().toString());
                            operationChecker();
                            resultField.setText(value1 + " " + operation + " " + value2 + " = " + result);
                            clearViews();
                        } catch (NumberFormatException e) {
                            Toast.makeText(MainActivity.this, R.string.incorrectFormat, Toast.LENGTH_SHORT).show();
                        }
                }
          }
    }

   public void onConvertClick(View v) {
        if (tvValue1.getText().toString().equals("0") || tvValue1.getText().toString().contains(".") || TextUtils.isEmpty(tvValue1.getText().toString())) {
            Toast.makeText(MainActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
        } else {
            int i = Integer.parseInt(tvValue1.getText().toString());
            resultField.setText(tvValue1.getText().toString() + " = " + FromArabicToRomanConverter.RomanNumerals(i));
            clearViews();
        }
   }

    public void onDelClick(View v) {
        switch (v.getId()) {
            case R.id.btnClear:
                clearViews();
                break;
            case R.id.btnBackspace:
                if (TextUtils.isEmpty(tvValue1.getText().toString())) {
                    return;
                } else {
                    val1 = tvValue1.getText().toString();
                    val2 = tvValue2.getText().toString();
                    if (val2.length() >= 1) {
                        val2 = val2.substring(0, val2.length() - 1);
                        tvValue2.setText(val2);
                    } else if (val2.length() == 0) {
                        tvOperation.setText("");
                        val1 = val1.substring(0, val1.length() - 1);
                        tvValue1.setText(val1);
                    }
                }
                break;
        }
    }

    private void operationChecker() {
        if (operation.equals("+")) {
            result = value1 + value2;
        } else {
            if (operation.equals("-")) {
                result = value1 - value2;
            } else {
                if (operation.equals("*")) {
                    result = value1 * value2;
                } else {
                    if (operation.equals("/")) {
                        result = value1 / value2;
                    }
                }
            }
        }
    }

    private void clearViews() {
        tvValue1.setText("");
        tvValue2.setText("");
        tvOperation.setText("");
    }

    private void initialization() {
        tvValue1 =  findViewById(R.id.tvValue1);
        tvOperation =  findViewById(R.id.tvOperation);
        tvValue2 =  findViewById(R.id.tvValue2);
        resultField =  findViewById(R.id.resultField);

    }
}
