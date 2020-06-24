package com.allinpay.controller.syb;


import com.alibaba.fastjson.JSON;
import com.allinpay.core.constant.syb.SybConstant;
import com.allinpay.core.util.syb.HttpConnectionUtil;
import com.allinpay.core.util.syb.MD5Util;

import java.util.*;

public class MerchantApiController {
    public static void main(String[] args) throws Exception {
        testMchIn();
//        testQuery();
        testQueryStatus();
        //testedit();
        //testConfig();
        //testMchEdit();
        //wxsubdevconfig();
        //gettoken();
        //cusactivity();
        //getappkey();
        //subbranchqry();
        //addsubbranch();
        //editsubbranch();
        //termqry();
        // addterm();

    }

    public static void getappkey() throws Exception {
        // TODO Auto-generated method stub
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", "00143957");
        params.put("orgid", "55158104816UVSN");
        params.put("version", "11");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("cusid", "409581053114298");//
        params.put("brand", "zyq");//
        params.put("prodmodel", "zyq");//
        params.put("signtype", "rsa");//
        params.put("termversion", "S-XTF-V2.0");//
        params.put("termsn", "28-C6-3F-8D-9E-FF");//
        params.put("termtype", "01");//
        Map<String, String> map1 = dorequest("https://vsp.allinpay.com/boxpayset/getsms", params, "0C1F8418C7A27E1BFAE727B8162ED775");
        //Map<String, String> map1 = dorequest("http://localhost:8080/vspp-cusweb/boxpayset/getsms", params,"allinpay123");

        print(map1);


        //params.put("smscode","111111");//
        //Map<String, String> map = dorequest("https://172.16.1.11:8443/vsppcusweb/boxpayset/getappkey", params,"allinpay123");
        //Map<String, String> map = dorequest("http://localhost:8080/vspp-cusweb/boxpayset/getappkey", params,"allinpay123");
        //print(map);
    }


    public static void cusactivity() throws Exception {
        // TODO Auto-generated method stub
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "990605055336000");//
        params.put("corpbusname", "飞跃国际");//
        params.put("branchname", "门店名称");//
        params.put("notifyurl", "https://www.baidu.com");//
        params.put("areacode", "广东省广州市天河区");//
        params.put("address", "体育西路107号");//
        params.put("acttype", "02");//
        params.put("zippicurl", "https://test.allinpaygd.com/vsppweb/ceshi.zip");//
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/cusactivity", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }


