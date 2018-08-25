package com.player.sunplayer.e;

import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/* compiled from: FileUtils */
public class f {
    public static final String[] a = new String[]{"264", "3g2", "3gp", "3gp2", "3gpp", "3gpp2", "3mm", "3p2", "60d", "aep", "ajp", "amv", "amx", "arf", "asf", "asx", "avb", "avd", "avi", "avs", "avs", "axm", "bdm", "bdmv", "bik", "bix", "bmk", "box", "bs4", "bsf", "byu", "camre", "clpi", "cpi", "cvc", "d2v", "d3v", "dav", "dce", "dck", "ddat", "dif", "dir", "divx", "dlx", "dmb", "dmsm", "dmss", "dnc", "dpg", "dream", "dsy", "dv", "dv-avi", "dv4", "dvdmedia", "dvr-ms", "dvx", "dxr", "dzm", "dzp", "dzt", "evo", "eye", "f4p", "f4v", "fbr", "fbr", "fbz", "fcp", "flc", "flh", "fli", "flv", "flx", "gl", "grasp", "gts", "gvi", "gvp", "hdmov", "hkm", "ifo", "imovi", "imovi", "iva", "ivf", "ivr", "ivs", "izz", "izzy", "jts", "lsf", "lsx", "m15", "m1pg", "m1v", "m21", "m21", "m2a", "m2p", "m2t", "m2ts", "m2v", "m4e", "m4u", "m4v", "m75", "meta", "mgv", "mj2", "mjp", "mjpg", "mkv", "mmv", "mnv", "mod", "modd", "moff", "moi", "moov", "mov", "movie", "mp21", "mp21", "mp2v", "mp4", "mp4v", "mpe", "mpeg", "mpeg4", "mpf", "mpg", "mpg2", "mpgin", "mpl", "mpls", "mpv", "mpv2", "mqv", "msdvd", "msh", "mswmm", "mts", "mtv", "mvb", "mvc", "mvd", "mve", "mvp", "mxf", "mys", "ncor", "nsv", "nvc", "ogm", "ogv", "ogx", "osp", "par", "pds", "pgi", "piv", "playlist", "pmf", "prel", "pro", "prproj", "psh", "pva", "pvr", "pxv", "qt", "qtch", "qtl", "qtm", "qtz", "rcproject", "rdb", "rec", "rm", "rmd", "rmp", "rmvb", "roq", "rp", "rts", "rts", "rum", "rv", "sbk", "sbt", "scm", "scm", "scn", "sec", "seq", "sfvidcap", "smil", "smk", "sml", "smv", "spl", "ssm", "str", "stx", "svi", "swf", "swi", "swt", "tda3mt", "tivo", "tix", "tod", "tp", "tp0", "tpd", "tpr", "trp", "ts", "tvs", "vc1", "vcr", "vcv", "vdo", "vdr", "veg", "vem", "vf", "vfw", "vfz", "vgz", "vid", "viewlet", "viv", "vivo", "vlab", "vob", "vp3", "vp6", "vp7", "vpj", "vro", "vsp", "w32", "wcp", "webm", "wm", "wmd", "wmmp", "wmv", "wmx", "wp3", "wpl", "wtv", "wvx", "xfl", "xvid", "yuv", "zm1", "zm2", "zm3", "zmv"};
    public static final String[] b = new String[]{"bmp", "pcx", "png", "jpeg", "gif", "jpg"};
    public static final String[] c = new String[]{"WAV", "MP3", "WMA", "OGG", "APE", "ACC"};
    private static final HashSet<String> d = new HashSet(Arrays.asList(a));
    private static final HashSet<String> e = new HashSet(Arrays.asList(b));
    private static final HashSet<String> f = new HashSet(Arrays.asList(c));
    private static String g = Environment.getExternalStorageDirectory().getPath();
    private static String h;

    public static boolean a(File file) {
        return d.contains(b(file));
    }

    public static String b(File file) {
        if (file != null) {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(46);
            if (lastIndexOf > 0 && lastIndexOf < name.length() - 1) {
                return name.substring(lastIndexOf + 1).toLowerCase();
            }
        }
        return null;
    }

    public static String a(long j) {
        double d = (double) j;
        StringBuilder stringBuilder;
        if (d < 1024.0d) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j);
            stringBuilder2.append("B");
            return stringBuilder2.toString();
        } else if (d < 1048576.0d) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1024.0d)}));
            stringBuilder.append("KB");
            return stringBuilder.toString();
        } else if (d < 1.073741824E9d) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1048576.0d)}));
            stringBuilder3.append("MB");
            return stringBuilder3.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1.073741824E9d)}));
            stringBuilder.append("GB");
            return stringBuilder.toString();
        }
    }

    public static String a(String str) {
        if (!n.a(str)) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf > 0 && lastIndexOf < str.length() - 1) {
                return str.substring(lastIndexOf + 1).toLowerCase();
            }
        }
        return "";
    }

    public static boolean b(String str) {
        return d.contains(a(str));
    }

    public static void a(List<File> list) {
        for (int i = 0; i < list.size(); i++) {
            if (((File) list.get(i)).getName().startsWith(".")) {
                list.remove(i);
            }
        }
        Collections.sort(list, new Comparator<File>() {

            public /* synthetic */ int compare(File obj, File obj2) {
                return a(obj, obj2);
            }

            public int a(File file, File file2) {
                if (file.isDirectory() && file2.isFile()) {
                    return -1;
                }
                if (file.isFile() && file2.isDirectory()) {
                    return 1;
                }
                return file.getName().toLowerCase().compareTo(file2.getName().toLowerCase());
            }
        });
    }

    public static List<File> a(File[] fileArr) {
        List<File> arrayList = new ArrayList();
        if (fileArr != null) {
            Collections.addAll(arrayList, fileArr);
        }
        return arrayList;
    }

    public static boolean c(File file) {
        return e.contains(b(file));
    }

    public static boolean d(File file) {
        return file.getName().endsWith(".txt");
    }

    public static boolean e(File file) {
        return f.contains(b(file));
    }

    public static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        stringBuilder.append("/TorrentPlayer");
        String stringBuilder2 = stringBuilder.toString();
        File file = new File(stringBuilder2);
        if (!file.exists()) {
            file.mkdirs();
        }
        return stringBuilder2;
    }

    public static String b(long j) {
        double d = (double) j;
        StringBuilder stringBuilder;
        if (d < 1024.0d) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(j);
            stringBuilder2.append("B");
            return stringBuilder2.toString();
        } else if (d < 1048576.0d) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1024.0d)}));
            stringBuilder.append("KB");
            return stringBuilder.toString();
        } else if (d < 1.073741824E9d) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1048576.0d)}));
            stringBuilder3.append("MB");
            return stringBuilder3.toString();
        } else {
            stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%.1f", new Object[]{Double.valueOf(d / 1.073741824E9d)}));
            stringBuilder.append("GB");
            return stringBuilder.toString();
        }
    }

    public static boolean f(File file) {
        return file.getName().toLowerCase().endsWith(".torrent");
    }

    public static List<File> b(File[] fileArr) {
        List<File> arrayList = new ArrayList();
        if (fileArr == null) {
            return arrayList;
        }
        for (File file : fileArr) {
            if (file.isDirectory() || f(file)) {
                arrayList.add(file);
            }
        }
        return arrayList;
    }

    public static boolean c(String str) {
        return f.contains(a(str));
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.endsWith(".txt");
    }

    public static boolean e(String str) {
        return e.contains(a(str));
    }
}
