package chung.bb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by carolynhung on 10/27/16.
 */
public class AnswerFragmentTwo  extends Fragment {

    private AnswerActivity ansActivity;
    ArrayList<Answer> answers = new ArrayList<Answer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, container,
                false);
        this.ansActivity= (AnswerActivity)getActivity();
        answers = ansActivity.getAnswers();
        TextView prosView = (TextView)rootView.findViewById(R.id.prosView);
        prosView.setText(answers.get(1).getPros());
        TextView consView = (TextView)rootView.findViewById(R.id.consView);
        consView.setText(answers.get(1).getCons());
        return rootView;
    }
}
