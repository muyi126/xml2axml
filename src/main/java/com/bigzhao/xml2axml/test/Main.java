package com.bigzhao.xml2axml.test;

import android.content.Context;
import com.bigzhao.xml2axml.Encoder;
import org.apache.commons.io.FileUtils;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Created by Roy on 15-10-6.
 */
public class Main {
//    public static void main(String args[]) throws IOException, XmlPullParserException {
//        String filePath_fby = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest33.xml";
//        String filePath_fby2 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest_fby2.xml";
//        File file2 = new File(filePath_fby2);
//        if (file2.exists()) {
//            file2.delete();
//        }
//        decode(filePath_fby, filePath_fby2);
//
//
//    }

//    public static void main(String args[]) throws IOException, XmlPullParserException {
//        //原始
////        String filePath = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug\\AndroidManifest.xml";
////        String filePath2 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug\\AndroidManifest2.xml";
////        String filePath3 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug\\AndroidManifest3.xml";
////        String filePath4 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug\\AndroidManifest4.xml";
//
//
//        String filePath = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest.xml";
//        String filePath2 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest2.xml";
//        String filePath3 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest3.xml";
//        String filePath4 = "D:\\Desktop\\BuildTest\\decod_360yun\\testManifest\\Contoralapp-debug_10_jiagu\\AndroidManifest4.xml";
////
////
//        File file2 = new File(filePath2);
//        File file3 = new File(filePath3);
//        File file4 = new File(filePath4);
//        if (file2.exists()) {
//            file2.delete();
//        }
//        if (file3.exists()) {
//            file3.delete();
//        }
//        if (file4.exists()) {
//            file4.delete();
//        }
//        decode(filePath, filePath2);
//        encode(filePath2, filePath3);
//        decode(filePath3, filePath4);
//
//    }

    public static void main(String args[]) throws IOException, XmlPullParserException {
        if (args[0].startsWith("e")) {
            encode(args[1], args[2]);
        } else if (args[0].startsWith("d")) {
            decode(args[1], args[2]);
        } else if (args[0].startsWith("t")) {
            decode(args[1], args[2]);
            encode(args[2], args[3]);
            decode(args[3], args[4]);
        }
    }

    public static void encode(String in, String out) throws IOException, XmlPullParserException {
        Encoder e = new Encoder();
        byte[] bs = e.encodeFile(new Context(), in);
        FileUtils.writeByteArrayToFile(new File(out), bs);
    }

    public static void decode(String in, String out) throws FileNotFoundException {
        AXMLPrinter.out = new PrintStream(new File(out));
        AXMLPrinter.main(new String[]{in});
        AXMLPrinter.out.close();
    }
}
