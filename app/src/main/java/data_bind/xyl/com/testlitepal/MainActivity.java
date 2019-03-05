package data_bind.xyl.com.testlitepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSONObject;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;


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


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_find1:
                find1();
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

        }
    }
    public  void find1(){
        List<UserModel> list = LitePal.findAll(UserModel.class);
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
        model2.setName("張三");
        model2.save();
        userModels.add(model);
        userModels.add(model2);

        LitePal.saveAll(userModels);

        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));

    }

    public  void update(){
        //LitePal.find(类,id);
        UserModel userModel=LitePal.find(UserModel.class,1);
        userModel.setName("李四");
        userModel.setAge(4);
        userModel.save();
        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
    public  void update2(){
        UserModel userModel=new UserModel();
        userModel.setName("张三");
        userModel.setAge(40);
        //根据id更新
        userModel.update(3);

        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }

    public  void update3(){
        UserModel userModel=new UserModel();
        userModel.setName("张三2");
        userModel.setAge(5);
        //更新多条
        userModel.updateAll("id=1 or id=3");

        List<UserModel> list = LitePal.findAll(UserModel.class);
        System.out.println(">]data="+ JSONObject.toJSONString(list));
    }
}
