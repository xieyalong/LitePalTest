package data_bind.xyl.com.testlitepal;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import data_bind.xyl.com.model.UserModel;
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_save).setOnClickListener(this);
        findViewById(R.id.tv_save_all).setOnClickListener(this);
        findViewById(R.id.tv_update1).setOnClickListener(this);
        findViewById(R.id.tv_update2).setOnClickListener(this);
        findViewById(R.id.tv_update3).setOnClickListener(this);
        findViewById(R.id.tv_update4).setOnClickListener(this);
        findViewById(R.id.tv_delete1).setOnClickListener(this);
        findViewById(R.id.tv_delete2).setOnClickListener(this);

        findViewById(R.id.tv_find1).setOnClickListener(this);
        findViewById(R.id.tv_find2).setOnClickListener(this);
        findViewById(R.id.tv_find3).setOnClickListener(this);
        findViewById(R.id.tv_find4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_find1:
                find1();
                break;
            case  R.id.tv_find2:
                find2();
                break;
            case  R.id.tv_find3:
                find3();
                break;
            case  R.id.tv_find4:
                find4();
                break;
            case  R.id.tv_save:
                save();
                break;
            case  R.id.tv_save_all:
                saveALL();
                break;
            case  R.id.tv_update1:
                update();
                break;
            case  R.id.tv_update2:
                update2();
                break;
            case  R.id.tv_update3:
                update3();
                break;
            case  R.id.tv_update4:
                update4();
                break;
            case  R.id.tv_delete1:
               delete2();
                break;
            case  R.id.tv_delete2:
                delete3();
                break;
        }
    }
    public  void find1(){
        //根据id查询1条
        UserModel user = LitePal.find(UserModel.class,9);
        System.out.println(">]data="+ JSONObject.toJSONString(user));
    }
    public  void find2(){
        //查询所有
        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }

    public  void find3(){
        //多个条件 查询多个
        List<UserModel> list= LitePal.where("age=? and name=?","40","谢亚龙")
                .find(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
    public  void find4(){
        List<Map<String,Object>> list=new ArrayList<>();
        Cursor cursor=LitePal.findBySQL("select * from usermodel where age=? and name=?","40","谢亚龙");
        int cols_count=cursor.getColumnCount();
        while (cursor.moveToNext()) {//遍历每一条
            Map<String, Object> map = new HashMap();
            for (int i = 0; i < cols_count; i++) {//遍历每一列
                //获取列名
                String key = cursor.getColumnName(i);
                //获取列值
                String value = cursor.getString(cursor.getColumnIndex(key));
                map.put(key, value);
                list.add(map);
            }
        }
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }



    public  void save(){
        UserModel model=new UserModel();
        model.setAge(40);
        model.setName("谢亚龙");
        model.save();
        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
    public  void saveALL(){
        List<UserModel> userModels=new ArrayList<>();
        UserModel model=new UserModel();
        model.setAge(40);
        model.setName("谢亚龙");
        model.save();
        UserModel model2=new UserModel();
        model2.setAge(30);
        model2.setName("张三");
        model2.save();
        userModels.add(model);
        userModels.add(model2);

        LitePal.saveAll(userModels);
    }

    public  void update(){
        //LitePal.find(类,id);
        UserModel userModel=LitePal.find(UserModel.class,1);
        userModel.setName("李四");
        userModel.setAge(4);
        userModel.save();
    }
    public  void update2(){
        UserModel userModel=new UserModel();
        userModel.setName("张三");
        userModel.setAge(40);
        //根据id更新
        userModel.update(3);
    }

    public  void update3(){
        //更新多条
        UserModel userModel=new UserModel();
        userModel.setName("张三2");
        userModel.setAge(5);
        //updateAll(条件) conditions=代表sql语句WHERE的部分
        userModel.updateAll("id=1 or id=3");
    }
    public  void update4(){
        //更新多条
        ContentValues cv=new ContentValues();
        cv.put("name","李四");
        cv.put("age",15);
//        updateAll("表",列和值，条件)
        LitePal.updateAll(UserModel.class,cv,"id>0");
        //根据id跟新一条
//        LitePal.update(UserModel.class,cv,1);
    }
    public void delete1(){
        //删除
        LitePal.delete(UserModel.class,1);
    }
    public void delete2(){
        //根据多个条件删除多个
        LitePal.deleteAll(UserModel.class,"age=? and name=?","40","谢亚龙");
    }
    public void delete3(){
        //使用model 根据id删除
        UserModel  userModel=LitePal.find(UserModel.class,6);
        userModel.delete();
    }

}