    public static void gettoken() throws Exception {
        // TODO Auto-generated method stub
        Map<String, String> params = buildMchBasicMap();
        params.put("wxappid", "wxf9a943607faae9da");//
        //params.put("sub_appid","wx23432143214214");
        Map<String, String> map = dorequest("https://vsp.allinpay.com/innershare/gettoken", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }


    public static void wxsubdevconfig() throws Exception {
        // TODO Auto-generated method stub
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", "00137145");
        params.put("cusid", "55016804816NHX7");
        params.put("version", "11");
        params.put("randomstr", System.currentTimeMillis() + "");
        params.put("mchid", "55016104816ABDJ");//
        params.put("configtype", "2");
        params.put("configval", "wx1d4b83894905fb5b");

        //Map<String, String> map = dorequest(SybConstants.SYB_MCHAPI_URL+"/wxsubdevconfig", params,SybConstants.SYB_MCHAPI_APPKEY);
        Map<String, String> map = dorequest("https://vsp.allinpay.com/merchantapi/wxsubdevconfig", params, "9616f43fa89f47258e12688d68bbf29a");

        print(map);
    }

    public static void testMchEdit() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        //params.put("mchid",SybConstants.SYB_MCH_ID);//
        params.put("shortname", "落端云串");
        params.put("servicephone", "88050983");
        params.put("comproperty", "3");
        params.put("corpbusname", "杭州市拱墅区蓝鲸宁餐饮店");
        params.put("creditcode", "92330105MA2B0R450T");
        params.put("creditcodeexpire", "2088-12-22");
        params.put("legal", "杨伟炜");
        params.put("legalidno", "320121198612050016");
        params.put("legalidtype", "05");
        params.put("legalidexpire", "1970-01-19");
        params.put("address", "杭行路666号万达金街东区223号");
        params.put("contactperson", "杨伟炜1");
        params.put("contactphone", "13913866163");
        params.put("contractdate", "2099-12-31");
        params.put("clearmode", "1");
        params.put("acctid", "6227003010200537446");
        params.put("acctname", "杨伟炜2");
        params.put("accttype", "0");
        params.put("accttp", "01");
        params.put("bankcode", "0308");
        params.put("cnapsno", "308301006123");

        params.put("province", "002104");
        params.put("city", "002105");

        List<Map<String, String>> sublist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("branchaddr", "杭行路666号万达金街东区2");
        submap.put("branchname", "杭州市拱墅区蓝鲸宁餐饮店");
        submap.put("contactperson", "杨伟炜3");
        submap.put("contactphone", "13913866163");
        sublist.add(submap);
        params.put("subbranchlist", JSON.toJSONString(sublist));

        List<Map<String, String>> prodlist = new ArrayList<Map<String, String>>();
        Map<String, String> prodmap4 = new HashMap<String, String>();
        prodmap4.put("pid", "P0001");
        prodmap4.put("mtrxcode", "VSP011");//预消费
        prodmap4.put("feerate", "5");
        prodlist.add(prodmap4);

        Map<String, String> prodmap5 = new HashMap<String, String>();
        prodmap5.put("pid", "P0001");
        prodmap5.put("mtrxcode", "VSP001");//消费
        prodmap5.put("feerate", "5");
        prodmap5.put("creditrate", "7");
        prodlist.add(prodmap5);

        Map<String, String> prodmap6 = new HashMap<String, String>();
        prodmap6.put("pid", "P0001");
        prodmap6.put("mtrxcode", "VSP501");//微信
        prodmap6.put("feerate", "5");
        prodlist.add(prodmap6);

//		Map<String, String> prodmap7 = new HashMap<String,String>();
//		prodmap7.put("pid", "P0001");
//		prodmap7.put("mtrxcode", "VSP004");//预授权
//		prodmap7.put("feerate", "5");
//		prodmap7.put("creditrate", "7");
//		prodlist.add(prodmap7);


//		Map<String, String> prodmap8 = new HashMap<String,String>();
//		prodmap8.put("pid", "P0001");
//		prodmap8.put("mtrxcode", "VSP511");//支付宝
//		prodmap8.put("feerate", "5");
//		prodlist.add(prodmap8);

        Map<String, String> prodmap9 = new HashMap<String, String>();
        prodmap9.put("pid", "P0001");
        prodmap9.put("mtrxcode", "VSP505");//Qq钱包支付
        prodmap9.put("feerate", "5");
        prodlist.add(prodmap9);

//		Map<String, String> prodmap10 = new HashMap<String,String>();
//		prodmap10.put("pid", "P0001");
//		prodmap10.put("mtrxcode", "VSP551");//银联扫码支付
//		prodmap10.put("feerate", "5");
//		prodmap10.put("creditrate", "7");
//		prodlist.add(prodmap10);

        params.put("prodlist", JSON.toJSONString(prodlist));
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/edit", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void testConfig() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "990581007636009");
        params.put("configtype", "0");
        params.put("configval", "wx2355e6436542745");
        params.put("wxpubnum", "wx12131");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/wxsubdevconfig", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void testMchIn() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("merchantid", "test001");//
        params.put("merchantname", "张记日用百货22319");
        params.put("shortname", "曼联周边");
        params.put("province", "001959");
        params.put("city", "001960");
        params.put("address", "广州天河");
        params.put("servicephone", "95516");
        params.put("mccid", "5399");
        params.put("comproperty", "1");
        params.put("corpbusname", "大时代科技有限公司");
        params.put("creditcode", "66666");
        params.put("creditcodeexpire", "2019-09-14");
        params.put("legal", "谭光");
        params.put("legalidexpire", "2019-09-14");
        params.put("legalidno", "440982198709235388");
        params.put("legalidtype", "01");
        params.put("contactperson", "文西");
        params.put("contactphone", "18676297876");
        params.put("addfacusid", "990605055336000");

        params.put("holdername", "控股股东名称");
        params.put("holderidno", "13243124321515");
        params.put("holderexpire", "2019-09-14");
        params.put("registerfund", "1");
        params.put("stafftotal", "1");
        params.put("operatelimit", "1");
        params.put("inspect", "1");
        params.put("thrcertflag", "1");
        params.put("innermanage", "1");

        params.put("organcode", "1231432141234");
        params.put("organexpire", "2019-09-14");
        params.put("pubacctinfo", "我的测试#1231232131");
        params.put("busconactperson", "文西");
        params.put("busconacttel", "18676297876");


        params.put("clearmode", "1");
        params.put("acctid", "6227003010200537446");//结算卡
        params.put("acctname", "谭光");
        params.put("accttp", "00");
        params.put("accttype", "0");
        params.put("bankcode", "0308");
        params.put("cnapsno", "308581002257");
        params.put("contractdate", "2019-01-14");
        params.put("expanduser", "技术测试(13800000000)");
        params.put("notifyurl", "http://113.108.182.3:10080/vo-apidemo/NotifyServlet");
        params.put("settidno", "0308123123213");

        params.put("corpbuspic", "http://e.hiphotos.baidu.com/image/h%3D300/sign=8d3a9ea62c7f9e2f6f351b082f31e962/500fd9f9d72a6059099ccd5a2334349b023bbae5.jpg");
        params.put("legalpic", "http://b.hiphotos.baidu.com/image/h%3D300/sign=0e422b4e90504fc2bd5fb605d5dce7f0/c8177f3e6709c93d51a3a9b1943df8dcd1005426.jpg");
        params.put("legalidpicback", "https://baike.baidu.com/pic/%E5%B1%85%E6%B0%91%E8%BA%AB%E4%BB%BD%E8%AF%81%E5%8F%B7%E7%A0%81/3400358/1/342ac65c103853436d22772c9413b07ecb8088ca?fr=lemma#aid=1&pic=6a63f6246b600c335d2d8bb5154c510fd9f9a173");
        params.put("legalidpicfront", "https://baike.baidu.com/pic/%E5%B1%85%E6%B0%91%E8%BA%AB%E4%BB%BD%E8%AF%81%E5%8F%B7%E7%A0%81/3400358/1/342ac65c103853436d22772c9413b07ecb8088ca?fr=lemma#aid=1&pic=f9dcd100baa1cd114b39c73eb512c8fcc3ce2d24");
        params.put("storepic", "http://h.hiphotos.baidu.com/image/h%3D300/sign=a76b226e943df8dcb93d8991fd1072bf/aec379310a55b3193c60aeec48a98226cefc1789.jpg");
        params.put("storeinnerpic", "http://h.hiphotos.baidu.com/image/h%3D300/sign=a76b226e943df8dcb93d8991fd1072bf/aec379310a55b3193c60aeec48a98226cefc1789.jpg");

        List<Map<String, String>> sublist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("branchname", "体育西门店");
        submap.put("branchaddr", "天河体育西");
        submap.put("contactperson", "文西");
        submap.put("contactphone", "18676297853");
        Map<String, String> submap2 = new HashMap<String, String>();
        submap2.put("branchname", "体育西门店2");
        submap2.put("branchaddr", "天河体育西2");
        submap2.put("contactperson", "文西2");
        submap2.put("contactphone", "18676297853");
        submap2.put("printnum", "3");
        submap2.put("termnum", "3");
        submap2.put("rejecttrxcodes", "VSP003");
        sublist.add(submap);
        sublist.add(submap2);
        params.put("subbranchlist", JSON.toJSONString(sublist));

        List<Map<String, String>> prodlist = new ArrayList<Map<String, String>>();
        Map<String, String> prodmap = new HashMap<String, String>();
        prodmap.put("pid", "P0001");
        prodmap.put("mtrxcode", "VSP501");//收银宝微信
        prodmap.put("feerate", "3.6");
        prodlist.add(prodmap);

        Map<String, String> prodmap2 = new HashMap<String, String>();
        prodmap2.put("pid", "P0001");
        prodmap2.put("mtrxcode", "VSP001");//收银宝消费
        prodmap2.put("feerate", "3.6");
        prodmap2.put("toplimit", "16.25");
        //	prodmap2.put("lowlimit", "A10");
        prodmap2.put("creditrate", "5.8");

        prodlist.add(prodmap2);

        Map<String, String> prodmap3 = new HashMap<String, String>();
        prodmap3.put("pid", "P0001");
        prodmap3.put("mtrxcode", "VSP511");//收银宝支付宝
        prodmap3.put("feerate", "3.6");
        prodlist.add(prodmap3);

        params.put("prodlist", JSON.toJSONString(prodlist));
        params.put("legalphone", "15197704032");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/add", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void testedit() throws Exception {
        Map<String, String> params = buildMchBasicMap();


        //params.put("merchantid","VC15160106259294");//
        params.put("mchid", "550581053990107");
        params.put("shortname", "曼联周边12313123");
        params.put("province", "001959");
        params.put("city", "001960");
        params.put("address", "广州天河");
        params.put("servicephone", "95516");
        params.put("mccid", "5399");
        params.put("comproperty", "1");
        params.put("corpbusname", "大时代科技有限公司");
        params.put("creditcode", "66666");
        params.put("creditcodeexpire", "2019-09-14");
        params.put("legal", "文西");
        params.put("legalidexpire", "2019-09-14");
        params.put("legalidno", "440982198709235388");
        params.put("legalidtype", "01");
        params.put("contactperson", "文西");
        params.put("contactphone", "18676297876");

        params.put("clearmode", "1");
        params.put("acctid", "6225882014004368");//结算卡
        params.put("acctname", "文西");
        params.put("accttp", "00");
        params.put("accttype", "0");
        params.put("bankcode", "0308");
        params.put("cnapsno", "308581002257");
        params.put("contractdate", "2019-01-14");

        List<Map<String, String>> sublist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("branchname", "体育西门店333");
        submap.put("branchaddr", "天河体育西");
        submap.put("contactperson", "文西");
        submap.put("contactphone", "18676297853");
        Map<String, String> submap2 = new HashMap<String, String>();
        submap2.put("branchname", "个体户冯杰云7028");
        submap2.put("branchaddr", "天河体育西2");
        submap2.put("contactperson", "文西2");
        submap2.put("contactphone", "18676297853");
        sublist.add(submap);
        sublist.add(submap2);
        params.put("subbranchlist", JSON.toJSONString(sublist, true));

        List<Map<String, String>> prodlist = new ArrayList<Map<String, String>>();
        Map<String, String> prodmap = new HashMap<String, String>();
        prodmap.put("pid", "P0003");
        prodmap.put("mtrxcode", "VSP501");//网上收银微信
        prodmap.put("feerate", "3.7");
        prodlist.add(prodmap);
        Map<String, String> prodmap1 = new HashMap<String, String>();
        prodmap1.put("pid", "P0003");
        prodmap1.put("mtrxcode", "VSP511");//网上收银支付宝
        prodmap1.put("feerate", "3.6");
        prodlist.add(prodmap1);
        Map<String, String> prodmap2 = new HashMap<String, String>();
        prodmap2.put("pid", "P0003");
        prodmap2.put("mtrxcode", "VSP505");//网上收银qq钱包
        prodmap2.put("feerate", "3.8");
        params.put("notifyurl", "http://113.108.182.3:10080/vo-apidemo/NotifyServlet");
        prodlist.add(prodmap2);

        params.put("prodlist", JSON.toJSONString(prodlist, true));
        System.out.println(JSON.toJSONString(prodlist, true));


        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/edit", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void testQueryStatus() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("merchantid", "test001");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/querystatus", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void testQuery() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55182104812RXTE");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/query", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void subbranchqry() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55158105399YQF2");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/subbranchqry", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void addsubbranch() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55158105399YQF2");

        List<Map<String, String>> sublist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("branchname", "体育东门店");
        submap.put("branchaddr", "天河体育东");
        submap.put("contactperson", "大黑");
        submap.put("contactphone", "18676297853");
        Map<String, String> submap2 = new HashMap<String, String>();
        submap2.put("branchname", "体育东门店2");
        submap2.put("branchaddr", "天河体育东2");
        submap2.put("contactperson", "大黑2");
        submap2.put("contactphone", "18676297853");
        submap2.put("printnum", "3");
        submap2.put("termnum", "3");
        submap2.put("rejecttrxcodes", "VSP004");
        sublist.add(submap);
        sublist.add(submap2);
        params.put("subbranchlist", JSON.toJSONString(sublist));

        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/addsubbranch", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void editsubbranch() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55158105399YQF2");

