package vn.name.ngochieu.appbanhang.retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.name.ngochieu.appbanhang.model.NotiResponse;
import vn.name.ngochieu.appbanhang.model.NotiSendData;

public interface ApiPushNofication {
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key=AAAAK6bzu2Q:APA91bH7ywGPQT2Fspbyo99eN1IabsKes5bSdXQZKTcImCfJIGgj88kWY_3Qp4yh1dlheAWA8XXrcMLaG1LC0DTU2M74JN4ddAW7lQ78Bwhi-UuCgaWrfUwMXaK-trDRnWUJpUNSRzCs"
            }
    )
    @POST("fcm/send")
    Observable<NotiResponse> sendNofitication(@Body NotiSendData data);

}
