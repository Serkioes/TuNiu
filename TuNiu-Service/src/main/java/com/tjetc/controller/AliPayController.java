package com.tjetc.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.tjetc.config.AliPayConfig;
import com.tjetc.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("alipay")
public class AliPayController {
    @Autowired
    private AliPayConfig aliPayConfig;
    @Autowired
    private OrderService orderService;

    @RequestMapping("pay")
    public void pay(@RequestParam("orderId") String orderId,@RequestParam("totalPrice") String price, HttpServletResponse response) throws IOException {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(aliPayConfig.getGatewayUrl(),aliPayConfig.getAppId(),aliPayConfig.getAppPrivateKey(),aliPayConfig.getFormat(),aliPayConfig.getCharset(),aliPayConfig.getAlipayPublicKey(),aliPayConfig.getSignType());
        // 设置请求参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        request.setReturnUrl(aliPayConfig.getReturnUrl());
        request.setNotifyUrl(aliPayConfig.getNotifyUrl());
        request.setBizContent("{\"out_trade_no\":\""+orderId+"\"," +
                "\"total_amount\":\"" + price + "\"," +
                "\"subject\":\"测试订单\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        // 执行请求,得到响应结果,跳转到支付宝支付页面
        String form="";
        try {
            form = alipayClient.pageExecute(request).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + aliPayConfig.getCharset());
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
    @RequestMapping("notify")
    public void payNotify(HttpServletRequest request){
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")){
            System.out.println("支付成功");
            System.out.println(request.getParameter("subject"));//交易名称
            System.out.println(request.getParameter("trade_status"));//交易状态
            System.out.println(request.getParameter("trade_no"));//支付宝交易凭证号
            System.out.println(request.getParameter("out_trade_no"));//商户订单号
            System.out.println(request.getParameter("total_amount"));//交易金额
            System.out.println(request.getParameter("buyer_id"));//买家在支付宝唯一id
            System.out.println(request.getParameter("gmt_payment"));//买家付款时间
            System.out.println(request.getParameter("buyer_pay_amount"));//买家付款金额

            String tradeNo = request.getParameter("out_trade_no");
//            String gmtPayment = request.getParameter("gmt_payment");
//            String alipayTradeNo = request.getParameter("trade_no");
            //更新订单状态为已支付
//            orders.setTime(gmtPayment);
//            orders.setPayNo(alipayTradeNo);

            orderService.updateById(tradeNo);
        }

    }
}