        List<Map<String, String>> sublist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();

        submap.put("branchno", "0001");
        submap.put("branchname", "体育东门店444");
        submap.put("branchaddr", "天河体育东333");
        submap.put("contactperson", "大黑3344433");
        submap.put("contactphone", "18676297853");
        Map<String, String> submap2 = new HashMap<String, String>();
        submap2.put("branchno", "0002");
        submap2.put("branchname", "体育东门店334432");
        submap2.put("branchaddr", "天河体育东334432");
        submap2.put("contactperson", "大黑334432");
        submap2.put("contactphone", "18676297853");
        submap2.put("printnum", "3");
        submap2.put("termnum", "3");
        submap2.put("rejecttrxcodes", "VSP004");
        sublist.add(submap);
        sublist.add(submap2);
        params.put("subbranchlist", JSON.toJSONString(sublist));

        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/editsubbranch", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }


    public static void termqry() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55158105399YQF2");
        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/termqry", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }

    public static void addterm() throws Exception {
        Map<String, String> params = buildMchBasicMap();
        params.put("mchid", "55158105399YQF2");

        List<Map<String, String>> termlist = new ArrayList<Map<String, String>>();
        Map<String, String> submap = new HashMap<String, String>();
        submap.put("branchno", "0002");
        submap.put("printnum", "3");
        submap.put("termnum", "2");
        submap.put("rejecttrxcodes", "VSP003");
        Map<String, String> submap2 = new HashMap<String, String>();
        submap2.put("branchno", "0003");
        submap2.put("printnum", "2");
        submap2.put("termnum", "3");
        ;
        submap2.put("rejecttrxcodes", "VSP003");
        termlist.add(submap);
        termlist.add(submap2);
        params.put("termlist", JSON.toJSONString(termlist));

        Map<String, String> map = dorequest(SybConstant.SYB_MCHAPI_URL + "/addterm", params, SybConstant.SYB_MCHAPI_APPKEY);
        print(map);
    }


