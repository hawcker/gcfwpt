package com.gcfwpt.designonline.config;

/**
 * 全局常量配置类，配置文件
 */
public class Constants {
	//获取登陆Token
	public static final String LOGINACTIVITYTOKEN = "http://123.57.55.147/crowdinterface/index.php?act=captcha&op=login";
	//登陆url
	public static final String LOGINACTIVITYLOGINURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=login";
	public static final String AUTHENACTIVITYAUTHENURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=security";
	public static final String FRAGMENTRUNSHORDERSTAG = "我要抢单";
	public static final String FRAGMENTHANDLEORDERSTAG = "处理订单";
	public static final String FRAGMENTMESSAGETAG = "消息中心";
	public static final String FRAGMENTMINETAG = "个人中心";

	public static final String APPNAME = "jisufeidan";
	// 我要抢单api地址
	public static final String RUSHORDERURL = "http://123.57.55.147/crowdinterface/index.php?act=order&op=list";
	// 处理订单api地址
	public static final String HANDLEORDERURL = "http://123.57.55.147/crowdinterface/index.php?act=order&op=list";
	// 订单详情
	public static final String HURRYRUSHORDERURL = "http://123.57.55.147/crowdinterface/index.php?act=order&op=view";
	// 快速抢单中快速抢单按钮
	public static final String HURRYRUSHORDERRUSHURL = "http://123.57.55.147/crowdinterface/index.php?act=order&op=accept";
	// 快速抢单中处理订单按钮
	public static final String HURRYRUSHORDERHANDLEURL = "http://123.57.55.147/crowdinterface/index.php?act=order&op=confirm";
	// 个人信息
	public static final String MEMBERINFORURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=index";
	// 历史订单
	public static final String HISTORYORDERSURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=history_order_list";
	// 所有行业
	public static final String ACCEPTORDERSCATEGORYURL = "http://123.57.55.147/crowdinterface/index.php?act=category&op=index";
	// 我的行业
	public static final String ACCEPTORDERSCATEGORYMYURL = "http://123.57.55.147/crowdinterface/index.php?act=category&op=my";
	// 配置行业
	public static final String ACCEPTORDERSCATEGORYCONFIGURL = "http://123.57.55.147/crowdinterface/index.php?act=category&op=config";
	// 消息中心
	public static final String MESSAGEURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=message";
	// 配送范围
	public static final String SHIPPINGSETURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=area_set";
	// 更新头像
	public static final String UPDATEAVATARURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=update";
	// 定位成功的广播
	public static final String LOCATIONCOMPLETED = "locationcompleted";
	// 版本更新
	public static final String CHECK_VERSION_URL = "http://123.57.55.147/crowdinterface/index.php?act=base&op=config";
	// 登陆出现问题
	public static final String LOGINPROBLEMURL = "http://123.57.55.147/crowdinterface/index.php?act=help&op=login";
	// 提现
	public static final String TIXIANURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=cash";
	// 订单收入列表
	public static final String INCOMELISTURL = "http://123.57.55.147/crowdinterface/index.php?act=member&op=income_list";
	// 上传身份证件
	public static final String UPLOADFILEURL = "http://123.57.55.147/crowdinterface/index.php?act=base&op=upload";
	// 取得钱包数据
	//public static final String WALLETINFOURL = "http://123.57.55.147/";
}
