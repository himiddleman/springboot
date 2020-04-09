package com.allinpay.controller.ali;

import com.allinpay.repository.domain.YouzanOrder;
import com.allinpay.service.YouzanOrderService;
import com.youzan.cloud.open.sdk.common.exception.SDKException;
import com.youzan.cloud.open.sdk.core.client.auth.Token;
import com.youzan.cloud.open.sdk.core.client.core.DefaultYZClient;
import com.youzan.cloud.open.sdk.core.client.core.YouZanClient;
import com.youzan.cloud.open.sdk.gen.v1_0_0.api.YouzanLogisticsOrderBatchQuery;
import com.youzan.cloud.open.sdk.gen.v1_0_0.model.YouzanLogisticsOrderBatchQueryParams;
import com.youzan.cloud.open.sdk.gen.v1_0_0.model.YouzanLogisticsOrderBatchQueryResult;
import com.youzan.cloud.open.sdk.gen.v4_0_1.api.YouzanTradesSoldGet;
import com.youzan.cloud.open.sdk.gen.v4_0_1.model.YouzanTradesSoldGetParams;
import com.youzan.cloud.open.sdk.gen.v4_0_1.model.YouzanTradesSoldGetResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author: tanguang
 * data: 2020-04-08
 **/
@Controller
@RequestMapping("/youzan")
@Slf4j
public class YouzanOrderController {
    @Autowired
    private YouzanOrderService orderService;

    public static void main(String[] args) throws SDKException, ParseException {
        //获取物流信息
        YouZanClient yzClient = new DefaultYZClient();
        Token token = new Token("935d5dcdf77ffd3c7e56f95aaaf2df4");
        YouzanLogisticsOrderBatchQuery youzanLogisticsOrderBatchQuery = new YouzanLogisticsOrderBatchQuery();
        //创建参数对象,并设置参数
        YouzanLogisticsOrderBatchQueryParams youzanLogisticsOrderBatchQueryParams = new YouzanLogisticsOrderBatchQueryParams();
        List<YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams> list = new ArrayList<>();
        YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams params = new YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams();
        params.setOrderId("E20190915000031054300042");
        params.setSourceId(1001);
        list.add(params);
        youzanLogisticsOrderBatchQueryParams.setOrderDetailParams(list);
        youzanLogisticsOrderBatchQuery.setAPIParams(youzanLogisticsOrderBatchQueryParams);
        YouzanLogisticsOrderBatchQueryResult result = yzClient.invoke(youzanLogisticsOrderBatchQuery, token, YouzanLogisticsOrderBatchQueryResult.class);
        System.out.println(result.getData().get(0).getExpressDetailModel().getExpressName());
        System.out.println(result.getData().get(0).getExpressDetailModel().getExpressNo());

        //        //获取订单信息 token 7天有效
//        YouZanClient yzClient = new DefaultYZClient();
//
//        Token token = new Token("935d5dcdf77ffd3c7e56f95aaaf2df4");
//
//        YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
//        //创建参数对象,并设置参数
//        YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
//        youzanTradesSoldGetParams.setStatus("TRADE_SUCCESS");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        youzanTradesSoldGetParams.setStartCreated(dateFormat.parse("2019-09-01 00:00:00"));
//        youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
//
//        YouzanTradesSoldGetResult result = yzClient.invoke(youzanTradesSoldGet, token, YouzanTradesSoldGetResult.class);
//        System.out.println(result);

        //授权码，有效期2分钟后失效或换取token成功后立即失效
//        DefaultYZClient yzClient = new DefaultYZClient();
//        TokenParameter tokenParameter = TokenParameter.code()
//                .clientId("d6049040ad37b0c8e1")
//                .clientSecret("a20f7f07ad4ccaac881aef4ea6b1bad3")
//                .code("")
//                .build();
//        OAuthToken codeToken = yzClient.getOAuthToken(tokenParameter);
//        System.out.println(codeToken);

        //令牌刷新 28 天
//        DefaultYZClient yzClient = new DefaultYZClient();
//        TokenParameter tokenParameter = TokenParameter.refresh()
//                .clientId("请填写您的client_id")
//                .clientSecret("请填写您的client_secret")
//                .refreshToken("8c40e75eaac453da9e1f95bc8402152")
//                .build();
//        OAuthToken refreshToken = yzClient.getOAuthToken(tokenParameter);
    }

    @GetMapping("/order/list")
    @ResponseBody
    public void getOrderList(String startTime, String endTime) {
        try {
            startTime = "2019-09-01 00:00:00";
            endTime = "2020-04-01 00:00:00";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = dateFormat.parse(startTime);
            Calendar calendar = Calendar.getInstance();
            long count = 0;
            log.info("开始同步平台订单数据，{}-{}", startTime, endTime);
            while (true) {
                calendar.setTime(startDate);
                //按月分批
//                calendar.add(Calendar.MONTH, 1);
                //按半月分批
                calendar.add(Calendar.DAY_OF_MONTH, 15);
                Date endDate = calendar.getTime();
                //超过当前时间
                if (endDate.getTime() > dateFormat.parse(endTime).getTime()) {
                    endDate = dateFormat.parse(endTime);
                    if (startDate.getTime() == endDate.getTime()) {
                        break;
                    }
                    count += dealYouzanOrders(startDate, endDate);
                    break;
                }
                count += dealYouzanOrders(startDate, endDate);
                startDate = endDate;
            }
            log.info("订单数据同步完成，订单数：{}", count);
        } catch (Exception e) {
            log.error("有赞平台订单数据同步失败！{}", e.getMessage());
        }
    }

