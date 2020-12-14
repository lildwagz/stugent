package ei.eseptiyadi.aps.network;

import ei.eseptiyadi.aps.model.addData.ResponseAddDataSiswa;
import ei.eseptiyadi.aps.model.addData.ResponseAddMapel;
import ei.eseptiyadi.aps.model.addData.ResponseUpdateNilai;
import ei.eseptiyadi.aps.model.deleteData.ResponseDeleteSiswa;
import ei.eseptiyadi.aps.model.filter.ResponseRombel;
import ei.eseptiyadi.aps.model.inrombel.ResponseDataMapel;
import ei.eseptiyadi.aps.model.inrombel.ResponseDetailKelas;
import ei.eseptiyadi.aps.model.inrombel.ResponseInRombel;
import ei.eseptiyadi.aps.model.inrombel.ResponseSiswaDetail;
import ei.eseptiyadi.aps.model.master.ResponseDataPenilain;
import ei.eseptiyadi.aps.model.master.ResponseJenjang;
import ei.eseptiyadi.aps.model.master.ResponseKelas;
import ei.eseptiyadi.aps.model.master.ResponseTahunAjaran;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("module/absensi/listsiswa_inreq.php")
    Call<ResponseRombel> datasiswaFilter (
        @Field("tahunajaran") String ta,
        @Field("kode_kelas") String kelas,
        @Field("kode_jurusan") String jurusan
    );

    @FormUrlEncoded
    @POST("module/absensi/listsiswa_inreq.php")
    Call<ResponseInRombel> inRombel (
        @Field("tahunajaran") String ta,
        @Field("kode_kelas") String kelas
    );

    @GET("module/master/datatahunajaran.php")
    Call<ResponseTahunAjaran> getTahunAjaran();

    @GET("module/master/datajenjang.php")
    Call<ResponseJenjang> getJenjang();

    @GET("module/master/datakelas.php")
    Call<ResponseKelas> getKelas();

    @GET("module/master/datapenilaian.php")
    Call<ResponseDataPenilain> getDataPenilain();

    @FormUrlEncoded
    @POST("module/addquery/add_siswa.php")
    Call<ResponseAddDataSiswa> addDataSiswa(
            @Field("NIS") String nisSiswa,
            @Field("nama_siswa") String nama_siswa,
            @Field("email_siswa") String email_siswa,
            @Field("username") String username,
            @Field("password") String password,
            @Field("kode_jurusan") String kode_jurusan,
            @Field("jenis_anggota") String jenis_anggota,
            @Field("periode") String periode,
            @Field("details") String details,

            @Field("kode_penilaian") String kode_penilaian,
            @Field("kode_mapel") String kode_mapel,
            @Field("kode_guru") String kode_guru



    );

    @FormUrlEncoded
    @POST("module/deletequery/deletesiswa.php")
    Call<ResponseDeleteSiswa> delSiswa(
            @Field("NIS") String NIS
    );

    @FormUrlEncoded
    @POST("module/addquery/add_mapel.php")
    Call<ResponseAddMapel> addMapel(
            @Field("kode_mapel") String kode_mapel,
            @Field("nama_matapelajaran") String nama_matapelajaran
    );

    @FormUrlEncoded
    @POST("module/search/datarombel/detail_kelas.php")
    Call<ResponseDetailKelas> detailsiswa (
            @Field("tahunajaran") String ta,
            @Field("kode_kelas") String kelas
    );

    @FormUrlEncoded
    @POST("module/search/datarombel/detail_siswa.php")
    Call<ResponseSiswaDetail> siswaDetail(
      @Field("kode_penilaian") String kode_penilaian,
      @Field("NIS") String NIS,
      @Field("kode_mapel") String kode_mapel

    );

    @GET("module/search/datarombel/data_mapel.php")
    Call<ResponseDataMapel> datamapel();

    @FormUrlEncoded
    @POST("module/addquery/update_nilai.php")
    Call<ResponseUpdateNilai> updateNilai(
            @Field("kode_penilaian") String kodepenilaian,
            @Field("NIS") String NIS,
            @Field("kode_mapel") String kode_mapel,
            @Field("nilai_update") int nilai_update,
            @Field("nama_mapel") String nama_mapel
    );
}
