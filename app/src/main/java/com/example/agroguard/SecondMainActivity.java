package com.example.agroguard;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondMainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    String[] identifiedPests = new String[QuestionAnswer.question.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondmain);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondmain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar as views
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total de perguntas: " + totalQuestion);

        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.submit_btn) {
            identifiedPests[currentQuestionIndex] = selectedAnswer;
            currentQuestionIndex++;
            if (currentQuestionIndex < totalQuestion) {
                loadNewQuestion();
            } else {
                finishQuiz();
            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion() {
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    void finishQuiz() {
        StringBuilder diagnosis = new StringBuilder("Possíveis pragas identificadas:\n");
        for (int i = 0; i < identifiedPests.length; i++) {
            int choiceIndex = getIndexFromChoice(i, identifiedPests[i]);
            diagnosis.append("- ").append(QuestionAnswer.possiblePests[i][choiceIndex]).append("\n");
        }

        new AlertDialog.Builder(this)
                .setTitle("Diagnóstico")
                .setMessage(diagnosis.toString())
                .setPositiveButton("Reiniciar", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    int getIndexFromChoice(int questionIndex, String choice) {
        for (int i = 0; i < QuestionAnswer.choices[questionIndex].length; i++) {
            if (QuestionAnswer.choices[questionIndex][i].equals(choice)) {
                return i;
            }
        }
        return -1;  // Caso não encontre, o que não deve acontecer.
    }

    void restartQuiz() {
        currentQuestionIndex = 0;
        identifiedPests = new String[QuestionAnswer.question.length];
        loadNewQuestion();
    }
}