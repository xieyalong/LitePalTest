package data_bind.xyl.com.testlitepal;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.io.FileUtils;
import org.litepal.LitePal;
import org.litepal.crud.callback.FindMultiCallback;
import org.litepal.crud.callback.SaveCallback;
import org.litepal.tablemanager.Connector;
import org.litepal.util.DBUtility;

import java.io.File;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import data_bind.xyl.com.model.UserModel;
/**
 * 切忌 要加混淆
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView tv_content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_content=findViewById(R.id.tv_content);
        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
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
        findViewById(R.id.db_info).setOnClickListener(this);
        findViewById(R.id.create_table).setOnClickListener(this);
        findViewById(R.id.delete_table).setOnClickListener(this);
        findViewById(R.id.add_column).setOnClickListener(this);
        findViewById(R.id.tv_root).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_root:
                root();
                break;
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
            case  R.id.db_info:
                dbinfo();
                break;
            case  R.id.create_table:
                create_table();
                break;
            case  R.id.delete_table:
                delete_table();
                break;
            case  R.id.add_column:
                add_column();
                break;

        }
    }
    public  void find1(){
        //查询最大的id
        int id=LitePal.max(UserModel.class,"id",Integer.TYPE);
        //根据id查询1条
        UserModel user = LitePal.find(UserModel.class,id);
        String str=JSONObject.toJSONString(user);
        System.out.println(">] data="+str);
        tv_content.setText(">] data="+str);
    }
    public  void find2(){
        //查询所有
//        List<UserModel> list = LitePal.findAll(UserModel.class); 同步
        //异步
        LitePal.findAllAsync(UserModel.class).listen(new FindMultiCallback<UserModel>() {
            @Override
            public void onFinish(List<UserModel> list) {
                if (null==list||0==list.size()){
                    Toast.makeText(MainActivity.this,"没有数据",Toast.LENGTH_SHORT).show();
                }else if (list.size()>101){
                    List<UserModel> models=list.subList(list.size()-100,list.size());
                    String str=JSONObject.toJSONString(models);
                    System.out.println(">] list="+str);
                    tv_content.setText(">] list="+str);
                }else{
                    String str=JSONObject.toJSONString(list);
                    System.out.println(">] list="+str);
                    tv_content.setText(">] list="+str);
                }
            }
        });

    }

    public  void find3(){
        //多个条件 查询多个
        List<UserModel> list= LitePal.where("age=? and name=?","40","谢亚龙")
                .find(UserModel.class);
        String str=JSONObject.toJSONString(list);
        System.out.println(">] list="+str);
        tv_content.setText(">] list="+str);
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
        String str=JSONObject.toJSONString(list);
        System.out.println(">] list="+str);
        tv_content.setText(">] list="+str);
    }
    public  void find_last(){
        UserModel model=LitePal.findLast(UserModel.class);
        String str=JSONObject.toJSONString(model);
        System.out.println(">] user="+str);
        tv_content.setText(">] user="+str);
    }
    //查询 id<800000 倒序 前3条
    public  void order(){
        System.out.println(">]order");
        List<UserModel> list=
                LitePal.where("id<?  order by  id desc limit 3","800000")
                        .find(UserModel.class);
        String str=JSONObject.toJSONString(list);
        System.out.println(">] list="+str);
        tv_content.setText(">] list="+str);
    }
    //查询 id<800000 倒序 前3条
    public  void order2(){
        System.out.println(">]order");
        List<UserModel> list=LitePal.where("id<? ","800000")
                .order("id desc limit 3").find(UserModel.class);
        String str=JSONObject.toJSONString(list);
        System.out.println(">] list="+str);
        tv_content.setText(">] list="+str);
    }
    //查询 id<800000 倒序 前3条
    public  void order3(){
        System.out.println(">]order");
        List<UserModel> list=LitePal.where("id<? ","800000")
                .order("id desc").limit(2).find(UserModel.class);
        String str=JSONObject.toJSONString(list);
        System.out.println(">] list="+str);
        tv_content.setText(">] list="+str);
    }
    public  void first(){
        UserModel userModel=LitePal.findFirst(UserModel.class);
        String str=JSONObject.toJSONString(userModel);
        System.out.println(">] user="+str);
        tv_content.setText(">] user="+str);
    }

    public  void find_columns(){
        List<UserModel> list = LitePal.select("name,age").where("id=?","700010").find(UserModel.class);
        String str=JSONObject.toJSONString(list);
        System.out.println(">] user="+str);
        tv_content.setText(">] user="+str);
    }
    //异步查询
    public  void findAsync(){
        LitePal.where("id<100").findAsync(UserModel.class)
                .listen(new FindMultiCallback<UserModel>() {
                    @Override
                    public void onFinish(List<UserModel> list) {
                        String str=JSONObject.toJSONString(list);
                        System.out.println(">] user="+str);
                        tv_content.setText(">] user="+str);
                    }
                });
    }

    public  void save(){
        try{
            LitePal.getDatabase();
            UserModel model=new UserModel();
            model.setAge(40);
            model.setName("谢亚龙");
            boolean boo= model.save();
            List<UserModel> list = LitePal.findAll(UserModel.class);
            System.out.println(">]data="+ JSONObject.toJSONString(list));
            tv_content.setText(">]update="+(boo==true?"添加成功":"添加失败"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    //异步添加
    public  void saveAsync(){
        UserModel model=new UserModel();
        model.setAge(33);
        model.setName("aaaa");
        model.saveAsync().listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                tv_content.setText(">]异步添加成功");
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
//        LitePal.saveAll(userModels);异步
        //同步
        LitePal.saveAllAsync(userModels).listen(new SaveCallback() {
            @Override
            public void onFinish(boolean success) {
                tv_content.setText(">]saveAllAsync="+success);
            }
        });
    }

    public  void update(){
        //LitePal.find(类,id);
        UserModel userModel=LitePal.find(UserModel.class,1);
        userModel.setName("李四");
        userModel.setAge(4);
        boolean boo=userModel.save();
        tv_content.setText(">]update="+(boo==true?"添加成功":"添加失败"));
    }
    public  void update2(){
        UserModel userModel=new UserModel();
        userModel.setName("张三");
        userModel.setAge(40);
        //根据id更新
        int i=  userModel.update(3);
        tv_content.setText(">]update="+(i>0?"更新成功":"更新失败"));
    }

    public  void update3(){
        //更新多条
        UserModel userModel=new UserModel();
        userModel.setName("张三2");
        userModel.setAge(5);
        //updateAll(条件) conditions=代表sql语句WHERE的部分
        int i= userModel.updateAll("id=1 or id=3");
        tv_content.setText(">]update="+(i>0?"更新成功":"更新失败"));
    }
    public  void update4(){
        //更新多条
        ContentValues cv=new ContentValues();
        cv.put("name","李四");
        cv.put("age",15);
//        updateAll("表",列和值，条件)
        int i=LitePal.updateAll(UserModel.class,cv,"id>0");
        //根据id跟新一条
//        LitePal.update(UserModel.class,cv,1);
        tv_content.setText(">]update="+(i>0?"更新成功":"更新失败"));
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
        tv_content.setText(">]count="+count);
    }
    //根据条件查询数据总条数
    public  void qt2(){
        int count=LitePal.where("age>?","30").count(UserModel.class);
        System.out.println(">]count="+count);
        tv_content.setText(">]count="+count);
    }
    //查询最大的id
    public  void qt3(){
        int max=LitePal.max(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]max="+max);
        tv_content.setText(">]max="+max);
    }
    //id小于30的最大值
    public  void qt4(){
        int max=LitePal.where("id<?","30").
                max(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]max="+max);
        tv_content.setText(">]max="+max);
    }
    //age的总和
    public  void qt5(){
        int sum=LitePal. sum(UserModel.class,"age",Integer.TYPE);
        System.out.println(">]sum="+sum);
        tv_content.setText(">]sum="+sum);
    }
    //age>30 id的总和
    public  void qt6(){
        int sum=LitePal.where("age>?","30").
                sum(UserModel.class,"id",Integer.TYPE);
        System.out.println(">]sum="+sum);
        tv_content.setText(">]sum="+sum);
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
    public  void dbinfo(){
        //输出 /storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db
        System.out.println(">]LitePal.getDatabase().getPath()="+LitePal.getDatabase().getPath());
        //输出 1
        System.out.println(">]LitePal.getDatabase().getVersion()="+LitePal.getDatabase().getVersion());
        //输出 [{"first":"main","second":"/storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db"}]
        System.out.println(">]LitePal.getDatabase().getAttachedDbs()="+JSONObject.toJSONString(LitePal.getDatabase().getAttachedDbs()));
        //{"attachedDbs":[{"first":"main","second":"/storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db"}],"databaseIntegrityOk":true,"dbLockedByCurrentThread":false,"dbLockedByOtherThreads":false,"inMemoryDatabase":false,"maximumSize":4398046507008,"open":true,"pageSize":4096,"path":"/storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db","readOnly":false,"syncedTables":{},"version":1,"writeAheadLoggingEnabled":false}
//        {
//            "attachedDbs":[
//            {
//                "first":"main",
//                    "second":"/storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db"
//            }
//    ],
//            "databaseIntegrityOk":true,
//                "dbLockedByCurrentThread":false,
//                "dbLockedByOtherThreads":false,
//                "inMemoryDatabase":false,
//                "maximumSize":4398046507008,
//                "open":true,
//                "pageSize":4096,
//                "path":"/storage/emulated/0/Android/data/data_bind.xyl.com.testlitepal/files/databases/xyldb.db",
//                "readOnly":false,
//                "syncedTables":{
//
//        },
//            "version":1,
//                "writeAheadLoggingEnabled":false
//        }
        System.out.println(">]LitePal.getDatabase()="+JSONObject.toJSONString(LitePal.getDatabase()));
        //获取表名 ["android_metadata","table_schema","sqlite_sequence","usermodel"]
        System.out.println(">]tables="+JSONObject.toJSONString(DBUtility.findAllTableNames(Connector.getDatabase())));
        //输出UserModel
        System.out.println(">]getTableNameByClassName="+JSONObject.toJSONString(DBUtility.getTableNameByClassName("data_bind.xyl.com.model.UserModel")));
        tv_content.setText(JSONObject.toJSONString(DBUtility.findAllTableNames(Connector.getDatabase())));
    }

    public  void  create_table(){

        //primary key autoincrement 自增长
        String sql="create table if not exists hs_user2(id integer primary key autoincrement,u_name text,u_date text)";
        LitePal.getDatabase().execSQL(sql);

        sql = "select count(*) as c from sqlite_master where type ='table' and name ='hs_user';";
        Cursor cursor = LitePal.getDatabase().rawQuery(sql, null);
        if(cursor.moveToNext()){
            int count = cursor.getInt(0);
            if(count>0){
                tv_content.setText("存在或者创建成功");
                System.out.println(">]count="+count);
                return;
            }
        }
    }
    public  void delete_table(){
        String sql="DROP TABLE hs_user2;";
        LitePal.getDatabase().execSQL(sql);
        sql = "select count(*) as c from sqlite_master where type ='table' and name ='hs_user2';";
        Cursor cursor = LitePal.getDatabase().rawQuery(sql, null);
        if(cursor.moveToNext()){
            int count = cursor.getInt(0);
            if(count<=0){
                tv_content.setText("删除成功");
                return;
            }
        }
    }
    public  void add_column(){
        String sql="alter table hs_user add age integer";
        LitePal.getDatabase().execSQL(sql);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public  void root(){
        try {

            System.out.println(">]Build.VERSION.SDK_INT="+Build.VERSION.SDK_INT);
            if (Build.VERSION.SDK_INT >= 26 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            //创建成功后在电脑上看不到，在手机上可以看到，可以在手机上复制到其他目录下电脑就可以看到
            File fileDB=new File(Environment.getExternalStorageDirectory().getPath()+"/数据库");
            FileUtils.forceMkdir(fileDB);

            System.out.println(">]="+LitePal.getDatabase().getPath());
            System.out.println(">]1"+ Environment.getExternalStorageDirectory().getPath());

            File file=new File(LitePal.getDatabase().getPath());
            FileUtils.copyFile(file,new File(fileDB.getAbsolutePath()+"/xyl.db"));



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void get_root(){
        if (is_root()){
            Toast.makeText(this, "已经具有ROOT权限!", Toast.LENGTH_LONG).show();
        }else{
            try{
                System.out.println("正在获取root");
                Runtime.getRuntime().exec("su");
            }catch (Exception e){
                Toast.makeText(this, "获取ROOT权限时出错!", Toast.LENGTH_LONG).show();
            }
        }
    }
    public static boolean is_root() {

        boolean res = false;

        try {
            if ((!new File("/system/bin/su").exists()) &&
                    (!new File("/system/xbin/su").exists())) {
                res = false;
            } else {
                res = true;
            }
            ;
        } catch (Exception e) {

        }
        return res;
    }
}









