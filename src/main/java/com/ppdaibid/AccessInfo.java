package com.ppdaibid;

import java.util.UUID;

import com.ppdaibid.utils.PropertiesUtil;

public class AccessInfo {
	public static final String ppdaiAccount = PropertiesUtil.getProperty("ppdaiAccount", "iqleo");
	public static final String code = PropertiesUtil.getProperty("code", "f2487c55fd664c4bb56335664f0c1c06");
	/**
	 * 应用ID
	 */
	public static final String appId = PropertiesUtil.getProperty("appId", "8edf86b43f6d4ef7951b5e1c21d8e4d7");
	/**
	 * 客户端私钥
	 */
	public static final String clientPrivateKey = PropertiesUtil.getProperty("clientPrivateKey", "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKb2onVIdIJMv8rnsvP4m915JvkAwB6D+qfMuOWZORIaVlJ6W/UCGbagm/e3ggmtb7hLH48+NMn+PsuyNWgrlIPtzNgKWCT3wOrQc/G7kSmeXuR9ilZdF2LZHGTMZkRHoAx+pCR8/Kjka1xk07W0avOR04Tj/vLytzaykp+pwC6vAgMBAAECgYA6g4U8BUCPycYNnlYhGqfFXohCC/lIuIhH9n92N9TAe2p1TVyc6TeJwY/nqPBx486bj/hngy2M/QWU7ce50kwhvpXSauo7wT9iI9GtE1BnmTIv59imo30d26RgMENpsZyad9GZ/ndTR261RA63PcIJC5+RKjzXA0YdEJoP7V40QQJBAOajry4DzVF8sMqKtuaINxUNHwAQWHdJJd6loAw2Zgqa/4cO7Wbn+/7SZPAn9o3QADoooiRQcAgrMunWj4p3cK0CQQC5UoWtBNHb/+L1uqyku/aeyfT+aBYEFKpg5H24vvXPzvg7IGKwPJpdJifXx+MJE5uJ3K1RObAfdI9Ye3/x1FxLAkEA2AN0fk93TJvRZVH4LgBkPqY0Y7XrdYWgH2DWfrmwZrx3GULE3P3nyem6OifjxzyKCe6mtgZUd4ImRFb9x71IXQJAHOky0XsfAiM5VPx6UnjAUKVRAx9Zz+kpa1Q6CMhBsNmpABBdVQEfU8jQDmmwkYntieRTZYiKYV3dvwAFRcTLYQJBALoEB9UrqOegCl84Ih8BakM2LhkQtZASZj2lz9VPD9WPWuWkXZRfaVlkpl6zSu6OVIB12NqBw2b3Rr8OXT0TNbc=");
	/**
	 * 服务端公钥
	 */
	public static final String serverPublicKey = PropertiesUtil.getProperty("serverPublicKey", "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCa5OfHO0YUzPqJ406ODGQP9S1adYiCX/F9NORXZLg/wclXLKQwo9t+Lg4/0l4GxxAm9ZPFDQCSwWgONBHiIQBtGNJFM/GifpOriLSNM1ZSjeaujp3C+S2d9gTobxW/o1o23azEF2lOKiyTtJyTFa9MOHiX5c597d3/lTvRtRU48QIDAQAB");
	/**
	 * 注册手机号
	 */
	public static final String mobile = PropertiesUtil.getProperty("mobile", "18688395156");
	/**
	 * 设备指纹，对应设备的唯一标识
	 */
	public static String deviceFP = UUID.randomUUID().toString().replaceAll("-", "");
//	public static final String deviceFP = "e3c43d2d77724d4c93da9f91bc2ead79";
	/**
	 * 用户在第三方平台上的唯一标识
	 */
	public static String openID = null;
//	public static String openID = "706762e882f94c809fa588bb262e330f";
	/**
	 * 用户给第三方平台的授权访问令牌,有效期7天
	 */
	public static String accessToken = null;
//	public static final String accessToken = "6e83bdf3-11ba-4420-a6ff-87fbf3c87dc0";
	/**
	 * 用户给第三方授权使用刷新令牌,有效期90天
	 */
	public static String refreshToken = null;
//	public static String refreshToken = "a21b0472-41bd-4805-b3cc-ec4f792e60bf";
	/**
	 * 用户给第三方授权访问令牌超时时间，单位s
	 */
	public static int expiresIn = 0;
//	public static int expiresIn = 604799;
}
