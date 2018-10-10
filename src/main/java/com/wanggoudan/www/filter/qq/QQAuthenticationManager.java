package com.wanggoudan.www.filter.qq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wanggoudan.www.entity.QQUser;
import com.wanggoudan.www.entity.UserEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.*;

import static com.wanggoudan.www.filter.qq.QQAuthenticationFilter.clientId;


public class QQAuthenticationManager implements AuthenticationManager {
    private static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();

    /**
     * 获取 QQ 登录信息的 API 地址
     */
    private final static String userInfoUri = "https://graph.qq.com/user/get_user_info";

    /**
     * 获取 QQ 用户信息的地址拼接
     */
    private final static String USER_INFO_API = "%s?access_token=%s&oauth_consumer_key=%s&openid=%s";

    static {
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        if (auth.getName() != null && auth.getCredentials() != null) {
            UserEntity user = getUserInfo(auth.getName(), (String) (auth.getCredentials()));
            return new UsernamePasswordAuthenticationToken(user,
                    null, user.getAuthorities());
        }
        throw new BadCredentialsException("Bad Credentials");
    }

    private UserEntity getUserInfo(String accessToken, String openId) {
        String url = String.format(USER_INFO_API, userInfoUri, accessToken, clientId, openId);
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BadCredentialsException("Bad Credentials!");
        }
        String resultText = document.text();
        JSONObject json = JSON.parseObject(resultText);

        String url2=String.format("http://www.wanggoudan.cn/user/getInfo?username=%s&fullname=%s&avatar=%s",openId,json.getString("nickname"),json.getString("figureurl_qq_2"));
        try {
            document = Jsoup.connect(url2).ignoreContentType(true).get();
        } catch (IOException e) {
//            throw new BadCredentialsException("Bad Credentials!");
            e.printStackTrace();
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        String result=document.text();
        JSONObject json2 = JSON.parseObject(result);
        UserEntity user = new UserEntity();
        user.setUsername(json2.getString("username"));
        user.setFullname(json2.getString("fullname"));
        user.setId(json2.getInteger("id"));
        user.setAvatar(json2.getString("avatar"));
        JSONArray authorities=json2.getJSONArray("authorities");
        for (int i=0;i<authorities.size();i++) {
            Map m = (Map) authorities.get(i);
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority((String) m.get("authority"));
            grantedAuthorities.add(grantedAuthority);
        }
        user.setAuthorities(grantedAuthorities);
        return user;
    }

}
