package com.zking.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
public class MyRealm extends AuthorizingRealm
{
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
  {
    System.out.println("Ȩ����֤��" + principals.getPrimaryPrincipal());
    return null;
  }

  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
    throws AuthenticationException
  {
    System.out.println("hello��֤����");

    String userName = (String)token.getPrincipal();
    System.out.println("�� Token�л�ȡ�� username" + userName);

    SimpleAuthenticationInfo infor = new SimpleAuthenticationInfo("java123", "m123", "12313134");
    return infor;
  }
}