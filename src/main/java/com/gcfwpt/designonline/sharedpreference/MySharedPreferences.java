package com.gcfwpt.designonline.sharedpreference;

import com.baoyz.treasure.Clear;
import com.baoyz.treasure.Default;
import com.baoyz.treasure.Preferences;
import com.baoyz.treasure.Remove;
/**
 * 全局偏好设置类
 * 安卓中有一个爱好设置如，是否自动登录，是否定时接收消息等功能的偏爱设置便可以在此类中加入字段
 * 此类是全局的用的是github开源框架
 */
// 全局都以commit方式提交
@Preferences(edit = Preferences.Edit.COMMIT)
public interface MySharedPreferences {
    // 是否第一次启动 字段：Is_first_start
    @Default("true")
    boolean getIs_first_start();
    void setIs_first_start(boolean bool);
    @Remove // 移除指定字段数据
    void removeIs_first_start();

    // 是否记住密码 字段：Is_auto_password
    @Default("false")
    boolean getIs_auto_password();
    void setIs_auto_password(boolean bool);
    @Remove // 移除指定字段数据
    void removeIs_auto_password();


    // 是否自动登录 字段：Is_auto_login
    @Default("false")
    boolean getIs_auto_login();
    void setIs_auto_login(boolean bool);
    @Remove // 移除指定字段数据
    void removeIs_auto_login();

    // 是否提示升级 字段：Is_tips
    @Default("true")
    boolean getIs_tips();
    void setIs_tips(boolean bool);
    @Remove // 移除指定字段数据
    void removeIs_tips();


    // 用户名 字段：NickName
    @Default("")
    String getNickName();
    void setNickName(String s);
    @Remove // 移除指定字段数据
    void removeNickName();

    // 支付宝账户 字段：Alipay_account
    @Default("")
    String getAlipay_account();
    void setAlipay_account(String s);
    @Remove // 移除指定字段数据
    void removeAlipay_account();


    // 是否全屏 字段：FullScreen
    @Default("false")
    boolean getFullScreen();
    void setFullScreen(boolean bool);
    @Remove // 移除指定字段数据
    void removeFullScreen();


    // 是否开启图片模式 字段：Enable_images
    @Default("true")
    boolean getEnable_images();
    void setEnable_images(boolean bool);
    @Remove // 移除指定字段数据
    void removeEnable_images();

    // 获取服务器端配置数据 字段：Json_data
    @Default("0L")
    long getJson_data();
    void setJson_data(long s);
    @Remove // 移除指定字段数据
    void removeJson_data();


    // 登陆Token值 字段：Token
    @Default("")
    String getToken();
    void setToken(String s);
    @Remove // 移除指定字段数据
    void removeToken();

    // 验证后的用户名 字段：VerifyUserName
    @Default("")
    String getVerifyUserName();
    void setVerifyUserName(String s);
    @Remove // 移除指定字段数据
    void removeVerifyUserName();

    // 验证身份信息状态 字段：VerifyUserNameStatus
    @Default("0")
    int getVerifyUserNameStatus();
    void setVerifyUserNameStatus(int s);
    @Remove // 移除指定字段数据
    void removeVerifyUserNameStatus();

    // 验证后的身份证号码 字段：IdCards
    @Default("")
    String getIdCards();
    void setIdCards(String s);
    @Remove // 移除指定字段数据
    void removeIdCards();

    // 会员ID 字段：MemberId
    @Default("")
    String getMemberId();
    void setMemberId(String s);
    @Remove // 移除指定字段数据
    void removeMemberId();


    // 会员手机号码 字段：MemberTel
    @Default("")
    String getMemberTel();
    void setMemberTel(String s);
    @Remove // 移除指定字段数据
    void removeMemberTel();

    // 会员手机号码 字段：MemberAvatar
    @Default("")
    String getMemberAvatar();
    void setMemberAvatar(String s);
    @Remove // 移除指定字段数据
    void removeMemberAvatar();

    // 会员定位 经度 字段：Longitude
    @Default("0.0")
    String getLongitude();
    void setLongitude(String s);
    @Remove // 移除指定字段数据
    void removeLongitude();

    // 会员定位 维度 字段：Latitud
    @Default("0.0")
    String getLatitud();
    void setLatitud(String s);
    @Remove // 移除指定字段数据
    void removeLatitud();


    // 配送范围 经度 字段：ShipLongitude
    @Default("0.0")
    String getShipLongitude();
    void setShipLongitude(String s);
    @Remove // 移除指定字段数据
    void removeShipLongitude();

    // 配送范围 纬度 字段：ShipLatitud
    @Default("0.0")
    String getShipLatitud();
    void setShipLatitud(String s);
    @Remove // 移除指定字段数据
    void removeShipLatitud();

    // 是否登录 字段：IsLogin
    @Default("0")
    int getIsLogin();
    void setIsLogin(int s);
    @Remove // 移除指定字段数据
    void removeIsLogin();

    // 是否体验APP模式 字段：TiyanMode
    @Default("true")
    boolean getTiyanMode();
    void setTiyanMode(boolean bool);
    @Remove // 移除指定字段数据
    void removeTiyanMode();

    // 是否接单 字段：IsAcceptOrders
    @Default("true")
    boolean getIsAcceptOrders();
    void setIsAcceptOrders(boolean bool);
    @Remove // 移除指定字段数据
    void removeIsAcceptOrders();

    // 改变接单范围半径 默认为1公里 字段：Radius
    @Default("1")
    int getRadius();
    void setRadius(int s);
    @Remove // 移除指定字段数据
    void removeRadius();

    // 定位省份 经度 字段：Province
    @Default("天津市")
    String getProvince();
    void setProvince(String s);
    @Remove // 移除指定字段数据
    void removeProvince();

    // 清空数据
    @Clear
    void clear();


}
