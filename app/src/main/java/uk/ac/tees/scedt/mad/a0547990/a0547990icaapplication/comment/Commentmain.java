package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.comment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.MYsqliteopenhelper;
import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.MainActivity;
import uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication.R;

public class Commentmain extends Activity implements View.OnClickListener{


        private ImageView comment,done;
        private TextView hide_down;
        private EditText comment_content;
        private Button comment_send;

        private LinearLayout rl_enroll;
        private RelativeLayout rl_comment;

        private ListView comment_list;
        private AdapterComment adapterComment;
        private List<Comment> data;
    private MYsqliteopenhelper mYsqliteopenhelper1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.commentmain);
            mYsqliteopenhelper1=new MYsqliteopenhelper(this);
            initView();
        }

        private void initView() {

            // 初始化评论列表
            comment_list = (ListView) findViewById(R.id.comment_list);
            // 初始化数据
            data = new ArrayList<>();
            // 初始化适配器
            adapterComment = new AdapterComment(getApplicationContext(), data);
            // 为评论列表设置适配器
            comment_list.setAdapter(adapterComment);

            comment = (ImageView) findViewById(R.id.comment);
            done=(ImageView) findViewById(R.id.done);
            hide_down = (TextView) findViewById(R.id.hide_down);
            comment_content = (EditText) findViewById(R.id.comment_content);
            comment_send = (Button) findViewById(R.id.comment_send);

            rl_enroll = (LinearLayout) findViewById(R.id.rl_enroll);
            rl_comment = (RelativeLayout) findViewById(R.id.rl_comment);

            setListener();
        }

        /**
         * 设置监听
         */
        public void setListener(){
            comment.setOnClickListener(this);
            done.setOnClickListener(this);

            hide_down.setOnClickListener(this);
            comment_send.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.comment:
                    // 弹出输入法
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    // 显示评论框
                    rl_enroll.setVisibility(View.GONE);
                    rl_comment.setVisibility(View.VISIBLE);
                    break;
                case R.id.hide_down:
                    // 隐藏评论框
                    rl_enroll.setVisibility(View.VISIBLE);
                    rl_comment.setVisibility(View.GONE);
                    // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                    InputMethodManager im = (InputMethodManager)getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                    break;
                case R.id.comment_send:
                    sendComment();
                    break;
                case R.id.done:
                    Intent intent0 = new Intent(Commentmain.this, MainActivity.class);
                    startActivity(intent0);
                    break;
                default:
                    break;
            }
        }

        /**
         * 发送评论
         */
        public void sendComment(){
            if(comment_content.getText().toString().equals("")){
                Toast.makeText(getApplicationContext(), "Cannot be empty！", Toast.LENGTH_SHORT).show();
            }else{
                // 生成评论数据
                Comment comment = new Comment();
                comment.setName("Anonymous"+(data.size()+1)+"：");
                comment.setContent(comment_content.getText().toString());
                adapterComment.addComment(comment);
                // 发送完，清空输入框
                comment_content.setText("");

                Toast.makeText(getApplicationContext(), "The comment was successful！", Toast.LENGTH_SHORT).show();
            }
        }
    }

