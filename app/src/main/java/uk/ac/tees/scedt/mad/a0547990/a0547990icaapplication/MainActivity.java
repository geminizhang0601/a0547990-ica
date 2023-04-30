package uk.ac.tees.scedt.mad.a0547990.a0547990icaapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Coffee";
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;

    private FloatingActionButton fab;
    //    设置coffee的数据来源
    //需要适配的数据
    private String[] coffee_name={"Espresso","Americano","Espresso Con Panna","Cold Brew Coffee","Latte","Cafe Mocha","Cappuccino","Caramel Macchiato","Flat White"};
    private String[] temperature={"Hot","Hot/Cold","Hot","Cold","Hot/Cold","Hot/Cold","Hot/Cold","Hot/Cold","Hot/Cold"};
    //图片集合
    private  int[] icons={R.drawable.espresso,R.drawable.americano, R.drawable.espressoconpanna,
            R.drawable.coldbrewcoffee, R.drawable.latte,R.drawable.cafemocha,R.drawable.cappuccino,
            R.drawable.caramelmacchiato,R.drawable.flatwhite};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化ListView控件
        ListView listView=findViewById(R.id.lv);
        //创建一个Adapter的实例
        MyBaseAdapter mAdapter=new MyBaseAdapter();
        //设置Adapter
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(MainActivity.this,Espresso.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(MainActivity.this,Americano.class);
                        startActivity(intent1);
                        break;
                }


            }
        });

    }
    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount(){       //得到item的总数
            return coffee_name.length;
        }

        @Override
        public Object getItem(int position){
            return coffee_name[position]; //返回item的数据对象
        }
        @Override
        public long getItemId(int position){
            return position;         //返回item的id
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent){//获取item中的View视图
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(MainActivity.this,R.layout.menu_item, null);
                holder=new ViewHolder();
                holder.coffee_name=convertView.findViewById(R.id.coffee_name);
                holder.temperature=convertView.findViewById(R.id.temperature);
                holder.photo=convertView.findViewById(R.id.photo);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.coffee_name.setText(coffee_name[position]);
            holder.temperature.setText(temperature[position]);
            holder.photo.setImageResource(icons[position]);
            return convertView;
        }

    }
    private static class CoffeeInfo {
        int coffee_name;
        int temperature;
        private final Class<? extends android.app.Activity> coffeeClass;

        public CoffeeInfo(int coffee_name, int temperature,
                        Class<? extends android.app.Activity> coffeeClass) {
            this.coffee_name = coffee_name;
            this.temperature = temperature;
            this.coffeeClass = coffeeClass;
        }
    }

    //    进行事件的监听 会在相应的图标被点击时被触发 弹出Toast进行显示
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Uri uri = Uri.parse("tel:40020547990");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Setting", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
    // 第一步：对listView添加监听器

    class ViewHolder{
        TextView coffee_name;
        TextView temperature;
        ImageView photo;
    }


    //    Activity生命周期的方法 设置Log 打印进行观察
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }










}