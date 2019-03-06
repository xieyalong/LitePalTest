package data_bind.xyl.com.testlitepal;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;

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
        /**
         * 切忌 要加混淆
         */
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

        findViewById(R.id.tv_count1).setOnClickListener(this);
        findViewById(R.id.tv_count2).setOnClickListener(this);
        findViewById(R.id.tv_max1).setOnClickListener(this);
        findViewById(R.id.tv_max2).setOnClickListener(this);
        findViewById(R.id.tv_sum1).setOnClickListener(this);
        findViewById(R.id.tv_sum2).setOnClickListener(this);

        findViewById(R.id.tv_table_list).setOnClickListener(this);

        findViewById(R.id.tv_test1).setOnClickListener(this);
        findViewById(R.id.tv_test2).setOnClickListener(this);
        findViewById(R.id.tv_test3).setOnClickListener(this);
        findViewById(R.id.tv_test2_update).setOnClickListener(this);
        findViewById(R.id.tv_last).setOnClickListener(this);
        findViewById(R.id.tv_order).setOnClickListener(this);
        findViewById(R.id.tv_order2).setOnClickListener(this);
        findViewById(R.id.tv_order3).setOnClickListener(this);

        findViewById(R.id.tv_first).setOnClickListener(this);
        findViewById(R.id.tv_columns).setOnClickListener(this);
        findViewById(R.id.tv_find_async).setOnClickListener(this);
        findViewById(R.id.saveAsync).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_table_list:
                TableListActivity.actionStart(this);
                break;
            case  R.id.tv_count1:
                qt1();
                break;
            case  R.id.tv_count2:
                qt2();
                break;
            case  R.id.tv_max1:
                qt3();
                break;
            case  R.id.tv_max2:
                qt4();
                break;
            case  R.id.tv_sum1:
                qt5();
                break;
            case  R.id.tv_sum2:
                qt6();
                break;
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
            case  R.id.tv_test1:
                test1();
                break;
            case  R.id.tv_test2:
                test2();
                break;
            case  R.id.tv_test3:
                test3();
                break;
            case  R.id.tv_test2_update:
                test2_update();
                break;
            case  R.id.tv_last:
                find_last();
                break;
            case  R.id.tv_order:
                order();
                break;
            case  R.id.tv_order2:
                order2();
                break;
            case  R.id.tv_order3:
                order3();
                break;
            case  R.id.tv_first:
                first();
                break;
            case  R.id.tv_columns:
                find_columns();
                break;
            case  R.id.tv_find_async:
                findAsync();
                break;
            case  R.id.saveAsync:
                saveAsync();
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
        Cursor cursor=LitePal.findBySQL("select * from usermodel where id=? or id=?","700011","700010");
        int cols_count=cursor.getColumnCount();
        while (cursor.moveToNext()) {//遍历每一条
            Map<String, Object> map = new HashMap();
            for (int i = 0; i < cols_count; i++) {//遍历每一列
                //获取列名
                String key = cursor.getColumnName(i);
                //获取列值
                String value = cursor.getString(cursor.getColumnIndex(key));
                map.put(key, value);
            }
            list.add(map);
        }
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
    public  void find_last(){
        UserModel model=LitePal.findLast(UserModel.class);
        System.out.println(">]user="+JSONObject.toJSONString(model));
    }
    //查询 id<800000 倒序 前3条
    public  void order(){
        System.out.println(">]order");
        List<UserModel> list=
                LitePal.where("id<?  order by  id desc limit 3","800000")
                        .find(UserModel.class);
        System.out.println(">]list="+JSONObject.toJSONString(list));
    }
    //查询 id<800000 倒序 前3条
    public  void order2(){
        System.out.println(">]order");
        List<UserModel> list=LitePal.where("id<? ","800000")
                .order("id desc limit 3").find(UserModel.class);
        System.out.println(">]list="+JSONObject.toJSONString(list));
    }
    //查询 id<800000 倒序 前3条
    public  void order3(){
        System.out.println(">]order");
        List<UserModel> list=LitePal.where("id<? ","800000")
                .order("id desc").limit(2).find(UserModel.class);
        System.out.println(">]list="+JSONObject.toJSONString(list));
    }
    public  void first(){
        UserModel userModel=LitePal.findFirst(UserModel.class);
        System.out.println(">] user="+JSONObject.toJSONString(userModel));
    }

    public  void find_columns(){
        List<UserModel> list = LitePal.select("name,age").where("id=?","700010").find(UserModel.class);
        System.out.println(">] user="+JSONObject.toJSONString(list));
    }
    //异步查询
    public  void findAsync(){
        LitePal.where("id<100").findAsync(UserModel.class)
                .listen(new FindMultiCallback<UserModel>() {
            @Override
            public void onFinish(List<UserModel> list) {
                System.out.println(">] user="+JSONObject.toJSONString(list));
            }
        });
    }

    public  void save(){
        UserModel model=new UserModel();
        model.setAge(40);
        model.setName("谢亚龙");
        model.save();
        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
    //异步添加
    public  void saveAsync(){
        UserModel model=new UserModel();
        model.setAge(33);
        model.setName("aaaa");
        model.saveAsync().listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                System.out.println(">]添加="+success);
                UserModel model=LitePal.findLast(UserModel.class);
                System.out.println(">]user="+ JSONObject.toJSONString(model));
            }
        });

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
    //数据总条数
    public  void qt1(){
        int count=LitePal.count(UserModel.class);
        System.out.println(">]count="+count);
    }
    //根据条件查询数据总条数
    public  void qt2(){
        int count=LitePal.where("age>?","30").count(UserModel.class);
        System.out.println(">]count="+count);
    }
    //查询最大的id
    public  void qt3(){
        int max=LitePal.max(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]max="+max);
    }
    //id小于30的最大值
    public  void qt4(){
        int max=LitePal.where("id<?","30").
                max(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]max="+max);
    }
    //age的总和
    public  void qt5(){
        int sum=LitePal. sum(UserModel.class,"age",Integer.TYPE);
        System.out.println(">]sum="+sum);
    }
    //age>30 id的总和
    public  void qt6(){
        int sum=LitePal.where("age>?","30").
                sum(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]sum="+sum);
    }
    public  void test1(){
        System.out.println(">]插入10万条数据-开始");
        List<UserModel> list=new ArrayList<>();
        for (int i = 0; i <100000 ; i++) {
            list.add(new UserModel("张三"+i,i));
        }
        LitePal.saveAll(list);
        System.out.println(">]插入10万条数据-结束");
    }
    public  void test2(){
        System.out.println(">]更新10万条数据-开始");
        UserModel userModel=new UserModel();
        userModel.setName("张三修改后");
        userModel.updateAll("id>0");
        System.out.println(">]更新10万条数据-结束");
    }
    public  void test2_update(){
        System.out.println(">]更新10万条数据-开始");
        ContentValues cv=new ContentValues();
        cv.put("name","张三修改后2");
        LitePal.updateAll(UserModel.class,cv,"id>0");
        System.out.println(">]更新10万条数据-结束");
    }
    public  void test3(){
        System.out.println(">]查询"+LitePal.count(UserModel.class)+"条数据-开始");
        LitePal.findAllAsync(UserModel.class).listen(new FindMultiCallback<UserModel>() {
            @Override
            public void onFinish(List<UserModel> list) {
                System.out.println(">]查询"+LitePal.count(UserModel.class)+"万条数据-结束");
            }
        });

    }

}









