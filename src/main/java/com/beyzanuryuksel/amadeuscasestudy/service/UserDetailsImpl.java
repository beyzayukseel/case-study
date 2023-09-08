package com.beyzanuryuksel.amadeuscasestudy.service;

import com.beyzanuryuksel.amadeuscasestudy.entity.Role;
import com.beyzanuryuksel.amadeuscasestudy.entity.RoleType;
import com.beyzanuryuksel.amadeuscasestudy.entity.User;
import com.beyzanuryuksel.amadeuscasestudy.model.LoginRequest;
import com.beyzanuryuksel.amadeuscasestudy.model.UserDto;
import com.beyzanuryuksel.amadeuscasestudy.model.UserResponse;
import com.beyzanuryuksel.amadeuscasestudy.repository.RoleRepository;
import com.beyzanuryuksel.amadeuscasestudy.security.JwtUtils;
import com.beyzanuryuksel.amadeuscasestudy.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsImpl {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    public UserResponse login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        log.info("Username -> {} date: {} logging", loginRequest.getUsername(), new Date());

        return (new UserResponse(jwt,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    public User register(UserDto createUserDto) {

        User user = new User();
        user.setIdentifyNumber(createUserDto.getIdentifyNumber());
        user.setPassword(encoder.encode(createUserDto.getPassword()));
        System.out.println("aa0" + user.getPassword());
        user.setEmail(createUserDto.getEmail());
        user.setUsername(createUserDto.getName());
        user.setIsBlocked(Boolean.FALSE);

        Set<String> strRoles = createUserDto.getRoles();
        Set<Role> roles = new HashSet<>();


        if (strRoles == null) {
            Role userRole = roleService.findByName(RoleType.ROLE_CUSTOMER.toString());
            roles.add(userRole);

        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleService.findByName(RoleType.ROLE_ADMIN.toString());
                        roles.add(adminRole);
                        break;

                    default:
                        Role userRole = roleService.findByName(RoleType.ROLE_CUSTOMER.toString());
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userService.saveUser(user);

        log.info("User ID -> {} date: {} registered", user.getId(), new Date());

        return user;
    }

    public void deleteUser(Long userId) {
        userService.deleteUser(userId);
    }
}
