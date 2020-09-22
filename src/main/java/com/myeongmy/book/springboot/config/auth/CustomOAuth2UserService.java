package com.myeongmy.book.springboot.config.auth;

import com.myeongmy.book.springboot.config.auth.dto.OAuthAttributes;
import com.myeongmy.book.springboot.config.auth.dto.SessionUser;
import com.myeongmy.book.springboot.domain.user.User;
import com.myeongmy.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes){
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}

/*
registrationId
: 현재 로그인 진행 중인 서비스를 구분하는 코드이다. 지금은 구글만 사용하지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지 구글 로그인인지 구분하기 위해 사용

userNameAttributeName
: oauth2 로그인 진행 시 키가 되는 필드값을 의미. 구글의 경우 기본 코드 "sub"를 지원하지만, 네이버 카카오 등은 지원하지 않는다. 이후 네이버와 구글 로그인을 동시 지원할 때 사용됨.

OAuthAttributes
: OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스.

SessionUser
: 세선에 사용자 정보를 담을 DTO 클래스

saveOrUpdate 메소드
: save와 update 기능을 같이 구현
기존에 사용자 정보가 있는 경우 map을 통해 이름과 사진 변경하여 저장, 처음 들어온 사용자의 경우 orElse메소드를 통해 그냥 저장
 */
