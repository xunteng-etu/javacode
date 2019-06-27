package com.edu.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.ArrayUtils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {
    /**
     * 1.创建一个32-byte的密匙
     */
    private static final byte[] secret = "f924f68726924ec0a827deac0896a4ff".getBytes();

    //生成一个token
    public static String creatToken(Map<String, Object> payloadMap,byte[] secret) throws JOSEException {
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙
        JWSSigner jwsSigner = new MACSigner(secret);

        //签名
        jwsObject.sign(jwsSigner);

        //生成token
        return jwsObject.serialize();
    }

    //解析一个token
    public static Map<String, Object> valid(String token,byte[] secret) throws ParseException, JOSEException {

        //解析token
        JWSObject jwsObject = JWSObject.parse(token);

        //获取到载荷
        Payload payload = jwsObject.getPayload();

        //建立一个解锁密匙
        JWSVerifier jwsVerifier = new MACVerifier(secret);

        Map<String, Object> resultMap = new HashMap<String,Object>();
        //判断token
        if (jwsObject.verify(jwsVerifier)) {
            resultMap.put("Result", 0);
            //载荷的数据解析成json对象。
            JSONObject jsonObject = payload.toJSONObject();
            resultMap.put("data", jsonObject);

            //判断token是否过期
//            if (jsonObject.containsKey("exp")) {
//                Long expTime = Long.valueOf(jsonObject.get("exp").toString());
//                Long nowTime = new Date().getTime();
//                //判断是否过期
//                if (nowTime > expTime) {
//                    //已经过期
//                    resultMap.clear();
//                    resultMap.put("Result", 2);
//                }
//            }
        } else {
            resultMap.put("Result", 1);
        }
        return resultMap;
    }

    //生成token的业务逻辑
    public static String TokenTest(String clientid, String clientsecret) {
        //获取生成token
        Map<String, Object> map = new HashMap<String,Object>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("clientid", clientid);
        map.put("clientsecret", clientsecret);
//        //生成时间
//        map.put("sta", new Date().getTime());
//        //过期时间
//        map.put("exp", new Date().getTime() + 6000);
        try {
            String token = TokenUtils.creatToken(map,secret);
            System.out.println("token=" + token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;
    }

    //处理解析的业务逻辑
//    public static JSONObject ValidToken(String token) {
//        JSONObject jsonObject = null;
//        //解析token
//        try {
//            if (token != null) {
//
//                Map<String, Object> validMap = TokenUtils.valid(token,secret);
//                int i = (int) validMap.get("Result");
//                if (i == 0) {
//                    System.out.println("token解析成功");
//                    jsonObject = (JSONObject) validMap.get("data");
//                    System.out.println("clientid是" + jsonObject.get("clientid"));
//                    System.out.println("clientsecret是" + jsonObject.get("clientsecret"));
////                    System.out.println("sta是" + jsonObject.get("sta"));
////                    System.out.println("exp是" + jsonObject.get("exp"));
//                } else if (i == 2) {
//                    System.out.println("token已经过期");
//                }
//            }
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (JOSEException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    public static void main(String[] ages) {
        //System.out.println(UUID.randomUUID().toString().trim().replaceAll("-", ""));


        //获取token
//        String token = TokenTest("clientid", "clientsecret");
//        System.out.println(token);
//        //解析token
//        JSONObject jsonObject = ValidToken(token);
//        System.out.println("解析后：" + jsonObject.toString());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("clientID","123456");
        map.put("clientSecret","123456");
        System.out.println(map.toString());

    }
}
