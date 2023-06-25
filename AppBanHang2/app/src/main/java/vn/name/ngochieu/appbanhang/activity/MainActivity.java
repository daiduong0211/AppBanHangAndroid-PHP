package vn.name.ngochieu.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.ngochieu.appbanhang.R;
import vn.name.ngochieu.appbanhang.adapter.LoaiSpAdapter;
import vn.name.ngochieu.appbanhang.adapter.SanPhamMoiAdapter;
import vn.name.ngochieu.appbanhang.model.LoaiSp;
import vn.name.ngochieu.appbanhang.model.SanPhamMoi;
import vn.name.ngochieu.appbanhang.model.User;
import vn.name.ngochieu.appbanhang.retrofit.ApiBanHang;
import vn.name.ngochieu.appbanhang.retrofit.RetrofitClient;
import vn.name.ngochieu.appbanhang.utils.Utils;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout    drawerLayout;
    LoaiSpAdapter loaiSpAdapter;
    List<LoaiSp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;
    List<SanPhamMoi> mangSpMoi;
    SanPhamMoiAdapter spAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch,imageMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        Paper.init(this);
        if (Paper.book().read("user")!=null){
            User user = Paper.book().read("user");
            Utils.user_current = user;
        }
        getToken();
        Anhxa();
        ActionBar();
        ActionViewFlipper();
        if(isConnected(this)){
            ActionViewFlipper();
            getLoaiSanPham();
            getSpMoi();
            getEventClick();
        }else {
            Toast.makeText(getApplicationContext(),"không có internet, vui lòng kết nối",Toast.LENGTH_LONG).show();
        }
    }
    private void getToken(){
        ////lấy token firebase truyền lên database để gửi thông báo
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        if (!TextUtils.isEmpty(s)){
                            compositeDisposable.add(apiBanHang.updateToken(Utils.user_current.getId(),s)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            messageModel -> {

                                            },
                                            throwable -> {
                                                Log.d("log", throwable.getMessage());
                                            }
                                    ));
                        }
                    }
                });
        //lấy ID_RECEIVED từ database bằng api =>> phục vụ việc chat
        compositeDisposable.add(apiBanHang.gettoken(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   userModel -> {
                       if (userModel.isSuccess()){
                           Utils.ID_RECEIVED = String.valueOf(userModel.getResult().get(0).getId());
                       }
                   },
                   throwable -> {

                   }
                ));
    }

    private void getEventClick() {
        listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent trangchu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangchu);
                        break;
                    case 1:
                        Intent dienthoai = new Intent(getApplicationContext(), DienThoaiActivity.class);
                        dienthoai.putExtra("loai",1);
                        startActivity(dienthoai);
                        break;
                    case 2:
                        Intent laptop = new Intent(getApplicationContext(), DienThoaiActivity.class);
                        laptop.putExtra("loai",2);
                        startActivity(laptop);
                        break;
                    case 5:
                        Intent donhang = new Intent(getApplicationContext(), XemDonActivity.class);
                        startActivity(donhang);
                        break;
                    case 6:
                        //xoa key user
                        Paper.book().delete("user");
                        Intent dangnhap = new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangnhap);
                        FirebaseAuth.getInstance().signOut();
                        break;
                }
            }
        });
    }

    private void getSpMoi() {
        compositeDisposable.add(apiBanHang.getSpMoi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if (sanPhamMoiModel.isSuccess()){
                                mangSpMoi = sanPhamMoiModel.getResult();
                                spAdapter = new SanPhamMoiAdapter(getApplicationContext(),mangSpMoi);
                                recyclerViewManHinhChinh.setAdapter(spAdapter);
                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(),"không kết nối được với sever"+throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void getLoaiSanPham() {
       compositeDisposable.add(apiBanHang.getLoaiSp().
               subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       loaiSpModel -> {
                           if (loaiSpModel.isSuccess()){
                               mangloaisp = loaiSpModel.getResult();
                               mangloaisp.add(new LoaiSp("Đăng xuất","data:image/png;base64,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"));
                               //khoi tao adapter
                               loaiSpAdapter = new LoaiSpAdapter(getApplicationContext(),mangloaisp);
                               listViewManHinhChinh.setAdapter(loaiSpAdapter);
                           }
                       }
               ));
    }

    private void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        mangquangcao.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        for (int i=0;i< mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });
    }

    private void Anhxa() {
        imgsearch = findViewById(R.id.imgsearch);
        imageMess = findViewById(R.id.image_mess);
        toolbar = findViewById(R.id.toobarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        //
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        //
        listViewManHinhChinh = findViewById(R.id.listviewmanhinhchinh);
        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        //khoi tao list
        mangloaisp = new ArrayList<>();
        mangSpMoi = new ArrayList<>();



        if (Utils.manggiohang == null) {
            Utils.manggiohang = new ArrayList<>();
        } else {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(giohang);
            }
        });
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);
            }
        });
        imageMess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ChatActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i = 0; i < Utils.manggiohang.size(); i++) {
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ( (wifi!=null && wifi.isConnected()) || (mobile!=null && mobile.isConnected())){
            return true;
        } else{
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}