package chung.bb;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    String id;
    ViewPager mViewPager;
    ArrayList<Answer> answers = new ArrayList<Answer>();

    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        mViewPager = (ViewPager) findViewById(R.id.pager);

//        Intent i = getIntent();
//        answers = (ArrayList<Answer>) i.getSerializableExtra("answerListExtra");

        answers = (ArrayList<Answer>)getIntent().getSerializableExtra("answerListExtra");
        System.out.println("answer size: " + answers.size());
       // System.out.println("answer 1: " + answers.get(0).toString());
        id = getIntent().getStringExtra("ans_id");
        System.out.println("id: "+ id + " and answers: "+ answers.get(Integer.valueOf(id)-1).getResp());
        int nextFrag = mViewPager.getCurrentItem() + 1;
        
        if (id.equals("1")){

            System.out.println("enter 1...");
            mViewPager.setCurrentItem(0);
        }else if(id.equals("2")){
            System.out.println("enter 2...");
            mViewPager.setCurrentItem(1);
        }else{
            System.out.println("enter 3...");
            mViewPager.setCurrentItem(2);
        }
        System.out.println("current item: before " + mViewPager.getCurrentItem());
        /** set the adapter for ViewPager */
        AnswerPagerAdapter adapter = new AnswerPagerAdapter(getSupportFragmentManager());
        adapter.getItem(mViewPager.getCurrentItem());
        System.out.println("current item: " + mViewPager.getCurrentItem());

        mViewPager.setAdapter(adapter);
    }

    public class AnswerPagerAdapter extends FragmentPagerAdapter {

        public AnswerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        @Override
//        public Fragment getItem(int id){
//            if (id.equals(1)) {
//                return new AnswerFragmentOne();
//            } else if (id.equals(2)) {
//                return new AnswerFragmentTwo();
//            } else{
//                return new AnswerFragmentThree();
//            }
//
//        }



        @Override
        public Fragment getItem(int position) {
            /** Show a Fragment based on the position of the current screen */
            if (position == 0) {
                return new AnswerFragmentOne();
            } else if (position == 1) {
                return new AnswerFragmentTwo();
            } else{
                return new AnswerFragmentThree();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
