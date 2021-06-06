package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class BasicActivity extends AppCompatActivity {

    private boolean isEvaluated = true;
    private String symbol;
    private String expression="";
    private Double result =(double) 0;
    private boolean isDotUsed = false;

    private void updateScreen(TextView user_screen, String symbol){
        if(isEvaluated) isEvaluated = false;
        char operator;
        expression += symbol;
        if(expression.length()!=0){
            operator = expression.charAt(expression.length()-1);
            if(operator == '+' || operator == '-' || operator == '*' || operator == '/');
            else result = eval(expression);
        }
        user_screen.setText(expression);
    }

    private void clearScreen(TextView user_screen){
        user_screen.setText("");
        expression="";
        result =(double) 0;
    }

    private void printResult(TextView user_screen){
        if(!isEvaluated) isEvaluated = true;
        result = eval(expression);
        user_screen.setText(Double.toString(result));
        expression ="";
    }

    public double eval(final String str) {
        try{
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < str.length()) ? str.charAt(pos) : -1;
                }

                boolean eat(int charToEat) {
                    while (ch == ' ') nextChar();
                    if (ch == charToEat) {
                        nextChar();
                        return true;
                    }
                    return false;
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();
                    if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                // Grammar:
                // expression = term | expression `+` term | expression `-` term
                // term = factor | term `*` factor | term `/` factor
                // factor = `+` factor | `-` factor | `(` expression `)`
                //        | number | functionName factor | factor `^` factor

                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm(); // addition
                        else if (eat('-')) x -= parseTerm(); // subtraction
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('*')) x *= parseFactor(); // multiplication
                        else if (eat('/')) x /= parseFactor(); // division
                        else return x;
                    }
                }

                double parseFactor() {
                    if (eat('+')) return parseFactor(); // unary plus
                    if (eat('-')) return -parseFactor(); // unary minus

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) { // parentheses
                        x = parseExpression();
                        eat(')');
                    } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                        while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                        x = Double.parseDouble(str.substring(startPos, this.pos));
                    } else if (ch >= 'a' && ch <= 'z') { // functions
                        while (ch >= 'a' && ch <= 'z') nextChar();
                        String func = str.substring(startPos, this.pos);
                        x = parseFactor();
                        switch (func) {
                            case "sqrt":
                                x = Math.sqrt(x);
                                break;
                            case "sin":
                                x = Math.sin(Math.toRadians(x));
                                break;
                            case "cos":
                                x = Math.cos(Math.toRadians(x));
                                break;
                            case "tan":
                                x = Math.tan(Math.toRadians(x));
                                break;
                            default:
                                throw new RuntimeException("Unknown function: " + func);
                        }
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }

                    if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                    return x;
                }
            }.parse();
        } catch (Exception e) {
            Toast.makeText(BasicActivity.this, "Invalid expression!!!", Toast.LENGTH_SHORT).show();
        }
        return 0.0;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        TextView user_screen = findViewById(R.id.user_screen);

        TextView btn_1 = findViewById(R.id.btn_1);
        TextView btn_2 = findViewById(R.id.btn_2);
        TextView btn_3 = findViewById(R.id.btn_3);
        TextView btn_4 = findViewById(R.id.btn_4);
        TextView btn_5 = findViewById(R.id.btn_5);
        TextView btn_6 = findViewById(R.id.btn_6);
        TextView btn_7 = findViewById(R.id.btn_7);
        TextView btn_8 = findViewById(R.id.btn_8);
        TextView btn_9 = findViewById(R.id.btn_9);
        TextView btn_00 = findViewById(R.id.btn_00);
        TextView btn_zero = findViewById(R.id.btn_zero);
        TextView btn_dot = findViewById(R.id.btn_dot);
        TextView btn_add = findViewById(R.id.btn_add);
        TextView btn_sub = findViewById(R.id.btn_sub);
        TextView btn_mul = findViewById(R.id.btn_mul);
        TextView btn_divide = findViewById(R.id.btn_div);
        TextView btn_inverse = findViewById(R.id.btn_inverse);
        TextView btn_square = findViewById(R.id.btn_square);
        TextView btn_clear = findViewById(R.id.btn_clear);
        TextView btn_equal =findViewById(R.id.btn_equal);

        btn_1.setOnClickListener(v -> updateScreen(user_screen,"1"));

        btn_2.setOnClickListener(v -> updateScreen(user_screen,"2"));

        btn_3.setOnClickListener(v -> updateScreen(user_screen, "3"));

        btn_4.setOnClickListener(v -> updateScreen(user_screen, "4"));

        btn_5.setOnClickListener(v -> updateScreen(user_screen, "5"));

        btn_6.setOnClickListener(v -> updateScreen(user_screen, "6"));

        btn_7.setOnClickListener(v -> updateScreen(user_screen, "7"));

        btn_8.setOnClickListener(v -> updateScreen(user_screen, "8"));

        btn_9.setOnClickListener(v -> updateScreen(user_screen, "9"));

        btn_zero.setOnClickListener(v -> updateScreen(user_screen, "0"));

        btn_00.setOnClickListener(v -> updateScreen(user_screen, "00"));

        btn_equal.setOnClickListener(v -> {
            if(isEvaluated) user_screen.setText(result.toString());
            else if(expression == "" && result!=0);
            else printResult(user_screen);
        });

        btn_clear.setOnClickListener(v -> clearScreen(user_screen));

        btn_inverse.setOnClickListener(v -> {

            isDotUsed = false;
            if(expression!="") result = eval(expression);
            result = 1 / result;
            user_screen.setText(Double.toString(result));
            expression="";
        });

        btn_square.setOnClickListener(v -> {
            isDotUsed = false;
            if(expression!="") result = eval(expression);
            result *= result;
            user_screen.setText(Double.toString(result));
            expression="";
        });

        btn_add.setOnClickListener(v -> {
            isDotUsed = false;
            if(expression=="") symbol = result.toString()+"+";
            else symbol = "+";
            updateScreen(user_screen,symbol);
        });

        btn_sub.setOnClickListener(v -> {
            isDotUsed = false;
            if(expression=="") symbol = result.toString()+"-";
            else symbol = "-";
            updateScreen(user_screen,symbol);
        });

        btn_mul.setOnClickListener(v -> {
            isDotUsed = false;
            if(expression=="") symbol = result.toString()+"*";
            else symbol = "*";
            updateScreen(user_screen,symbol);
        });

        btn_divide.setOnClickListener(v -> {
            isDotUsed = false;
            if(expression=="") symbol = result.toString()+"/";
            else symbol = "/";
            updateScreen(user_screen,symbol);
        });

        btn_dot.setOnClickListener(v -> {
            if(isDotUsed) {
                Toast.makeText(BasicActivity.this, "Decimal point is used!!!", Toast.LENGTH_SHORT).show();
            }
            else {
                updateScreen(user_screen, ".");
                isDotUsed=true;
            }
        });

    }
}