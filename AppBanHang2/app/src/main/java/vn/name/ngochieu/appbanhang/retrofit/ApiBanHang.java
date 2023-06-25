package vn.name.ngochieu.appbanhang.retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.name.ngochieu.appbanhang.model.DonHangModel;
import vn.name.ngochieu.appbanhang.model.LoaiSpModel;
import vn.name.ngochieu.appbanhang.model.MessageModel;
import vn.name.ngochieu.appbanhang.model.SanPhamMoiModel;
import vn.name.ngochieu.appbanhang.model.UserModel;

public interface ApiBanHang {
    @GET("getloaisp.php")
    Observable<LoaiSpModel> getLoaiSp();

    @GET("getspmoi.php")
    Observable<SanPhamMoiModel> getSpMoi();

    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> getSanPham(
            @Field("loai") int loai
    );
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangki(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String moblie,
            @Field("uid") String uid

    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );
    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<MessageModel> createOrder(
            @Field("email") String email ,
            @Field("sdt") String sdt ,
            @Field("tongtien") String tongtien ,
            @Field("iduser") int id ,
            @Field("diachi") String diachi ,
            @Field("soluong") int soluong ,
            @Field("chitiet") String chitiet
    );
    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("iduser") int id
    );
    @POST("updatetoken.php")
    @FormUrlEncoded
    Observable<MessageModel> updateToken(
            @Field("id") int id ,
            @Field("token") String token
    );
    @POST("updatemomo.php")
    @FormUrlEncoded
    Observable<MessageModel> updateMomo(
            @Field("id") int id ,
            @Field("token") String token
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<SanPhamMoiModel> search(
            @Field("search") String search
    );
    @POST("gettoken.php")
    @FormUrlEncoded
    Observable<UserModel> gettoken(
            @Field("status") int status
    );
}