    public static Map<String, String> buildMchBasicMap() {
        TreeMap<String, String> params = new TreeMap<String, String>();
        params.put("appid", SybConstant.SYB_MCHAPI_APPID);
        params.put("cusid", SybConstant.SYB_MCHAPI_CUSID);
        params.put("version", "11");
        params.put("randomstr", System.currentTimeMillis() + "");
        return params;
    }

    public static Map<String, String> dorequest(String url, Map<String, String> params, String key) throws Exception {
        TreeMap<String, String> treeMap = new TreeMap<>(params);

        params.put("sign", MD5Util.sign(treeMap, key));
        HttpConnectionUtil http = new HttpConnectionUtil(url);
        http.init();
        byte[] bys = http.postParams(params, true);
        String result = new String(bys, "UTF-8");
        Map<String, String> map = handleResult(result, key);
        return map;
    }

    private static Map<String, String> handleResult(String result, String appkey) throws Exception {
        Map map = JSON.parseObject(result, Map.class);
        if (map == null) {
            throw new Exception("返回数据错误");
        }
        if ("SUCCESS".equals(map.get("retcode"))) {
            TreeMap tmap = new TreeMap();
            tmap.putAll(map);
            String sign = tmap.remove("sign").toString();
            String sign1 = "";
            sign1 = MD5Util.sign(tmap, appkey);

            if (sign1.toLowerCase().equals(sign.toLowerCase())) {
                return map;
            } else {
                throw new Exception("验证签名失败");
            }

        } else {
            throw new Exception(map.get("retmsg").toString());
        }
    }


    public static void print(Map<String, String> map) {
        System.out.println("返回数据如下:");
        if (map != null) {
            for (String key : map.keySet()) {
                System.out.println(key + ";" + map.get(key));
            }
        }
    }
}