    private List<YouzanOrder> YouzanTradesSoldGetResult2Bean(YouzanTradesSoldGetResult result) throws SDKException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<YouzanOrder> orderList = new ArrayList<>();
        for (YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfolist info : result.getData().getFullOrderInfoList()) {
            YouzanTradesSoldGetResult.YouzanTradesSoldGetResultFullorderinfo fullOrderInfo = info.getFullOrderInfo();
            YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrders order = fullOrderInfo.getOrders().get(0);
            YouzanTradesSoldGetResult.YouzanTradesSoldGetResultOrderinfo orderInfo = fullOrderInfo.getOrderInfo();
            YouzanOrder youzanOrder = new YouzanOrder();
            youzanOrder.setOrderNo(orderInfo.getTid());
            youzanOrder.setCurrency("CNY");
            youzanOrder.setPrice(order.getPrice());
            youzanOrder.setBuyerName(orderInfo.getOrderExtra().getIdCardName());
            youzanOrder.setBuyerIdNo(orderInfo.getOrderExtra().getIdCardNumber());
            youzanOrder.setGoodsType(order.getItemType().toString());
            youzanOrder.setGoodsName(order.getTitle());
            youzanOrder.setGoodsCount(order.getNum().toString());
            youzanOrder.setPayTime(dateFormat.format(orderInfo.getPayTime()));
            youzanOrder.setExpressType(orderInfo.getExpressType().toString());
            youzanOrder.setExpressNo("");
            youzanOrder.setFreight(order.getFreight());
            youzanOrder.setTax(order.getTaxTotal());
            youzanOrder.setOther_fee("");
            orderList.add(youzanOrder);
        }
        //补充物流信息
        return findDeliveryInfo(orderList);
    }

    public List<YouzanOrder> findDeliveryInfo(List<YouzanOrder> orderList) throws SDKException {
        List<String> orderNoList = orderList.stream().map(order -> order.getOrderNo()).collect(Collectors.toList());
        //获取物流信息
        YouZanClient yzClient = new DefaultYZClient();
        Token token = new Token("935d5dcdf77ffd3c7e56f95aaaf2df4");
        YouzanLogisticsOrderBatchQuery youzanLogisticsOrderBatchQuery = new YouzanLogisticsOrderBatchQuery();
        //创建参数对象,并设置参数
        YouzanLogisticsOrderBatchQueryParams youzanLogisticsOrderBatchQueryParams = new YouzanLogisticsOrderBatchQueryParams();
        List<YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams> list = new ArrayList<>();
        for (String orderNo : orderNoList) {
            YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams params = new YouzanLogisticsOrderBatchQueryParams.YouzanLogisticsOrderBatchQueryParamsOrderdetailparams();
            params.setOrderId(orderNo);
            params.setSourceId(1002);
            list.add(params);
        }
        youzanLogisticsOrderBatchQueryParams.setOrderDetailParams(list);
        youzanLogisticsOrderBatchQuery.setAPIParams(youzanLogisticsOrderBatchQueryParams);
        YouzanLogisticsOrderBatchQueryResult result = yzClient.invoke(youzanLogisticsOrderBatchQuery, token, YouzanLogisticsOrderBatchQueryResult.class);

        for (YouzanLogisticsOrderBatchQueryResult.YouzanLogisticsOrderBatchQueryResultData data : result.getData()) {
            for (YouzanOrder order : orderList) {
                if (order.getOrderNo().equalsIgnoreCase(data.getOrderId())) {
                    order.setExpressType(data.getExpressDetailModel().getExpressName());
                    order.setExpressNo(data.getExpressDetailModel().getExpressNo());
                    break;
                }
            }
        }
        return orderList;
    }

    private long dealYouzanOrders(Date startDate, Date endDate) {
        try {
            //当前循环次数
            long cycle = 1;
            //请求有赞总次数
            long sum;
            //订单总数
            long count;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            do {
                //获取订单信息 token 7天有效
                YouZanClient yzClient = new DefaultYZClient();

                Token token = new Token("935d5dcdf77ffd3c7e56f95aaaf2df4");
                YouzanTradesSoldGet youzanTradesSoldGet = new YouzanTradesSoldGet();
                //创建参数对象,并设置参数
                YouzanTradesSoldGetParams youzanTradesSoldGetParams = new YouzanTradesSoldGetParams();
                youzanTradesSoldGetParams.setPageNo(((Long) cycle).intValue());
                youzanTradesSoldGetParams.setPageSize(100);
                youzanTradesSoldGetParams.setStatus("TRADE_SUCCESS");
                youzanTradesSoldGetParams.setStartCreated(startDate);
                youzanTradesSoldGetParams.setEndCreated(endDate);
                youzanTradesSoldGet.setAPIParams(youzanTradesSoldGetParams);
                //请求
                YouzanTradesSoldGetResult result = yzClient.invoke(youzanTradesSoldGet, token, YouzanTradesSoldGetResult.class);
                List<YouzanOrder> orderList = YouzanTradesSoldGetResult2Bean(result);
                orderService.saveBatch(orderList);
                if (result.getData().getTotalResults() % 100 != 0) {
                    sum = result.getData().getTotalResults() / 100 + 1;
                } else {
                    sum = result.getData().getTotalResults() / 100;
                }
                cycle++;
                count = result.getData().getTotalResults();
            } while (cycle <= sum);
            log.info("{}-{}订单数据分批同步已完成，订单数为：{}", dateFormat.format(startDate),
                    dateFormat.format(endDate), count);
            return count;
        } catch (Exception e) {
            log.error("订单处理失败！{}", e);
            return 0;
        }
    }


}
