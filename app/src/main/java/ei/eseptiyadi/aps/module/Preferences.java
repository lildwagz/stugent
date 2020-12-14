package ei.eseptiyadi.aps.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class Preferences {



    static final String Key_TahunAjaran = "kode_transaksi";
    static final String key_Kelas = "kelas";
    static final String Key_KodePenilain = "Kode_penilaian";
    static final String key_KodeGuru = "kode_guru";



    // Shared Preferences
    SharedPreferences pref;
    int PRIVATE_MODE = 0;


    // Editor for Shared preferences
    SharedPreferences.Editor editor;

    // Context
    Context context;

    // tahun ajaran
    private static SharedPreferences getSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static void setKey_TahunAjaran(Context context, String kodetransaksi) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(Key_TahunAjaran, kodetransaksi);
        editor.apply();
    }

    public static String getKey_TahunAjaran(Context context) {
        return getSharedPreferences(context).getString(Key_TahunAjaran,"");
    }

    public static void clearKeyTahunAjaran(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(Key_TahunAjaran);
        editor.apply();
    }

    //kelas
    private static SharedPreferences getSharedClass(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKey_Kelas (Context context, String kodekelas){
        SharedPreferences.Editor editor = getSharedClass(context).edit();
        editor.putString(key_Kelas, kodekelas);
        editor.apply();
    }

    public static String getKey_Kelas(Context context){
        return getSharedClass(context).getString(key_Kelas,"");
    }

    public static void clearKeyKelas(Context context) {
        SharedPreferences.Editor editor = getSharedClass(context).edit();
        editor.remove(key_Kelas);
        editor.apply();
    }

    //KodePenilaian
    private static SharedPreferences getSharedKodepenilain(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKey_KodePenilain (Context context, String kodepenilian){
        SharedPreferences.Editor editor = getSharedKodepenilain(context).edit();
        editor.putString(Key_KodePenilain, kodepenilian);
        editor.apply();
    }

    public static String getKey_KodePenilain(Context context){
        return getSharedKodepenilain(context).getString(Key_KodePenilain,"");
    }

    public static void clearKeyKodePenilian(Context context) {
        SharedPreferences.Editor editor = getSharedKodepenilain(context).edit();
        editor.remove(Key_KodePenilain);
        editor.apply();
    }

    //kodeGuru
    private static SharedPreferences getSharedKodeGuru(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKey_KodeGuru (Context context, String kodeGuru){
        SharedPreferences.Editor editor = getSharedKodeGuru(context).edit();
        editor.putString(key_KodeGuru, kodeGuru);
        editor.apply();
    }

    public static String getKey_KodeGuru(Context context){
        return getSharedKodeGuru(context).getString(key_KodeGuru,"");
    }

    public static void clearKeyKodeGuru(Context context){
        SharedPreferences.Editor editor = getSharedKodeGuru(context).edit();
        editor.remove(key_KodeGuru);
        editor.apply();
    }
}
