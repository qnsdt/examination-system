package com.ruoyi.framework.web.service;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;

/**
 * 登录校验方法
 * 
 * @author ruoyi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;
    
    @Autowired
	public RedisTemplate redisTemplate;
    
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;
    


    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
	/*public String login(String username, String password, String code, String uuid)
	{
	    boolean captchaOnOff = configService.selectCaptchaOnOff();
	    // 验证码开关
	    if (captchaOnOff)
	    {
	        validateCapcha(username, code, uuid);
	    }
	    // 用户验证
	    Authentication authentication = null;
	    try
	    {
	        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
	        authentication = authenticationManager
	                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
	    }
	    catch (Exception e)
	    {
	        if (e instanceof BadCredentialsException)
	        {
	            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
	            throw new UserPasswordNotMatchException();
	        }
	        else
	        {
	            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
	            throw new CustomException(e.getMessage());
	        }
	    }
	    AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
	    LoginUser loginUser = (LoginUser) authentication.getPrincipal();
	    recordLoginInfo(loginUser.getUser());
	    // 生成token
	    return tokenService.createToken(loginUser);
	}*/
    
    public String login(String username, String password, String code, String uuid)
    {
    	// 在此加入密码输入次数校验
    	
		/*try
		{
			System.out.println("username:" + username);
			System.out.println("password:" + password);
			System.out.println("code:" + code);
			System.out.println("uuid:" + uuid);
			System.out.println("TimeUnit.MINUTES:" + TimeUnit.MINUTES);
		}
		catch(Exception e) {
			throw new CustomException("密码输入错误超过五次");	
		}*/
    	Object obj = redisTemplate.opsForValue().get(Constants.LOGIN_ERROR_COUNT  + username);
    	// LoginErrorData loginErrorData = redisCache.getCacheObject(Constants.LOGIN_ERROR_COUNT  + username);
    	
    	
    	if (obj != null) {
        	if (Integer.parseInt(obj.toString()) >= 5) {
        		throw new CustomException("密码输入错误超过五次，系统锁定十分钟");	
        	}
    	} 
    	
		boolean captchaOnOff = configService.selectCaptchaOnOff();
		// 验证码开关
		if (captchaOnOff)
		{
		    validateCapcha(username, code, uuid);
		}
		// 用户验证
		Authentication authentication = null;
		try
		{
		    // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
		    authentication = authenticationManager
		            .authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch (Exception e)
		{
		    if (e instanceof BadCredentialsException)
		    {
		    	// System.out.println("这里报口令不匹配错误");
		    	int count = 1;
		    	if (obj != null) {
		    	    count = Integer.parseInt(obj.toString());
		    		count ++;
		    		// loginErrorData = new LoginErrorData(username, count, System.currentTimeMillis());
				} /*else {
					loginErrorData = new LoginErrorData(username, 1, System.currentTimeMillis());
					
					}*/
		    	// System.out.println("loginErrorData:" + loginErrorData.toString());
		    	// redisCache.setCacheObject(Constants.LOGIN_ERROR_COUNT  + username, loginErrorData, 10, TimeUnit.MINUTES);
		    	redisTemplate.opsForValue().set(Constants.LOGIN_ERROR_COUNT  + username, count, 10, TimeUnit.MINUTES);
		        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
		        throw new UserPasswordNotMatchException();
		    }
		    else
		    {
		        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
		        throw new CustomException(e.getMessage());
		    }
		}
		AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
		LoginUser loginUser = (LoginUser) authentication.getPrincipal();
		recordLoginInfo(loginUser.getUser());
		// 生成token
		return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     * 
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public void validateCapcha(String username, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null)
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha))
        {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     */
    public void recordLoginInfo(SysUser user)
    {
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(user);
    }
}
