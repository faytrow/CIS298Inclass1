package edu.kvcc.cis298.cis298inclass1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    /*
    Array of questions. We send over the resource id from R.java
    as the first parameter of the constructor.
    We will use this stored ResourceId as the first
    parameter of our Question class.
     */
    private Question[] mQuestionBank = new Question[] {
             new Question(R.string.question_oceans, false)
            ,new Question(R.string.question_mideast, false)
            ,new Question(R.string.question_africa, false)
            ,new Question(R.string.question_americas, true)
            ,new Question(R.string.question_asia, true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Get a reference to the textview that displays the question
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        updateQuestion();


        //use R to reach the view and pull a reference
        //to the Button we want to use in code.
        //We will get access to the button in the view by using
        //the id property that we declared on the layout.
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * This will allow us to cycle the index back to zero if it going to be 5.
                 */
                mCurrentIndex = (mCurrentIndex +1) % mQuestionBank.length;
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        //Get the question from the array.
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        //Set the text on the question text view to the string resource.
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

                if (userPressedTrue == answerIsTrue) {
                    messageResId = R.string.correct_toast;
                } else  {
                    messageResId = R.string.incorrect_toast;
                }
                //Make the toast like before, but now we only need 1.
                Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
}
