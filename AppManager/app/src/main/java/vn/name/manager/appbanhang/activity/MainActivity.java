package vn.name.manager.appbanhang.activity;

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
import vn.name.manager.appbanhang.R;
import vn.name.manager.appbanhang.adapter.LoaiSpAdapter;
import vn.name.manager.appbanhang.adapter.SanPhamMoiAdapter;
import vn.name.manager.appbanhang.model.LoaiSp;
import vn.name.manager.appbanhang.model.SanPhamMoi;
import vn.name.manager.appbanhang.model.User;
import vn.name.manager.appbanhang.retrofit.ApiBanHang;
import vn.name.manager.appbanhang.retrofit.RetrofitClient;
import vn.name.manager.appbanhang.utils.Utils;

public class    MainActivity extends AppCompatActivity {
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
    ImageView imgsearch;

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
            //lấy token firebase truyền lên database phục vụ việc gửi thông báo
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

                                            }
                                    ));
                        }
                    }
                });
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
                        Intent quanli = new Intent(getApplicationContext(), QuanLiActivity.class);
                        startActivity(quanli);
                        break;
                    case 7:
                        Intent chat = new Intent(getApplicationContext(), UserActivity.class);
                        startActivity(chat);
                        break;
                    case 8:
                        //xoa key user
                        Paper.book().delete("user");
                        FirebaseAuth.getInstance().signOut();
                        Intent dangnhap = new Intent(getApplicationContext(), DangNhapActivity.class);
                        startActivity(dangnhap);
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
                               mangloaisp.add(new LoaiSp("Quản lí","data:image/png;base64,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"));
                               mangloaisp.add(new LoaiSp("Chat","data:image/png;base64,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"));
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
            imageView.setScaleType(ImageView.ScaleType.FIT_XY); // scale hình ảnh = kích thước của view
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(4000); // settime chuyển cảnh
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
                //gọi NavigationView
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        imgsearch = findViewById(R.id.imgsearch);
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