package server.crm.services.custom_user_details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import server.crm.dao.CrmDao;
import server.crm.entities.Role;
import server.crm.entities.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author: trungntm
 * @create: 20/10/2018
 * @content: add class CustomUserDetailsServiceImpl implement from UserDetailsService security
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: Inject UserDao
     */
    @Autowired
    private CrmDao userDao;

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: Overwrite method loadUserByUsername of UserDetailsService
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> userOptional=userDao.findUserByUsername(s);
        if(!userOptional.isPresent())
            throw new UsernameNotFoundException("User name not found in function loadUserByUsername. Please check again! ");
        User user=userOptional.get();
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),getAuthorities(user.getRoles()));
    }
    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: add method getAuthorities to get list roles and list privileges of user
     */
    private Collection<? extends GrantedAuthority> getAuthorities(
            Set<Role> roles) {
        Set<GrantedAuthority> authorities
                = new HashSet<>();
        for (Role role: roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            role.getPrivileges().stream()
                    .map(p -> new SimpleGrantedAuthority(p.getPrivilegeName()))
                    .forEach(authorities::add);
        }
        return authorities;
    }
}
