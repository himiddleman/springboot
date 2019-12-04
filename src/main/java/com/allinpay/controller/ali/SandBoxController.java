package com.allinpay.controller.ali;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayCreditCreditriskDataPutRequest;
import com.alipay.api.response.AlipayCreditCreditriskDataPutResponse;

public class SandBoxController {
    private static String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCK84r+Pz5YLxLDedGtUGA0UUkCQXClWW7RPXXYfdDpbvyxR7k7FzM1ypeUtGaMx3TM6FSDfST/iaWUFmU7RnWi9zO+wRCu048qjM3m56IIIHlAt4/xmBz7q+I/6v+DpxaKirrjzypeiPrkaC98gwFcq4+SZ231Foc8UOX1G26KJLXWcVOpRZtvbNpWe/WU2bn0cDbp4KwGTTFM1esEZJzmPrUe9xVB6i+0/DFVsP53KqQiIzQN3O7+7XomVtUV1HI1usx+aJTxx0/s8P3Wpyifu7OJvGKoQrgG6WF+pnGQfc7eegjQ6M5i0JKgpMDZajpNnVHZ1I/dBgRB4DGcQZsHAgMBAAECggEBAIrUmuM8vUXOgqjcZisMS5L6REAEslAC8z4MclpEGx+gzMk0KFZp77inGpxxQqUWBBVXbFmUCAtptA4p8isvUwnog8Lrk4eZyUlyNaend7U2/O80eJWirhKmwf+rBHC92T/DLMfAjUy3ADaGAiwdmJbGs0RMIwjl4WjaS2iCaAeM9aHch8LQ5qPLudceY7ToKqoJJN1ehRclc2eQXH2KVqZx1q0B4VyN9cd/frm4Tj9spHPyFvW8yhJbOM6dPJGRTXNbyZus6K1MojEPcHfUoquKKTnOPNaM2yYDDSGMf42vNbBBWD4UvN3SCUGzMYtd1qUBecSfqKQXZnYSQ16Q34ECgYEA5uROgTtX6UfhdRRpzCY9xRvVm4U5PuSciACpidSl6TzaOsSTcYl1bAOnq03X+5WazZOer65nk7r/DPm8gIt2MNxKnMp7i7yMy3g6DHzAB8p/RvqCcBt28JkdkR82GFVw4z9lEaHRekRhEYLEp6hEIjFlNW4qjOmuhqALDpiB5IkCgYEAmg+/IwA1CE2xX+4PjglR7RSz8gkYA0zfkQHYWsBvhianY96gAo7EUsMrJsHKp8Xm5bk/UlSvQB7ZBVQM6mxZ0VxHkdnu1T+nfM1YszUJ9fOuQLGqBGvq5IlLoTCEdIbWvGVJSiHPcXJK8grXfBA3geiErmV/PFQkn/PeW5dLvw8CgYArtfciwhBPipslJhstWeQBx9Y1nhALVw5HNRDjlg1ezXVnAMYFDYfcTgjVBmSoLVdkKdk12yeisRkN5M2J7F0QvPOnITIobyaccoNSz98u2mgkENLW1Gw8+k+9pJICI1K+wsaoguD8a6uQvBfswY88hetqJRQLH0Y6GFxfhAG7OQKBgHzObOWJ8Wx8pEk2rls+qaQKtYxKWa2yxRrQB9ndM3vWLJz3aWEpD1qMyZRoQiiQZnGMFQQ5no+vbdinMp9mj4zP2RsLFjSjkKfGIylqulsJwgye4UEbu/rQTviSOXu9EJEm7Kyln0wkbxqONetHp/AR0gsGJoFVGSaLhYNlmfN5AoGANaWLho4KGJ9Iz3sj0h2rgjxXDLrQQgYM+zAOl4sUzNu3hdRlIsg5iY0WiNuv9YrawjMlPm47rnoiJIB9fsVfF/f9bPWyyp2BDa0Q4JucYcDsU+pcpyTgM9+DQpHW62bJKcfuB0/mQdK2Bvz5lOtJ9yYPZKcD0LdyMetIdKkQJT4=";
    private static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApTuYu0Yt+dlgu/7mctu/nqayAqmDXxVNcLBJ56F9I0L1Rz0IYr0Spd/iBVrujMNuetnS9dEq9rxWUWX7QO8j9TzCxQv6123hOtzB/OnZEnIq865rjTGEPayqj2lJaLrj90axEih5s7l5q1geKsZyVX5P4kVZ9BcpAC1P7E1/zMdj1MJmIbz2kAWeelQMXB/T4PQ25FU+qaqBE4/CjlZ+LCJ8RpzKik71JRwUC+xezPx4RQ0DbwNolpLOZjeX4kui2+qa7pwFkHtuNl+F3dLFbZ5fi3F5iqHN4NmQYqIqWE1Ka/ur7PZ/IqUUcPZmY+nUsKMGJKRT9fonyE4Vo9rUFwIDAQAB";
    private static String APP_ID = "2016101600696285";
    private static String FORMAT = "json";
    private static String CHARSET = "utf-8";
    private static String SIGN_TYPE = "RSA2";
    private static String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

    public static void main(String[] args) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, PRIVATE_KEY, FORMAT, CHARSET, PUBLIC_KEY, SIGN_TYPE);
        AlipayCreditCreditriskDataPutRequest request = new AlipayCreditCreditriskDataPutRequest();
        request.setBizContent("{" +
                "\"entityname\":\"asdfgh\"," +
                "\"entitycode\":\"2088101026578910\"," +
                "\"entitytype\":\"ALIPAY\"," +
                "\"dataprovider\":\"CAINIAO\"," +
                "\"dataorgid\":\"226630000000019649\"," +
                "\"category\":\"CHANGE_INFO\"," +
                "\"objectcontent\":\"{\\\"currency\\\":\\\"156\\\",\\\"payAccount\\\":\\\"2088101026578910\\\",\\\"payAmt\\\":10,\\\"payDate\\\":\\\"2016-08-02 10:51:57\\\",\\\"policyCode\\\":\\\"BC\\\",\\\"serialNo\\\":\\\"1\\\"}\"" +
                "  }");
        AlipayCreditCreditriskDataPutResponse response = alipayClient.execute(request);
        if (response.isSuccess()) {
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
    }


}



